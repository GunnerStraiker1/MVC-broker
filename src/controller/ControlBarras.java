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

    public void actualizarGrafica(String producto, String valor) {
        dataset.setValue(Integer.parseInt(valor), producto, "Votos");
    }

    public void crearBarGraphic() {
        //bitacora("crearBarGraphic", "Creaci�n de la grafica");
        JFreeChart grafica = ChartFactory.createBarChart("Conteo Votos", "Producto", "Votos", dataset,
                PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame = new ChartFrame("BarFrame", grafica);
        frame.pack();
        frame.setVisible(true);
    }

}
