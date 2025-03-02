package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GRPCServer {
    public static void main(String[] args) {
        try {
            Server server = ServerBuilder.forPort(50051)
                    .addService(new OrderProcessingService())
                    .build()
                    .start();

            System.out.println("gRPC Server is running on port 50051...");
            server.awaitTermination();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
