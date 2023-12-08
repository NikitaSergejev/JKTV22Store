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
public abstract class PurchaseFacade extends AbstractFacade<Purchase> {
     private EntityManager em;

    public PurchaseFacade() {
        super(Purchase.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTV22StorePU");
        this.em = emf.createEntityManager();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Purchase> findPurchaseOfMonth(int numMonth, int dayOfMonth) {
     
        
        return 
    }
   
}
