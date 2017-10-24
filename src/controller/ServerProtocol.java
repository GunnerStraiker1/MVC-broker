/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Victor Perera
 */
public class ServerProtocol {
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
            try {
                theOutput=(JSONObject)parser.parse("{\"Servicio\":\"Inicio Servidor\"}");
            } catch (ParseException ex) {
                ex.getMessage();
            }
           state=SENTKNOCKKNOCK;
       }
        else if(state==SENTKNOCKKNOCK){
            if (theInput.get("Servicio").equals("contar")) {
                if (theInput.get("Producto").equals("Zucaritas")) {
                    try {
                        theOutput=(JSONObject)parser.parse("{\"Producto\":\"Zucaritas\",\"Resultado\":\"15\"}");
                    } catch (ParseException ex) {
                        ex.getMessage();
                    }
                }
                else if(theInput.get("Producto").equals("Frootloops")){
                    try {
                        theOutput=(JSONObject)parser.parse("{\"Producto\":\"Frootloops\",\"Resultado\":\"10\"}");
                    } catch (ParseException ex) {
                        ex.getMessage();
                    }
                }
               }
           else if (theInput.equals("Cerrar")) {
               try {
                theOutput=(JSONObject)parser.parse("{\"Servicio\":\"cerrar\"}");
            } catch (ParseException ex) {
                ex.getMessage();
            }
           }
        }
        return theOutput;
    }
    

}
