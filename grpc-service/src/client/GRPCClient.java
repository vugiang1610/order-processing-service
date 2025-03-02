package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import orderprocessing.OrderProcessingGrpc;
import orderprocessing.OrderProcessingOuterClass.OrderRequest;
import orderprocessing.OrderProcessingOuterClass.OrderResponse;

public class GRPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("SERVER_IP", 50051)
                .usePlaintext()
                .build();

        OrderProcessingGrpc.OrderProcessingBlockingStub stub = OrderProcessingGrpc.newBlockingStub(channel);

        OrderRequest request = OrderRequest.newBuilder()
                .setProductId("P001")
                .setQuantity(10)
                .build();

        OrderResponse response = stub.calculateTotal(request);
        System.out.println("Total Price: $" + response.getTotalPrice());

        channel.shutdown();
    }
}
