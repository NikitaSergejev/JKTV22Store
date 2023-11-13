/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nikit
 */
public class SaveManager {
    private final String PRODUCTS_FILENAME = "products";
    private final String CUSTOMERS_FILENAME = "customers";
    private final String PURCHAIES_FILENAME = "purchaies"; 
    
    public void saveProducts(List<Product> products){
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(PRODUCTS_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            fos.flush();
          } catch (FileNotFoundException ex) {
            System.out.println("File \"products\" does not exist");
          } catch (IOException ex) {//ошибка ввода вывода
            System.out.println("Error I/0");
        }
    }
    public List<Product> loadProducts(){
      List<Product> products = new ArrayList<>();
      FileInputStream fis;
      ObjectInputStream ois; 
        try {
            fis = new FileInputStream(PRODUCTS_FILENAME);
            ois = new ObjectInputStream(fis);
            products = ( List<Product>) ois.readObject();           
        } catch (FileNotFoundException ex) {
             System.out.println("File \"products\" does not exist");
        }catch (IOException ex) {
             System.out.println("Error I/0 books");
        }catch (ClassNotFoundException ex) {
             System.out.println("Class Product not found");
          }
        return products;
    }
    public List<Customer> loadCustomers(){
     List<Customer> customers = new ArrayList<>();
      FileInputStream fis;
      ObjectInputStream ois; 
        try {
            fis = new FileInputStream(CUSTOMERS_FILENAME);
            ois = new ObjectInputStream(fis);
            customers = (List<Customer>) ois.readObject();           
        } catch (FileNotFoundException ex) {
             System.out.println("File \"customers\" does not exist");
        }catch (IOException ex) {
             System.out.println("Error I/0 books");
        }catch (ClassNotFoundException ex) {
             System.out.println("Class Customer not found");
          }
        return customers;  
        
    }
    public void saveCustomers(List<Customer> customers) {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(CUSTOMERS_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.flush();
          } catch (FileNotFoundException ex) {
            System.out.println("File \"customers\" does not exist");
          } catch (IOException ex) {//ошибка ввода вывода
            System.out.println("Error I/0");
        }
    }
     public List<Purchase> loadPurchaies() {
     List<Purchase> purchaies = new ArrayList<>();
      FileInputStream fis;
      ObjectInputStream ois; 
        try {
            fis = new FileInputStream(PURCHAIES_FILENAME);
            ois = new ObjectInputStream(fis);
            purchaies = (List<Purchase>) ois.readObject();           
        } catch (FileNotFoundException ex) {
             System.out.println("File \"purchaies\" does not exist");
        }catch (IOException ex) {
             System.out.println("Error I/0 books");
        }catch (ClassNotFoundException ex) {
             System.out.println("Class Purchase not found");
          }
        return purchaies;  
        
    }
            
    public void savePurchaies(List<Purchase> purchaies) {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(PURCHAIES_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(purchaies);
            oos.flush();
          } catch (FileNotFoundException ex) {
            System.out.println("File \"purchaies\" does not exist");
          } catch (IOException ex) {//ошибка ввода вывода
            System.out.println("Error I/0");
        }
    }


}
