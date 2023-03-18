/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.vista.inicio;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author javier
 */
public class Inicio extends JPanel {

    private JPanel panelTituloPrincipal;
    private JPanel panelTitulo;
    private JLabel imageneIglesiaLogo;
    private JLabel titulo;

    private JPanel panelInformacionPrincipal;

    private JPanel panelReporteFinanza;
    private JPanel panelReporteDatos;

    private JPanel panelGraficas;

    private JPanel panelEtiquetas;

    private JPanel panelEtiquetasGraficaOfrenda;
    private JLabel etiquetaFinanzasReporte;
    private JLabel etiquetaOfrenda;
    private JLabel etiquetaTotalOfrenda;

    private DefaultPieDataset data1, data2;
    private JFreeChart chart1, chart2;
    private ChartPanel panel1, panel2;

    private JLabel etiquetaTotal;

    private JPanel panelEtiquetasGraficaOfrendaSeminario;
    private JLabel etiquetaOfrendaSeminario;
    private JLabel etiquetaTotalOfrendaSeminario;

    private JPanel panelGraficasOfrendaSeminario;

    private JPanel panelNumerosSacerdotes,
            panelNumerosCoros,
            panelNumerosServicios;
    private JPanel panelFooter;
    private JLabel etiquetaFooter;
    
    private JButton botonLinkEvangelio, botonLinkSanto, botonLinkMisa;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoGrafica = new Color(28, 225, 206);
    private Color colorBotonesMenu = new Color(245, 222, 179);
    private Color colorMenu = new Color(72, 126, 176);
    private Color colorBotonVaticanNews = Color.decode("#CC0000");
    
    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;

    public Inicio() {
        setBackground(colorFormulario);
        setLayout(new BorderLayout());
    }

    public void inicializarComponentes() {
        colocarTitulo();
        colocarPanelInformacionPrincipal();

        colocarPanelReporteFinanza();
        colocarPanelReporteDatos();
        colocarPanelGraficas();
        colocarLogo();
        colocarBotones();
        /*colocarPanelEtiquetasGraficaOfrenda();
        colocarPanelEtiquetasDatosGrafica();
        colocarEtiquetaPanelDatosGrafica();
        colocarEtiquetaTotal();
*/
        /*colocarPanelEtiquetasGraficaOfrendaSeminario();
        colocarEtiquetaPanelDatosGraficaSeminario();
        colocarPanelGraficasOfrendaSeminario();
        colocarGraficaOferdas();
        colocarGraficaOfrendasSeminario();
        */
        colocarPanelNumeroSacerdotes();
        colocarPanelNumeroCoros();
        colocarPanelNumeroServicios();
        
        footer();
        
        cajaDatosSacerdotes();
        cajaDatosCoros();
        cajaDatosServicios();
        //colocarPanelDatosNumeros();
    }

    private void colocarTitulo() {
        panelTituloPrincipal = new JPanel(new BorderLayout());
        panelTitulo = new JPanel(new GridLayout(2, 1));
        imageneIglesiaLogo = new JLabel(new ImageIcon("src/mx/unach/imagenes/church.png"));
        titulo = new JLabel("DashBoard");
        titulo.setFont(new Font("Lato Heavy", 2, 18));
        panelTitulo.add(imageneIglesiaLogo);
        panelTitulo.add(titulo);
        panelTituloPrincipal.add(panelTitulo, BorderLayout.WEST);
        add(panelTituloPrincipal, BorderLayout.NORTH);
    }

    private void colocarPanelInformacionPrincipal() {
        panelInformacionPrincipal = new JPanel(new GridLayout(1, 2));
        panelInformacionPrincipal.setBackground(Color.blue);
        add(panelInformacionPrincipal, BorderLayout.CENTER);
    }

    private void colocarPanelReporteFinanza() {
        panelReporteFinanza = new JPanel(new GridLayout(2, 1));
        panelReporteFinanza.setBackground(colorFormulario);

        panelInformacionPrincipal.add(panelReporteFinanza);
    }

