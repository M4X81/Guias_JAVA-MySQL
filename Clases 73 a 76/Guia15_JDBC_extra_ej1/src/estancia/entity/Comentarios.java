/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estancia.entity;

/**
 *
 * @author Max
 */
public class Comentarios {
    
    private int id_comentario;
    private int id_casa;
    private String comentario;
    private Cliente cliente;

    public Comentarios() {
    }

    public Comentarios(int id_comentario, int id_casa, String comentario, Cliente cliente) {
        this.id_comentario = id_comentario;
        this.id_casa = id_casa;
        this.comentario = comentario;
        this.cliente = cliente;
    }

    public int getId_comentario() {
        return id_comentario;
    }

    public void setId_comentario(int id_comentario) {
        this.id_comentario = id_comentario;
    }

    public int getId_casa() {
        return id_casa;
    }

    public void setId_casa(int id_casa) {
        this.id_casa = id_casa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
    
}
