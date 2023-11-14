/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.Scanner;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
public class PurchaseManager {
  private Scanner scanner;
  private List<Customer> customers;
  private List<Product> products;
  private CustomerManager customerManager;
  private ProductManager  productManager;
  
    public PurchaseManager(Scanner scanner, ProductManager productManager, CustomerManager customerManager) {
        this.scanner = scanner;      
        this.customerManager = customerManager;
        this.productManager = productManager;
    }
  public Purchase sellProduct(List<Product> products, List<Customer> customers){
      Purchase purchase = new Purchase();
       /*
            1.Вывести список покупателей
            2.Выбрать покупателя
            3.Вывести список товаров
            4.Покупатель вводит номер товара
            5.Покупатель вводит количество количество товара 
            5.Проверка на количества денег у покупателя
            6.Добавить в purchase дату покупки товара
            7.Вычисления денег у покупателя после покупки
       */
       customerManager.printListCustomers(customers);
       System.out.print("input number customer: ");
       int selectedCustomerNumber =(KeyboardInput.inputNumber(1, 100));
       purchase.setCustomer(customers.get(selectedCustomerNumber-1));
       productManager.printListProducts(products); 
       int selectedProductNumber = (KeyboardInput.inputNumber(1, 500));
      if (products.get(selectedProductNumber-1).getQuantity() > 0)
      if (products.get(selectedProductNumber-1).getPrice() <= purchase.getCustomer().getMoney())    
      {
          purchase.setProduct(products.get(selectedProductNumber-1));
          products.get(selectedProductNumber-1).setQuantity(products.get(selectedProductNumber-1).getQuantity()-1);
          purchase.setDate(new GregorianCalendar().getTime());
          
          purchase.getCustomer().setMoney(purchase.getCustomer().getMoney() - products.get(selectedProductNumber-1).getPrice());
      
      
      }else{
          System.out.println("Foto camera not enought or no money");
          return null;
      }
    return purchase;
  
  
  }

    public int printAmoundPriceForAllTheTime(List<Purchase> purchaies) {
        int totalSpentAmount = 0;

        for (Purchase purchase : purchaies) {
            totalSpentAmount += purchase.getProduct().getPrice();
        }
        System.out.println("Total amount spent: " + totalSpentAmount + "€");
        return totalSpentAmount;
            
        }
        
    
}