    private void colocarPanelGraficas() {
        JPanel panelBorder = new JPanel(new BorderLayout());
        panelGraficas = new JPanel(new GridLayout(4, 1));
        
        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();
        JPanel aux3 = new JPanel();
        JPanel aux4 = new JPanel();
        
        panelBorder.add(aux1, BorderLayout.NORTH);
        panelBorder.add(aux2, BorderLayout.WEST);
        panelBorder.add(panelGraficas, BorderLayout.CENTER);
        panelBorder.add(aux3, BorderLayout.SOUTH);
        panelBorder.add(aux4, BorderLayout.EAST);
        panelBorder.setBackground(colorFondoGrafica);
        panelGraficas.setBackground(colorMenu);
        
        
        
        panelReporteFinanza.add(panelBorder);
    }
    
    private void colocarLogo() {
        JLabel logoVaticano = new JLabel(new ImageIcon("src/mx/unach/imagenes/logo_vati.jpg"));
        JPanel aux = new JPanel(new BorderLayout());
        aux.setBackground(colorMenu);
        aux.add(logoVaticano, BorderLayout.CENTER);
        panelGraficas.add(aux);
    }
    
    private void colocarBotones() {
        JPanel aux1 = new JPanel(new BorderLayout());
        JPanel aux2 = new JPanel(new FlowLayout());
        botonLinkEvangelio = new JButton("Evangelio de Hoy");
        botonLinkEvangelio.setBackground(colorBotonVaticanNews);
        botonLinkEvangelio.setForeground(Color.WHITE);
        aux2.add(botonLinkEvangelio, BorderLayout.WEST);
        aux1.add(aux2);
        aux2.setBackground(colorMenu);
        
        JPanel aux3 = new JPanel(new BorderLayout());
        JPanel aux4 = new JPanel(new FlowLayout());
        botonLinkSanto = new JButton("Santo de Hoy");
        botonLinkSanto.setBackground(colorBotonVaticanNews);
        botonLinkSanto.setForeground(Color.WHITE);
        aux4.add(botonLinkSanto, BorderLayout.WEST);
        aux3.add(aux4);
        aux4.setBackground(colorMenu);
        
        JPanel aux5 = new JPanel(new BorderLayout());
        JPanel aux6 = new JPanel(new FlowLayout());
        botonLinkMisa = new JButton("Misa de Hoy");
        botonLinkMisa.setBackground(colorBotonVaticanNews);
        botonLinkMisa.setForeground(Color.WHITE);
        aux6.add(botonLinkMisa, BorderLayout.WEST);
        aux5.add(aux6);
        aux6.setBackground(colorMenu);
        
        panelGraficas.add(aux1);
        panelGraficas.add(aux3);
        panelGraficas.add(aux5);
        
        botonLinkEvangelio.addActionListener(eventoBotonLinkEvangelio());
        botonLinkSanto.addActionListener(eventoBotonLinkSanto());
        botonLinkMisa.addActionListener(eventoBotonLinkMisa());
        
    }
    
    private ActionListener eventoBotonLinkEvangelio() {
        return (ae) ->
        {
            try {

                Desktop.getDesktop().browse(new URI("https://www.vaticannews.va/es/evangelio-de-hoy.html"));

        } catch (URISyntaxException ex) {

            System.out.println(ex);

        }catch(IOException e){

            System.out.println(e);

        }
        };
    }
    
    private ActionListener eventoBotonLinkSanto() {
        return (ae) ->
        {
            try {

                Desktop.getDesktop().browse(new URI("https://www.vaticannews.va/es/santos.html"));

        } catch (URISyntaxException ex) {

            System.out.println(ex);

        }catch(IOException e){

            System.out.println(e);

        }
        };
    }
    
    private ActionListener eventoBotonLinkMisa() {
        return (ae) ->
        {
            try {

                Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query=misa+de+hoy+vatican+news+espa%C3%B1ol"));

        } catch (URISyntaxException ex) {

            System.out.println(ex);

        }catch(IOException e){

            System.out.println(e);

        }
        };
    }

