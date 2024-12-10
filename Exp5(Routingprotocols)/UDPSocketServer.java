import java.net.*;
import java.io.*;

public class UDPSocketServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        byte[] receiveData = new byte[1024];
        try {
            socket = new DatagramSocket(9876); // Set up the server on port 9876
            System.out.println("Server is running and waiting for packets...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received message from client: " + receivedMessage);
                
                // Send acknowledgment back to the client
                String response = "Received: " + receivedMessage;
                DatagramPacket sendPacket = new DatagramPacket(response.getBytes(), response.length(), clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

