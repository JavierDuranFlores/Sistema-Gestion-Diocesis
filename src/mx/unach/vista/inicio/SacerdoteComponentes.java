/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.vista.inicio;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import mx.unach.controlador.SacerdoteControlador;
import mx.unach.repositorio.jpa.Sacerdote;

/**
 *
 * @author javier
 */
public class SacerdoteComponentes extends JPanel {

    private JPanel panelTitulo;
    private JLabel etiquetaTitulo;
    private JLabel logo;
    private JPanel panelDatos;
    private JPanel panelFormulario;
    private JPanel panelTabla;

    private JPanel panelBoton;
    private JPanel panelFormFondo;
    private JPanel panelForm;
    private JPanel panelFormAbajo;

    private JPanel panelNombre;
    private JPanel panelMinisterio;

    private JPanel panelFormSacerdote;
    private JLabel etiquetaFormSacerdote;
    private JTextField campoFormSacerdote;

    private JPanel panelFormFecha;
    private JLabel etiquetaFormFecha;
    private JDateChooser dateChooser;
    //private JTextField campoFormSacerdote;

    private JPanel panelDireccion;

    private JPanel panelFormDireccion;
    private JLabel etiquetaFormDireccion;
    private JTextArea areaFormDireccion;

    private JPanel panelTelefono;
    private JPanel panelCumple;

    private JPanel panelFormTelefono;
    private JLabel etiquetaFormTelefono;
    private JTextField campoFormTelefono;

    private JPanel panelFormTiempoMinisterio;
    private JLabel etiquetaFormTiempoMinisterio;
    private JTextField campoFormTiempoMinisterio;

    private JPanel panelContra;

    private JPanel panelFormContra;
    private JLabel etiquetaFormContra;
    private JTextField campoFormContra;

    private JButton botonAgregar, botonEditar, botonEliminar;

