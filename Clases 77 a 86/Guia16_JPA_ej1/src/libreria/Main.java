/*

 */
package libreria;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import libreria.entities.Autor;
import libreria.services.ServicioPrincipal;


/**
 *
 * @author Max
 */
public class Main {
    public static void main(String[] args) {
        ServicioPrincipal sp = new ServicioPrincipal();
        LibreriaForm form = new LibreriaForm();
        sp.menuGeneral();
        
           //estas lineas son para cerrar la ejecucion del formulario que queda en segundo plano
           //sino queda todo tildado
            SwingUtilities.invokeLater(() -> {
                form.dispose();         
                System.exit(0);
            });
        
        
//       //Esto carga los formularios 
// SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                LibreriaForm form = new LibreriaForm();
//                form.setVisible(true);
//            }
//        });

//        //esto es para probar conexion
//        try {
//             EntityManager em = Persistence.createEntityManagerFactory("Guia16_JPA_ej1PU").createEntityManager();
//             try {
//                Autor autor = new Autor("borges",true);
//                em.getTransaction().begin();
//                em.persist(autor);
//                em.getTransaction().commit();
//                JOptionPane.showMessageDialog( null, "coneccion correcta a la base de datos");
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog( null, "error: " + e.toString());
//            }
//        } catch (Exception e) {
//        }


       
    }
  
}



