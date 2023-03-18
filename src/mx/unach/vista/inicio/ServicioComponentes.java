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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.unach.controlador.ServicioControlador;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;

/**
 *
 * @author javier
 */
public class ServicioComponentes extends JPanel {

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

    private JPanel panelFormServicio;
    private JLabel etiquetaFormServicio;
    private JTextField campoFormServicio;

    private JPanel panelFormDiaServicio;
    private JLabel etiquetaFormDiaServicio;
    private JDateChooser dateChooserDiaServicio;
    //private JTextField campoFormSacerdote;

    private JPanel panelSacerdoteId;

    private JPanel panelFormSacerdoteId;
    private JLabel etiquetaFormSacerdoteId;
    private JComboBox comboFormSacerdoteId;

    private JPanel panelEscritura;
    private JPanel panelDiaSerivicio;

    private JPanel panelFormEscritura;
    private JLabel etiquetaFormEscritura;
    private JTextField campoFormEscritura;

    private JPanel panelNombreCoro;

    private JPanel panelFormNombreCoro;
    private JLabel etiquetaFormNombreCoro;
    private JTextField campoFormNombreCoro;

    private JPanel panelFormTiempoServicio;
    private JLabel etiquetaFormTiempoServicio;
    private JComboBox comboFormTiempoServicio;

    private JPanel panelCoroId;

    private JPanel panelFormCoroId;
    private JLabel etiquetaFormCoroId;
    private JComboBox comboFormCoroId;

    private JPanel panelNombreSacerdote;

    private JPanel panelFormNombreSacerdote;
    private JLabel etiquetaFormNombreSacerdote;
    private JTextField campoFormNombreSacerdote;
    
    private JPanel panelMisaId;
    
    private JPanel panelFormMisaId;
    private JLabel etiquetaFormMisaId;
    private JComboBox comboFormMisaId;

    private JPanel panelTelefono;

    private JPanel panelFormTelefono;
    private JLabel etiquetaFormTelefono;
    private JTextField campoFormTelefono;
    
    private JButton botonAgregar, botonEditar, botonEliminar, botonRegresar;