    private void colocarPanelEtiquetasGraficaOfrenda() {
        panelEtiquetasGraficaOfrenda = new JPanel(new GridLayout(1, 3));
        panelEtiquetasGraficaOfrenda.setBackground(colorFondoGrafica);
        panelGraficas.add(panelEtiquetasGraficaOfrenda);
    }

    private void colocarPanelEtiquetasDatosGrafica() {
        panelEtiquetas = new JPanel(new GridLayout(3, 1));
        panelEtiquetasGraficaOfrenda.add(panelEtiquetas);
    }

    private void colocarEtiquetaPanelDatosGrafica() {
        JPanel panelFLowetiquetaFinanzasReporte = new JPanel(new FlowLayout());
        panelFLowetiquetaFinanzasReporte.setBackground(colorFondoGrafica);
        etiquetaFinanzasReporte = new JLabel("Reporte Finanzas");
        etiquetaFinanzasReporte.setFont(new Font("Tahoma", 1, 15));
        panelFLowetiquetaFinanzasReporte.add(etiquetaFinanzasReporte);
        JPanel panelFlowetiquetaOfrenda = new JPanel(new FlowLayout());
        panelFlowetiquetaOfrenda.setBackground(colorFondoGrafica);
        etiquetaOfrenda = new JLabel("Ofrendas");
        etiquetaOfrenda.setFont(new Font("Tahoma", 1, 15));
        panelFlowetiquetaOfrenda.add(etiquetaOfrenda);
        JPanel panelFlowetiquetaTotalOfrenda = new JPanel(new FlowLayout());
        panelFlowetiquetaTotalOfrenda.setBackground(colorFondoGrafica);
        etiquetaTotalOfrenda = new JLabel("3000  Pesos");
        etiquetaTotalOfrenda.setFont(new Font("Tahoma", 1, 15));
        panelFlowetiquetaTotalOfrenda.add(etiquetaTotalOfrenda);
        panelEtiquetas.add(panelFLowetiquetaFinanzasReporte);
        panelEtiquetas.add(panelFlowetiquetaOfrenda);
        panelEtiquetas.add(panelFlowetiquetaTotalOfrenda);
    }

    private void colocarEtiquetaTotal() {
        etiquetaTotal = new JLabel("Total: 0");
        etiquetaTotal.setFont(new Font("Tahoma", 1, 15));
        JPanel panelFlow = new JPanel(new FlowLayout());
        panelFlow.setBackground(colorFondoGrafica);
        panelEtiquetasGraficaOfrenda.add(etiquetaTotal);
    }

    private void colocarPanelEtiquetasGraficaOfrendaSeminario() {
        panelEtiquetasGraficaOfrendaSeminario = new JPanel(new GridLayout(3, 1));
        panelEtiquetas.add(panelEtiquetasGraficaOfrendaSeminario);
    }

    private void colocarEtiquetaPanelDatosGraficaSeminario() {
        etiquetaOfrendaSeminario = new JLabel("Ofrendas");
        etiquetaOfrendaSeminario.setFont(new Font("Tahoma", 1, 15));
        etiquetaTotalOfrendaSeminario = new JLabel("3000  Pesos");
        etiquetaTotalOfrendaSeminario.setFont(new Font("Tahoma", 1, 15));
        panelEtiquetasGraficaOfrendaSeminario.add(etiquetaOfrendaSeminario);
        panelEtiquetasGraficaOfrendaSeminario.add(etiquetaTotalOfrendaSeminario);
        panelEtiquetasGraficaOfrendaSeminario.setBackground(colorFondoGrafica);
        panelEtiquetasGraficaOfrenda.add(panelEtiquetasGraficaOfrendaSeminario);
    }

    private void colocarPanelGraficasOfrendaSeminario() {
        panelGraficasOfrendaSeminario = new JPanel(new GridLayout(1, 2));
        panelGraficasOfrendaSeminario.setBackground(colorFondoGrafica);
        panelGraficas.add(panelGraficasOfrendaSeminario);
    }

