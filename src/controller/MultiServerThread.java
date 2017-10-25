package controller;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MultiServerThread extends Thread {
    private Socket socket = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
    
    @Override
    public void run() {

        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            JSONObject inputLine;
            JSONObject outputLine;
            JSONParser parser = new JSONParser();
            ServerProtocol kkp = new ServerProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine=(JSONObject)parser.parse(in.readLine())) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.toString().equals("{\"Servicio\":\"cerrar\"}"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
        } catch (ParseException ex) {
            Logger.getLogger(MultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}