/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.Votos;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.vistaUsuario;

/**
 *
 * @author Victor Perera
 */
public class ControlGral implements ActionListener{
    protected Votos votaciones;
    protected vistaUsuario vista;
    protected Socket kkSocket;
    PrintWriter out;
    BufferedReader in;
    BufferedReader stdIn;
    JSONObject fromServer; 
    JSONObject fromUser = null;
    public ControlGral(Votos votaciones, vistaUsuario vista) {
        this.votaciones = votaciones;
        this.vista = vista;
        try {
            this.kkSocket= new Socket("localhost",4444);
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        vista.btnZuca.addActionListener(this);
        vista.btnFroot.addActionListener(this);
        vista.btnChoco.addActionListener(this);
        vista.btnFinish.addActionListener(this);
        vista.lbChoco.setText("ChocoKrispis");
        vista.lbFroot.setText("Froot Loops");
        vista.lbZuca.setText("Zucaritas");
        
        iniciarInterfaz();
        ControlPastel controlPie = new ControlPastel();
        ControlBarras controlBar = new ControlBarras();
        try{
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        }catch(Exception e){
            e.getMessage();
        }
        
        mensajesCliente(kkSocket);
        
    }
    public void iniciarInterfaz(){
        vista.setTitle("Votacion");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
   
    @SuppressWarnings("empty-statement")
    public void mensajesCliente(Socket kkSocket) {
        try { 
            JSONParser parser = new JSONParser();
            while((this.fromServer = ((JSONObject) parser.parse(in.readLine()))) != null) {
                System.out.println("Server: " + this.fromServer);
                if (this.fromServer.toJSONString().equals("{\"Servicio\":\"cerrar\"}")){
                    System.exit(0);;   
                }
                System.out.println("Este se puede manipular: " + this.fromServer);
            }
            
        } catch (IOException ex) {
            ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(ControlGral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnZuca) {
            Date date = new Date();
            fromUser =  votaciones.crearJSON("votar", "Zucaritas", date.toString());
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser );
                    out.println(fromUser);
                }
                
                
        }
        else if(e.getSource() == vista.btnFroot){
            Date date = new Date();
            fromUser = votaciones.crearJSON("votar", "FrootLoops", date.toString());
                if (fromUser != null) {
                    System.out.println("Client:" + fromUser );
                    out.println(fromUser);
                }
        }
        
        else if(e.getSource() == vista.btnChoco){
            Date date = new Date();
            fromUser = votaciones.crearJSON("votar", "ChocoKrispis", date.toString());
                if (fromUser != null) {
                    System.out.println("Client:" + fromUser );
                    out.println(fromUser);
                }
        }
        
        else if(e.getSource() == vista.btnFinish){
            Date date = new Date();
            fromUser = votaciones.crearJSON("Cerrar", "votaciones", date.toString());
                if (fromUser != null) {
                    System.out.println("Client:" + fromUser );
                    out.println(fromUser);
                }
        }
    }
}
