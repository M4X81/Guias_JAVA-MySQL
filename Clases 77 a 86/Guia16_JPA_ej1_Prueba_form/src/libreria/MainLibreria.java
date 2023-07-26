/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria;

import javax.swing.SwingUtilities;
import libreria.services.ServicioPrincipal;

/**
 *
 * @author Max
 */
public class MainLibreria {
    public static void main(String[] args) {
       // ServicioPrincipal sp = new ServicioPrincipal();     
      //  sp.menuGeneral();

        try {
             LibreriaForm form = new LibreriaForm();
            ServicioPrincipal servicio = new ServicioPrincipal();
            servicio.menuGeneral();
            //estas lineas son para cerrar la ejecucion del formulario que queda en segundo plano            
           //sino queda todo tildado
            SwingUtilities.invokeLater(() -> {
                form.dispose();         
                System.exit(0);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
    los formularios los voy a eliminar y crear uno alternativo con los formularios asi dejo este limpio y funcional
    sino tengo que revisar demasiadas cosas cada vez que se rompe algo y tengo que estar frenando la ejecucion en segindo plano de los formularios...
    */
      
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
  