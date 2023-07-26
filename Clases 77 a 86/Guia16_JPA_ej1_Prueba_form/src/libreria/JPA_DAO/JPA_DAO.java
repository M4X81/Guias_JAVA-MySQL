/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.JPA_DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Max
 */
public class JPA_DAO<T> {
    protected EntityManagerFactory emf;
    protected EntityManager em;

    public JPA_DAO(){

        emf = Persistence.createEntityManagerFactory("Guia16_JPA_ej1PU");
        em = emf.createEntityManager();
    }

    protected void connect() {
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
    }

    protected void disconnect() {
        if (em.isOpen()) {
            em.close();
        }
    }
    protected void create(T object){
        connect();
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        disconnect();    
    }
    
    protected void update(T object){
        connect();
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        disconnect();  
    }
    protected void delete(T object){
        connect();
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
        disconnect();  
    }
}