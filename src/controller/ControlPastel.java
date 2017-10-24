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
        dataPie.setValue("C", 40);
        dataPie.setValue("Java", 45);
        dataPie.setValue("Python", 15);
        crearPieGraphic();
    }
    
    
    
    /*public void incrementarZucaritas(){
    	bitacora("incrementarZucaritas", "Incrementa voto");
        int noVotosZucaritas = contador.obtenerVotos("Registro 1");
        dataPie.setValue(contador.getNombreProducto(), noVotosZucaritas);
    }
    
    public void incrementarFrootLoops(){
    	bitacora("incrementarFrootLoops", "Incrementa voto");
        int noVotosFrootLoops = contador.obtenerVotos("Registro 2");
        dataPie.setValue(contador.getNombreProducto(), noVotosFrootLoops);
    }
    
    public void incrementarChocokrispis(){
    	bitacora("incrementarChocokrispis", "Incrementa voto");
        int noVotosChocokrispis = contador.obtenerVotos("Registro 3");
        dataPie.setValue(contador.getNombreProducto(), noVotosChocokrispis);
    }*/
    
    public void crearPieGraphic(){
    	//bitacora("crearPieGraphic", "Creacion de la grafica");
        JFreeChart chart = ChartFactory.createPieChart("Ejemplo Pie", dataPie, true,true,false);
        ChartFrame frame = new ChartFrame("JFreeChart",chart);
        frame.pack();
        frame.setVisible(true);     
    }

	/*@Override
	public void actionPerformed(ActionEvent e) {
		bitacora("actionPerformed", "Actualizacion de cada voto nuevo en la grafica");
		if(interfazUsuario.jBVotoZucaritas == e.getSource()){
            Date fecha = new Date();
            incrementarZucaritas();
        }
        
        else if(vistaUsuario.jBVotoFrut == e.getSource()){
            Date fecha = new Date();
            incrementarFrootLoops();
        }
        
        else if(vistaUsuario.jBChoco == e.getSource()){
            Date fecha = new Date();
            incrementarChocokrispis();
        }
		
	}

	public static void bitacora(String nombreMetodo, String mensaje) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {

			Calendar calendario = new GregorianCalendar();
			
			int hora =calendario.get(Calendar.HOUR_OF_DAY);
			int minutos = calendario.get(Calendar.MINUTE);
			int segundos = calendario.get(Calendar.SECOND);			
			String tiempo = hora + ":" + minutos + ":" + segundos;
			
			String nameClase = ControlPastel.class.getName();
			
			fichero = new FileWriter("Bitacora.txt", true);
			pw = new PrintWriter(fichero);

			pw.println(tiempo + " " + nombreMetodo + " de la clase " + nameClase +  " " + mensaje);

		} catch (Exception e) {
			e.getMessage();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
	}*/
	
}
