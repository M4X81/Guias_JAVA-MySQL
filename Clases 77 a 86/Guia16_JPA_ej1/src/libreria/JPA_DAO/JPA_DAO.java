package libreria.JPA_DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author Max
 */
public abstract class JPA_DAO<T> {

    protected EntityManagerFactory emf;
    protected EntityManager em;

    public JPA_DAO() {

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

    protected void create(T object) {
        try {
            connect();
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el objeto " + e.toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            disconnect();
        }
    }

    protected void update(T object) {
        try {
            connect();
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el objeto " + e.toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            disconnect();
        }
    }

    protected void delete(T object) {
        try {
            connect();
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el objeto " + e.toString());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            disconnect();
        }
    }
}
