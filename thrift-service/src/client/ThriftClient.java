package client;

import orderprocessing.OrderProcessing;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {
    public static void main(String[] args) {
        try {
            TTransport transport = new TSocket("SERVER_IP", 9090);
            transport.open();

            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            OrderProcessing.Client client = new OrderProcessing.Client(protocol);

            String productId = "P001";
            int quantity = 10;
            double total = client.calculateTotal(productId, quantity);
            System.out.println("Total Price: $" + total);

            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
