package MainJPA.DAOs;

import MainJPA.entities.Alumno;
import java.util.List;

/**
 *
 * @author Max
 */


public class Alumno_DAO extends JPA_DAO<Alumno>{
    
    public Alumno_DAO(){
        super();
    }
    public void crearAlumno(Alumno alumno){
        super.create(alumno);   
    }
    public void editarAlumno(Alumno alumno){
        super.update(alumno);
    }
    public void borrarAlumno(Long id){
        Alumno alumno = findId(id);
        super.delete(alumno);
    }
    
    public Alumno findId(Long id){
        super.connect();
        Alumno alumno = em.find(Alumno.class, id);
        super.disconnect();
        return alumno;
    }
    
    public List<Alumno> listarAlumnos(){
        super.connect();
        List<Alumno> alumnos = em.createNamedQuery("Alumno.findAll", Alumno.class).getResultList();
        super.disconnect();
        return alumnos;
    }
    
    public Long cantidadAlumnos(){
        super.connect();
        Long cantidad =(Long)em.createNativeQuery("select count(*) from alumnos").getSingleResult();
        return cantidad;
    }
}
