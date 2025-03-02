package server;

import orderprocessing.OrderProcessing;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftServer {
    public static void main(String[] args) {
        try {
            OrderProcessingHandler handler = new OrderProcessingHandler();
            OrderProcessing.Processor<OrderProcessingHandler> processor = new OrderProcessing.Processor<>(handler);
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Thrift Server is running on port 9090...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
