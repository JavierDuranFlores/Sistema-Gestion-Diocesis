/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import mx.unach.vista.inicio.CoroComponentes;
import mx.unach.vista.inicio.InformacionComponentes;
import mx.unach.vista.inicio.FinanzaComponentes;
import mx.unach.vista.inicio.FinanzaServicioComponentes;
import mx.unach.vista.inicio.Inicio;
import mx.unach.vista.inicio.InicioManejador;
import mx.unach.vista.inicio.MisaComponentes;
import mx.unach.vista.inicio.SacerdoteComponentes;
import mx.unach.vista.inicio.ServicioComponentes;

/**
 *
 * @author javier
 */
public class InicioControlador implements ActionListener {

    private InicioManejador inicioManejador;
    private Inicio inicio;
    private SacerdoteComponentes sacerdoteComponentes;
    private CoroComponentes coroComponentes;
    
    //private FinanzaComponentesI finanzaComponentes;
    private FinanzaServicioComponentes finanzaComponentes;
    private MisaComponentes misaComponentes;
    private ServicioComponentes servicioComponentes;

    boolean instanciaSacerdote = false;
    boolean instanciaCoro = false;
    
    boolean instanciaFinanaza = false;
    boolean instanciaMisa = false;
    boolean instanciaServicio = false;

    private Color colorFormulario = new Color(241, 242, 246);

    public InicioControlador(InicioManejador inicioManejador) {
        this.inicioManejador = inicioManejador;
        inicio = new Inicio();
        sacerdoteComponentes = new SacerdoteComponentes();
        coroComponentes = new CoroComponentes();
       
        //finanzaComponentes = FinanzaComponentesI.getInstacia(inicioManejador);
        //finanzaComponentes = new FinanzaComponentesI();
        finanzaComponentes = new FinanzaServicioComponentes();
        misaComponentes = new MisaComponentes();
        servicioComponentes = new ServicioComponentes();
       
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource().equals(inicioManejador.getBotonInicio())) {
            // INICIO
            inicioManejador.addContenedor(inicio);
            inicio.inicializarComponentes();
        } else if (ae.getSource().equals(inicioManejador.getBotonSacerdote())) {
            // SACERDOTE
            if (instanciaSacerdote == false) {
                instanciaSacerdote = true;
                inicioManejador.addContenedor(sacerdoteComponentes);
                sacerdoteComponentes.iniciarComponentes();
            } else {
                inicioManejador.addContenedor(sacerdoteComponentes);
                sacerdoteComponentes.limpiarTabla();
                sacerdoteComponentes.colocarFilas();
            }

        } else if (ae.getSource().equals(inicioManejador.getBotonCoro())) {
            // CORO
            if (instanciaCoro == false) {
                instanciaCoro = true;
                inicioManejador.addContenedor(coroComponentes);
                coroComponentes.iniciarComponentes();
            } else {
                inicioManejador.addContenedor(coroComponentes);
                /*finanzaComponentes.limpiarTabla();
                finanzaComponentes.colocarFilas();*/
            }
        } else if (ae.getSource().equals(inicioManejador.getBotonMisa())) {
            // MISA
            if (instanciaMisa == false) {
                System.out.println(instanciaMisa);
                instanciaMisa = true;
                inicioManejador.addContenedor(misaComponentes);
                misaComponentes.iniciarComponentes();
            } else {
                System.out.println(instanciaMisa);
                inicioManejador.addContenedor(misaComponentes);
                misaComponentes.limpiarTabla();
                misaComponentes.colocarFilas();
            }

        } else if (ae.getSource().equals(inicioManejador.getBotonServicio())) {
            // SERVICIO
            if (instanciaServicio == false) {
                instanciaServicio = true;
                inicioManejador.addContenedor(servicioComponentes);
                servicioComponentes.iniciarComponentes();
            } else {
                System.out.println(instanciaMisa);
                inicioManejador.addContenedor(servicioComponentes);
                servicioComponentes.limpiarTabla();
                servicioComponentes.colocarFilas();
            }

        } else if (ae.getSource().equals(inicioManejador.getBotonFinanza())) {
            // FINANZA
            if (instanciaFinanaza == false) {
                instanciaFinanaza = true;
                inicioManejador.addContenedor(finanzaComponentes);
                finanzaComponentes.iniciarComponentes();
            } else {
                inicioManejador.addContenedor(finanzaComponentes);
                finanzaComponentes.limpiarTabla();
                finanzaComponentes.colocarFilas();
            }

        } /* else if (ae.getSource().equals(inicioManejador.getBotonInfo())) {
        
        } */else if (ae.getSource().equals(inicioManejador.getBotonSalir())) {
            System.exit(0);
        }

    }

}
