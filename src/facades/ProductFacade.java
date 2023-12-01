/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nikit
 */
public class ProductFacade {
    private final EntityManager em;

    public ProductFacade() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV22StorePU");
        this.em = emf.createEntityManager();
    }
    public void create(Product product){
       em.getTransaction().begin();
          em.persist(product);
       em.getTransaction().commit();
    }
    public Product find(Long id){
        return em.find(Product.class, id);
    }
    public List<Product> findAll(){
        return em.createQuery("SELECT product FROM Product product").getResultList();
    }
    public void edit(Product product){
        em.getTransaction().begin();
          em.merge(product);
       em.getTransaction().commit();
    }
}
