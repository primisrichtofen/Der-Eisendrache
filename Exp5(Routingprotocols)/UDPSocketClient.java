import java.net.*;
import java.io.*;

public class UDPSocketClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter message to send to server:");
            String message = reader.readLine();
            
            // Send the message to the server
            DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
            socket.send(sendPacket);

            // Receive acknowledgment from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server Response: " + response);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

