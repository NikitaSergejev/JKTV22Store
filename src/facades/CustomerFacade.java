/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nikit
 */
public class CustomerFacade extends AbstractFacade<Customer> {
    private final EntityManager em;

    public CustomerFacade() {
        super(Customer.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV22StorePU");
        this.em = emf.createEntityManager();
    }
   
     @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
