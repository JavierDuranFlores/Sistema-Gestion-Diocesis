/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.vista.inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import mx.unach.controlador.InicioControlador;

/**
 *
 * @author javier
 */
public class InicioManejador {

    private JFrame ventanaPrincipal;
    private JPanel panelPrincipal;

    private JPanel panelMenu;
    private JPanel panelDatosFondo;
    private JPanel panelDatosMedio;
    private JPanel panelDatosArriba;

    private JPanel panelLogo;
    private JLabel etiquetaLogo;
    private JLabel etiquetaTitilo;
    private JPanel panelOpcionesPrincipal,
            panelOpcionesSecundario;

    private JMenuBar barraOpciones;
    private JMenu menuInicio,
            menuSacerdote,
            menuCoro,
            menuMisa,
            menuServicio,
            menuFinanzas,
            menuInfo,
            menuSalir;
    private JPanel panelInicio,
            panelSacerdote,
            panelCoro,
            panelMisa,
            panelSerivicio,
            panelFinanza,
            panelInfo,
            panelSalir;
    private JButton botonInicio,
            botonSacerdote,
            botonCoro,
            botonMisa,
            botonServicio,
            botonFinanza,
            botonInfo,
            botonSalir;
    private JLabel etiquetaImagenIncio,
            etiquetaImagenSacerdote,
            etiquetaImagenCoro,
            etiquetaImagenMisas,
            etiquetaImagenServicio,
            etiquetaImagenFinanzas,
            etiquetaImagenInfo,
            etiquetaImagenSalir;
    private Color colorFormulario = new Color(241, 242, 246);

    //private Color colorMenu = new Color(27, 20, 100);(115,158,178) rgb(225, 177, 44), rgb(232, 65, 24), rgb(140, 122, 230), rgb(72, 126, 176)
    private Color colorMenu = new Color(72, 126, 176);

    private Color colorBotonesMenu = new Color(245, 222, 179);
    private Color colorLetrasBotones = new Color(0, 0, 0);

    int ancho = Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto = Toolkit.getDefaultToolkit().getScreenSize().height;

    private InicioControlador inicioControlador;
    
    private JPanel panelInformacion;
    private JLabel etiquetaInfo1, etiquetaInfo2;
    private JLabel etiquetaInfo0;

    private InicioManejador() {
        inicioControlador = new InicioControlador(this);
        
        
        colocarVenatanPrincipal();

        colocarPanelPrincipal();

        colocarPanelMenu();
        colocarPanelDatos();
        colocarPanelDatosArriba();

        colocarPanelLogo();

        colocarPanelOpciones();
        colocarBotonInicio();
        colocarBotonSacerdote();
        colocarBotonCoro();
        colocarBotonMisa();
        colocarBotonFinanza();
        colocarBotonSalir();
        //colocarBarrarMenu();
        //colocarMenu();

        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
    }
    
    private static InicioManejador inicioManejador;
    
    public static InicioManejador instacia() {
        if (inicioManejador == null) 
            inicioManejador = new InicioManejador();
        return inicioManejador;
    }

