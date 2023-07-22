/*

 */
package libreria;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import libreria.entities.Autor;


/**
 *
 * @author Max
 */
public class Main {
    public static void main(String[] args) {
        
        try {
             EntityManager em = Persistence.createEntityManagerFactory("Guia16_JPA_ej1PU").createEntityManager();
             try {
                Autor autor = new Autor("borges",true);
                em.getTransaction().begin();
                em.persist(autor);
                em.getTransaction().commit();
                JOptionPane.showMessageDialog( null, "coneccion correcta a la base de datos");
            } catch (Exception e) {
                JOptionPane.showMessageDialog( null, "error: " + e.toString());
            }
        } catch (Exception e) {
        }
       
    }
  
}



