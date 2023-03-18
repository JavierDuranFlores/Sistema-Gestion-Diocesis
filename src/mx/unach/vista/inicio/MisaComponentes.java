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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.unach.controlador.MisaControlador;
import mx.unach.controlador.ServicioControlador;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;

/**
 *
 * @author javier
 */
public class MisaComponentes extends JPanel {

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

    private JPanel panelFormDiaMisa;
    private JLabel etiquetaFormDiaMisa;
    private JDateChooser dateChooserDiaMisa;
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
    
    private JButton botonAgregar, botonEditar, botonEliminar;
    
    private JPanel panelServicioId;
    
    /*private JPanel panelFormServicioId;
    private JLabel etiquetaFormServicioId;
    private JComboBox comboFormServicioId;*/
    
    private JPanel panelFormServicioId;
    private JLabel etiquetaFormServicioId;
    private JButton botonFormServicioId;
    
    //Panel titulo sacerdotes
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;

    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"MID", "MDia", "MHr", "MSI", "MCI"};

    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Misa> listaMisas;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    private int tamLetraForm = 14;
    private int columnasCampo = 14;
    
    private MisaControlador mc;
    private InicioManejador inicioManejador;

    public MisaComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.red);
        tabla = new JTable();
        tabla.setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        tabla.setModel(dtm);
        colocarColumna();
        colocarFilas();
        this.inicioManejador = inicioManejador;
        mc = new MisaControlador(this);
        tabla.addMouseListener(mc);
        //iniciarComponentes();
    }
    
    private static MisaComponentes misaComponentes;
    
    public static MisaComponentes instacia (InicioManejador inicioManejador) {
        if (misaComponentes == null)
            misaComponentes = new MisaComponentes();
            
        return misaComponentes;
    }

    public void iniciarComponentes() {
        colocarPanelTitulo();

        colocarPanelDatos();

        colocarPanelFormulario();
        colocarComponentesForm();
        colocarComponentesTitulo();
        colocarComponenteFormFechaMisa();
        //colocarComponentesFormNombreSacerdote();
        //colocarComponenteFormServicioId();
        colocarComponenteFormSacerdoteId();
        colocarComponenteFormTelefonoYAMinisterio();
        //colocarComponentesFormNombreCoro();
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
        etiquetaTitulo = new JLabel("REGISTRO MISAS");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/sacerdote-servicio.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/servicios.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);

        panelTituloSacerdote.add(new JPanel());
        panelTituloSacerdote.add(panelEtiquetaTituloSacerdote);
    }

    private void colocarComponenteFormFechaMisa() {
        panelDiaSerivicio = new JPanel(new GridLayout(1, 1));

        panelFormDiaMisa = new JPanel(new GridLayout(2, 1));
        JPanel panelFecha = new JPanel(new FlowLayout());
        etiquetaFormDiaMisa = new JLabel("Fecha de la Misa");
        etiquetaFormDiaMisa.setFont(new Font("Times New Roman", 1, 13));
        panelFecha.add(etiquetaFormDiaMisa);
        JPanel panelCalendar = new JPanel(new BorderLayout());
        dateChooserDiaMisa = new JDateChooser(null, null, null,
                new JSpinnerDateEditor());
        dateChooserDiaMisa.setFont(new Font("Times New Roman", 1, 14));
        panelCalendar.add(dateChooserDiaMisa, BorderLayout.CENTER);

        panelFormDiaMisa.add(panelFecha);
        panelFormDiaMisa.add(panelCalendar);

        panelDiaSerivicio.add(panelFormDiaMisa);

        //panelForm.add(new JPanel());
        panelFormAbajo.add(new JPanel());
        panelFormAbajo.add(panelDiaSerivicio);

    }

    private void colocarComponenteFormSacerdoteId() {

        panelSacerdoteId = new JPanel(new GridLayout(2, 1));

        panelFormSacerdoteId = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormSacerdoteId = new JLabel("Sacerdote");
        etiquetaFormSacerdoteId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormSacerdoteId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormSacerdoteId = new JComboBox(obtenerSacerdotesNombre());
        panelCampo.add(comboFormSacerdoteId);

        panelFormSacerdoteId.add(panelEtiqueta, BorderLayout.NORTH);
        panelFormSacerdoteId.add(panelCampo, BorderLayout.CENTER);

        panelForm.add(panelFormSacerdoteId);
        comboFormSacerdoteId.addItemListener(mc);

    }
    
    

    private String[] obtenerSacerdotesNombre() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Sacerdote> listaSacerdotes = manager.createNamedQuery("Sacerdote.findAll", Sacerdote.class);
        
        List<Sacerdote> sacerdotes = listaSacerdotes.getResultList();
        
        String ids[] = new String [sacerdotes.size()+1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < sacerdotes.size(); i++) {
            ids[j] = String.valueOf(sacerdotes.get(i).getNombre());
            j++;
        }
        return ids;
    }
    
    /*private void colocarComponenteFormServicioId() {

        panelServicioId = new JPanel(new GridLayout(2, 1));

        panelFormServicioId = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormServicioId = new JLabel("Servicio Id");
        etiquetaFormServicioId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormServicioId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormServicioId = new JComboBox(obtenerServicioId());
        panelCampo.add(comboFormServicioId);

        panelFormServicioId.add(panelEtiqueta, BorderLayout.NORTH);
        panelFormServicioId.add(panelCampo, BorderLayout.CENTER);

        panelFormAbajo.add(panelFormServicioId);
        comboFormServicioId.addItemListener(mc);

    }*/
    
    private void colocarComponenteFormServicioId() {

        panelServicioId = new JPanel(new GridLayout(2, 1));

        panelFormServicioId = new JPanel(new BorderLayout());
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormServicioId = new JLabel("Servicio");
        etiquetaFormServicioId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormServicioId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        botonFormServicioId = new JButton("Agregar");
        panelCampo.add(botonFormServicioId);

        panelFormServicioId.add(panelEtiqueta, BorderLayout.NORTH);
        panelFormServicioId.add(panelCampo, BorderLayout.CENTER);

        panelFormAbajo.add(panelFormServicioId);
        
        botonFormServicioId.addActionListener(mc);

    }
    
    private String[] obtenerServicioId() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();
        
        TypedQuery<Servicio> listaServicios = manager.createNamedQuery("Servicio.findAll", Servicio.class);
        
        List<Servicio> servicios = listaServicios.getResultList();
        String ids[] = new String [servicios.size()+1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < servicios.size(); i++) {
            ids[j] = String.valueOf(servicios.get(i).getId());
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

    String[] horaMisa = {"8AM-10AM", "10:15AM-12PM", "6PM-8:30PM"};
    
    private void colocarComponenteFormTelefonoYAMinisterio() {
        panelEscritura = new JPanel(new GridLayout(1, 1));
        panelMinisterio = new JPanel(new GridLayout(1, 1));

        

        panelFormTiempoServicio = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiquetaTiempoMinisterio = new JPanel(new FlowLayout());
        etiquetaFormTiempoServicio = new JLabel("Hora");
        etiquetaFormTiempoServicio.setFont(new Font("Times New Roman", 1, 13));
        panelEtiquetaTiempoMinisterio.add(etiquetaFormTiempoServicio);
        JPanel panelCampoTiempoMinisterio = new JPanel(new FlowLayout());
        comboFormTiempoServicio = new JComboBox(horaMisa);
        
        panelCampoTiempoMinisterio.add(comboFormTiempoServicio);

        panelFormTiempoServicio.add(panelEtiquetaTiempoMinisterio);
        panelFormTiempoServicio.add(panelCampoTiempoMinisterio);

        panelMinisterio.add(panelFormTiempoServicio);

        //panelForm.add(new JPanel());
        panelFormAbajo.add(panelMinisterio);
        panelFormAbajo.add(new JPanel());
        
        comboFormTiempoServicio.addItemListener(mc);
    }

    private void colocarComponenteFormContra() {

        panelCoroId = new JPanel(new GridLayout(1, 1));

        panelFormCoroId = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormCoroId = new JLabel("Coro");
        etiquetaFormCoroId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormCoroId);
        JPanel panelCampo = new JPanel(new FlowLayout());
        comboFormCoroId = new JComboBox(obtenerCoroId());
        panelCampo.add(comboFormCoroId);

        panelFormCoroId.add(panelContraFlow);
        panelFormCoroId.add(panelCampo);

        panelCoroId.add(panelFormCoroId);

        panelForm.add(panelCoroId);
        
        comboFormCoroId.addItemListener(mc);

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
            ids[j] = String.valueOf(coros.get(i).getNombre());
            j++;
        }
        return ids;
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
        
        botonAgregar.addActionListener(mc);
        botonEditar.addActionListener(mc);
        botonEliminar.addActionListener(mc);
    }

    private void colocarPanelTabla() {
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.RED);
        panelTabla.add(tabla, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(tabla));
        panelDatos.add(panelTabla);
    }

    private void colocarColumna() {

        for (int i = 0; i < 5; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        manager = factory.createEntityManager();
        listaMisas = manager.createQuery("SELECT m FROM Misa m", Misa.class);
        List<Misa> lista = listaMisas.getResultList();
        Misa[] miarray = new Misa[lista.size()];
        miarray = lista.toArray(miarray);
        Object misa[] = new Object[5];
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (Misa i : miarray) {
            misa[0] = i.getId();
            misa[1] = formatter.format(i.getDia());
            misa[2] = i.getHora();
            misa[3] = i.getSacerdoteid().getId();
            misa[4] = i.getCoroid().getId();
            dtm.addRow(misa);
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
        return dateChooserDiaMisa;
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

    public JButton getBotonFormServicioId() {
        return botonFormServicioId;
    }
    
}