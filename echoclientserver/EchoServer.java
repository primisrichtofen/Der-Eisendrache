import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Echo Server is running on port " + portNumber + "...");
            while (true) {
                try (
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("Connected to client: " + clientSocket.getInetAddress());
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("Received: " + inputLine);
                        out.println(inputLine);
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client connection.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + portNumber);
            e.printStackTrace();
        }
    }
}

