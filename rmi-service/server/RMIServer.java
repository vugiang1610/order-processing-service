package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            OrderProcessingImpl orderProcessing = new OrderProcessingImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("OrderProcessingService", orderProcessing);
            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
