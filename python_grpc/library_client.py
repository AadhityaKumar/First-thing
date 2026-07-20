from __future__ import print_function

import logging

import grpc
import librarySystem_pb2
import librarySystem_pb2_grpc


def run():
    with grpc.insecure_channel("grpc-server:50051") as channel:
        stub = librarySystem_pb2_grpc.GreeterStub(channel)
        response = stub.SayHello(librarySystem_pb2.HelloRequest(arg1=5, arg2=4))
        print("Greeter client received: " + str(response.answer))



if __name__ == "__main__":
    logging.basicConfig()
    run()
