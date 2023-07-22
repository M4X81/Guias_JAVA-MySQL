package MainJPA.DAOs;

import MainJPA.entities.Curso;
import MainJPA.enums.DiaSemana;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Max
 */


public class Curso_DAO extends JPA_DAO<Curso>{
   
    public Curso_DAO(){
        super();
    }
    
    public void crearCurso(Curso curso){
        super.create(curso);
    }
    public void editarCurso(Curso curso){
        super.update(curso);
    }
    public void borrarCurso(Long id){
        Curso curso = em.find(Curso.class, id);
        super.delete(curso);
    }
    
    public List<Curso> listarCursos(){
        super.connect();
        List<Curso>cursos = em.createNamedQuery("Curso.findAll").getResultList();
        super.disconnect();
        return cursos;       
    }
    public Curso findId(Long id){
        super.connect();
        Curso curso = em.createNamedQuery("Curso.findById",
                Curso.class).setParameter("id", id).getSingleResult();
        super.disconnect();
        return curso;
    }
    /*
    public Curso findByName(String nombre){
        super.connect();
        Curso curso = (Curso) em.createQuery( "select c from Curso c where c.nombre = :nombre", Curso.class
        ).setParameter("nombre", nombre).getResultList();
        super.disconnect();
        return curso;
    }
*/
    public List<Curso> findByName(String nombre) {
    super.connect();
    List<Curso> cursos = new ArrayList<>();
    try {
        cursos = em.createQuery("select c from Curso c where c.nombre = :nombre", Curso.class)
                .setParameter("nombre", nombre)
                .getResultList();
    } catch (NoResultException e) {
        System.out.println("No se encontraron cursos con el nombre: " + nombre);
    } finally {
        super.disconnect();
    }
    return cursos;
}

    
    public List<Curso> listarCursosPorDia(DiaSemana dia){
        super.connect();
         List<Curso>cursos = em.createQuery("select c from Curso c where c.dia = :dia",
                 Curso.class).setParameter("dia",dia.toString()).getResultList();
         super.disconnect();
         return cursos;
    }
    
}
