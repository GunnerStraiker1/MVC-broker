package controller;
import java.net.*;
import java.io.*;
 
public class MultiServer {
    public static void main(String[] args) throws IOException {
 
    /*if (args.length != 1) {
        System.err.println("Usage: java KKMultiServer <port number>");
        System.exit(1);
    }*/
 
        int portNumber = 8888;
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
                new MultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}