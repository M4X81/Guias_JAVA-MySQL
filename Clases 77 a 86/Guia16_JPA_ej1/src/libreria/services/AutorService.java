package libreria.services;

import javax.swing.JOptionPane;
import libreria.JPA_DAO.Autor_DAO;
import libreria.entities.Autor;

/**
 *
 * @author Max
 */
public class AutorService {
    
    private Autor_DAO aDao;
    
    public AutorService(){
        aDao = new Autor_DAO();
    }
    
       public Autor crearAutor(String nombre) {
        try {
            Autor autor = new Autor(nombre, true);
            aDao.crearAutor(autor);
           JOptionPane.showMessageDialog(null, "Creado exitosamente");
            return autor;   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear : " + e.toString());
            return null;
        }
    }
     
      public Autor modificarAutor(Integer id, String nombre) {
        try {
            Autor autor = aDao.findId(id);
            autor.setNombre(nombre);
            aDao.editarAutor(autor);
            JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            return autor;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar : " + e.toString());
            return null;
        }
    }
       public Boolean borrarAutor(Integer id) {
        try {
            aDao.borrarAutor(id);
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar : " + e.toString());
            return false;
        }
    }
        public Autor encontrarId(Integer id) {
        try {
            Autor autor = aDao.findId(id);
            return autor;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda : " + e.toString());
            return null;
        }
    }
    
}
