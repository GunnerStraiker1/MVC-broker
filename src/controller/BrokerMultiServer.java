import java.net.*;
import java.io.*;
 
public class BrokerMultiServer {
    public static void main(String[] args) throws IOException {
  
        int portNumber = 4444;
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
                new BrokerServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}