    private void colocarGraficaOferdas() {
        data1 = new DefaultPieDataset();
        data1.setValue("Recolectado", 500);
        data1.setValue("Meta", 500);

        // Creando el Grafico
        chart1 = ChartFactory.createRingChart(
                "",
                data1,
                false,
                false,
                false);
        chart1.getPlot().setBackgroundPaint(colorFondoGrafica);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/mx/unach/imagenes/fondoGrafica.png"));
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*XYPlot plot = (XYPlot) chart1.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart1.setBackgroundImage(img);
        plot.setBackgroundImage(img);
        plot.setForegroundAlpha(0.5f);
        chart1.setBorderVisible(false);*/
        // Mostrar Grafico
        panel1 = new ChartPanel(chart1, false);
        panel1.setRangeZoomable(false);
        panel1.setPreferredSize(new Dimension(100, 100));
        panel1.setBackground(colorFondoGrafica);
        panel1.setForeground(colorFondoGrafica);
        panelGraficasOfrendaSeminario.add(panel1);
        /*frame.pack();
        frame.setVisible(true);*/
    }

    private void colocarGraficaOfrendasSeminario() {
        data2 = new DefaultPieDataset();
        data2.setValue("Recolectado", 500);
        data2.setValue("Meta", 500);

        // Creando el Grafico
        chart2 = ChartFactory.createRingChart(
                "",
                data2,
                false,
                false,
                false);
        chart2.setBorderVisible(false);
        chart2.getPlot().setBackgroundPaint(colorFondoGrafica);
        // Mostrar Grafico
        panel2 = new ChartPanel(chart2, false);
        panel2.setRangeZoomable(false);
        panel2.setPreferredSize(new Dimension(100, 100));
        panelGraficasOfrendaSeminario.add(panel2);
        /*frame.pack();
        frame.setVisible(true);*/
    }

    private void colocarPanelReporteDatos() {
        panelReporteDatos = new JPanel(new GridLayout(3, 1));
        panelReporteDatos.setBackground(colorFormulario);
        panelInformacionPrincipal.add(panelReporteDatos);
    }
    private JPanel panelNumerosSacerdotesFondo;
    private void colocarPanelNumeroSacerdotes() {
        panelNumerosSacerdotesFondo = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.setBackground(Color.yellow);
        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();
        JPanel aux3 = new JPanel();
        JPanel aux4 = new JPanel();
        
        JPanel center = new JPanel(new BorderLayout());
        
        JPanel aux5 = new JPanel();
        JPanel aux6 = new JPanel();
        JPanel aux7 = new JPanel();
        JPanel aux8 = new JPanel();
        
        JPanel center2 = new JPanel(new BorderLayout());
        
        JPanel aux9 = new JPanel();
        JPanel aux10 = new JPanel();
        JPanel aux11 = new JPanel();
        JPanel aux12 = new JPanel();
        
        JPanel center3 = new JPanel(new BorderLayout());
        
        JPanel aux13 = new JPanel();
        JPanel aux14 = new JPanel();
        JPanel aux15 = new JPanel();
        JPanel aux16 = new JPanel();
        
        center = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.add((aux1), BorderLayout.WEST);
        panelNumerosSacerdotesFondo.add((aux2), BorderLayout.NORTH);
        panelNumerosSacerdotesFondo.add(center, BorderLayout.CENTER);
        panelNumerosSacerdotesFondo.add((aux3), BorderLayout.EAST);
        panelNumerosSacerdotesFondo.add((aux4), BorderLayout.SOUTH);
        
        center2 = new JPanel(new BorderLayout());
        center.add((aux5), BorderLayout.WEST);
        center.add((aux6), BorderLayout.NORTH);
        center.add(center2, BorderLayout.CENTER);
        center.add((aux7), BorderLayout.EAST);
        center.add((aux8), BorderLayout.SOUTH);
        
        center3 = new JPanel(new BorderLayout());
        center2.add((aux9), BorderLayout.WEST);
        center2.add((aux10), BorderLayout.NORTH);
        center2.add(center3, BorderLayout.CENTER);
        center2.add((aux11), BorderLayout.EAST);
        center2.add((aux12), BorderLayout.SOUTH);
        
        panelNumerosSacerdotes = new JPanel(new BorderLayout());
        center3.add((aux13), BorderLayout.WEST);
        center3.add((aux14), BorderLayout.NORTH);
        center3.add(panelNumerosSacerdotes, BorderLayout.CENTER);
        center3.add((aux15), BorderLayout.EAST);
        center3.add((aux16), BorderLayout.SOUTH);

        panelNumerosSacerdotes.setBackground(colorBotonesMenu);
        panelReporteDatos.add(panelNumerosSacerdotesFondo);
    }
    
