package libreria.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Max
 */
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "activo")
    private Boolean alta;
    @OneToMany(mappedBy = "autor") // Un autor puede tener muchos libros (un libro para un autor)
    private List<Libro> libros;

    public Autor(List<Libro> libros) {
        this.libros = libros;
    }
    
    public Autor() {
    }

    public Autor(String nombre, Boolean alta) {
        this.nombre = nombre;
        this.alta = alta;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    
    @Override
    public String toString() {
        return "Nombre: " + nombre + " id=" + id +  ", alta=" + alta ;
    }

}
