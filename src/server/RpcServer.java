package server;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.protobuf.Message;

import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.ServerBuilder;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.stub.StreamObserver;
import protobuf.generated.KeyValueServiceGrpc.KeyValueServiceImplBase;
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
 * An RPC server that can receive GET, PUT, and DELETE
 * commands for key-value pairs to store in a map.
 * This implementation uses gRPC, which provides support
 * for multithreading client connections.
 * @author lscholte
 *
 */
public class RpcServer implements Server {
  
  private Map<String, String> map;
  private io.grpc.Server grpcServer;
  
  private final long simulatedRpcProcessingTimeMs;

  /**
   * Constructs an RpcServer assigned to the specified port
   * with a specified and map for storying key-value pairs.
   * A simulated amount of RPC processing time (in milliseconds)
   * can be specified to better demonstrated that the server is capable
   * of handling simultaneous calls from multiple clients.
   * @param port the port number assigned to the server
   * @param map the map to store key-value pairs
   * @param simulatedRpcProcessingTimeMs a simulated amount of time for RPC calls to take
   */
  public RpcServer(int port, Map<String, String> map, long simulatedRpcProcessingTimeMs) {
    this.simulatedRpcProcessingTimeMs = Math.max(0, simulatedRpcProcessingTimeMs);
    this.map = map;
    grpcServer = ServerBuilder
        .forPort(port)
        .addService(new KeyValueService())
        .intercept(new ClientInfoInterceptor())
        .build();
    
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      Logger.logInfo("Shutting down server");
      try {
        grpcServer.shutdown().awaitTermination(5, TimeUnit.SECONDS);
      }
      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }));
  }

  @Override
  public void start() throws InterruptedException, IOException {
    Logger.logInfo("Starting server");
    Logger.logInfo(String.format("All RPC calls will have an additional simulated processing time of %dms", simulatedRpcProcessingTimeMs));
    
    grpcServer.start();
    grpcServer.awaitTermination();
  }
      
  /**
   * A gRPC service class that handles the PUT, GET, and DELETE RPC calls.
   * @author lscholte
   *
   */
  private class KeyValueService extends KeyValueServiceImplBase {
    
    private Object lock;
    
    public KeyValueService() {
      lock = new Object();
    }
    
    @Override
    public void put(PutRequest request, StreamObserver<PutResponse> responseObserver) {      
      PutResponse.Builder responseBuilder = PutResponse.newBuilder();
      buildAndSendResponse(request, responseObserver, responseBuilder, () -> {
        if (!request.hasKey() || !request.hasValue()) {
          responseBuilder.setErrorStatus(ErrorStatus.INVALID_REQUEST_FORMAT);
          return;
        }
        synchronized (lock) {
          map.put(request.getKey(), request.getValue());
        }
      });
    }
    
    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {            
      DeleteResponse.Builder responseBuilder = DeleteResponse.newBuilder();
      buildAndSendResponse(request, responseObserver, responseBuilder, () -> {
        if (!request.hasKey()) {
          responseBuilder.setErrorStatus(ErrorStatus.INVALID_REQUEST_FORMAT);
          return;
        }
        boolean entryRemoved;
        synchronized (lock) {
          entryRemoved = map.remove(request.getKey()) != null;        
        }
        
        responseBuilder.setDeleted(entryRemoved);
      });
    }
    
    @Override
    public void get(GetRequest request, StreamObserver<GetResponse> responseObserver) {
      GetResponse.Builder responseBuilder = GetResponse.newBuilder();
      buildAndSendResponse(request, responseObserver, responseBuilder, () -> {
        if (!request.hasKey()) {
          responseBuilder.setErrorStatus(ErrorStatus.INVALID_REQUEST_FORMAT);
          return;
        }
        String value;
        synchronized (lock) {
          value = map.get(request.getKey());
        }
        
        if (value != null) {
          responseBuilder.setValue(value);        
        }
      });
    }
    
    @SuppressWarnings("unchecked")
    private <T extends Message> void buildAndSendResponse(
        Message request,
        StreamObserver<T> responseObserver,
        T.Builder responseBuilder,
        Runnable buildResponse) {
      Logger.logInfo(String.format("Received %s", ProtobufUtils.getPrintableMessage(request)));

      try {
        try {
          Thread.sleep(simulatedRpcProcessingTimeMs);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        buildResponse.run();
      }
      finally {
        T response = (T)responseBuilder.build();
        responseObserver.onNext((T)responseBuilder.build());
        responseObserver.onCompleted();
        Logger.logInfo(String.format("Sending %s", ProtobufUtils.getPrintableMessage(response)));
      }
    }
  }
  
  /**
   * a gRPC interceptor class to allow logging of client information from which
   * RPC requests were sent.
   * @author lscholte
   *
   */
  private class ClientInfoInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> Listener<ReqT> interceptCall(
        ServerCall<ReqT, RespT> call,
        Metadata headers,
        ServerCallHandler<ReqT, RespT> next) {
      Logger.logInfo(
          String.format(
              "Received request from %s",
              call.getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR).toString()));
      return next.startCall(call, headers);
    }
  }
}
