import java.net.*;
import java.io.*;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        String serverAddress = "localhost"; // DNS server address
        int serverPort = 53; // DNS server port

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter hostname or IP address: ");
        String request = reader.readLine();

        byte[] sendData = request.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(serverAddress), serverPort);
        socket.send(sendPacket);

        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Resolved: " + response);

        socket.close();
    }
}

