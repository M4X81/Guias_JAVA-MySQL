package libreria.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import libreria.JPA_DAO.Autor_DAO;
import libreria.entities.Autor;

/**
 *
 * @author Max
 */
public class AutorService {

    private Autor_DAO aDao;

    public AutorService() {
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

    public Autor modificarAutor(Autor autor, String nombre) {
        try {
            autor.setNombre(nombre);
            aDao.editarAutor(autor);
            JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            return autor;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar : " + e.toString());
            return null;
        }
    }

public boolean eliminarAutor(Integer id) {
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
            JOptionPane.showMessageDialog(null, "Busqueda exitosa");
            return autor;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda : " + e.toString());
            return null;
        }
    }

    public List<Autor> mostrarLista() {
        try {
            return aDao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de autores: " + e.toString());
            return null;
        }

    }
    
    public Autor mostrarAutor(Integer id, String nombre) {
    if (id != null) {
        return aDao.findId(id);
    } else if (nombre != null && !nombre.isEmpty()) {
        return aDao.findName(nombre);
    } else {
        return null;
    }
}


//    public Autor mostrarAutor(Integer id, String nombre) {
//    if (id != null) {
//        return aDao.findId(id);
//    } else if (nombre != null && !nombre.isEmpty()) {
//        List<Autor> autores = (List<Autor>) aDao.findName(nombre);
//        if (!autores.isEmpty()) {
//       
//            return autores.get(0);
//        } else {
//           JOptionPane.showMessageDialog(null, "no se encuentra el autor : " );
//            return null;
//        }
//    } else {
//        return null;
//    }
//}

//    public List<Autor> mostrarAutor(Integer id, String nombre) {
//        if (id != null) {
//            List<Autor> autores = new ArrayList<>();
//            Autor autor = aDao.findId(id);
//            if (autor != null) {
//                autores.add(autor);
//            }
//            return autores;
//        } else if (nombre != null && !nombre.isEmpty()) {
//            return aDao.findName(nombre);
//        } else {
//            return null;
//        }
//    }

}


