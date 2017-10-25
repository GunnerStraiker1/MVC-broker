/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Victor Perera
 */
public class Votos {
    private String nombreProducto;
    private int noVotos;
    
    public JSONObject crearJSON(String servicio, String producto, String Fecha) {
        JSONObject json =null;
        try {
            String ip = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            String str ="{\"Servicio\":\""+servicio+"\",\"IP\":\""+ip+"\",\"Producto\":\""+producto+"\",\"Fecha\":\""+Fecha+"\"}";
            JSONParser parser = new JSONParser();
            
            json = (JSONObject)parser.parse(str);
            
        } catch (UnknownHostException | ParseException ex) {
            ex.getMessage();
        }
        
        return json;
    }
    public JSONObject registrarCliente(String servicio) {
        JSONObject json =null;
        try {
            String ip = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            String str ="{\"Servicio\":\""+servicio+"\",\"IP\":\""+ip+"\"}";
            JSONParser parser = new JSONParser();
            
            json = (JSONObject)parser.parse(str);
            
        } catch (UnknownHostException | ParseException ex) {
            ex.getMessage();
        }
        return json;
    }
    
    public String[] desencriptarJSON(JSONObject json){
        String[] datosVoto =  new String[2];
        datosVoto[0] = json.get("Producto").toString();
        datosVoto[1] = json.get("Resultado").toString();
        return datosVoto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getNoVotos() {
        return noVotos;
    }

    public void setNoVotos(int noVotos) {
        this.noVotos = noVotos;
    }
    
    
    

}