    private JLabel etiquetaNumeroSacerdotes;
    private JLabel etiquetaInfoNumeroSacerdotes;
    
    private void cajaDatosSacerdotes() {
        
        etiquetaNumeroSacerdotes = new JLabel("     Numero de Sacerdotes");
        etiquetaNumeroSacerdotes.setFont(new Font("Open Sans", 3, 14));
        
        etiquetaInfoNumeroSacerdotes = new JLabel("     "+getNumeroSacerdotes());
        etiquetaInfoNumeroSacerdotes.setFont(new Font("Open Sans", 2, 14));
        JLabel img = new JLabel(new ImageIcon("src/mx/unach/imagenes/icon-sacerdote.png"));
        JPanel aux = new JPanel(new BorderLayout());
        JPanel aux1 = new JPanel(new FlowLayout());
        aux1.add(etiquetaInfoNumeroSacerdotes);
        aux1.add(img);
        aux.add(aux1, BorderLayout.WEST);
        aux.setBackground(colorBotonesMenu);
        aux1.setBackground(colorBotonesMenu);
        panelNumerosSacerdotes.add(etiquetaNumeroSacerdotes, BorderLayout.NORTH);
        panelNumerosSacerdotes.add(aux, BorderLayout.CENTER);
    }
    
    private Long getNumeroSacerdotes() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Long> numeroSacerdotes = manager.createNamedQuery("Sacerdote.numeroSacerdotes", Long.class);
        