    //Panel titulo sacerdotes
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;

    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"SID", "SNom", "STel", "SSI", "SSN", "SCI", "SCN", "SL", "SMI"};

    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Servicio> listaServicios;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    private int tamLetraForm = 14;
    private int columnasCampo = 14;
    
    private ServicioControlador sc;
    
    private InicioManejador inicioManejador;

    public ServicioComponentes() {
        setLayout(new BorderLayout());
        tabla = new JTable();
        tabla.setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        tabla.setModel(dtm);
        colocarColumna();
        colocarFilas();
        sc = new ServicioControlador(this);
        tabla.addMouseListener(sc);
        
    }

    public void iniciarComponentes() {
        colocarPanelTitulo();

        colocarPanelDatos();

        colocarPanelFormulario();
        colocarComponentesForm();
        colocarComponentesTitulo();
        colocarComponenteFormSacerdoteYFecha();
        colocarComponentesFormNombreSacerdote();
        colocarComponenteFormSacerdoteId();
        colocarComponenteFormMisaId();
        colocarComponenteFormTelefonoYAMinisterio();
        colocarComponentesFormTelefono();
        colocarComponentesFormNombreCoro();
        
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
        panelDatos.add(panelFormulario);
    }

    private void colocarComponentesForm() {
        panelFormFondo = new JPanel(new GridLayout(3, 1));
        panelTituloSacerdote = new JPanel(new GridLayout(2, 1));
        panelForm = new JPanel(new GridLayout(1, 5));
        panelFormAbajo = new JPanel(new GridLayout(1, 5));
        panelFormFondo.add(panelTituloSacerdote);
        panelFormFondo.add(panelForm);
        panelFormFondo.add(panelFormAbajo);
        panelFormulario.add(panelFormFondo, BorderLayout.CENTER);
    }

    private void colocarComponentesTitulo() {
        panelEtiquetaTituloSacerdote = new JPanel(new FlowLayout());
        etiquetaTitulo = new JLabel("REGISTRO SERVICIOS");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/sacerdote-servicio.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/servicios.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);

        panelTituloSacerdote.add(new JPanel());
        panelTituloSacerdote.add(panelEtiquetaTituloSacerdote);
    }

    private void colocarComponenteFormSacerdoteYFecha() {
        panelNombre = new JPanel(new GridLayout(1, 1));
        panelDiaSerivicio = new JPanel(new GridLayout(1, 1));

        panelFormServicio = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormServicio = new JLabel("Nombre Servicio");
        etiquetaFormServicio.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormServicio);
        JPanel panelServicio = new JPanel(new FlowLayout());
        campoFormServicio = new JTextField(columnasCampo);
        campoFormServicio.setPreferredSize(new Dimension(0, 25));
        panelServicio.add(campoFormServicio);

        panelFormServicio.add(panelEtiqueta);
        panelFormServicio.add(panelServicio);

        panelFormDiaServicio = new JPanel(new GridLayout(2, 1));
        JPanel panelFecha = new JPanel(new FlowLayout());
        etiquetaFormDiaServicio = new JLabel("Fecha del Servicio");
        etiquetaFormDiaServicio.setFont(new Font("Times New Roman", 1, 13));
        panelFecha.add(etiquetaFormDiaServicio);
        JPanel panelCalendar = new JPanel(new BorderLayout());
        dateChooserDiaServicio = new JDateChooser(null, null, null,
                new JSpinnerDateEditor());
        dateChooserDiaServicio.setFont(new Font("Times New Roman", 1, 14));
        panelCalendar.add(dateChooserDiaServicio, BorderLayout.CENTER);

        panelFormDiaServicio.add(panelFecha);
        panelFormDiaServicio.add(panelCalendar);

        panelNombre.add(panelFormServicio);
        panelDiaSerivicio.add(panelFormDiaServicio);

        //panelForm.add(new JPanel());
        panelForm.add(panelNombre);
        panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormSacerdoteId() {

        panelSacerdoteId = new JPanel(new GridLayout(2, 1));

        panelFormSacerdoteId = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormSacerdoteId = new JLabel("Sacerdote Id");
        etiquetaFormSacerdoteId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormSacerdoteId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormSacerdoteId = new JComboBox(obtenerSacerdotesId());
        panelCampo.add(comboFormSacerdoteId);

        panelFormSacerdoteId.add(panelEtiqueta, BorderLayout.NORTH);
        panelFormSacerdoteId.add(panelCampo, BorderLayout.CENTER);

        panelForm.add(panelFormSacerdoteId);
        comboFormSacerdoteId.addItemListener(sc);

    }

    private String[] obtenerSacerdotesId() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Sacerdote> listaSacerdotes = manager.createNamedQuery("Sacerdote.findAll", Sacerdote.class);
        
        List<Sacerdote> sacerdotes = listaSacerdotes.getResultList();
        String ids[] = new String [sacerdotes.size()+1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < sacerdotes.size(); i++) {
            ids[j] = String.valueOf(sacerdotes.get(i).getId());
            j++;
        }
        return ids;
    }

    private void colocarComponentesFormNombreSacerdote() {
        panelNombreSacerdote = new JPanel(new GridLayout(2, 1));

        panelFormNombreSacerdote = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormNombreSacerdote = new JLabel("Nombre Sacerdote");
        etiquetaFormNombreSacerdote.setFont(new Font("Times New Roman", 1, 13));
        panelEtiqueta.add(etiquetaFormNombreSacerdote);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormNombreSacerdote = new JTextField(12);
        campoFormNombreSacerdote.setEditable(false);
        panelCampo.add(campoFormNombreSacerdote);

        panelNombreSacerdote.add(panelEtiqueta, BorderLayout.NORTH);
        panelNombreSacerdote.add(panelCampo, BorderLayout.CENTER);

        panelFormAbajo.add(panelNombreSacerdote);
    }

    private void colocarComponentesFormNombreCoro() {
        panelNombreCoro = new JPanel(new GridLayout(2, 1));

        panelFormNombreSacerdote = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormNombreCoro = new JLabel("Nombre Coro");
        etiquetaFormNombreCoro.setFont(new Font("Times New Roman", 1, 13));
        panelEtiqueta.add(etiquetaFormNombreCoro);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormNombreCoro = new JTextField(12);
        campoFormNombreCoro.setEditable(false);
        panelCampo.add(campoFormNombreCoro);

        panelNombreCoro.add(panelEtiqueta, BorderLayout.NORTH);
        panelNombreCoro.add(panelCampo, BorderLayout.CENTER);

        panelFormAbajo.add(panelNombreCoro);
        panelFormAbajo.add(new JPanel());
    }

    String tiempoServicios[] = {"8AM-10AM", "10:15AM-12PM", "6PM-8:30PM"};
    
    private void colocarComponenteFormTelefonoYAMinisterio() {
        panelEscritura = new JPanel(new GridLayout(1, 1));
        panelMinisterio = new JPanel(new GridLayout(1, 1));

        panelFormEscritura = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormEscritura = new JLabel("Escritura");
        etiquetaFormEscritura.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormEscritura);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormEscritura = new JTextField(columnasCampo);
        campoFormEscritura.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormEscritura);

        panelFormEscritura.add(panelEtiqueta);
        panelFormEscritura.add(panelCampo);

        panelFormTiempoServicio = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiquetaTiempoMinisterio = new JPanel(new FlowLayout());
        etiquetaFormTiempoServicio = new JLabel("Duracion");
        etiquetaFormTiempoServicio.setFont(new Font("Times New Roman", 1, 13));
        panelEtiquetaTiempoMinisterio.add(etiquetaFormTiempoServicio);
        JPanel panelCampoTiempoMinisterio = new JPanel(new FlowLayout());
        comboFormTiempoServicio = new JComboBox(tiempoServicios);
        
        panelCampoTiempoMinisterio.add(comboFormTiempoServicio);

        panelFormTiempoServicio.add(panelEtiquetaTiempoMinisterio);
        panelFormTiempoServicio.add(panelCampoTiempoMinisterio);

        panelEscritura.add(panelFormEscritura);
        panelMinisterio.add(panelFormTiempoServicio);

        //panelForm.add(new JPanel());
        panelForm.add(panelEscritura);
        
        comboFormTiempoServicio.addItemListener(sc);
    }

    private void colocarComponenteFormContra() {

        panelCoroId = new JPanel(new GridLayout(1, 1));

        panelFormCoroId = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormCoroId = new JLabel("Coro Id");
        etiquetaFormCoroId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormCoroId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormCoroId = new JComboBox(obtenerCoroId());
        panelCampo.add(comboFormCoroId);

        panelFormCoroId.add(panelContraFlow);
        panelFormCoroId.add(panelCampo);

        panelCoroId.add(panelFormCoroId);

        panelForm.add(panelCoroId);
        
        comboFormCoroId.addItemListener(sc);

    }
    
    private String[] obtenerCoroId() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Coro> listaCoros = manager.createNamedQuery("Coro.findAll", Coro.class);
        
        List<Coro> coros = listaCoros.getResultList();
        String ids[] = new String [coros.size()+1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < coros.size(); i++) {
            ids[j] = String.valueOf(coros.get(i).getId());
            j++;
        }
        return ids;
    }
    
    private void colocarComponenteFormMisaId() {

        panelMisaId = new JPanel(new GridLayout(1, 1));

        panelFormMisaId = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormMisaId = new JLabel("Misa Id");
        etiquetaFormMisaId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormMisaId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormMisaId = new JComboBox(obtenerMisaId());
        panelCampo.add(comboFormMisaId);

        panelFormMisaId.add(panelContraFlow);
        panelFormMisaId.add(panelCampo);

        panelMisaId.add(panelFormMisaId);

        panelForm.add(panelMisaId);
        
        comboFormMisaId.addItemListener(sc);

    }
    
    private String[] obtenerMisaId() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Misa> listaMisas= manager.createNamedQuery("Misa.findAll", Misa.class);
        
        List<Misa> misas = listaMisas.getResultList();
        String ids[] = new String [misas.size()+1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < misas.size(); i++) {
            ids[j] = String.valueOf(misas.get(i).getId());
            j++;
        }
        return ids;
    }
    
    private void colocarComponentesFormTelefono() {
        panelTelefono = new JPanel(new GridLayout(2, 1));

        panelFormTelefono = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormTelefono = new JLabel("Telefono");
        etiquetaFormTelefono.setFont(new Font("Times New Roman", 1, 13));
        panelEtiqueta.add(etiquetaFormTelefono);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormTelefono = new JTextField(12);
        panelCampo.add(campoFormTelefono);

        panelTelefono.add(panelEtiqueta, BorderLayout.NORTH);
        panelTelefono.add(panelCampo, BorderLayout.CENTER);

        panelFormAbajo.add(panelTelefono);
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
        
        botonRegresar = new JButton("Regresar");
        botonRegresar.setBackground(colorFondoBotones);
        botonRegresar.setForeground(colorLetrasBotones);
        
        panelBoton.add(botonAgregar);
        panelBoton.add(botonEditar);
        panelBoton.add(botonEliminar);
        //panelBoton.add(botonRegresar);
        JPanel panelAux = new JPanel(new BorderLayout());
        panelAux.add(panelBoton, BorderLayout.CENTER);
        panelFormulario.add(panelAux, BorderLayout.SOUTH);
        
        botonAgregar.addActionListener(sc);
        botonEditar.addActionListener(sc);
        botonEliminar.addActionListener(sc);
        //botonRegresar.addActionListener(sc);
    }

    private void colocarPanelTabla() {
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.RED);
        panelTabla.add(tabla, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(tabla));
        panelDatos.add(panelTabla);
    }

    private void colocarColumna() {

        for (int i = 0; i < 9; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        manager = factory.createEntityManager();
        listaServicios = manager.createQuery("SELECT s FROM Servicio s", Servicio.class);
        List<Servicio> lista = listaServicios.getResultList();
        Servicio[] miarray = new Servicio[lista.size()];
        miarray = lista.toArray(miarray);
        Object servicio[] = new Object[9];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Servicio i : miarray) {
            servicio[0] = i.getId();
            servicio[1] = i.getNombre();
            servicio[2] = i.getTelefono();
            servicio[3] = i.getSacerdoteid().getId();
            servicio[4] = i.getSacerdotenombre();
            servicio[5] = i.getCoroid().getId();
            servicio[6] = i.getCoronombre();
            servicio[7] = i.getLectura();
            servicio[8] = i.getMisaid().getId();
            dtm.addRow(servicio);
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

    public JTextField getCampoFormServicio() {
        return campoFormServicio;
    }

    public JTextField getCampoFormEscritura() {
        return campoFormEscritura;
    }

    public JTextField getCampoFormNombreSacerdote() {
        return campoFormNombreSacerdote;
    }

    public JTextField getCampoFormNombreCoro() {
        return campoFormNombreCoro;
    }

    public JComboBox getComboFormSacerdoteId() {
        return comboFormSacerdoteId;
    }

    public JComboBox getComboFormCoroId() {
        return comboFormCoroId;
    }

    public JComboBox getComboFormTiempoServicio() {
        return comboFormTiempoServicio;
    }

    public JDateChooser getDiaServicio() {
        return dateChooserDiaServicio;
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

    public JButton getBotonRegresar() {
        return botonRegresar;
    }

    public void setInicioManejador(InicioManejador inicioManejador) {
        this.inicioManejador = inicioManejador;
    }

    public InicioManejador getInicioManejador() {
        return inicioManejador;
    }

    public JComboBox getComboFormMisaId() {
        return comboFormMisaId;
    }

    public JTextField getCampoFormTelefono() {
        return campoFormTelefono;
    }
    
}