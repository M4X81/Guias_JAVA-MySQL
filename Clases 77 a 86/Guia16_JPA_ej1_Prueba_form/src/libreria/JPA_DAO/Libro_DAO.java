package libreria.JPA_DAO;

import java.util.List;
import libreria.entities.Libro;

/**
 *
 * @author Max
 */
public class Libro_DAO extends JPA_DAO<Libro>{
    
    public Libro_DAO(){
        super();
    }
    
    public void crearLibro(Libro libro){
        super.create(libro);       
    }
    
    public void editarLibro(Libro libro){
        super.update(libro);       
    }
   
    public void borrarLibro(Long id){
        Libro libro = findId(id);
        super.delete(libro);       
    }
    
    public Libro findId(Long id){
        connect();
        Libro libro = em.find(Libro.class, id);
        disconnect();
        return libro;
    }
      
     public List<Libro> findName(String name) {
        connect();
        List<Libro> libros;
        libros = em.createQuery("SELECT l FROM Libro l WHERE l.nombre LIKE :name")
                .setParameter("name", name).getResultList();
        disconnect();
        return libros;
    }

    public List<Libro> findAll() {
        connect();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        disconnect();
        return libros;
    }
    
    public List<Libro> findTitle(String title) {
        connect();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :title")
                .setParameter("title", title).getResultList();
        disconnect();
        return libros;
    }
    
    public List<Libro> findAutor(String name) {
    connect();
    List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :name", Libro.class)
            .setParameter("name", "%" + name + "%").getResultList();
    disconnect();
    return libros;
}

//  public List<Libro> findAutor(String name) {
//    connect();
//    List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.nombre LIKE :name")
//            .setParameter("name", "%" + name + "%").getResultList();
//    disconnect();
//    return libros;
//}

 
  public List<Libro> findEditorial(String nombreEditorial) {
    connect();
    List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombreEditorial", Libro.class)
            .setParameter("nombreEditorial", "%" + nombreEditorial + "%").getResultList();
    disconnect();
    return libros;
}

//     public List<Libro> findEditorial(String name) {
//        connect();
//        List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.Editorial e WHERE e.nombre LIKE :name")
//                .setParameter("name", name).getResultList();
//        disconnect();
//        return libros;
//    }
    
}
