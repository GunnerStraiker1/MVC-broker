package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import model.Votos;


public class ControlPastel {
    
    DefaultPieDataset dataPie = new DefaultPieDataset();
    
    public ControlPastel() {
        crearPieGraphic();
    }
    
    public void actualizarGrafica(String producto, String valor){
        dataPie.setValue(producto, Integer.parseInt(valor));
    }
    
    public void crearPieGraphic(){
    	//bitacora("crearPieGraphic", "Creacion de la grafica");
        JFreeChart chart = ChartFactory.createPieChart("Ejemplo Pie", dataPie, true,true,false);
        ChartFrame frame = new ChartFrame("JFreeChart",chart);
        frame.pack();
        frame.setVisible(true);     
    }
	
}
