import java.net.*;
import java.io.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(53); // DNS typically works on port 53
        byte[] receiveData = new byte[1024];

        System.out.println("DNS Server is running...");
        
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
            
            System.out.println("Received request: " + clientRequest);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String response = resolveRequest(clientRequest);
            byte[] responseData = response.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        }
    }

    private static String resolveRequest(String request) {
        try {
            InetAddress address = InetAddress.getByName(request);
            return address.getHostAddress(); // Return IP address for given hostname
        } catch (UnknownHostException e) {
            try {
                InetAddress[] addresses = InetAddress.getAllByName(request);
                return addresses[0].getHostName(); // Return hostname for given IP address
            } catch (UnknownHostException ex) {
                return "Unable to resolve";
            }
        }
    }
}
