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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mx.unach.controlador.FinanzaControlador;
import mx.unach.controlador.FinanzaControlador;

/**
 *
 * @author javier
 */
public class FinanzaComponentes extends JPanel{
    
    private JPanel panelPrincipal;
    
    private JPanel panelServicios,
                   panelOfrendas;
    
    private JLabel imagenServicio,
                   imagenOfrenda;
    
    private JLabel etiquetaTitulo;
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;
    
    private JPanel panelBotonesEImagenes;
    
    private JPanel panelTitulo;
    private JLabel logo;
    
    private JButton botonOfrendas, botonServicios;
    
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    
    private FinanzaControlador finanzaControlador;
    private InicioManejador inicioManejador;
    public FinanzaComponentes () {
        setBackground(Color.yellow);
        //this.inicioManejador = inicioManejador;
    }
    
    
    /*public static FinanzaComponentesI getInstacia(InicioManejador inicioManejador) {
        if (finanzaComponentes == null)
            finanzaComponentes = new FinanzaComponentesI(inicioManejador);
        
        return finanzaComponentes;
    }*/
    
    public void colocarComponetes() {
        setLayout(new BorderLayout());
        iniciarPanelPrincipal();
        colocarComponentesTitulo();
        iniciarPanelBotonesEImagenes();
        
        colocarPanelServicios();
        colocarPanelOfrendas();
        
        colocarPanelTitulo();
    }
    private JPanel panelPrincipalFlow ;
    private void iniciarPanelPrincipal() {
        panelPrincipal = new JPanel(new BorderLayout());
        
        panelPrincipalFlow = new JPanel(new FlowLayout());
        add(panelPrincipal, BorderLayout.CENTER);
    }
    
    private void iniciarPanelBotonesEImagenes() {
        panelBotonesEImagenes = new JPanel(new GridLayout(1,2));
        panelPrincipal.add(panelBotonesEImagenes, BorderLayout.CENTER);
    }
    
    
    private void colocarPanelServicios() {
        panelServicios = new JPanel(new FlowLayout());
        imagenServicio = new JLabel(new ImageIcon("src/mx/unach/imagenes/churchF.png"));
        botonServicios = new JButton("  SERVICIOS  ");
        botonServicios.setFont(new Font("Arial", 1, 20));
        botonServicios.setBackground(colorFondoBotones);
        botonServicios.setForeground(colorLetrasBotones);
        panelServicios.add(imagenServicio);
        panelServicios.add(botonServicios);
        panelBotonesEImagenes.add(panelServicios);
        botonServicios.addActionListener(finanzaControlador);
    }

    private void colocarPanelOfrendas() {
        panelOfrendas = new JPanel(new FlowLayout());
        imagenOfrenda = new JLabel(new ImageIcon("src/mx/unach/imagenes/ofrendaF.png"));
        botonOfrendas = new JButton("  OFRENDAS  ");
        botonOfrendas.setFont(new Font("Arial", 1, 20));
        botonOfrendas.setBackground(colorFondoBotones);
        botonOfrendas.setForeground(colorLetrasBotones);
        panelOfrendas.add(imagenOfrenda);
        panelOfrendas.add(botonOfrendas);
        panelBotonesEImagenes.add(panelOfrendas);
        botonOfrendas.addActionListener(finanzaControlador);
    }
    
    private void colocarComponentesTitulo() {
        panelEtiquetaTituloSacerdote = new JPanel(new FlowLayout());
        etiquetaTitulo = new JLabel("REGISTRO FINANZAS");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/ofrenda.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/nun.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);
        panelPrincipal.add(panelEtiquetaTituloSacerdote, BorderLayout.NORTH);
    }
    
    private void colocarPanelTitulo() {
        panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(245, 222, 179));
        panelTitulo.setPreferredSize(new Dimension(100, 40));
        JPanel panelTituloFlow = new JPanel(new FlowLayout());
        etiquetaTitulo = new JLabel("Diócesis de Tapachula");
        logo = new JLabel(new ImageIcon("src/mx/unach/imagenes/church.png"));
        panelTituloFlow.setBackground(new Color(245, 222, 179));
        panelTituloFlow.add(logo);
        panelTituloFlow.add(etiquetaTitulo);
        panelTitulo.add(panelTituloFlow, BorderLayout.WEST);
        add(panelTitulo, BorderLayout.NORTH);

    }

    public JButton getBotonOfrendas() {
        return botonOfrendas;
    }

    public JButton getBotonServicios() {
        return botonServicios;
    }

    public InicioManejador getInicioManejador() {
        return inicioManejador;
    }

}
