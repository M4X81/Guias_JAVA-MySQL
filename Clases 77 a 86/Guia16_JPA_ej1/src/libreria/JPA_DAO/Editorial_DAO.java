package libreria.JPA_DAO;

import java.util.List;
import libreria.entities.Editorial;

/**
 *
 * @author Max
 */
public class Editorial_DAO extends JPA_DAO<Editorial>{
    
    public Editorial_DAO(){
        super();
    }
    
    public void crearEditorial(Editorial editorial){
        super.create(editorial);       
    }
    
    public void editarEditorial(Editorial editorial){
        super.update(editorial);       
    }
   
    public void borrarEditorial(Integer id){
        Editorial editorial = findId(id);
        super.delete(editorial);       
    }
    
    public Editorial findId(Integer id){
        connect();
        Editorial editorial = em.find(Editorial.class, id);
        disconnect();
        return editorial;
    }
      
     public List<Editorial> findName(String name) {
        connect();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :name")
                .setParameter("name", name).getResultList();
        disconnect();
        return editoriales;
    }

    public List<Editorial> findAll() {
        connect();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        disconnect();
        return editoriales;
    }
}
