package server;

import io.grpc.stub.StreamObserver;
import orderprocessing.OrderProcessingGrpc;
import orderprocessing.OrderProcessingOuterClass.OrderRequest;
import orderprocessing.OrderProcessingOuterClass.OrderResponse;

import java.sql.*;

public class OrderProcessingService extends OrderProcessingGrpc.OrderProcessingImplBase {
    private Connection connection;

    public OrderProcessingService() {
        try {
            String url = "jdbc:mysql://localhost:3306/ecommerce";
            String user = "root";
            String password = "yourpassword";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void calculateTotal(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT price FROM products WHERE id = ?");
            stmt.setString(1, request.getProductId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                double total = price * request.getQuantity();

                OrderResponse response = OrderResponse.newBuilder()
                        .setTotalPrice(total)
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new Exception("Product not found"));
            }
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }
}
