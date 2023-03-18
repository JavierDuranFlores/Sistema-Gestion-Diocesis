/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.vista.login;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import mx.unach.controlador.LoginControlador;

/**
 *
 * @author javier
 */
public class Login {
 
    private JFrame ventanaPrincipal;
    private JPanel panelPrincipal;
    
    private JPanel panelLogo,
                   panelDatos;
    
    private JLabel logoIglesia;
    
    private JPanel panelTituloSalir;
    
    private JPanel panelTituloIglesia;
    private JLabel tituloIglesia;
    
    private JPanel panelBotonCerrarVentanaLogin;
    private JButton botonCerrarVentanaLogin;
    
    private JPanel panelLogoDatos;
    private JLabel logoIglesiaDatos;
    
    /* Componentes para el formulario de inicio */
    
    private GridBagLayout gbl;
    private GridBagConstraints constraints;
    
    private JPanel panelEtiquetaNombreUsuarioFlow;
    private JPanel panelEtiquetaNombreUsuario;
    private JLabel etiquetaNombreUsuario;
    private JPanel panelCampoNombreUsuario;
    private JTextField campoNombreUsuario;
    
    private JPanel panelEtiquetaContra;
    private JLabel etiquetaContra;
    private JPanel panelCampoContra;
    private JPasswordField campoContra;
    
    private JButton login;
    private JPanel panelLogin;
    
    /* Controlador Login */
    private LoginControlador loginControlador;
    
    private Color colorPanelLogo = new Color(27, 20, 100);
    private Color colorPanelDatos = new Color(247, 241, 227);
    
    public void iniciarComponentes() {
        
        colocarVenatanaPrincipal();
        colocarPanelPrincipal();
        
        componentesPanelesDatos();
        
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
        
    }
    
    private void colocarVenatanaPrincipal() {
        ventanaPrincipal = new JFrame();
        ventanaPrincipal.setSize(new Dimension(450, 280));
        ventanaPrincipal.setUndecorated(true);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
    }
 
    private void colocarPanelPrincipal() {
        panelPrincipal = new JPanel(new BorderLayout());
        ventanaPrincipal.add(panelPrincipal);
    }
    
    private void componentesPanelesDatos() {
        colocarPanelLogo();
        colocarImagenPanelLogo();
        colocarPanelDatos();
        inicarPanelTitulo();
        colocarTituloIglesia();
        colocarBotonCerrarVentanaLogin();
        colocarLogoDatos();
        
        gbl = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        colocarFormularioEtiquetaNombreUsuario();
        colocarFormularioEtiquetaContra();
        
        loginControlador = new LoginControlador(this);
        
        colocarBotonIngresar();
        
    }
    
    private void colocarPanelLogo() {
        panelLogo = new JPanel(new BorderLayout());
        panelLogo.setBackground(colorPanelLogo);
        panelLogo.setPreferredSize(new Dimension(140, 280));
        panelPrincipal.add(panelLogo, BorderLayout.WEST);
    }
    
    private void colocarImagenPanelLogo() {
        logoIglesia = new JLabel(new ImageIcon("src/mx/unach/imagenes/iglesia.png"));
        panelLogo.add(logoIglesia, BorderLayout.CENTER);
    }
    
    private void colocarPanelDatos() {
        panelDatos = new JPanel(new GridLayout(5, 1));
        panelDatos.setBackground(colorPanelDatos);
        panelDatos.setPreferredSize(new Dimension(310, 280));
        panelPrincipal.add(panelDatos, BorderLayout.CENTER);
    }
    
    private void inicarPanelTitulo() {
        panelTituloSalir = new JPanel(new BorderLayout());
        panelTituloSalir.setBackground(colorPanelDatos);
        panelDatos.add(panelTituloSalir);
    }
    
    private void colocarTituloIglesia() {
        panelTituloIglesia = new JPanel(new FlowLayout());
        panelTituloIglesia.setBackground(colorPanelDatos);
        panelTituloIglesia.setPreferredSize(new Dimension(0, 0));
        tituloIglesia = new JLabel("   Diócesis de Tapachula");
        tituloIglesia.setFont(new Font("Lato Heavy", 3, 22));
        panelTituloIglesia.add(tituloIglesia);
        panelTituloSalir.add(panelTituloIglesia, BorderLayout.CENTER);
        
    }
    
