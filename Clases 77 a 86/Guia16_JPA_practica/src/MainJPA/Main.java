package MainJPA;

import MainJPA.services.ServiciosGenerales;

/**
 *
 * @author Max
 */
public class Main {
   
    public static void main(String[] args) {
        ServiciosGenerales sg = new ServiciosGenerales();
        sg.cargarBase();
        sg.consultas();
        
        
    }
    
    
}
