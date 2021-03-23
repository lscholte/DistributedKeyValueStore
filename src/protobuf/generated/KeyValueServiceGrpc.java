package protobuf.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.36.0)",
    comments = "Source: KeyValueService.proto")
public final class KeyValueServiceGrpc {

  private KeyValueServiceGrpc() {}

  public static final String SERVICE_NAME = "protobuf.KeyValueService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.PutRequest,
      protobuf.generated.KeyValueServiceMessages.PutResponse> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Put",
      requestType = protobuf.generated.KeyValueServiceMessages.PutRequest.class,
      responseType = protobuf.generated.KeyValueServiceMessages.PutResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.PutRequest,
      protobuf.generated.KeyValueServiceMessages.PutResponse> getPutMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.PutRequest, protobuf.generated.KeyValueServiceMessages.PutResponse> getPutMethod;
    if ((getPutMethod = KeyValueServiceGrpc.getPutMethod) == null) {
      synchronized (KeyValueServiceGrpc.class) {
        if ((getPutMethod = KeyValueServiceGrpc.getPutMethod) == null) {
          KeyValueServiceGrpc.getPutMethod = getPutMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.KeyValueServiceMessages.PutRequest, protobuf.generated.KeyValueServiceMessages.PutResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.PutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.PutResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueServiceMethodDescriptorSupplier("Put"))
              .build();
        }
      }
    }
    return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.GetRequest,
      protobuf.generated.KeyValueServiceMessages.GetResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = protobuf.generated.KeyValueServiceMessages.GetRequest.class,
      responseType = protobuf.generated.KeyValueServiceMessages.GetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.GetRequest,
      protobuf.generated.KeyValueServiceMessages.GetResponse> getGetMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.GetRequest, protobuf.generated.KeyValueServiceMessages.GetResponse> getGetMethod;
    if ((getGetMethod = KeyValueServiceGrpc.getGetMethod) == null) {
      synchronized (KeyValueServiceGrpc.class) {
        if ((getGetMethod = KeyValueServiceGrpc.getGetMethod) == null) {
          KeyValueServiceGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.KeyValueServiceMessages.GetRequest, protobuf.generated.KeyValueServiceMessages.GetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.GetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.GetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueServiceMethodDescriptorSupplier("Get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.DeleteRequest,
      protobuf.generated.KeyValueServiceMessages.DeleteResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Delete",
      requestType = protobuf.generated.KeyValueServiceMessages.DeleteRequest.class,
      responseType = protobuf.generated.KeyValueServiceMessages.DeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.DeleteRequest,
      protobuf.generated.KeyValueServiceMessages.DeleteResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<protobuf.generated.KeyValueServiceMessages.DeleteRequest, protobuf.generated.KeyValueServiceMessages.DeleteResponse> getDeleteMethod;
    if ((getDeleteMethod = KeyValueServiceGrpc.getDeleteMethod) == null) {
      synchronized (KeyValueServiceGrpc.class) {
        if ((getDeleteMethod = KeyValueServiceGrpc.getDeleteMethod) == null) {
          KeyValueServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<protobuf.generated.KeyValueServiceMessages.DeleteRequest, protobuf.generated.KeyValueServiceMessages.DeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protobuf.generated.KeyValueServiceMessages.DeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyValueServiceMethodDescriptorSupplier("Delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeyValueServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceStub>() {
        @java.lang.Override
        public KeyValueServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueServiceStub(channel, callOptions);
        }
      };
    return KeyValueServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeyValueServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceBlockingStub>() {
        @java.lang.Override
        public KeyValueServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueServiceBlockingStub(channel, callOptions);
        }
      };
    return KeyValueServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeyValueServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyValueServiceFutureStub>() {
        @java.lang.Override
        public KeyValueServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyValueServiceFutureStub(channel, callOptions);
        }
      };
    return KeyValueServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class KeyValueServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void put(protobuf.generated.KeyValueServiceMessages.PutRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.PutResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void get(protobuf.generated.KeyValueServiceMessages.GetRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.GetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void delete(protobuf.generated.KeyValueServiceMessages.DeleteRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.DeleteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPutMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.KeyValueServiceMessages.PutRequest,
                protobuf.generated.KeyValueServiceMessages.PutResponse>(
                  this, METHODID_PUT)))
          .addMethod(
            getGetMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.KeyValueServiceMessages.GetRequest,
                protobuf.generated.KeyValueServiceMessages.GetResponse>(
                  this, METHODID_GET)))
          .addMethod(
            getDeleteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                protobuf.generated.KeyValueServiceMessages.DeleteRequest,
                protobuf.generated.KeyValueServiceMessages.DeleteResponse>(
                  this, METHODID_DELETE)))
          .build();
    }
  }

  /**
   */
  public static final class KeyValueServiceStub extends io.grpc.stub.AbstractAsyncStub<KeyValueServiceStub> {
    private KeyValueServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueServiceStub(channel, callOptions);
    }

    /**
     */
    public void put(protobuf.generated.KeyValueServiceMessages.PutRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.PutResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(protobuf.generated.KeyValueServiceMessages.GetRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.GetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(protobuf.generated.KeyValueServiceMessages.DeleteRequest request,
        io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.DeleteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class KeyValueServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<KeyValueServiceBlockingStub> {
    private KeyValueServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protobuf.generated.KeyValueServiceMessages.PutResponse put(protobuf.generated.KeyValueServiceMessages.PutRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.KeyValueServiceMessages.GetResponse get(protobuf.generated.KeyValueServiceMessages.GetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public protobuf.generated.KeyValueServiceMessages.DeleteResponse delete(protobuf.generated.KeyValueServiceMessages.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class KeyValueServiceFutureStub extends io.grpc.stub.AbstractFutureStub<KeyValueServiceFutureStub> {
    private KeyValueServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyValueServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyValueServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.KeyValueServiceMessages.PutResponse> put(
        protobuf.generated.KeyValueServiceMessages.PutRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.KeyValueServiceMessages.GetResponse> get(
        protobuf.generated.KeyValueServiceMessages.GetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protobuf.generated.KeyValueServiceMessages.DeleteResponse> delete(
        protobuf.generated.KeyValueServiceMessages.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PUT = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_DELETE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final KeyValueServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(KeyValueServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PUT:
          serviceImpl.put((protobuf.generated.KeyValueServiceMessages.PutRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.PutResponse>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((protobuf.generated.KeyValueServiceMessages.GetRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.GetResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((protobuf.generated.KeyValueServiceMessages.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<protobuf.generated.KeyValueServiceMessages.DeleteResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class KeyValueServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KeyValueServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protobuf.generated.KeyValueServiceMessages.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KeyValueService");
    }
  }

  private static final class KeyValueServiceFileDescriptorSupplier
      extends KeyValueServiceBaseDescriptorSupplier {
    KeyValueServiceFileDescriptorSupplier() {}
  }

  private static final class KeyValueServiceMethodDescriptorSupplier
      extends KeyValueServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    KeyValueServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (KeyValueServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KeyValueServiceFileDescriptorSupplier())
              .addMethod(getPutMethod())
              .addMethod(getGetMethod())
              .addMethod(getDeleteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
