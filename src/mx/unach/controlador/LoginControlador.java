/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import mx.unach.repositorio.dao.SacerdoteJpaController;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.vista.inicio.InicioManejador;
import mx.unach.vista.login.Login;

/**
 *
 * @author javier
 */
public class LoginControlador implements ActionListener {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SacerdoteJpaController sjc;
    private Login login;
    private String campoNombreUsuario;
    private String campoContra;

    private InicioManejador inicio;

    public LoginControlador(Login login) {
        this.login = login;
        emf = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        this.sjc = new SacerdoteJpaController(emf);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        em = emf.createEntityManager();

        TypedQuery<Sacerdote> query = em.createNamedQuery("Sacerdote.findByNombreAndContra", Sacerdote.class);
        query.setParameter("nombre", login.getCampoNombreUsuario().getText());
        query.setParameter("contra", login.getCampoContra().getText());
        //query.setParameter("id", 2);

        try {
            Sacerdote sacerdote = query.getSingleResult();

            if (sacerdote != null) {
                login.getVentanaPrincipal().dispose();
                inicio = InicioManejador.instacia();
                //inicio.iniciarComponentes();
            }
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }

        //System.out.println(sacerdote.toString());/*
        /*System.out.println(login.getCampoNombreUsuario().getText());
        System.out.println(login.getCampoContra().getText());*/
    }

}
