
import controller.*;
import model.Votos;
import view.vistaUsuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor Perera
 */
public class Main {
    public static void main(String[] args) {
        Votos votos = new Votos();
        vistaUsuario vista = new vistaUsuario();
        
        ControlGral control = new ControlGral(votos,vista);
        
    }
}
