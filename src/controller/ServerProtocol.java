/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
 
    public JSONObject processInput(JSONObject theInput) throws UnknownHostException {
        JSONObject theOutput = null;
        JSONParser parser = new JSONParser();
        int resultado;
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
            if (theInput.get("Servicio").equals("votar")) {
                    try {
                        resultado = revisarProductoData((String)theInput.get("Producto"), (String)theInput.get("Datos"));
                        theOutput=(JSONObject)parser.parse("{\"Servicio\":\"contar\",\"Producto\":\""+ (String)theInput.get("Producto")+"\",\"Resultado\":\"" + String.valueOf(resultado) +"\"}");
                    } catch (ParseException ex) {
                        ex.getMessage();
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
    
    public int revisarProductoData(String nombreArchivo, String datos){
        int totalVotos;
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombreArchivo+".txt",true);
            pw = new PrintWriter(fichero);
            pw.println(datos);

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
        totalVotos = contarVotos(nombreArchivo);
        return totalVotos;
    }
    
    public int contarVotos(String nombreArchivo){
        int votos=0;
        File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (nombreArchivo+".txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null)
            votos++;
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
        
        return votos;
    }
    
}
