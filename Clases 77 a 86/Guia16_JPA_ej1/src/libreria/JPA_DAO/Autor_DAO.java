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
        Autor autor = findId(id);
        super.delete(autor);
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
