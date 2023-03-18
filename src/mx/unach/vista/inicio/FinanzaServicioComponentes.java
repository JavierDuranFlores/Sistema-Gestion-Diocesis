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
import mx.unach.controlador.FinanzaControlador;
import mx.unach.repositorio.jpa.Finanza;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;

/**
 *
 * @author javier
 */
public class FinanzaServicioComponentes extends JPanel {

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

    private JPanel panelServicioId;
    private JPanel panelMinisterio;

    private JPanel panelFormServicioId;
    private JLabel etiquetaFormServicioId;
    private JComboBox comboFormServicioId;

    private JPanel panelFormFechaCreacion;
    private JLabel etiquetaFormFechaCreacion;
    private JDateChooser dateChooser;
    //private JTextField campoFormSacerdote;

    private JPanel panelMontoOfrendas;

    private JPanel panelFormMontoOfrendas;
    private JLabel etiquetaFormMontoOfrendas;
    private JTextField campoFormMontoOfrendas;

    private JPanel panelMontoRecolecta;
    private JPanel panelCumple;

    private JPanel panelFormMontoRecolecta;
    private JLabel etiquetaFormMontoRecolecta;
    private JTextField campoFormMontoRecolecta;

    private JPanel panelNombreServicio;

    private JPanel panelFormNombreServicio;
    private JLabel etiquetaFormNombreServicio;
    private JTextField campoFormNombreServicio;

    private JButton botonAgregar, botonEditar, botonEliminar, botonRegresar;

    //Panel titulo sacerdotes
    JPanel panelTituloSacerdote;
    JPanel panelEtiquetaTituloSacerdote;
    JLabel etiquetaTituloSacerdote,
            etiquetaImagenSacerdote1,
            etiquetaImagenSacerdote2;

    private JTable tabla;
    private DefaultTableModel dtm;
    String columnas[] = {"FID", "FSI", "FD", "FSN"};

    //Conexion a bd
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Finanza> listaFinanzas;

    private Color colorFormulario = new Color(241, 242, 246);
    private Color colorFondoBotones = new Color(6, 82, 221);
    private Color colorLetrasBotones = new Color(245, 246, 250);
    private int tamLetraForm = 14;
    private int columnasCampo = 14;

    private FinanzaControlador finanzaControlador;

    public FinanzaServicioComponentes() {
        setLayout(new BorderLayout());
        setBackground(Color.red);
        tabla = new JTable();
        tabla.setDefaultEditor(Object.class, null);
        dtm = new DefaultTableModel();
        tabla.setModel(dtm);
        colocarColumna();
        colocarFilas();
        this.finanzaControlador = new FinanzaControlador(this);
        tabla.addMouseListener(finanzaControlador);
    }
    
    private static FinanzaComponentes finanzaControladorServicio;
    
    /*public static FinanzaComponentes getInstance(FinanzaComponentesI finanzaComponentes) {
        if (finanzaControladorServicio == null) {
            
            finanzaControladorServicio = new FinanzaComponentes(finanzaComponentes);
        }
        return finanzaControladorServicio;
    }*/

    public void iniciarComponentes() {
        colocarPanelTitulo();

        colocarPanelDatos();

        colocarPanelFormulario();
        colocarComponentesForm();
        colocarComponentesTitulo();
        colocarComponenteFormSacerdoteYFecha();
        colocarComponenteFormMontoOfrendas();
        //colocarComponenteFormRecolectaYAMinisterio();
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
        etiquetaTitulo = new JLabel("REGISTRO FINANZAS");
        etiquetaTitulo.setFont(new Font("Times New Roman", 1, 20));
        etiquetaImagenSacerdote1 = new JLabel(new ImageIcon("src/mx/unach/imagenes/ofrenda.png"));
        etiquetaImagenSacerdote2 = new JLabel(new ImageIcon("src/mx/unach/imagenes/nun.png"));
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote1);
        panelEtiquetaTituloSacerdote.add(etiquetaTitulo);
        panelEtiquetaTituloSacerdote.add(etiquetaImagenSacerdote2);

