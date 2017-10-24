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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Votos;


public class ControlBarras {

	DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public ControlBarras() {
		crearBarGraphic();
	}

	/*public void incrementarZucaritas() {
		bitacora("incrementarZucaritas", "Incrementa voto");
		int noVotosZucaritas = contador.obtenerVotos("Registro 1");
		dataset.setValue(noVotosZucaritas, contador.getNombreProducto(), "Votos");
	}

	public void incrementarFrootLoops() {
		bitacora("incrementarFrootLoops", "Incrementa voto");
		int noVotosFrootLoops = contador.obtenerVotos("Registro 2");
		dataset.setValue(noVotosFrootLoops, contador.getNombreProducto(), "Votos");
	}

	public void incrementarChocokrispis() {
		bitacora("incrementarChocokrispis", "Incrementa voto");
		int noVotosChocokrispis = contador.obtenerVotos("Registro 3");
		dataset.setValue(noVotosChocokrispis, contador.getNombreProducto(), "Votos");
	}*/

	public void crearBarGraphic() {
		//bitacora("crearBarGraphic", "Creaci�n de la grafica");
		JFreeChart grafica = ChartFactory.createBarChart("Conteo Votos", "Producto", "Votos", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		ChartFrame frame = new ChartFrame("BarFrame", grafica);
		frame.pack();
		frame.setVisible(true);
	}

	/*@Override
	public void actionPerformed(ActionEvent e) {
		bitacora("actionPerformed", "Actualizacion de cada voto nuevo en la grafica");
		if (interfazUsuario.jBVotoZucaritas == e.getSource()) {
			Date fecha = new Date();
			incrementarZucaritas();
		}

		else if (vistaUsuario.jBVotoFrut == e.getSource()) {
			Date fecha = new Date();
			incrementarFrootLoops();
		}

		else if (vistaUsuario.jBChoco == e.getSource()) {
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
			
			String nameClase = ControlBarras.class.getName();
			
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
