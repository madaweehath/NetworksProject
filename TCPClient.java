import java.io.*;
import java.net.*;

public class TCPClient {
public static void main(String[] args) {
try {
// Connect to the server running on localhost and port 9999
    Socket clientSocket = new Socket( "192.168.100.104" , 9999);
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);

    System.out.println("Connected to server.");

    while (true) {
// Get user input for command (B, H, or Q)
        System.out.println("Enter command (B: to convert to binary, H: to convert to hexadecimal, Q: to quit):");
        String command = inFromUser.readLine();

         if (command.equals("Q")) {
             break;
         }

// Get user input for number to convert
        System.out.println("Enter number to convert:");
        String numberStr = inFromUser.readLine();

// Send request to server
        outToServer.println(command + " " + numberStr);

// Receive response from server and print it
        String response = inFromServer.readLine();
        System.out.println("Server response: " + response);
    }

// Close the client socket
    clientSocket.close();
} //END TRY
catch (SocketException e) {
// if server is down
    System.out.println("Server is down, please try later.");
} catch (IOException e) {
    e.printStackTrace();
}
} // END MAIN
} //END CLASS
