package com.example.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {

  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("grpc-server", 50051)
        .usePlaintext()
        .build();

    GreeterGrpc.GreeterBlockingStub stub =
        GreeterGrpc.newBlockingStub(channel);

    HelloWorldProto.HelloRequest request =
        HelloWorldProto.HelloRequest.newBuilder()
            .setArg1(5)
            .setArg2(9)
            .build();

    HelloWorldProto.HelloReply response = stub.sayHello(request);

    System.out.println("Response from server: " + response.getAnswer());

    channel.shutdown();
  }
}
