package server;

import orderprocessing.OrderProcessing;
import java.sql.*;

public class OrderProcessingHandler implements OrderProcessing.Iface {
    private Connection connection;

    public OrderProcessingHandler() {
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
    public double calculateTotal(String productId, int quantity) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT price FROM products WHERE id = ?");
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                return price * quantity;
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error");
        }
    }
}
