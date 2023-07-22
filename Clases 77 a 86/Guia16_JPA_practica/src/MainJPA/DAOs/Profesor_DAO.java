package MainJPA.DAOs;

import MainJPA.entities.Profesor;
import java.util.List;

/**
 *
 * @author Max
 */


public class Profesor_DAO extends JPA_DAO<Profesor>{

    public Profesor_DAO() {
        super();
    }
    
    public void crearProfesor(Profesor profesor){
        super.create(profesor);
    }
    
    
    public void editarProfesor(Profesor profesor){
        super.update(profesor);
    }
    
    
    public void borrarProfesor(Long id){      
        Profesor profesor = em.find(Profesor.class, id);
        super.delete(profesor);
    }
    
    public List<Profesor> listarProfesores(){
        super.connect();
        List<Profesor> profesores = em.createNamedQuery("Profesor.findAll").getResultList();
        super.disconnect();
        return profesores;
    }

    public Profesor findId(Long id){
        super.connect();
        Profesor profesor = em.createNamedQuery("Profesor.findById", Profesor.class).setParameter("id", id).getSingleResult();
        super.disconnect();
        return profesor;
    }
    
    public List<Profesor> findByName(String nombre){
        super.connect();
        List<Profesor> profesor = em.createQuery("select p from Profesor p where p.nombre = :nombre ",
                Profesor.class).setParameter("nombre", nombre).getResultList();
        super.disconnect();
        return profesor;
    }
}
