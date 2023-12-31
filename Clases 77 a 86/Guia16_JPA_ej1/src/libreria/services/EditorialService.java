package libreria.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import libreria.JPA_DAO.Editorial_DAO;
import libreria.entities.Editorial;

/**
 *
 * @author Max
 */
public class EditorialService {

    private Editorial_DAO eDao = null;

    public EditorialService() {
        eDao = new Editorial_DAO();
    }

    public Editorial crearEditorial(String nombre) {
        try {
            Editorial editorial = new Editorial(nombre, true);
            eDao.crearEditorial(editorial);
            JOptionPane.showMessageDialog(null, "Creado exitosamente");
            return editorial;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear : " + e.toString());
            return null;
        }
    }

    public Editorial modificarEditorial(Integer id, String nombre) {
        try {
            Editorial editorial = eDao.findId(id);
            editorial.setNombre(nombre);
            eDao.editarEditorial(editorial);
            JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            return editorial;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar : " + e.toString());
            return null;
        }
    }

    public Boolean borrarEditorial(Integer id) {
        try {
            eDao.borrarEditorial(id);
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar : " + e.toString());
            return false;
        }
    }

    public Editorial encontrarId(Integer id) {
        try {
            Editorial editorial = eDao.findId(id);
            return editorial;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda : " + e.toString());
            return null;
        }
    }

    public List<Editorial> mostrarListaEd() {
        try {
            return eDao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de editoriales: " + e.toString());
            return null;
        }
    }

    public List<Editorial> mostrarEditorial(Integer id, String nombre) {
        if (id != null) {
            List<Editorial> editoriales = new ArrayList<>();
            Editorial editorial = eDao.findId(id);
            if (editorial != null) {
                editoriales.add(editorial);
            }
            return editoriales;
        } else if (nombre != null && !nombre.isEmpty()) {
            return (List<Editorial>) eDao.findName(nombre);
        } else {
            return null;
        }
    }
}
