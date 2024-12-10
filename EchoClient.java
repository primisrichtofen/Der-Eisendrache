import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String hostName = "localhost"; // Update this to the server's IP if not on the same machine
        int portNumber = 12345;

        try (
            Socket echoSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            System.out.println("Connected to Echo Server at " + hostName + ":" + portNumber);
            System.out.println("Type your messages (type 'exit' to quit):");
            
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Closing connection...");
                    break;
                }
                out.println(userInput);
                String response = in.readLine();
                if (response != null) {
                    System.out.println("Echo: " + response);
                } else {
                    System.out.println("Server closed the connection.");
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostName);
            System.err.println("Check if the server hostname is correct.");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName + ":" + portNumber);
            System.err.println("Check if the server is running and reachable.");
        }
    }
}

