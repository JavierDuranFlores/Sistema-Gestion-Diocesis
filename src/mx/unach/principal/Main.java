/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.principal;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.vista.inicio.InicioManejador;
import mx.unach.vista.login.Login;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author javier
 */
public class Main {
    
    public static void main(String[] args) {
        
        
        /*EntityManagerFactory factory = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        
        EntityManager manager = factory.createEntityManager();
        
        TypedQuery<Sacerdote> listaSacerdotes = manager.createQuery("SELECT s FROM Sacerdote s", Sacerdote.class);
        
        listaSacerdotes.getResultList().stream()
                .forEach(
                        (sacerdote) ->
                        {
                            System.out.println(sacerdote.getNombre());
                        }
                );
        */
        
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sistema-Gestion-IglesiaPU");
        try {
            Short tiempo = 5;
            Sacerdote sacerdote = new Sacerdote("Jose", new Date(), "Antorcha", "9627301864", tiempo, "123456");
            SacerdoteJpaController contSacerdote = new SacerdoteJpaController(emf);
            contSacerdote.create(sacerdote);
            
            List<Sacerdote> list = contSacerdote.findSacerdoteEntities();
            
            list.stream().forEach(s -> System.out.println(s.toString()));
            
            emf.close();
        }catch (Exception e) { 
            e.printStackTrace();
            System.out.println(e.getMessage());
        }*/
        
        Login login = new Login();
        login.iniciarComponentes();
        
        //InicioManejador inicio = InicioManejador.instacia();
//        inicio.iniciarComponentes();
        
        /*DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("C", 100);
        data.setValue("Java", 900);
 
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createRingChart(
         "Ejemplo Rapido de Grafico en un ChartFrame", 
         data, 
         true, 
         true, 
         false);

        // Mostrar Grafico
        ChartFrame frame = new ChartFrame("JFreeChart", chart);
        frame.pack();
        frame.setVisible(true);*/
        
    }
    
}