        //List<Sacerdote> sacerdotes = listaSacerdotes.getResultList();
        return numeroSacerdotes.getSingleResult();
    }
           
    private void colocarPanelNumeroCoros() {
        JPanel panelNumerosSacerdotesFondo = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.setBackground(Color.yellow);
        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();
        JPanel aux3 = new JPanel();
        JPanel aux4 = new JPanel();
        
        JPanel center = new JPanel(new BorderLayout());
        
        JPanel aux5 = new JPanel();
        JPanel aux6 = new JPanel();
        JPanel aux7 = new JPanel();
        JPanel aux8 = new JPanel();
        
        JPanel center2 = new JPanel(new BorderLayout());
        
        JPanel aux9 = new JPanel();
        JPanel aux10 = new JPanel();
        JPanel aux11 = new JPanel();
        JPanel aux12 = new JPanel();
        
        JPanel center3 = new JPanel(new BorderLayout());
        
        JPanel aux13 = new JPanel();
        JPanel aux14 = new JPanel();
        JPanel aux15 = new JPanel();
        JPanel aux16 = new JPanel();
        
        center = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.add((aux1), BorderLayout.WEST);
        panelNumerosSacerdotesFondo.add((aux2), BorderLayout.NORTH);
        panelNumerosSacerdotesFondo.add(center, BorderLayout.CENTER);
        panelNumerosSacerdotesFondo.add((aux3), BorderLayout.EAST);
        panelNumerosSacerdotesFondo.add((aux4), BorderLayout.SOUTH);
        
        center2 = new JPanel(new BorderLayout());
        center.add((aux5), BorderLayout.WEST);
        center.add((aux6), BorderLayout.NORTH);
        center.add(center2, BorderLayout.CENTER);
        center.add((aux7), BorderLayout.EAST);
        center.add((aux8), BorderLayout.SOUTH);
        
        center3 = new JPanel(new BorderLayout());
        center2.add((aux9), BorderLayout.WEST);
        center2.add((aux10), BorderLayout.NORTH);
        center2.add(center3, BorderLayout.CENTER);
        center2.add((aux11), BorderLayout.EAST);
        center2.add((aux12), BorderLayout.SOUTH);

        panelNumerosCoros = new JPanel(new BorderLayout());
        center3.add((aux13), BorderLayout.WEST);
        center3.add((aux14), BorderLayout.NORTH);
        center3.add(panelNumerosCoros, BorderLayout.CENTER);
        center3.add((aux15), BorderLayout.EAST);
        center3.add((aux16), BorderLayout.SOUTH);
        
        panelNumerosCoros.setBackground(colorMenu);
        panelReporteDatos.add(panelNumerosSacerdotesFondo);
    }
    
    private JLabel etiquetaNumeroCoros;
    private JLabel etiquetaInfoNumeroCoros;
    
    private void cajaDatosCoros() {
        
        etiquetaNumeroCoros = new JLabel("     Numero de Coros");
        etiquetaNumeroCoros.setFont(new Font("Open Sans", 3, 14));
        etiquetaInfoNumeroCoros = new JLabel("     "+getNumeroCoros());
        etiquetaInfoNumeroCoros.setFont(new Font("Open Sans", 2, 14));
        JLabel img = new JLabel(new ImageIcon("src/mx/unach/imagenes/coro-r.png"));
        JPanel aux = new JPanel(new BorderLayout());
        JPanel aux1 = new JPanel(new FlowLayout());
        aux1.add(etiquetaInfoNumeroCoros);
        aux1.add(img);
        aux.add(aux1, BorderLayout.WEST);
        aux.setBackground(colorMenu);
        aux1.setBackground(colorMenu);
        
        panelNumerosCoros.add(etiquetaNumeroCoros, BorderLayout.NORTH);
        panelNumerosCoros.add(aux, BorderLayout.CENTER);
    }
    
    private Long getNumeroCoros() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Long> numeroCoros = manager.createNamedQuery("Coro.numeroCoros", Long.class);
        
        //List<Sacerdote> sacerdotes = listaSacerdotes.getResultList();
        return numeroCoros.getSingleResult();
    }
    
    //licencias de software
    private void colocarPanelNumeroServicios() {
        JPanel panelNumerosSacerdotesFondo = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.setBackground(Color.yellow);
        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();
        JPanel aux3 = new JPanel();
        JPanel aux4 = new JPanel();
        
        JPanel center = new JPanel(new BorderLayout());
        
        JPanel aux5 = new JPanel();
        JPanel aux6 = new JPanel();
        JPanel aux7 = new JPanel();
        JPanel aux8 = new JPanel();
        
        JPanel center2 = new JPanel(new BorderLayout());
        
        JPanel aux9 = new JPanel();
        JPanel aux10 = new JPanel();
        JPanel aux11 = new JPanel();
        JPanel aux12 = new JPanel();
        
        JPanel center3 = new JPanel(new BorderLayout());
        
        JPanel aux13 = new JPanel();
        JPanel aux14 = new JPanel();
        JPanel aux15 = new JPanel();
        JPanel aux16 = new JPanel();
        
        center = new JPanel(new BorderLayout());
        panelNumerosSacerdotesFondo.add((aux1), BorderLayout.WEST);
        panelNumerosSacerdotesFondo.add((aux2), BorderLayout.NORTH);
        panelNumerosSacerdotesFondo.add(center, BorderLayout.CENTER);
        panelNumerosSacerdotesFondo.add((aux3), BorderLayout.EAST);
        panelNumerosSacerdotesFondo.add((aux4), BorderLayout.SOUTH);

        center2 = new JPanel(new BorderLayout());
        center.add((aux5), BorderLayout.WEST);
        center.add((aux6), BorderLayout.NORTH);
        center.add(center2, BorderLayout.CENTER);
        center.add((aux7), BorderLayout.EAST);
        center.add((aux8), BorderLayout.SOUTH);
        
        center3 = new JPanel(new BorderLayout());
        center2.add((aux9), BorderLayout.WEST);
        center2.add((aux10), BorderLayout.NORTH);
        center2.add(center3, BorderLayout.CENTER);
        center2.add((aux11), BorderLayout.EAST);
        center2.add((aux12), BorderLayout.SOUTH);
        
        panelNumerosServicios = new JPanel(new BorderLayout());
        center3.add((aux13), BorderLayout.WEST);
        center3.add((aux14), BorderLayout.NORTH);
        center3.add(panelNumerosServicios, BorderLayout.CENTER);
        center3.add((aux15), BorderLayout.EAST);
        center3.add((aux16), BorderLayout.SOUTH);
        
        panelNumerosServicios.setBackground(new Color(253, 203, 110));
        panelReporteDatos.add(panelNumerosSacerdotesFondo);
    }
    
    
    private JLabel etiquetaNumeroServicios;
    private JLabel etiquetaInfoNumeroServicios;
    
    private void cajaDatosServicios() {
        
        etiquetaNumeroServicios = new JLabel("     Numero de Servicios");
        etiquetaNumeroServicios.setFont(new Font("Open Sans", 3, 14));
        etiquetaInfoNumeroServicios = new JLabel("     "+getNumeroServicios());
        etiquetaInfoNumeroServicios.setFont(new Font("Open Sans", 2, 14));
        
        JLabel img = new JLabel(new ImageIcon("src/mx/unach/imagenes/sacerdote-servicio.png"));
        JPanel aux = new JPanel(new BorderLayout());
        JPanel aux1 = new JPanel(new FlowLayout());
        aux1.add(etiquetaInfoNumeroServicios);
        aux1.add(img);
        aux.add(aux1, BorderLayout.WEST);
        aux.setBackground(new Color(253, 203, 110));
        aux1.setBackground(new Color(253, 203, 110));
        panelNumerosServicios.add(etiquetaNumeroServicios, BorderLayout.NORTH);
        panelNumerosServicios.add(aux, BorderLayout.CENTER);
    }
    
    private Long getNumeroServicios() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Long> numeroServicio = manager.createNamedQuery("Servicio.numeroServicios", Long.class);
        
        //List<Sacerdote> sacerdotes = listaSacerdotes.getResultList();
        return numeroServicio.getSingleResult();
    }

    
    private void footer() {
        etiquetaFooter = new JLabel("Diócesis de Tapachula");
        JLabel etiquetaCreditos = new JLabel("By Javier&Rodrigo");
        etiquetaFooter.setFont(new Font("Lato Heavy", 3, 18));
        etiquetaFooter.setForeground(new Color(52, 73, 94));
        etiquetaCreditos.setFont(new Font("Lato Heavy", 3, 13));
        etiquetaCreditos.setForeground(new Color(52, 73, 94));
        panelFooter = new JPanel(new GridLayout(3, 1));
        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();
        JPanel panelGrid1 = new JPanel(new GridLayout(2, 1));
        JPanel panelGrid2 = new JPanel(new GridLayout(2, 1));
        JPanel panelFlow1 = new JPanel(new FlowLayout());
        JPanel panelFlow2 = new JPanel(new FlowLayout());
        panelFlow1.add(etiquetaFooter);
        panelFlow2.add(etiquetaCreditos);
        panelGrid1.add(panelGrid2);
        panelGrid2.add(panelFlow1);
        panelGrid2.add(panelFlow2);
        panelFooter.add(aux1);
        panelFooter.add(aux2);
        panelFooter.add(panelGrid1);
        panelReporteFinanza.add(panelFooter);
    }
}
