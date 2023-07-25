package libreria.JPA_DAO;

import java.util.List;
import libreria.entities.Autor;

/**
 *
 * @author Max
 */
public class Autor_DAO extends JPA_DAO<Autor> {

    public Autor_DAO() {
        super();
    }

    public void crearAutor(Autor autor) {
        super.create(autor);
    }

    public void editarAutor(Autor autor) {
        super.update(autor);
    }
  
    public void borrarAutor(Integer id) {
        connect();
        em.getTransaction().begin();
        Autor autor = em.find(Autor.class, id);
        if (autor != null) {
            autor.setAlta(false);
        }
        em.getTransaction().commit();
        disconnect();
    }
    /* tuve que agregar este metodo xq me daba un error 
    error al eliminar: java.lang.illegalArgumentException: Entity must be managed to call remove: libreria.entities.Autor@1c025cb, 
    try merging the detached and try the remove again
    que es que el metodo eliminar heredado de DAO  no gestionaba (managed) el objeto o entidad por el EntityManager de JPA.
    
    */
    public void eliminarAutor(Autor autor) {
    connect();
    em.getTransaction().begin();
    Autor managedAutor = em.merge(autor); // Vuelvo a asociar la entidad con el contexto de persistencia
    em.remove(managedAutor); 
    em.getTransaction().commit();
    disconnect();
}


    public Autor findId(Integer id) {
        connect();
        Autor autor = em.find(Autor.class, id);
        disconnect();
        return autor;
    }

    public List<Autor> findName(String name) {
        connect();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :name")
                .setParameter("name", name).getResultList();
        disconnect();
        return autores;
    }

    public List<Autor> findAll() {
        connect();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        disconnect();
        return autores;
    }
}
