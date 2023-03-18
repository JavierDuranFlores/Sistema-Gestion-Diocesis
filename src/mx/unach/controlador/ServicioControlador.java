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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import mx.unach.repositorio.dao.ServicioJpaController;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Servicio;
import mx.unach.vista.inicio.InicioManejador;
import mx.unach.vista.inicio.MisaComponentes;
import mx.unach.vista.inicio.ServicioComponentes;

/**
 *
 * @author javier
 */
public class ServicioControlador extends MouseAdapter implements ActionListener, ItemListener {

    private ServicioComponentes sc;
    private ServicioJpaController servicioJpaController;

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Servicio> listaServicios;

    private String idServicio;
    private InicioManejador inicioManejador;

    boolean instanciaMisa = false;

    private MisaComponentes misaComponentes;

    public ServicioControlador(ServicioComponentes sc) {
        this.sc = sc;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == sc.getBotonAgregar()) {

            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            manager = factory.createEntityManager();

            TypedQuery<Short> findIdMax = manager.createNamedQuery("Sacerdote.findIdMax", Short.class);
            TypedQuery<Sacerdote> sacerdoteId = manager.createNamedQuery("Sacerdote.findById", Sacerdote.class);
            sacerdoteId.setParameter("id", Short.parseShort(sc.getComboFormSacerdoteId().getSelectedItem().toString()));
            Sacerdote sacerdote = sacerdoteId.getSingleResult();
            Short id = findIdMax.getSingleResult();

            TypedQuery<Coro> coroId = manager.createNamedQuery("Coro.findById", Coro.class);
            coroId.setParameter("id", Short.parseShort(sc.getComboFormCoroId().getSelectedItem().toString()));
            Coro coro = coroId.getSingleResult();
            
            TypedQuery<Misa> misaId = manager.createNamedQuery("Misa.findById", Misa.class);
            misaId.setParameter("id", Short.parseShort(sc.getComboFormMisaId().getSelectedItem().toString()));
            Misa misa = misaId.getSingleResult();

            Short idMisa = misa.getId();
            if (idMisa!=0)
                idMisa++;
            
            Servicio s = new Servicio(idMisa,
                    sc.getCampoFormServicio().getText(),
                    sc.getCampoFormTelefono().getText(),
                    sacerdote,
                    sacerdote.getNombre(),  
                    coro,
                    coro.getNombre(),
                    sc.getCampoFormEscritura().getText(),
                    misa);
            System.out.println(s.toString());
            servicioJpaController = new ServicioJpaController(factory);
            servicioJpaController.create(s);

            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == sc.getBotonEditar()) {

            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            TypedQuery<Short> findIdMax = manager.createNamedQuery("Sacerdote.findIdMax", Short.class);
            TypedQuery<Sacerdote> sacerdoteId = manager.createNamedQuery("Sacerdote.findById", Sacerdote.class);
            sacerdoteId.setParameter("id", Short.parseShort(sc.getComboFormSacerdoteId().getSelectedItem().toString()));
            Sacerdote sacerdote = sacerdoteId.getSingleResult();
            Short id = findIdMax.getSingleResult();

            TypedQuery<Coro> coroId = manager.createNamedQuery("Coro.findById", Coro.class);
            coroId.setParameter("id", Short.parseShort(sc.getComboFormCoroId().getSelectedItem().toString()));
            Coro coro = coroId.getSingleResult();

            TypedQuery<Misa> misaId = manager.createNamedQuery("Misa.findById", Misa.class);
            misaId.setParameter("id", Short.parseShort(sc.getComboFormMisaId().getSelectedItem().toString()));
            Misa misa = misaId.getSingleResult();
            
            Servicio s = new Servicio(Short.parseShort(idServicio),
                    sc.getCampoFormServicio().getText(),
                    sc.getCampoFormTelefono().getText(),
                    sacerdote,
                    sacerdote.getNombre(),
                    coro,
                    coro.getNombre(),
                    sc.getCampoFormEscritura().getText(),
                    misa);
            System.out.println(s.toString());
            servicioJpaController = new ServicioJpaController(factory);
            try {
                servicioJpaController.edit(s);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }

            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == sc.getBotonEliminar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            servicioJpaController = new ServicioJpaController(factory);
            try {
                servicioJpaController.destroy(Short.parseShort(idServicio));
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ServicioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }

            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();
        } else if (ae.getSource() == sc.getBotonRegresar()) {
            
            // MISA
            if (instanciaMisa == false) {
                System.out.println("if: " + instanciaMisa);
                instanciaMisa = true;
                
                inicioManejador.addContenedor(misaComponentes);
                misaComponentes.iniciarComponentes();

            } else {
                System.out.println("else:" + instanciaMisa);
                inicioManejador.addContenedor(misaComponentes);
                misaComponentes.limpiarTabla();
                misaComponentes.colocarFilas();
            }
            System.out.println(misaComponentes.hashCode());
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);

        idServicio = table.getValueAt(row, 0).toString();
        String nombreServicio = table.getValueAt(row, 1).toString();
        String telefono = table.getValueAt(row, 2).toString();
        String sacerdoteId = table.getValueAt(row, 3).toString();
        String sacerdoteNombre = table.getValueAt(row, 4).toString();
        String coroId = table.getValueAt(row, 5).toString();
        String coroNombre = table.getValueAt(row, 6).toString();
        String lectura = table.getValueAt(row, 7).toString();
        String misaId = table.getValueAt(row, 8).toString();
        

        sc.getCampoFormServicio().setText(nombreServicio);
        sc.getComboFormSacerdoteId().setSelectedItem(sacerdoteId);
        sc.getComboFormMisaId().setSelectedItem(misaId);
        sc.getCampoFormEscritura().setText(lectura);
        sc.getComboFormCoroId().setSelectedItem(coroId);
        sc.getCampoFormNombreSacerdote().setText(sacerdoteNombre);
        sc.getCampoFormTelefono().setText(telefono);
        sc.getCampoFormNombreCoro().setText(coroNombre);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == sc.getComboFormCoroId()) {
            sc.getCampoFormNombreCoro().setText(obtenerCoroNombre());
        } else if (ie.getSource() == sc.getComboFormSacerdoteId()) {
            sc.getCampoFormNombreSacerdote().setText(obtenerSacerdoteNombre());
        }
    }

    private String obtenerCoroNombre() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();

        TypedQuery<Coro> busqueda = manager.createNamedQuery("Coro.findById", Coro.class);
        busqueda.setParameter("id", Short.parseShort(sc.getComboFormCoroId().getSelectedItem().toString()));
        Coro coro = busqueda.getSingleResult();

        return coro.getNombre();
    }

    private String obtenerSacerdoteNombre() {
        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();

        TypedQuery<Sacerdote> busqueda = manager.createNamedQuery("Sacerdote.findById", Sacerdote.class);
        busqueda.setParameter("id", Short.parseShort(sc.getComboFormSacerdoteId().getSelectedItem().toString()));
        Sacerdote sacerdote = busqueda.getSingleResult();

        return sacerdote.getNombre();
    }

    private void limpiarFormulario() {

        sc.getCampoFormServicio().setText(" ");
        sc.getCampoFormEscritura().setText(" ");
        sc.getDiaServicio().setDate(null);
        sc.getCampoFormNombreSacerdote().setText(" ");
        sc.getCampoFormNombreCoro().setText(" ");

    }

}
