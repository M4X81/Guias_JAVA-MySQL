/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainJPA.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Max
 */
@NamedQuery(
name= "Alumno.findAll",
query = "SELECT a FROM Alumno a")
@Entity
@Table(name= "alumnos")
public class Alumno extends Persona{
    
    @ManyToMany
    @JoinTable(name = "curso_alumno",
            joinColumns = @JoinColumn(name= "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id"))
    private Set<Curso> cursos;

    public Alumno() {
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "Alumno{" + "cursos=" + cursos + '}';
    }
    
}
