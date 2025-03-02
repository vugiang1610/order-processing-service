package client;

import server.OrderProcessing;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("SERVER_IP", 1099);
            OrderProcessing service = (OrderProcessing) registry.lookup("OrderProcessingService");

            String productId = "P001";
            int quantity = 10;
            double total = service.calculateTotal(productId, quantity);
            System.out.println("Total Price: $" + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
