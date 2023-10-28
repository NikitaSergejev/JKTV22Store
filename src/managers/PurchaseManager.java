/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.util.Scanner;

/**
 *
 * @author nikit
 */
public class PurchaseManager {
  private Scanner scanner;
  private CustomerManager customerManager;
  private ProductManager  productManager;
  
  public PurchaseManager(Scanner scanner,CustomerManager customerManager, ProductManager  productManager) {
        this.scanner = scanner;
        this.customerManager = customerManager;
        this.productManager = productManager;
        
    }
  
  
  
  
}