        panelTituloSacerdote.add(new JPanel());
        panelTituloSacerdote.add(panelEtiquetaTituloSacerdote);
    }

    private void colocarComponenteFormSacerdoteYFecha() {
        panelServicioId = new JPanel(new GridLayout(1, 1));

        panelFormServicioId = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormServicioId = new JLabel("Servicio Id");
        etiquetaFormServicioId.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormServicioId);
        JPanel panelSacerdote = new JPanel(new FlowLayout());
        comboFormServicioId = new JComboBox(obtenerServiciosId());
        panelSacerdote.add(comboFormServicioId);

        panelFormServicioId.add(panelEtiqueta);
        panelFormServicioId.add(panelSacerdote);

        panelServicioId.add(panelFormServicioId);

        panelForm.add(new JPanel());
        panelForm.add(panelServicioId);

        comboFormServicioId.addItemListener(finanzaControlador);

    }

    private String[] obtenerServiciosId() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();

        TypedQuery<Servicio> listaServicios = manager.createNamedQuery("Servicio.findAll", Servicio.class);

        List<Servicio> servicio = listaServicios.getResultList();
        servicio.forEach(e -> System.out.println(e.getId()));
        String ids[] = new String[servicio.size() + 1];
        ids[0] = " ";
        int j = 1;
        for (int i = 0; i < servicio.size(); i++) {
            ids[j] = String.valueOf(servicio.get(i).getId());
            j++;
        }
        return ids;
    }

    private void colocarComponenteFormMontoOfrendas() {

        panelMontoOfrendas = new JPanel(new GridLayout(2, 1));

        panelFormMontoOfrendas = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormMontoOfrendas = new JLabel("Monto de Ofrendas");
        etiquetaFormMontoOfrendas.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormMontoOfrendas);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormMontoOfrendas = new JTextField(columnasCampo);
        panelCampo.add(campoFormMontoOfrendas);

        panelFormMontoOfrendas.add(panelEtiqueta);
        panelFormMontoOfrendas.add(panelCampo);

        panelForm.add(panelFormMontoOfrendas);

        panelForm.add(new JPanel());

    }

    private void colocarComponenteFormRecolectaYAMinisterio() {
        panelMontoRecolecta = new JPanel(new GridLayout(1, 1));
        panelMinisterio = new JPanel(new GridLayout(1, 1));

        panelFormMontoRecolecta = new JPanel(new GridLayout(2, 1));
        JPanel panelEtiqueta = new JPanel(new FlowLayout());
        etiquetaFormMontoRecolecta = new JLabel("Monto de Recolectas");
        etiquetaFormMontoRecolecta.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelEtiqueta.add(etiquetaFormMontoRecolecta);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormMontoRecolecta = new JTextField(columnasCampo);
        campoFormMontoRecolecta.setPreferredSize(new Dimension(0, 25));
        panelCampo.add(campoFormMontoRecolecta);

        panelFormMontoRecolecta.add(panelEtiqueta);
        panelFormMontoRecolecta.add(panelCampo);

        panelMontoRecolecta.add(panelFormMontoRecolecta);

        //panelForm.add(new JPanel());
        //panelFormAbajo.add(new JPanel());
        panelForm.add(panelMontoRecolecta);
        panelForm.add(new JPanel());
        //panelFormAbajo.add(panelMinisterio);
        //panelFormAbajo.add(new JPanel());

    }

    private void colocarComponenteFormContra() {

        panelNombreServicio = new JPanel(new GridLayout(1, 1));

        panelFormNombreServicio = new JPanel(new GridLayout(2, 1));
        JPanel panelContraFlow = new JPanel(new FlowLayout());
        etiquetaFormNombreServicio = new JLabel("Nombre del Servicio");
        etiquetaFormNombreServicio.setFont(new Font("Times New Roman", 1, tamLetraForm));
        panelContraFlow.add(etiquetaFormNombreServicio);
        JPanel panelCampo = new JPanel(new FlowLayout());
        campoFormNombreServicio = new JTextField(12);
        campoFormNombreServicio.setEditable(false);
        panelCampo.add(campoFormNombreServicio);

        panelFormNombreServicio.add(panelContraFlow);
        panelFormNombreServicio.add(panelCampo);

        panelNombreServicio.add(panelFormNombreServicio);

        panelFormAbajo.add(panelNombreServicio);

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
        JPanel panelAux = new JPanel(new BorderLayout());
        panelAux.add(panelBoton, BorderLayout.CENTER);
        panelFormulario.add(panelAux, BorderLayout.SOUTH);

        botonAgregar.addActionListener(finanzaControlador);
        botonEditar.addActionListener(finanzaControlador);
        botonEliminar.addActionListener(finanzaControlador);
        botonRegresar.addActionListener(finanzaControlador);
    }

    private void colocarPanelTabla() {
        panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.RED);
        panelTabla.add(tabla, BorderLayout.CENTER);
        panelTabla.add(new JScrollPane(tabla));
        panelDatos.add(panelTabla);
    }

    private void colocarColumna() {

        for (int i = 0; i < 4; i++) {
            dtm.addColumn(columnas[i]);
        }

    }

    public void colocarFilas() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        manager = factory.createEntityManager();
        listaFinanzas = manager.createQuery("SELECT s FROM Finanza s", Finanza.class);
        List<Finanza> lista = listaFinanzas.getResultList();
        Finanza[] miarray = new Finanza[lista.size()];
        miarray = lista.toArray(miarray);
        Object finanza[] = new Object[4];

        for (Finanza i : miarray) {
            finanza[0] = i.getId();
            finanza[1] = i.getServicioid().getId();
            finanza[2] = i.getCantidad();
            finanza[3] = i.getServicionombre();
            dtm.addRow(finanza);
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

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JComboBox getComboFormServicioId() {
        return comboFormServicioId;
    }

    public JTextField getCampoFormMontoOfrendas() {
        return campoFormMontoOfrendas;
    }

    public JTextField getCampoFormMontoRecolecta() {
        return campoFormMontoRecolecta;
    }

    public JTextField getCampoFormNombreServicio() {
        return campoFormNombreServicio;
    }

    public JButton getBotonRegresar() {
        return botonRegresar;
    }

}