    private void colocarBotonCerrarVentanaLogin() {
        panelBotonCerrarVentanaLogin = new JPanel(new BorderLayout());
        panelBotonCerrarVentanaLogin.setBackground(colorPanelDatos);
        panelBotonCerrarVentanaLogin.setPreferredSize(new Dimension(25, 0));
        botonCerrarVentanaLogin = new JButton();
        botonCerrarVentanaLogin.setBackground(colorPanelDatos);
        ImageIcon iconoCerrar = new ImageIcon("src/mx/unach/imagenes/salir.png");
        botonCerrarVentanaLogin.setIcon(iconoCerrar);
        botonCerrarVentanaLogin.addActionListener(eventoBotonCerrar());
        panelBotonCerrarVentanaLogin.add(botonCerrarVentanaLogin, BorderLayout.NORTH);
        panelTituloSalir.add(panelBotonCerrarVentanaLogin, BorderLayout.EAST);
    }
    
    private ActionListener eventoBotonCerrar() {
        return (e) -> {
            System.exit(0);
        };
    }
    
    private void colocarLogoDatos() {
        panelLogoDatos = new JPanel(new BorderLayout());
        panelLogoDatos.setPreferredSize(new Dimension(1, 0));
        logoIglesiaDatos = new JLabel(new ImageIcon("src/mx/unach/imagenes/iglesia-48.png"));
        panelLogoDatos.add(logoIglesiaDatos, BorderLayout.NORTH);
        panelDatos.add(panelLogoDatos);
    }
    
    private GridBagConstraints colocarPanelesLugar(int x, int y, int w, int h) {

        constraints.gridx = x; // El área de texto empieza en la columna cero.
        constraints.gridy = y; // El área de texto empieza en la fila cero
        constraints.gridwidth = w; // El área de texto ocupa dos columnas.
        constraints.gridheight = h; // El área de texto ocupa 2 filas.
        constraints.insets.bottom = 1; // El area de texto esta separado en el bottom por 1 fila

        return this.constraints;

    }
    
    private void colocarFormularioEtiquetaNombreUsuario() {
        panelEtiquetaNombreUsuario = new JPanel(new BorderLayout());
        JPanel panelGrid = new JPanel(new GridLayout(2, 1));
        etiquetaNombreUsuario = new JLabel("      Nombre Usuario");
        JPanel panelBordeTitulo = new JPanel(new BorderLayout());
        campoNombreUsuario = new JTextField(15);
        JPanel panelBordeCampo = new JPanel(new BorderLayout());
        panelBordeTitulo.add(etiquetaNombreUsuario, BorderLayout.WEST);
        panelBordeCampo.add(campoNombreUsuario, BorderLayout.WEST);
        panelGrid.add(panelBordeTitulo);
        JPanel panelFLowCampo = new JPanel(new FlowLayout());
        panelFLowCampo.add(panelBordeCampo);
        panelGrid.add(panelFLowCampo);
        panelEtiquetaNombreUsuario.add(panelGrid, BorderLayout.CENTER);
        panelDatos.add(panelEtiquetaNombreUsuario);
    }
    
    private void colocarFormularioEtiquetaContra() {
        panelEtiquetaContra = new JPanel(new BorderLayout());
        JPanel panelGrid = new JPanel(new GridLayout(2, 1));
        etiquetaContra = new JLabel("      Contraseña");
        JPanel panelBordeTitulo = new JPanel(new BorderLayout());
        campoContra = new JPasswordField(15);
        JPanel panelBordeCampo = new JPanel(new BorderLayout());
        panelBordeTitulo.add(etiquetaContra, BorderLayout.WEST);
        panelBordeCampo.add(campoContra, BorderLayout.WEST);
        panelGrid.add(panelBordeTitulo);
        JPanel panelFLowCampo = new JPanel(new FlowLayout());
        panelFLowCampo.add(panelBordeCampo);
        panelGrid.add(panelFLowCampo);
        panelEtiquetaContra.add(panelGrid, BorderLayout.CENTER);
        panelDatos.add(panelEtiquetaContra);
    }
    
   private void colocarBotonIngresar() {
       panelLogin = new JPanel(new FlowLayout());
       login = new JButton("Ingresar");
       login.setBackground(colorPanelLogo);
       login.setForeground(colorPanelDatos);
       panelLogin.add(login);
       panelDatos.add(panelLogin);
       
       login.addActionListener(loginControlador);
   }

    public JTextField getCampoNombreUsuario() {
        return campoNombreUsuario;
    }

    public JTextField getCampoContra() {
        return campoContra;
    }

    public JFrame getVentanaPrincipal() {
        return ventanaPrincipal;
    }
   
}
