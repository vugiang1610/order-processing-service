package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OrderProcessing extends Remote {
    double calculateTotal(String productId, int quantity) throws RemoteException;
}
