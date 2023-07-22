package libreria.services;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import libreria.JPA_DAO.Libro_DAO;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;

/**
 *
 * @author Max
 */
public class LibroService {

    private Libro_DAO lDao;

    public LibroService() {
        lDao = new Libro_DAO();
    }

    public Libro crearLibro(Long ISBN, String titulo, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        try {
            Libro libro = new Libro(null, titulo, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);
            lDao.crearLibro(libro);
            JOptionPane.showMessageDialog(null, "Creado exitosamente");
            return libro;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear : " + e.toString());
            return null;
        }
    }

    public Libro modificarLibro(Long ISBN, String titulo, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        try {
            Libro libro = lDao.findId(ISBN);
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(alta);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            lDao.editarLibro(libro);
            JOptionPane.showMessageDialog(null, "Modificado exitosamente");
            return libro;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar : " + e.toString());
        }
        return null;
    }

    public Boolean borrarLibro(Long ISBN) {
        try {
            lDao.borrarLibro(ISBN);
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar : " + e.toString());
            return false;
        }
    }

    public Libro encontrarISBN(Long ISBN) {
        try {
            Libro libro = lDao.findId(ISBN);
            return libro;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la busqueda : " + e.toString());
            return null;
        }
    }

    public List<Libro> byTitulo(String titulo) {
        List<Libro> libros = new ArrayList();
        try {
            libros = lDao.findName(titulo);
            JOptionPane.showMessageDialog(null, "Libro encontrado");
            return libros;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "no se puede encontrar el libro : " + e.toString());
        }
        return null;
    }

    public List<Libro> byAutor(Autor autor) {
        List<Libro> libros = new ArrayList();
        try {
            libros = lDao.findAutor(autor.getNombre());
            JOptionPane.showMessageDialog(null, "Libro encontrado");
            return libros;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede encontrar el libro : " + e.toString());
        }
        return null;
    }
    public List<Libro> byEditorial(Editorial editorial){
        List<Libro> libros = new ArrayList<>();
        try {
            libros = lDao.findEditorial(editorial.getNombre());
            JOptionPane.showMessageDialog(null, "Libro encontrado");
            return libros;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede encontrar el libro : " + e.toString());
        }
        return null;
    }
}
