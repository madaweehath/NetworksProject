import java.io.*;
import java.net.*;

public class TCPServer {
public static void main(String[] args) {
try {
     // Create a server socket to listen for incoming connections on port 9999
     // binds to the specified port 
    ServerSocket serverSocket = new ServerSocket(9999);
    System.out.println("Server is running...");
        Socket clientSocket = serverSocket.accept();    
    while (true) 
    {
        // Accept incoming client connections

        System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName()); //???????????doesnt run

        // Set up input and output streams for communication with the client
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        // Read the request from the client
        String request = inFromClient.readLine();

        // Process the request
        if (request.equals(" ")) {
            outToClient.println("500 Request is empty");
        } else {
            
            //The request has two parts: a letter and number
            String[] parts = request.split(" ");
            if (parts.length == 1){
                if (parts[0].equals("B") || parts[0].equals("H") )
                outToClient.println("400 The number is missing");
                else if( !parts[0].equals("B") || !parts[0].equals("H") )
                outToClient.println("300 Bad request");
            }else{
                String command = parts[0];
                String numberStr = parts[1];
                            int number = Integer.parseInt(numberStr);
                            // Perform conversion based on the command
                            if (command.equals("B")) {
                                outToClient.println("200 " + Integer.toBinaryString(number));
                            } else if (command.equals("H")) {
                                outToClient.println("200 " + Integer.toHexString(number).toUpperCase());
                            }
                }
        }
        serverSocket.close();
    }
            
}// END OF TRY
catch (IOException e) {
    e.printStackTrace();
}
}// END OF MAIN
}// END OF CLASS
