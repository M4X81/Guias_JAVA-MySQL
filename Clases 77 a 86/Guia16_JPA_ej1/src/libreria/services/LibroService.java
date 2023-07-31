package libreria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import libreria.JPA_DAO.Autor_DAO;
import libreria.JPA_DAO.Editorial_DAO;
import libreria.JPA_DAO.Libro_DAO;
import libreria.entities.Autor;
import libreria.entities.Editorial;
import libreria.entities.Libro;

/**
 *
 * @author Max
 */
public class LibroService {

    Scanner input = new Scanner(System.in).useDelimiter("\n");
    private final Autor_DAO aDao;
    private final Editorial_DAO eDao;
    private final Libro_DAO lDao;

    public LibroService() {
        aDao = new Autor_DAO();
        eDao = new Editorial_DAO();
        lDao = new Libro_DAO();
    }

    public Libro crearLibro() {
    try {
        System.out.println("Ingrese el título del libro:");
        String titulo = input.nextLine();
        System.out.println("Ingrese año de salida");
        Integer anio = input.nextInt();
        System.out.println("Ingrese la cantidad total de ejemplares:");
        Integer ejemplares = input.nextInt();
        input.nextLine();
//        System.out.println("Ingrese la cantidad de ejemplares prestados:");
//        Integer ejemplaresPrestados = input.nextInt();
//        input.nextLine();
//        System.out.println("Ingrese la cantidad de ejemplares restantes:");
//        Integer ejemplaresRestantes = input.nextInt();
//        input.nextLine();
//        System.out.println("Ingrese 'true' si el libro está disponible o 'false' si está dado de baja:");
//        Boolean alta = input.nextBoolean();
//        input.nextLine(); // Limpio buffer
        System.out.println("Ingrese el nombre del autor:");
        String nombreAutor = input.nextLine();
        System.out.println("Ingrese el nombre de la editorial:");
        String nombreEditorial = input.nextLine();

        if (ejemplares < 0  || titulo.isEmpty() || nombreAutor.isEmpty() || nombreEditorial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese datos válidos para el libro");
            return null;
        }

        Autor autor = aDao.findName(nombreAutor);
        if (autor == null) {
            autor = new Autor(nombreAutor, true);
            aDao.crearAutor(autor);
            System.out.println("Autor creado exitosamente");
        }

      Editorial editorial = eDao.findName(nombreEditorial);
        if (editorial == null) {             
            editorial = new Editorial(nombreEditorial, true);
            eDao.crearEditorial(editorial);
            System.out.println("Editorial creada exitosamente");
        } else {
            System.out.println("Editorial encontrada en la base de datos: " + editorial.getNombre());
        }

        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setAlta(true);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        lDao.crearLibro(libro);
        JOptionPane.showMessageDialog(null, "Libro creado exitosamente");
        return libro;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al crear el libro: " + e.toString());
        return null;
    }
}

    public Libro modificarLibro(Long ISBN) {
        try {
            Libro libro = lDao.findId(ISBN);
            if (libro == null) {
                JOptionPane.showMessageDialog(null, "No se encontró un libro con el ISBN proporcionado.");
                return null;
            }

            System.out.println("Ingrese el nuevo título del libro:");
            libro.setTitulo(input.nextLine());

            System.out.println("Ingrese nuevo año de impresión");
            libro.setAnio(input.nextInt());
            
            System.out.println("Ingrese la nueva cantidad total de ejemplares:");
            libro.setEjemplares(input.nextInt());

            System.out.println("Ingrese la cantidad de ejemplares prestados:");
            libro.setEjemplaresPrestados(input.nextInt());

            System.out.println("Cantidad de ejemplares restantes:");
            libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
            System.out.println(libro.getEjemplaresRestantes());

            System.out.println("Ingrese 'true' si el libro está disponible o 'false' si está dado de baja:");
            libro.setAlta(input.nextBoolean());
            input.nextLine(); //limpio buffer

            lDao.editarLibro(libro);
            JOptionPane.showMessageDialog(null, "Libro modificado exitosamente");
            return libro;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el libro: " + e.toString());
            return null;
        }
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
        List<Libro> libros = new ArrayList<>();
        try {
            libros = lDao.findAutor(autor.getNombre());
            JOptionPane.showMessageDialog(null, "Libro encontrado");
            return libros;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede encontrar el libro : " + e.toString());
        }
        return null;
    }

    public List<Libro> byEditorial(Editorial editorial) {
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

    public List<Libro> mostrarLista() {
        try {
            return lDao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de libros: " + e.toString());
            return null;
        }

    }
}
