/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import mx.unach.repositorio.dao.FinanzaJpaController;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Finanza;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;
import mx.unach.vista.inicio.FinanzaComponentes;
import mx.unach.vista.inicio.FinanzaComponentes;
import mx.unach.vista.inicio.FinanzaServicioComponentes;

/**
 *
 * @author javier
 */
public class FinanzaControlador extends MouseAdapter implements ActionListener, ItemListener {

    private FinanzaServicioComponentes fc;
    private FinanzaComponentes finanzaComponentes;
    private FinanzaJpaController finanzaJpaController;

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Finanza> listaServicios;

    private String idFinanza;

    public FinanzaControlador(FinanzaServicioComponentes fc) {
        this.fc = fc;
    }
    
    private static FinanzaControlador finanzaControladorServicio;
    
    /*public static FinanzaControlador getInstancia(FinanzaComponentes fc, FinanzaComponentesI finanzaComponentes) {
        if (finanzaControladorServicio == null)
            finanzaControladorServicio = new FinanzaControlador(fc, finanzaComponentes);
        
        return finanzaControladorServicio;
    }*/

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == fc.getBotonAgregar()) {

            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            TypedQuery<Servicio> servicioId = manager.createNamedQuery("Servicio.findById", Servicio.class);
            servicioId.setParameter("id", Short.parseShort(fc.getComboFormServicioId().getSelectedItem().toString()));
            Servicio servicio = servicioId.getSingleResult();
            Finanza s = new Finanza(servicio, servicio.getNombre(), Double.parseDouble(fc.getCampoFormMontoOfrendas().getText()));
            finanzaJpaController = new FinanzaJpaController(factory);
            finanzaJpaController.create(s);

            fc.limpiarTabla();
            fc.colocarFilas();

            limpiarFormulario();
        } else if (ae.getSource() == fc.getBotonEditar()) {

            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            TypedQuery<Servicio> servicioId = manager.createNamedQuery("Servicio.findById", Servicio.class);
            servicioId.setParameter("id", Short.parseShort(fc.getComboFormServicioId().getSelectedItem().toString()));
            Servicio servicio = servicioId.getSingleResult();
            System.out.println("Update: " + servicio.getId());
            Finanza s = new Finanza(Short.parseShort(idFinanza),
                    servicio,
                    servicio.getNombre(),
                    Double.parseDouble(fc.getCampoFormMontoOfrendas().getText()));
            System.out.println(s.toString());
            finanzaJpaController = new FinanzaJpaController(factory);
            try {
                finanzaJpaController.edit(s);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                ex.getMessage();
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }

            fc.limpiarTabla();
            fc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == fc.getBotonEliminar()) {

            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            finanzaJpaController = new FinanzaJpaController(factory);
            try {
                finanzaJpaController.destroy(Short.parseShort(idFinanza));
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FinanzaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }

            fc.limpiarTabla();
            fc.colocarFilas();

            limpiarFormulario();

        } 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);

        idFinanza = table.getValueAt(row, 0).toString();
        String servicioId = table.getValueAt(row, 1).toString();
        String montoOfrendas = table.getValueAt(row, 2).toString();
        String servicioNombre = table.getValueAt(row, 3).toString();

        fc.getComboFormServicioId().setSelectedItem(servicioId);
        fc.getCampoFormMontoOfrendas().setText(montoOfrendas);
        fc.getCampoFormNombreServicio().setText(servicioNombre);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

        fc.getCampoFormNombreServicio().setText(obtenerServicioNombre());

    }

    private String obtenerServicioNombre() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();

        TypedQuery<Servicio> busqueda = manager.createNamedQuery("Servicio.findById", Servicio.class
        );
        busqueda.setParameter("id", Short.parseShort(fc.getComboFormServicioId().getSelectedItem().toString()));
        Servicio servicio = busqueda.getSingleResult();

        return servicio.getNombre();
    }

    private void limpiarFormulario() {
        fc.getCampoFormMontoOfrendas().setText(" ");
        fc.getCampoFormNombreServicio().setText(" ");
    }

}
