/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.vista.inicio;

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
import javax.swing.table.DefaultTableModel;
import mx.unach.controlador.CoroControlador;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Sacerdote;

/**
 *
 * @author javier
 */
public class CoroComponentes extends JPanel {

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

    private JPanel panelFormCoro;
    private JLabel etiquetaFormCoro;
    private JTextField campoFormCoro;

    private JPanel panelFormFechaCreacion;
    private JLabel etiquetaFormFechaCreacion;
    private JDateChooser dateChooser;
    //private JTextField campoFormSacerdote;

    private JPanel panelNMiembros;

    private JPanel panelFormNMiembros;
    private JLabel etiquetaFormNMiembros;
    private JTextField campoFormNMiembros;

    private JPanel panelCondutorCoro;
    private JPanel panelCumple;

    private JPanel panelFormConductorCoro;
    private JLabel etiquetaFormConductorCoro;
    private JTextField campoFormConductorCoro;

    private JPanel panelPianistaPrincipal;

    private JPanel panelFormPianistaPrincipal;
    private JLabel etiquetaFormPianistaPrincipal;
    private JTextField campoFormPianistaPrincipal;

    private JButton botonAgregar, botonEditar, botonEliminar;

    //Panel titulo sacerdotes
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;

    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"CID", "CNom", "CMie", "CCon", "CPP", "CC"};

    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Coro> listaCoros;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    private int tamLetraForm = 14;
    private int columnasCampo = 14;

    private CoroControlador coroControlador;
    
    public CoroComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.red);
        tabla = new JTable();
        tabla.setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        tabla.setModel(dtm);
        colocarColumna();
        colocarFilas();
        coroControlador = new CoroControlador(this);
        tabla.addMouseListener(coroControlador);
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
        colocarComponenteFormContra();

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
        panelFormulario.setBackground(Color.BLUE);
        panelDatos.add(panelFormulario);
    }

    private void colocarComponentesForm() {
        panelFormFondo = new JPanel(new GridLayout(3, 1));
        panelTituloSacerdote = new JPanel(new GridLayout(2, 1));
        panelForm = new JPanel(new GridLayout(1, 4));
        panelFormAbajo = new JPanel(new GridLayout(1, 6));
        panelFormFondo.add(panelTituloSacerdote);
        panelFormFondo.add(panelForm);
        panelFormFondo.add(panelFormAbajo);
        panelFormulario.add(panelFormFondo, BorderLayout.CENTER);
    }

    private void colocarComponentesTitulo() {
        panelEtiquetaTituloSacerdote = new JPanel(new FlowLayout());
        etiquetaTitulo = new JLabel("REGISTRO COROS");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/piano.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/coro-r.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);

        panelTituloSacerdote.add(new JPanel());
        panelTituloSacerdote.add(panelEtiquetaTituloSacerdote);
    }

    private void colocarComponenteFormSacerdoteYFecha() {
        panelNombre = new JPanel(new GridLayout(1, 1));
        panelCumple = new JPanel(new GridLayout(1, 1));

        panelFormCoro = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormCoro = new JLabel("Nombre Coro");
        etiquetaFormCoro.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormCoro);
        JPanel panelSacerdote = new JPanel(new FlowLayout());
        campoFormCoro = new JTextField(columnasCampo);
        campoFormCoro.setPreferredSize(new Dimension(0, 25));
        panelSacerdote.add(campoFormCoro);

        panelFormCoro.add(panelEtiqueta);
        panelFormCoro.add(panelSacerdote);

        panelFormFechaCreacion = new JPanel(new GridLayout(2, 1));
        JPanel panelFecha = new JPanel(new FlowLayout());
        etiquetaFormFechaCreacion = new JLabel("Creado");
        etiquetaFormFechaCreacion.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelFecha.add(etiquetaFormFechaCreacion);
        JPanel panelCalendar = new JPanel(new BorderLayout());
        dateChooser = new JDateChooser(null, null, null,
                new JSpinnerDateEditor());
        dateChooser.setFont(new Font("Times New Roman", 1, 14));
        panelCalendar.add(dateChooser, BorderLayout.CENTER);

        panelFormFechaCreacion.add(panelFecha);
        panelFormFechaCreacion.add(panelCalendar);

        panelNombre.add(panelFormCoro);
        panelCumple.add(panelFormFechaCreacion);

        //panelForm.add(new JPanel());
        panelForm.add(panelNombre);
        panelFormAbajo.add(new JPanel());
        panelFormAbajo.add(new JPanel());
        panelFormAbajo.add(panelCumple);
        panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormDireccion() {

        panelNMiembros = new JPanel(new GridLayout(2, 1));

        panelFormNMiembros = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormNMiembros = new JLabel("Numero de miembros");
        etiquetaFormNMiembros.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormNMiembros);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormNMiembros = new JTextField(columnasCampo);
        panelCampo.add(campoFormNMiembros);

        panelFormNMiembros.add(panelEtiqueta);
        panelFormNMiembros.add(panelCampo);

        panelForm.add(panelFormNMiembros);

    }

    private void colocarComponenteFormTelefonoYAMinisterio() {
        panelCondutorCoro = new JPanel(new GridLayout(1, 1));
        panelMinisterio = new JPanel(new GridLayout(1, 1));

        panelFormConductorCoro = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormConductorCoro = new JLabel("Condutor del Coro");
        etiquetaFormConductorCoro.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormConductorCoro);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormConductorCoro = new JTextField(columnasCampo);
        campoFormConductorCoro.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormConductorCoro);

        panelFormConductorCoro.add(panelEtiqueta);
        panelFormConductorCoro.add(panelCampo);

        panelCondutorCoro.add(panelFormConductorCoro);

        //panelForm.add(new JPanel());
        //panelFormAbajo.add(new JPanel());

        panelForm.add(panelCondutorCoro);
        //panelFormAbajo.add(panelMinisterio);
        panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormContra() {

        panelPianistaPrincipal = new JPanel(new GridLayout(1, 1));

        panelFormPianistaPrincipal = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormPianistaPrincipal = new JLabel("Pianista Principal");
        etiquetaFormPianistaPrincipal.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormPianistaPrincipal);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormPianistaPrincipal = new JTextField(12);
        campoFormPianistaPrincipal.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormPianistaPrincipal);

        panelFormPianistaPrincipal.add(panelContraFlow);
        panelFormPianistaPrincipal.add(panelCampo);

        panelPianistaPrincipal.add(panelFormPianistaPrincipal);

        panelForm.add(panelPianistaPrincipal);

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
        
        botonAgregar.addActionListener(coroControlador);
        botonEditar.addActionListener(coroControlador);
        botonEliminar.addActionListener(coroControlador);
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
        listaCoros = manager.createQuery("SELECT c FROM Coro c", Coro.class);
        List<Coro> lista = listaCoros.getResultList();
        Coro[] miarray = new Coro[lista.size()];
        miarray = lista.toArray(miarray);
        Object coro[] = new Object[6];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Coro i : miarray) {
            coro[0] = i.getId();
            coro[1] = i.getNombre();
            coro[2] = i.getMiembros();
            coro[3] = i.getConductor();
            coro[4] = i.getPianistaprincipal();
            coro[5] = formatter.format(i.getCreado());
            dtm.addRow(coro);
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

    public JTextField getCampoFormCoro() {
        return campoFormCoro;
    }

    public JTextField getCampoFormConductorCoro() {
        return campoFormConductorCoro;
    }

    public JTextField getCampoFormNMiembros() {
        return campoFormNMiembros;
    }
    
    public JTextField getCampoFormPianistaPrincipal() {
        return campoFormPianistaPrincipal;
    }

    public JDateChooser getCreado() {
        return dateChooser;
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

}
