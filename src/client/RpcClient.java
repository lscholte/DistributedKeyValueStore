package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status.Code;
import io.grpc.StatusRuntimeException;
import protobuf.generated.KeyValueServiceGrpc;
import protobuf.generated.KeyValueServiceGrpc.KeyValueServiceBlockingStub;
import protobuf.generated.KeyValueServiceMessages.DeleteRequest;
import protobuf.generated.KeyValueServiceMessages.DeleteResponse;
import protobuf.generated.KeyValueServiceMessages.ErrorStatus;
import protobuf.generated.KeyValueServiceMessages.GetRequest;
import protobuf.generated.KeyValueServiceMessages.GetResponse;
import protobuf.generated.KeyValueServiceMessages.PutRequest;
import protobuf.generated.KeyValueServiceMessages.PutResponse;
import utilities.Logger;
import utilities.ProtobufUtils;

/**
 * An RPC client that sends PUT, GET, and DELETE requests
 * for key-value pairs to an RPC server.
 * @author lscholte
 *
 */
public class RpcClient implements Client {
  
  private static final int RESPONSE_TIMEOUT_S = 10;
  
  private KeyValueServiceBlockingStub serviceStub;
  
  /**
   * Constructs an RpcClient that will send requests to a server
   * with the specified IP and port number.
   * @param ip the IP of the server that will receive requests
   * @param port the port number on which the server is listening for requests
   */
  public RpcClient(String ip, int port) throws UnknownHostException, IOException {
    ManagedChannel channel = ManagedChannelBuilder
        .forAddress(ip, port)
        .usePlaintext()
        .build();
    serviceStub = KeyValueServiceGrpc.newBlockingStub(channel);
    
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      Logger.logInfo("Shutting down client");
      try {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }));
    
  }
  
  @Override
  public void start() throws Throwable {
    Logger.logInfo("Starting client");
    
    sendInitialRequests();

    CommandReader commandReader = new CommandReader(this);    
    while (true) {
      commandReader.readInput();
    }
  }
  
  /**
   * {@inheritDoc}
   * <br><br>
   * The client will block for up to
   * {@value #RESPONSE_TIMEOUT_S} seconds waiting for a response
   * from the server.
   */
  @Override
  public void sendPut(String key, String value) {
    final String requestName = "PUT";

    //Build request    
    PutRequest.Builder requestBuilder = PutRequest.newBuilder();
    requestBuilder.setKey(key);
    requestBuilder.setValue(value);
    
    
    //Send request
    PutRequest request = requestBuilder.build();
    Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));
    try {
      PutResponse response = serviceStub
        .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
        .put(request);
      
      Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
      
      if (response.getErrorStatus() != ErrorStatus.NONE) {
        handleErrorStatus(requestName, response.getErrorStatus());
      }
      else {
        Logger.logInfo(String.format("The value %s has been put under key %s", value, key));
      }
    }
    catch (StatusRuntimeException e) {
      handleGrpcError(requestName, e.getStatus().getCode());
    }
  }
  
  /**
   * {@inheritDoc}
   * <br><br>
   * The client will block for up to
   * {@value #RESPONSE_TIMEOUT_S} seconds waiting for a response
   * from the server.
   */
  @Override
  public void sendDelete(String key) {
    final String requestName = "DELETE";

    //Build request    
    DeleteRequest.Builder requestBuilder = DeleteRequest.newBuilder();
    requestBuilder.setKey(key);
    
    
    //Send request
    DeleteRequest request = requestBuilder.build();
    Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));
    try {
      DeleteResponse response = serviceStub
        .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
        .delete(request);
      
      Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
      
      if (response.getErrorStatus() != ErrorStatus.NONE) {
        handleErrorStatus(requestName, response.getErrorStatus());
      }
      else if (response.getDeleted()) {
        Logger.logInfo(String.format("The key %s has been deleted", key));
      }
      else {
        Logger.logInfo(String.format("The key %s did not exist", key));
      }
    }
    catch (StatusRuntimeException e) {
      handleGrpcError(requestName, e.getStatus().getCode());
    }
  }
  
  /**
   * {@inheritDoc}
   * <br><br>
   * The client will block for up to
   * {@value #RESPONSE_TIMEOUT_S} seconds waiting for a response
   * from the server.
   */
  @Override
  public void sendGet(String key) {
    final String requestName = "GET";
    
    //Build request    
    GetRequest.Builder requestBuilder = GetRequest.newBuilder();
    requestBuilder.setKey(key);
    
    
    //Send request
    GetRequest request = requestBuilder.build();
    Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(request)));
    try {
      GetResponse response = serviceStub
        .withDeadlineAfter(RESPONSE_TIMEOUT_S, TimeUnit.SECONDS)
        .get(request);
      
      Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(response)));
      
      if (response.getErrorStatus() != ErrorStatus.NONE) {
        handleErrorStatus(requestName, response.getErrorStatus());
      }
      else if (response.hasValue()) {
        Logger.logInfo("Value is " + response.getValue());      
      }
      else {
        Logger.logInfo(String.format("There is no value for key %s", key));
      }
    }
    catch (StatusRuntimeException e) {
      handleGrpcError(requestName, e.getStatus().getCode());
    }
  }
  
  /**
   * Sends a sequence of 5 PUT requests followed by
   * 5 GET requests followed by 5 DELETE requests.
   */
  private void sendInitialRequests() {    
    int messageCount = 5;
    
    Logger.logInfo("Sending 5 initial PUT requests");
    for (int i = 0; i < messageCount; ++i) {
      sendPut("Key" + i, "Value" + i);
    }
    
    Logger.logInfo("Sending 5 initial GET requests");
    for (int i = 0; i < messageCount; ++i) {
      sendGet("Key" + i);
    }
    
    Logger.logInfo("Sending 5 initial DELETE requests");
    for (int i = 0; i < messageCount; ++i) {
      sendDelete("Key" + i);
    }
  }
  
  private void handleGrpcError(String requestType, Code code) {
    switch (code) {
      case UNAVAILABLE:
        Logger.logError(String.format("%s failed because server is unavailable", requestType));
        break;
      case DEADLINE_EXCEEDED:
        Logger.logError(String.format("%s timed out waiting for response", requestType));
        break;
      default:
        Logger.logError(String.format("%s failed with error status code %s", requestType, code.toString()));
        break;
    }
  }
  
  private void handleErrorStatus(String requestType, ErrorStatus status) {
    switch (status) {
      case NONE:
        break;
      case INVALID_REQUEST_FORMAT:
        Logger.logError(String.format("%s failed due to invalid request", requestType));
        break;
      default:
        Logger.logError(String.format("%s failed with error status code %s", requestType, status));
    }
  }

}
