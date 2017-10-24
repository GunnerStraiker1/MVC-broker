package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class BrokerProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
 
    private static final int NUMJOKES = 5;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    /*private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };*/
 
    public JSONObject processInput(JSONObject theInput) throws UnknownHostException {
        JSONObject theOutput = null;
        JSONParser parser = new JSONParser();
        String ip = String.valueOf(InetAddress.getLocalHost().getHostAddress());
        Date date = new Date();
        if(state==WAITING){
           String json = "{\"Servicio\":\"registrar\", \"IP\":\""+ip+"\"}";
            try {
                theOutput=(JSONObject) parser.parse(json);
            } catch (ParseException ex) {
                ex.getMessage();
            }
           state=SENTKNOCKKNOCK;
       }
        else if(state==SENTKNOCKKNOCK){
            if (theInput.get("Servicio").equals("votar")) {
                String json = "{\"Servicio\":\"contar\", \"resultado\":\"5\"}";
                try {
                    theOutput=(JSONObject) parser.parse(json);
                } catch (ParseException ex) {
                    ex.getMessage();
                }
          }
           else if (theInput.get("Servicio").equals("Cerrar")) {
               String json = "{\"Servicio\":\"cerrar\"}";
                try {
                    theOutput=(JSONObject) parser.parse(json);
                } catch (ParseException ex) {
                    ex.getMessage();
                }
           }
        }
        /*if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Who's there?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "You're supposed to say \"Who's there?\"! " +
                "Try again. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                theOutput = answers[currentJoke] + " Want another? (y/n)";
                state = ANOTHER;
            } else {
                theOutput = "You're supposed to say \"" + 
                clues[currentJoke] + 
                " who?\"" + 
                "! Try again. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("y")) {
                theOutput = "Knock! Knock!";
                if (currentJoke == (NUMJOKES - 1))
                    currentJoke = 0;
                else
                    currentJoke++;
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Bye.";
                state = WAITING;
            }
        }*/
        return theOutput;
    }
    public JSONObject conectarServidor(JSONObject peticion){
        JSONObject resultado = null;
        
        String hostName = "localhost";
        int portNumber = 8888;
        
 
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            JSONObject fromServer;
            JSONObject fromUser;
            int i =0;
            JSONParser parser = new JSONParser();
 
            while ((fromServer = (JSONObject)parser.parse(in.readLine())) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")){
                    break;
                }
                else{          
                    resultado = fromServer; 
                    if(i==0){
                    fromUser = peticion;
                    if (fromUser != null) {
                         System.out.println("Client:" + fromUser);
                         out.println(fromUser);
                         i++;
                     }
                    }
                    else{
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } catch (ParseException ex) {
            ex.getMessage();
        }
        
        return resultado;
    }
    
}