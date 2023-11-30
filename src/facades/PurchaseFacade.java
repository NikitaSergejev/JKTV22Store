/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Purchase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nikit
 */
public class PurchaseFacade {
     private EntityManager em;

    public PurchaseFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV22StorePU");
        this.em = emf.createEntityManager();
    }
    public void create(Purchase purchase){
       em.getTransaction().begin();
          em.persist(purchase);
       em.getTransaction().commit();
    }
    public Purchase find(Long id){
        return em.find(Purchase.class, id);
    }
    public List<Purchase> findAll(){
        return em.createQuery("SELECT purchase FROM Purchase purchase").getResultList();
    }
    public void edit(Purchase purchase){
        em.getTransaction().begin();
          em.merge(purchase);
       em.getTransaction().commit();
    }
}
