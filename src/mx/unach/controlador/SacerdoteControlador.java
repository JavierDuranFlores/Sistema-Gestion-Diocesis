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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import mx.unach.repositorio.dao.SacerdoteJpaController;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.vista.inicio.SacerdoteComponentes;

/**
 *
 * @author javier
 */
public class SacerdoteControlador extends MouseAdapter implements ActionListener {

    private SacerdoteComponentes sc;
    private SacerdoteJpaController sacerdoteJpaController;

    private EntityManagerFactory factory;
    private EntityManager manager;
    private TypedQuery<Sacerdote> listaSacerdotes;

    String idSacerdote = "";

    public SacerdoteControlador(SacerdoteComponentes sc) {
        this.sc = sc;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == sc.getBotonAgregar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            manager = factory.createEntityManager();

            TypedQuery<Short> findIdMax = manager.createNamedQuery("Sacerdote.findIdMax", Short.class);

            Short id = findIdMax.getSingleResult();
            Sacerdote s = new Sacerdote(
                    sc.getCampoFormSacerdote().getText(),
                    sc.getNacimiento().getDate(),
                    sc.getAreaFormDireccion().getText(),
                    sc.getCampoFormTelefono().getText(),
                    Short.parseShort(sc.getCampoFormTiempoMinisterio().getText()),
                    "123");
            System.out.println(s.toString());
            sacerdoteJpaController = new SacerdoteJpaController(factory);
            sacerdoteJpaController.create(s);

            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == sc.getBotonEditar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            Sacerdote s = new Sacerdote(Short.parseShort(idSacerdote),
                    sc.getCampoFormSacerdote().getText(),
                    sc.getNacimiento().getDate(),
                    sc.getAreaFormDireccion().getText(),
                    sc.getCampoFormTelefono().getText(),
                    Short.parseShort(sc.getCampoFormTiempoMinisterio().getText()),
                    sc.getCampoFormContra().getText());
            System.out.println("Nombre: "+s.getNombre());

            try {
                sacerdoteJpaController = new SacerdoteJpaController(factory);

                sacerdoteJpaController.edit(s);
            } catch (NonexistentEntityException ex) {

            } catch (Exception ex) {
            }

            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();

        } else if (ae.getSource() == sc.getBotonEliminar()) {
            factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");

            sacerdoteJpaController = new SacerdoteJpaController(factory);
            try {
                sacerdoteJpaController.destroy(Short.parseShort(idSacerdote));
            } catch (IllegalOrphanException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SacerdoteControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(SacerdoteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            sc.limpiarTabla();
            sc.colocarFilas();

            limpiarFormulario();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        idSacerdote = table.getValueAt(row, 0).toString();
        String nombreSacerdote = table.getValueAt(row, 1).toString();
        String direccionSacerdote = table.getValueAt(row, 3).toString();
        String telefonoSacerdote = table.getValueAt(row, 4).toString();
        String cumpleSacerdote = table.getValueAt(row, 2).toString();
        String tiempoMinisterioSacerdote = table.getValueAt(row, 5).toString();

        sc.getCampoFormSacerdote().setText(nombreSacerdote);
        sc.getAreaFormDireccion().setText(direccionSacerdote);
        sc.getCampoFormTelefono().setText(telefonoSacerdote);
        sc.getNacimiento().setDate(new Date(cumpleSacerdote));
        sc.getCampoFormTiempoMinisterio().setText(tiempoMinisterioSacerdote);

    }

    private void limpiarFormulario() {
        sc.getCampoFormSacerdote().setText(" ");
        sc.getAreaFormDireccion().setText(" ");
        sc.getCampoFormTelefono().setText(" ");
        //sc.getCampoFormContra().setText(" ");
        sc.getNacimiento().setDate(null);
        sc.getCampoFormTiempoMinisterio().setText(" ");
    }

}