    //Panel titulo sacerdotes
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;

    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"PID", "PNom", "PNac", "PDir", "PTel", "PTM"};

    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Sacerdote> listaSacerdotes;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    private int tamLetraForm = 14;
    private int columnasCampo = 14;
    
    private SacerdoteControlador sacerdoteControlador;

    public SacerdoteComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.red);
        tabla = new JTable();
        tabla.setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        tabla.setModel(dtm);
        colocarColumna();
        colocarFilas();
        sacerdoteControlador = new SacerdoteControlador(this);
        tabla.addMouseListener(sacerdoteControlador);
    }

    public void iniciarComponentes() {
        colocarPanelTitulo();

        colocarPanelDatos();

        colocarPanelFormulario();
        colocarComponentesForm();
        colocarComponentesTitulo();
        colocarComponenteFormSacerdoteYFecha();
        colocarComponenteFormDireccion();
        colocarComponenteFormTelefonoYAMinisterio();
        //colocarComponenteFormContra();

        colocarPanelTabla();
        colocarPanelBotones();

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

    private void colocarPanelDatos() {
        panelDatos = new JPanel(new GridLayout(2, 1));
        add(panelDatos, BorderLayout.CENTER);
    }

    private void colocarPanelFormulario() {
        panelFormulario = new JPanel(new BorderLayout());
        panelDatos.add(panelFormulario);
    }

    private void colocarComponentesForm() {
        panelFormFondo = new JPanel(new GridLayout(3, 1));
        panelTituloSacerdote = new JPanel(new GridLayout(2, 1));
        panelForm = new JPanel(new GridLayout(1, 4));
        panelFormAbajo = new JPanel(new GridLayout(1, 6));
        panelForm.setBackground(colorFormulario);
        panelFormFondo.add(panelTituloSacerdote);
        panelFormFondo.add(panelForm);
        panelFormFondo.add(panelFormAbajo);
        panelFormulario.add(panelFormFondo, BorderLayout.CENTER);
    }

    private void colocarComponentesTitulo() {
        panelEtiquetaTituloSacerdote = new JPanel(new FlowLayout());
        etiquetaTitulo = new JLabel("REGISTRO SACERDOTES");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/icon-sacerdote.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/cruz.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);

        panelTituloSacerdote.add(new JPanel());
        panelTituloSacerdote.add(panelEtiquetaTituloSacerdote);
    }

    private void colocarComponenteFormSacerdoteYFecha() {
        panelNombre = new JPanel(new GridLayout(1, 1));
        panelCumple = new JPanel(new GridLayout(1, 1));

        panelFormSacerdote = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormSacerdote = new JLabel("Nombre Sacerdote");
        etiquetaFormSacerdote.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormSacerdote);
        JPanel panelSacerdote = new JPanel(new FlowLayout());
        campoFormSacerdote = new JTextField(columnasCampo);
        campoFormSacerdote.setPreferredSize(new Dimension(0, 25));
        panelSacerdote.add(campoFormSacerdote);

        panelFormSacerdote.add(panelEtiqueta);
        panelFormSacerdote.add(panelSacerdote);

        panelFormFecha = new JPanel(new GridLayout(2, 1));
        JPanel panelFecha = new JPanel(new FlowLayout());
        etiquetaFormFecha = new JLabel("Cumpleaños");
        etiquetaFormFecha.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelFecha.add(etiquetaFormFecha);
        JPanel panelCalendar = new JPanel(new BorderLayout());
        dateChooser = new JDateChooser(null, null, null,
                new JSpinnerDateEditor());
        dateChooser.setFont(new Font("Times New Roman", 1, 14));
        panelCalendar.add(dateChooser, BorderLayout.CENTER);

        panelFormFecha.add(panelFecha);
        panelFormFecha.add(panelCalendar);

        panelNombre.add(panelFormSacerdote);
        panelCumple.add(panelFormFecha);

        //panelForm.add(new JPanel());
        panelForm.add(panelNombre);
        panelFormAbajo.add(new JPanel());
        panelFormAbajo.add(panelCumple);
        panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormDireccion() {

        panelDireccion = new JPanel(new GridLayout(2, 1));

        panelFormDireccion = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormDireccion = new JLabel("Direccion");
        etiquetaFormDireccion.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormDireccion);
        JPanel panelCampo = new JPanel(new FlowLayout());
        areaFormDireccion = new JTextArea(12, 5);
        panelCampo.add(areaFormDireccion);

        panelFormDireccion.add(panelEtiqueta, BorderLayout.NORTH);
        panelFormDireccion.add(areaFormDireccion, BorderLayout.CENTER);

        panelForm.add(panelFormDireccion);

    }

    private void colocarComponenteFormTelefonoYAMinisterio() {
        panelTelefono = new JPanel(new GridLayout(1, 1));
        panelMinisterio = new JPanel(new GridLayout(1, 1));

        panelFormTelefono = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormTelefono = new JLabel("Telefono");
        etiquetaFormTelefono.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormTelefono);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormTelefono = new JTextField(columnasCampo);
        campoFormTelefono.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormTelefono);

        panelFormTelefono.add(panelEtiqueta);
        panelFormTelefono.add(panelCampo);

        panelFormTiempoMinisterio = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiquetaTiempoMinisterio = new JPanel(new FlowLayout());
        etiquetaFormTiempoMinisterio = new JLabel("Tiempo Ministerio");
        etiquetaFormTiempoMinisterio.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiquetaTiempoMinisterio.add(etiquetaFormTiempoMinisterio);
        JPanel panelCampoTiempoMinisterio = new JPanel(new FlowLayout());
        campoFormTiempoMinisterio = new JTextField(columnasCampo);
        campoFormTiempoMinisterio.setPreferredSize(new Dimension(0, 25));
        panelCampoTiempoMinisterio.add(campoFormTiempoMinisterio);

        panelFormTiempoMinisterio.add(panelEtiquetaTiempoMinisterio);
        panelFormTiempoMinisterio.add(panelCampoTiempoMinisterio);

        panelTelefono.add(panelFormTelefono);
        panelMinisterio.add(panelFormTiempoMinisterio);

        //panelForm.add(new JPanel());
        panelForm.add(panelTelefono);
        panelFormAbajo.add(panelMinisterio);
        panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormContra() {

        panelContra = new JPanel(new GridLayout(1, 1));

        panelFormContra = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormContra = new JLabel("Contraseña");
        etiquetaFormContra.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormContra);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormContra = new JTextField(12);
        campoFormContra.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormContra);

        panelFormContra.add(panelContraFlow);
        panelFormContra.add(panelCampo);

        panelContra.add(panelFormContra);

        panelForm.add(panelContra);

    }

    private void colocarPanelBotones() {
        panelBoton = new JPanel(new FlowLayout());

        botonAgregar = new JButton("Agregar");
        botonAgregar.setBackground(colorFondoBotones);
        botonAgregar.setForeground(colorLetrasBotones);
        botonEditar = new JButton("Editar");
        botonEditar.setBackground(colorFondoBotones);
        botonEditar.setForeground(colorLetrasBotones);
        botonEliminar = new JButton("Eliminar");
        botonEliminar.setBackground(colorFondoBotones);
        botonEliminar.setForeground(colorLetrasBotones);

        panelBoton.add(botonAgregar);
        panelBoton.add(botonEditar);
        panelBoton.add(botonEliminar);
        JPanel panelAux = new JPanel(new BorderLayout());
        panelAux.add(panelBoton, BorderLayout.CENTER);
        panelFormulario.add(panelAux, BorderLayout.SOUTH);
        
        botonAgregar.addActionListener(sacerdoteControlador);
        botonEditar.addActionListener(sacerdoteControlador);
        botonEliminar.addActionListener(sacerdoteControlador);
    }

    private void colocarPanelTabla() {
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.RED);
        panelTabla.add(tabla, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(tabla));
        panelDatos.add(panelTabla);
    }

    private void colocarColumna() {

        for (int i = 0; i < 6; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        manager = factory.createEntityManager();
        listaSacerdotes = manager.createQuery("SELECT s FROM Sacerdote s", Sacerdote.class);
        List<Sacerdote> lista = listaSacerdotes.getResultList();
        Sacerdote[] miarray = new Sacerdote[lista.size()];
        miarray = lista.toArray(miarray);
        Object cliente[] = new Object[9];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Sacerdote i : miarray) {
            cliente[0] = i.getId();
            cliente[1] = i.getNombre();
            cliente[2] = formatter.format(i.getNacimiento());
            cliente[3] = i.getDireccion();
            cliente[4] = i.getTelefono();
            cliente[5] = i.getTiempoministerio();
            dtm.addRow(cliente);
        }

    }

    public void limpiarTabla() {
        int nColumn = dtm.getRowCount() - 1;
        if (nColumn >= 0) {
            for (int i = nColumn; i >= 0; i--) {
                dtm.removeRow(i);
            }
        }

    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public JTextField getCampoFormSacerdote() {
        return campoFormSacerdote;
    }

    public JTextField getCampoFormTelefono() {
        return campoFormTelefono;
    }

    public JTextField getCampoFormContra() {
        return campoFormContra;
    }

    public JTextField getCampoFormTiempoMinisterio() {
        return campoFormTiempoMinisterio;
    }

    public JDateChooser getNacimiento() {
        return dateChooser;
    }

    public JTextArea getAreaFormDireccion() {
        return areaFormDireccion;
    }
    

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JTable getTabla() {
        return tabla;
    }
    
}
