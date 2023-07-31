package libreria;

import javax.swing.JOptionPane;
import libreria.services.ServicioPrincipal;

/**
 *
 * @author Max
 */
public class Main {

    public static void main(String[] args) {
        try {
            ServicioPrincipal servicio = new ServicioPrincipal();
            servicio.menuGeneral();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error de coneccion: " + e.toString());
        }

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
