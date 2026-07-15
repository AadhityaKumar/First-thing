package com.example.helloworld;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class HelloWorldServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    int port = 50051;

    Server server = ServerBuilder
        .forPort(port)
        .addService(new GreeterService())
        .build()
        .start();

    System.out.println("Server started on port " + port);

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.err.println("Shutting down gRPC server");
      server.shutdown();
    }));

    server.awaitTermination();
  }

  static class GreeterService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(
        HelloWorldProto.HelloRequest request,
        StreamObserver<HelloWorldProto.HelloReply> responseObserver) {

      int answer = request.getArg1() + request.getArg2();

      HelloWorldProto.HelloReply reply =
          HelloWorldProto.HelloReply.newBuilder()
              .setAnswer(answer)
              .build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}
