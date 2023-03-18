/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import mx.unach.repositorio.dao.CoroJpaController;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.vista.inicio.CoroComponentes;
import mx.unach.vista.inicio.SacerdoteComponentes;

/**
 *
 * @author javier
 */
public class CoroControlador extends MouseAdapter implements ActionListener {

    private CoroComponentes cc;
    private CoroJpaController coroJpaController;

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Coro> listaCoros;

    String idCoro = "";

    public CoroControlador(CoroComponentes cc) {
        this.cc = cc;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == cc.getBotonAgregar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            manager = factory.createEntityManager();

            TypedQuery<Short> findIdMax = manager.createNamedQuery("Coro.findIdMax", Short.class);
            Short id;
            if (findIdMax == null)
                id = 1;
            else
                id = findIdMax.getSingleResult();
            Coro c = new Coro(id,
                    cc.getCampoFormCoro().getText(),
                    Short.parseShort(cc.getCampoFormNMiembros().getText()),
                    cc.getCampoFormConductorCoro().getText(),
                    cc.getCampoFormPianistaPrincipal().getText(),
                    cc.getCreado().getDate());
            System.out.println(c.toString());
            coroJpaController = new CoroJpaController(factory);
            coroJpaController.create(c);

            cc.limpiarTabla();
            cc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == cc.getBotonEditar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            Coro c = new Coro(Short.parseShort(idCoro),
                    cc.getCampoFormCoro().getText(),
                    Short.parseShort(cc.getCampoFormNMiembros().getText()),
                    cc.getCampoFormConductorCoro().getText(),
                    cc.getCampoFormPianistaPrincipal().getText(),
                    cc.getCreado().getDate());
            //System.out.println(c.getCreado());

            try {
                coroJpaController = new CoroJpaController(factory);

                coroJpaController.edit(c);
            } catch (NonexistentEntityException ex) {

            } catch (Exception ex) {
            }

            cc.limpiarTabla();
            cc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == cc.getBotonEliminar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            coroJpaController = new CoroJpaController(factory);
            try {
                coroJpaController.destroy(Short.parseShort(idCoro));
            } catch (IllegalOrphanException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SacerdoteControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SacerdoteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            cc.limpiarTabla();
            cc.colocarFilas();

            limpiarFormulario();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        idCoro = table.getValueAt(row, 0).toString();
        String nombreCoro = table.getValueAt(row, 1).toString();
        String numeroMiembro = table.getValueAt(row, 2).toString();
        String conductorCoro = table.getValueAt(row, 3).toString();
        String pianistaPrincipal = table.getValueAt(row, 4).toString();
        String creado = table.getValueAt(row, 5).toString();

        cc.getCampoFormCoro().setText(nombreCoro);
        cc.getCampoFormNMiembros().setText(numeroMiembro);
        cc.getCampoFormConductorCoro().setText(conductorCoro);
        cc.getCampoFormPianistaPrincipal().setText(pianistaPrincipal);
        cc.getCreado().setDate(new Date(creado));
        
        System.out.println(creado);

    }

    private void limpiarFormulario() {
        cc.getCampoFormCoro().setText(" ");
        cc.getCampoFormNMiembros().setText(" ");
        cc.getCampoFormConductorCoro().setText(" ");
        cc.getCampoFormPianistaPrincipal().setText(" ");
        cc.getCreado().setDate(null);
    }

}
