/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grpc.chat;

/**
 *
 * @author fauzanhilmi
 */

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class ChatterGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<grpc.chat.GRPCChat.mName,
      grpc.chat.GRPCChat.mString> METHOD_CREATE_NICKNAME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "grpc.chat.Chatter", "createNickname",
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mName.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mString.parser()));
  public static final io.grpc.MethodDescriptor<grpc.chat.GRPCChat.mNameChannel,
      grpc.chat.GRPCChat.mBoolean> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "grpc.chat.Chatter", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mNameChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mBoolean.parser()));
  public static final io.grpc.MethodDescriptor<grpc.chat.GRPCChat.mNameChannel,
      grpc.chat.GRPCChat.mBoolean> METHOD_LEAVE_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "grpc.chat.Chatter", "leaveChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mNameChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mBoolean.parser()));
  public static final io.grpc.MethodDescriptor<grpc.chat.GRPCChat.mNameChannelMsg,
      grpc.chat.GRPCChat.mBoolean> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "grpc.chat.Chatter", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mNameChannelMsg.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mBoolean.parser()));
  public static final io.grpc.MethodDescriptor<grpc.chat.GRPCChat.mName,
      grpc.chat.GRPCChat.mString> METHOD_GET_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "grpc.chat.Chatter", "getMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mName.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(grpc.chat.GRPCChat.mString.parser()));

  public static ChatterStub newStub(io.grpc.Channel channel) {
    return new ChatterStub(channel);
  }

  public static ChatterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatterBlockingStub(channel);
  }

  public static ChatterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatterFutureStub(channel);
  }

  public static interface Chatter {

    public void createNickname(grpc.chat.GRPCChat.mName request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver);

    public void joinChannel(grpc.chat.GRPCChat.mNameChannel request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver);

    public void leaveChannel(grpc.chat.GRPCChat.mNameChannel request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver);

    public void sendMessage(grpc.chat.GRPCChat.mNameChannelMsg request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver);

    public void getMessage(grpc.chat.GRPCChat.mName request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver);
  }

  public static interface ChatterBlockingClient {

    public grpc.chat.GRPCChat.mString createNickname(grpc.chat.GRPCChat.mName request);

    public grpc.chat.GRPCChat.mBoolean joinChannel(grpc.chat.GRPCChat.mNameChannel request);

    public grpc.chat.GRPCChat.mBoolean leaveChannel(grpc.chat.GRPCChat.mNameChannel request);

    public grpc.chat.GRPCChat.mBoolean sendMessage(grpc.chat.GRPCChat.mNameChannelMsg request);

    public grpc.chat.GRPCChat.mString getMessage(grpc.chat.GRPCChat.mName request);
  }

  public static interface ChatterFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mString> createNickname(
        grpc.chat.GRPCChat.mName request);

    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> joinChannel(
        grpc.chat.GRPCChat.mNameChannel request);

    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> leaveChannel(
        grpc.chat.GRPCChat.mNameChannel request);

    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> sendMessage(
        grpc.chat.GRPCChat.mNameChannelMsg request);

    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mString> getMessage(
        grpc.chat.GRPCChat.mName request);
  }

  public static class ChatterStub extends io.grpc.stub.AbstractStub<ChatterStub>
      implements Chatter {
    private ChatterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatterStub(channel, callOptions);
    }

    @java.lang.Override
    public void createNickname(grpc.chat.GRPCChat.mName request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_CREATE_NICKNAME, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void joinChannel(grpc.chat.GRPCChat.mNameChannel request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void leaveChannel(grpc.chat.GRPCChat.mNameChannel request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessage(grpc.chat.GRPCChat.mNameChannelMsg request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void getMessage(grpc.chat.GRPCChat.mName request,
        io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request, responseObserver);
    }
  }

  public static class ChatterBlockingStub extends io.grpc.stub.AbstractStub<ChatterBlockingStub>
      implements ChatterBlockingClient {
    private ChatterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatterBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public grpc.chat.GRPCChat.mString createNickname(grpc.chat.GRPCChat.mName request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_CREATE_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public grpc.chat.GRPCChat.mBoolean joinChannel(grpc.chat.GRPCChat.mNameChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public grpc.chat.GRPCChat.mBoolean leaveChannel(grpc.chat.GRPCChat.mNameChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public grpc.chat.GRPCChat.mBoolean sendMessage(grpc.chat.GRPCChat.mNameChannelMsg request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public grpc.chat.GRPCChat.mString getMessage(grpc.chat.GRPCChat.mName request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request);
    }
  }

  public static class ChatterFutureStub extends io.grpc.stub.AbstractStub<ChatterFutureStub>
      implements ChatterFutureClient {
    private ChatterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatterFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mString> createNickname(
        grpc.chat.GRPCChat.mName request) {
      return futureUnaryCall(
          channel.newCall(METHOD_CREATE_NICKNAME, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> joinChannel(
        grpc.chat.GRPCChat.mNameChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> leaveChannel(
        grpc.chat.GRPCChat.mNameChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mBoolean> sendMessage(
        grpc.chat.GRPCChat.mNameChannelMsg request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<grpc.chat.GRPCChat.mString> getMessage(
        grpc.chat.GRPCChat.mName request) {
      return futureUnaryCall(
          channel.newCall(METHOD_GET_MESSAGE, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Chatter serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("grpc.chat.Chatter")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_CREATE_NICKNAME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                grpc.chat.GRPCChat.mName,
                grpc.chat.GRPCChat.mString>() {
              @java.lang.Override
              public void invoke(
                  grpc.chat.GRPCChat.mName request,
                  io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver) {
                serviceImpl.createNickname(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                grpc.chat.GRPCChat.mNameChannel,
                grpc.chat.GRPCChat.mBoolean>() {
              @java.lang.Override
              public void invoke(
                  grpc.chat.GRPCChat.mNameChannel request,
                  io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
                serviceImpl.joinChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                grpc.chat.GRPCChat.mNameChannel,
                grpc.chat.GRPCChat.mBoolean>() {
              @java.lang.Override
              public void invoke(
                  grpc.chat.GRPCChat.mNameChannel request,
                  io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
                serviceImpl.leaveChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                grpc.chat.GRPCChat.mNameChannelMsg,
                grpc.chat.GRPCChat.mBoolean>() {
              @java.lang.Override
              public void invoke(
                  grpc.chat.GRPCChat.mNameChannelMsg request,
                  io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mBoolean> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                grpc.chat.GRPCChat.mName,
                grpc.chat.GRPCChat.mString>() {
              @java.lang.Override
              public void invoke(
                  grpc.chat.GRPCChat.mName request,
                  io.grpc.stub.StreamObserver<grpc.chat.GRPCChat.mString> responseObserver) {
                serviceImpl.getMessage(request, responseObserver);
              }
            }))).build();
  }
}
