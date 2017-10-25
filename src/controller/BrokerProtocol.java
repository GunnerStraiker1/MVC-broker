package controller;

import java.io.BufferedReader;
import java.io.FileWriter;
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

    public JSONObject processInput(JSONObject theInput) throws UnknownHostException {
        JSONObject jsonConverted,theOutput = null;
        JSONParser parser = new JSONParser();
        String ip = String.valueOf(InetAddress.getLocalHost().getHostAddress());
        Date date = new Date();
        if(state==WAITING){
           String json = "{\"Servicio\":\"Iniciar Broker\", \"IP\":\""+ip+"\"}";
            try {
                theOutput=(JSONObject) parser.parse(json);
            } catch (ParseException ex) {
                ex.getMessage();
            }
           state=SENTKNOCKKNOCK;
       }
        else if(state==SENTKNOCKKNOCK){
            if (theInput.get("Servicio").equals("Registrar")) {
                registrarUsuario(theInput.get("IP").toString());
                String json = "{\"Servicio\":\"Iniciar Broker\", \"IP\":\"" + ip + "\"}";
                try {
                    theOutput = (JSONObject) parser.parse(json);
                } catch (ParseException ex) {
                    ex.getMessage();
                }
            }
            else if (theInput.get("Servicio").equals("votar")) {
                String json = "{\"Servicio\":\"votar\", \"Producto\":\""+(String)theInput.get("Producto")+"\",\"Datos\":\""+ 
                        (String)theInput.get("Fecha")+" "+(String)theInput.get("IP") +"\"}";
                try {
                    jsonConverted=(JSONObject) parser.parse(json);
                    theOutput = conectarServidor(jsonConverted);
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
    
    public void registrarUsuario(String ip){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("Usuarios.txt",true);
            pw = new PrintWriter(fichero);
            pw.println(ip +new Date().toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
}