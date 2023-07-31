package libreria.JPA_DAO;

import java.util.List;
import javax.persistence.TypedQuery;
import libreria.entities.Editorial;

/**
 *
 * @author Max
 */
public class Editorial_DAO extends JPA_DAO<Editorial> {

    public Editorial_DAO() {
        super();
    }

 public void crearEditorial(Editorial editorial) {
    editorial.setAlta(true); 
    super.create(editorial);
}

    public void editarEditorial(Editorial editorial) {
        super.update(editorial);
    }

    public void borrarEditorial(Integer id) {
        Editorial editorial = findId(id);
        super.delete(editorial);
    }

    public Editorial findId(Integer id) {
        connect();
        Editorial editorial = em.find(Editorial.class, id);
        disconnect();
        return editorial;
    }

    
public Editorial findName(String name) {
    connect();
    TypedQuery<Editorial> query = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :name", Editorial.class)
            .setParameter("name", name);
    List<Editorial> editoriales = query.getResultList();
    disconnect();

    if (!editoriales.isEmpty()) {
        return editoriales.get(0);
    } else {
        return null; 
    }
}

//este me asignaba a todos los libros en la misma editorial( supongo que por el LIKE ("name", "%" + name + "%")...
//public Editorial findName(String name) {
//    connect();
//    List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :name", Editorial.class)
//            .setParameter("name", "%" + name + "%").getResultList();
//    disconnect();
//
//    if (!editoriales.isEmpty()) {
//        return editoriales.get(0);
//    } else {
//        return null; 
//    }
//}


    public List<Editorial> findAll() {
        connect();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        disconnect();
        return editoriales;
    }
}
