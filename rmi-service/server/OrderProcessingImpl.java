package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class OrderProcessingImpl extends UnicastRemoteObject implements OrderProcessing {
    private Connection connection;

    public OrderProcessingImpl() throws RemoteException {
        super();
        try {
            String url = "jdbc:mysql://localhost:3306/ecommerce";
            String user = "root";
            String password = "yourpassword";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database connection failed!");
        }
    }

    @Override
    public double calculateTotal(String productId, int quantity) throws RemoteException {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT price FROM products WHERE id = ?");
            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                double price = rs.getDouble("price");
                return price * quantity;
            } else {
                throw new RemoteException("Product not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database error");
        }
    }
}
