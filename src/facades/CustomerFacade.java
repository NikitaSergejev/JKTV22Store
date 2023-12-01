/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nikit
 */
public class CustomerFacade {
    private final EntityManager em;

    public CustomerFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV22StorePU");
        this.em = emf.createEntityManager();
    }
    public void create(Customer customer){
       em.getTransaction().begin();
          em.persist(customer);
       em.getTransaction().commit();
    }
    public Customer find(Long id){
        return em.find(Customer.class, id);
    }
    public List<Customer> findAll(){
        
        return em.createQuery("SELECT customer FROM Customer customer").getResultList();
    }
    public void edit(Customer customer){
        em.getTransaction().begin();
          em.merge(customer);
       em.getTransaction().commit();
    }
}
