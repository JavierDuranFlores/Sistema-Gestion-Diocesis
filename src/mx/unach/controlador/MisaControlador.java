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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import mx.unach.repositorio.dao.MisaJpaController;
import mx.unach.repositorio.dao.SacerdoteJpaController;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.vista.inicio.InicioManejador;
import mx.unach.vista.inicio.MisaComponentes;
import mx.unach.vista.inicio.ServicioComponentes;

/**
 *
 * @author javier
 */
public class MisaControlador extends MouseAdapter implements ActionListener, ItemListener {

    private MisaComponentes mc;
    private MisaJpaController misaJpaController;
    private ServicioComponentes servicioComponentes;
    boolean instanciaServicio = false;
    private InicioManejador inicioManejador;

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Sacerdote> listaSacerdotes;

    String idMisa = "";

    public MisaControlador(MisaComponentes mc) {
        this.mc = mc;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == mc.getBotonAgregar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            manager = factory.createEntityManager();

            TypedQuery<Sacerdote> sacerdoteNombre = manager.createNamedQuery("Sacerdote.findByNombre", Sacerdote.class);
            sacerdoteNombre.setParameter("nombre", mc.getComboFormSacerdoteId().getSelectedItem().toString());
            Sacerdote sacerdote = sacerdoteNombre.getSingleResult();

            TypedQuery<Coro> coroNombre = manager.createNamedQuery("Coro.findByNombre", Coro.class);
            coroNombre.setParameter("nombre", mc.getComboFormCoroId().getSelectedItem().toString());
            Coro coro = coroNombre.getSingleResult();

            TypedQuery<Short> findIdMax = manager.createNamedQuery("Sacerdote.findIdMax", Short.class);

            Short id = findIdMax.getSingleResult();
            if (id != 0) {
                id++;
            }
            Misa m = new Misa(id,
                    mc.getDiaServicio().getDate(),
                    mc.getComboFormTiempoServicio().getSelectedItem().toString(),
                    sacerdote,
                    coro);
            System.out.println(m.toString());
            misaJpaController = new MisaJpaController(factory);
            misaJpaController.create(m);

            mc.limpiarTabla();
            mc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == mc.getBotonEditar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            TypedQuery<Sacerdote> sacerdoteId = manager.createNamedQuery("Sacerdote.findByNombre", Sacerdote.class);
            sacerdoteId.setParameter("nombre", mc.getComboFormSacerdoteId().getSelectedItem().toString());
            Sacerdote sacerdote = sacerdoteId.getSingleResult();

            TypedQuery<Coro> coroId = manager.createNamedQuery("Coro.findByNombre", Coro.class);
            coroId.setParameter("nombre", mc.getComboFormCoroId().getSelectedItem().toString());
            Coro coro = coroId.getSingleResult();

            Misa m = new Misa(Short.parseShort(idMisa),
                    mc.getDiaServicio().getDate(),
                    mc.getComboFormTiempoServicio().getSelectedItem().toString(),
                    sacerdote,
                    coro);
            System.out.println(m.toString());

            try {
                misaJpaController = new MisaJpaController(factory);

                misaJpaController.edit(m);
            } catch (NonexistentEntityException ex) {

            } catch (Exception ex) {
            }

            mc.limpiarTabla();
            mc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == mc.getBotonEliminar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            misaJpaController = new MisaJpaController(factory);
            try {
                misaJpaController.destroy(Short.parseShort(idMisa));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SacerdoteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            mc.limpiarTabla();
            mc.colocarFilas();

            limpiarFormulario();
        }

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

        manager = factory.createEntityManager();

        

        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);

        idMisa = table.getValueAt(row, 0).toString();
        String misaDia = table.getValueAt(row, 1).toString();
        String misaHora = table.getValueAt(row, 2).toString();
        String misaSacerdoteId = table.getValueAt(row, 3).toString();
        String misaCoroId = table.getValueAt(row, 4).toString();

        TypedQuery<Coro> findIdCoro = manager.createNamedQuery("Coro.findById", Coro.class);
        findIdCoro.setParameter("id", Short.parseShort(misaCoroId));
        Coro coro = findIdCoro.getSingleResult();
        
        TypedQuery<Sacerdote> findIdSacerdote = manager.createNamedQuery("Sacerdote.findById", Sacerdote.class);
        findIdSacerdote.setParameter("id", Short.parseShort(misaSacerdoteId));
        Sacerdote sacerdote = findIdSacerdote.getSingleResult();
        
        //System.out.println(misaServicioId);
        mc.getComboFormSacerdoteId().setSelectedItem(sacerdote.getNombre());
        mc.getComboFormCoroId().setSelectedItem(coro.getNombre());
        mc.getDiaServicio().setDate(new Date(misaDia));
        mc.getComboFormTiempoServicio().setSelectedItem(misaHora);

    }

    private void limpiarFormulario() {

        mc.getComboFormSacerdoteId().setSelectedItem(" ");
        mc.getComboFormCoroId().setSelectedItem(" ");
        mc.getDiaServicio().setDate(null);
        mc.getComboFormTiempoServicio().setSelectedItem(" ");

    }

}