    public void iniciarComponentes() {
        colocarVenatanPrincipal();

        colocarPanelPrincipal();

        colocarPanelMenu();
        colocarPanelDatos();
        colocarPanelDatosArriba();

        colocarPanelLogo();

        colocarPanelOpciones();
        colocarBotonInicio();
        colocarBotonSacerdote();
        colocarBotonCoro();
        colocarBotonMisa();
        //colocarBotonServicio();
        colocarBotonFinanza();
        //colocarBotonServicio();
        colocarBotonSalir();
        
        colocarBarrarMenu();
        colocarMenu();
        
        colocarInfoDev();

        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);

    }

    private void colocarVenatanPrincipal() {
        ventanaPrincipal = new JFrame();
        ventanaPrincipal.setUndecorated(true);
        ventanaPrincipal.setSize((ancho - ((19 * ancho) / 100)), (alto - ((19 * alto) / 100)));
        ventanaPrincipal.setLocationRelativeTo(null);
    }

    private void colocarPanelPrincipal() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.yellow);
        ventanaPrincipal.add(panelPrincipal);
    }

    private void colocarPanelMenu() {
        panelMenu = new JPanel(new BorderLayout());
        panelMenu.setBackground(colorMenu);//1000 - 100
        panelMenu.setPreferredSize(new Dimension((12 * ancho) / 100, (alto - ((10 * alto) / 100))));
        panelPrincipal.add(panelMenu, BorderLayout.WEST);
    }

    private void colocarPanelDatos() {
        panelDatosFondo = new JPanel(new FlowLayout());
        panelDatosFondo.setPreferredSize(new Dimension((12 * ancho) / 100, (alto - ((10 * alto) / 100))));
        panelDatosFondo.setBackground(colorMenu);
        panelDatosMedio = new JPanel(new FlowLayout());
        panelDatosMedio.setBackground(colorMenu);
        panelDatosMedio.setPreferredSize(new Dimension((66 * ancho) / 100, (80 * alto) / 100));
        panelDatosFondo.add(panelDatosMedio);
        panelPrincipal.add(panelDatosFondo, BorderLayout.CENTER);
    }

    private void colocarPanelDatosArriba() {

        panelDatosArriba = new JPanel(new BorderLayout());
        panelDatosArriba.setBackground(colorFormulario);
        panelDatosArriba.setPreferredSize(new Dimension((65 * ancho) / 100, (78 * alto) / 100));
        JLabel img = new JLabel(new ImageIcon("src/mx/unach/imagenes/iglesia.jpg"));
        panelDatosArriba.add(img);
        panelDatosMedio.add(panelDatosArriba);
    }

    public void agregarPanel(JPanel panel) {
        panelDatosArriba.add(panel, BorderLayout.CENTER);
    }

    private void colocarPanelLogo() {
        panelLogo = new JPanel(new BorderLayout());
        panelLogo.setBackground(colorMenu);
        etiquetaLogo = new JLabel(new ImageIcon("src/mx/unach/imagenes/iglesia-48.png"));
        etiquetaTitilo = new JLabel("    Diócesis de Tapachula");
        etiquetaTitilo.setFont(new Font("Lato Heavy", 3, 13));
        etiquetaTitilo.setForeground(colorBotonesMenu);
        panelLogo.add(etiquetaLogo, BorderLayout.CENTER);
        panelLogo.add(etiquetaTitilo, BorderLayout.SOUTH);
        panelMenu.add(panelLogo, BorderLayout.NORTH);
    }

    private void colocarPanelOpciones() {
        panelOpcionesPrincipal = new JPanel(new GridLayout(11, 1));
        panelOpcionesPrincipal.setBackground(colorMenu);
        panelMenu.add(panelOpcionesPrincipal, BorderLayout.CENTER);
    }
    
    private void colocarPanelInfo() {
        panelInformacion = new JPanel(new FlowLayout());
        panelInformacion.setBackground(Color.yellow);
        panelMenu.add(panelInformacion, BorderLayout.SOUTH);
    }

    private void colocarBarrarMenu() {
        barraOpciones = new JMenuBar();
        barraOpciones.setLayout(new GridLayout(0, 1));
        barraOpciones.setBackground(colorMenu);
        barraOpciones.setPreferredSize(new Dimension(100/*(ancho - ((10 * ancho) / 100))*/, 100));
        panelMenu.add(barraOpciones, BorderLayout.CENTER);
    }

    private void colocarBotonInicio() {
        JPanel borde = new JPanel(new BorderLayout());
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelInicio = new JPanel(new BorderLayout());
        panelInicio.setBackground(colorMenu);
        botonInicio = new JButton("INICIO         ");
        botonInicio.setBackground(colorBotonesMenu);
        botonInicio.setForeground(colorLetrasBotones);
        botonInicio.setFont(new Font("Arial", 1, 13));
        etiquetaImagenIncio = new JLabel(new ImageIcon("src/mx/unach/imagenes/casa.png"));

        panelInicio.add(etiquetaImagenIncio, BorderLayout.WEST);
        panelInicio.add(botonInicio, BorderLayout.CENTER);
        JPanel aux1 = new JPanel();
        aux1.setBackground(colorMenu);
        JPanel aux2 = new JPanel();
        aux2.setBackground(colorMenu);
        panelOpcionesPrincipal.add(aux1);
        //panelOpcionesPrincipal.add(aux2);
        aux.add(panelInicio);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);
        eventosBotones(botonInicio);
    }

    private void colocarBotonSacerdote() {
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelSacerdote = new JPanel(new BorderLayout());
        panelSacerdote.setBackground(colorMenu);
        botonSacerdote = new JButton("SACERDOTE");
        botonSacerdote.setBackground(colorBotonesMenu);
        botonSacerdote.setForeground(colorLetrasBotones);
        botonSacerdote.setFont(new Font("Arial", 1, 13));
        etiquetaImagenSacerdote = new JLabel(new ImageIcon("src/mx/unach/imagenes/user-black.png"));
        panelSacerdote.add(etiquetaImagenSacerdote, BorderLayout.WEST);
        panelSacerdote.add(botonSacerdote, BorderLayout.CENTER);
        aux.add(panelSacerdote);
        panelOpcionesPrincipal.add(aux);
        eventosBotones(botonSacerdote);
    }

    private void colocarBotonCoro() {
        JPanel borde = new JPanel(new BorderLayout());
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelCoro = new JPanel(new BorderLayout());
        panelCoro.setBackground(colorMenu);
        botonCoro = new JButton("CORO         ");
        botonCoro.setBackground(colorBotonesMenu);
        botonCoro.setForeground(colorLetrasBotones);
        botonCoro.setFont(new Font("Arial", 1, 13));
        etiquetaImagenCoro = new JLabel(new ImageIcon("src/mx/unach/imagenes/coro.png"));
        panelCoro.add(etiquetaImagenCoro, BorderLayout.WEST);
        panelCoro.add(botonCoro, BorderLayout.CENTER);
        aux.add(panelCoro);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);
        eventosBotones(botonCoro);
    }

    private void colocarBotonMisa() {
        JPanel borde = new JPanel(new BorderLayout());
        borde.setBackground(colorMenu);
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelMisa = new JPanel(new BorderLayout());
        panelMisa.setBackground(colorMenu);
        botonMisa = new JButton("MISAS        ");
        botonMisa.setBackground(colorBotonesMenu);
        botonMisa.setForeground(colorLetrasBotones);
        botonMisa.setFont(new Font("Arial", 1, 13));
        etiquetaImagenMisas = new JLabel(new ImageIcon("src/mx/unach/imagenes/misa.png"));
        panelMisa.add(etiquetaImagenMisas, BorderLayout.WEST);
        panelMisa.add(botonMisa, BorderLayout.CENTER);
        aux.add(panelMisa);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);
        eventosBotones(botonMisa);
        colocarBotonServicio();
        
        /*JPanel borde2 = new JPanel(new BorderLayout());
        borde.setBackground(Color.cyan);
        JPanel aux2 = new JPanel(new FlowLayout());
        aux2.setBackground(Color.cyan);
        panelSerivicio = new JPanel(new BorderLayout());
        panelSerivicio.setBackground(colorMenu);
        botonServicio = new JButton("SERVICIO   ");
        botonServicio.setBackground(colorBotonesMenu);
        botonServicio.setForeground(colorLetrasBotones);
        botonServicio.setFont(new Font("Arial", 1, 13));
        etiquetaImagenServicio = new JLabel(new ImageIcon("src/mx/unach/imagenes/servicios.png"));
        panelSerivicio.add(etiquetaImagenServicio, BorderLayout.WEST);
        panelSerivicio.add(botonServicio, BorderLayout.CENTER);
        aux2.add(panelSerivicio);
        borde2.add(aux2, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde2);
        eventosBotones(botonServicio);*/
        //colocarBotonServicio();        
    }
    
    private void colocarBotonServicio() {
        JPanel borde = new JPanel(new BorderLayout());
        borde.setBackground(colorMenu);
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelSerivicio = new JPanel(new BorderLayout());
        panelSerivicio.setBackground(colorMenu);
        botonServicio = new JButton("SERVICIO   ");
        botonServicio.setBackground(colorBotonesMenu);
        botonServicio.setForeground(colorLetrasBotones);
        botonServicio.setFont(new Font("Arial", 1, 13));
        etiquetaImagenServicio = new JLabel(new ImageIcon("src/mx/unach/imagenes/servicios.png"));
        panelSerivicio.add(etiquetaImagenServicio, BorderLayout.WEST);
        panelSerivicio.add(botonServicio, BorderLayout.CENTER);
        aux.add(panelSerivicio);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);
        eventosBotones(botonServicio);
    }

    private void colocarBotonFinanza() {
        JPanel borde = new JPanel(new BorderLayout());
        borde.setBackground(colorMenu);
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelFinanza = new JPanel(new BorderLayout());
        panelFinanza.setBackground(colorMenu);
        botonFinanza = new JButton("FINANZAS  ");
        botonFinanza.setBackground(colorBotonesMenu);
        botonFinanza.setForeground(colorLetrasBotones);
        botonFinanza.setFont(new Font("Arial", 1, 13));
        etiquetaImagenFinanzas = new JLabel(new ImageIcon("src/mx/unach/imagenes/finanzas.png"));
        panelFinanza.add(etiquetaImagenFinanzas, BorderLayout.WEST);
        panelFinanza.add(botonFinanza, BorderLayout.CENTER);
        aux.add(panelFinanza);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);
        eventosBotones(botonFinanza);
    }

    private void colocarBotonSalir() {
        
        JPanel borde = new JPanel(new BorderLayout());
        borde.setBackground(colorMenu);
        JPanel aux = new JPanel(new FlowLayout());
        aux.setBackground(colorMenu);
        panelSalir = new JPanel(new BorderLayout());
        panelSalir.setBackground(colorMenu);
        botonSalir = new JButton("SALIR         ");
        botonSalir.setBackground(colorBotonesMenu);
        botonSalir.setForeground(colorLetrasBotones);
        botonSalir.setFont(new Font("Arial", 1, 13));
        etiquetaImagenSalir = new JLabel(new ImageIcon("src/mx/unach/imagenes/logout.png"));
        panelSalir.add(etiquetaImagenSalir, BorderLayout.WEST);
        panelSalir.add(botonSalir, BorderLayout.CENTER);
        aux.add(panelSalir);
        borde.add(aux, BorderLayout.WEST);
        panelOpcionesPrincipal.add(borde);

        eventosBotones(botonSalir);
        
        colocarInfoDev();
    }

    private void colocarMenu() {
        menuInicio = new JMenu("INICIO");
        JPanel panelFlow = new JPanel();
        menuInicio.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuInicio);

        menuSacerdote = new JMenu("SACERDOTES");
        menuSacerdote.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuSacerdote);

        menuCoro = new JMenu("CORO");
        menuCoro.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuCoro);

        menuMisa = new JMenu("SERVICIOS");
        menuMisa.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuMisa);

        menuFinanzas = new JMenu("FINANZAS");
        menuFinanzas.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuFinanzas);

        menuSalir = new JMenu("SALIR");
        menuSalir.setIcon(new ImageIcon("src/mx/unach/imagenes/casa.png"));
        barraOpciones.add(menuSalir);
    }
    
        private void colocarInfoDev() {
            etiquetaInfo0 = new JLabel("                   Developers");
            etiquetaInfo1 = new JLabel("        Jose Rodrigo Solorzano");
            etiquetaInfo2 = new JLabel("            Javier Duran Flores");

            etiquetaInfo0.setFont(new Font("Arial", 3, 10));
            etiquetaInfo0.setBackground(Color.BLACK);
            etiquetaInfo0.setForeground(Color.BLACK);
            etiquetaInfo1.setFont(new Font("Arial", 3, 10));
            etiquetaInfo1.setBackground(Color.BLACK);
            etiquetaInfo1.setForeground(Color.BLACK);
            etiquetaInfo2.setFont(new Font("Arial", 3, 10));
            etiquetaInfo2.setForeground(Color.BLACK);
            panelOpcionesPrincipal.add(etiquetaInfo0);
            panelOpcionesPrincipal.add(etiquetaInfo1);
            panelOpcionesPrincipal.add(etiquetaInfo2);
    }
    
    public void addContenedor(JPanel panel) {
        if (panelDatosArriba.getComponentCount() > 0) {
            panelDatosArriba.removeAll();
        }
        panelDatosArriba.add(panel);
        SwingUtilities.updateComponentTreeUI(ventanaPrincipal);
    }

    private void eventosBotones(JButton boton) {
        boton.addActionListener(inicioControlador);
    }

    public JButton getBotonInicio() {
        return botonInicio;
    }

    public JButton getBotonSacerdote() {
        return botonSacerdote;
    }

    public JButton getBotonCoro() {
        return botonCoro;
    }

    public JButton getBotonMisa() {
        return botonMisa;
    }

    public JButton getBotonFinanza() {
        return botonFinanza;
    }

    public JButton getBotonSalir() {
        return botonSalir;
    }

    public JPanel getPanelDatosArriba() {
        return panelDatosArriba;
    }

    public JFrame getVentanaPrincipal() {
        return ventanaPrincipal;
    }

    public JButton getBotonServicio() {
        return botonServicio;
    }

    

    
    
}
