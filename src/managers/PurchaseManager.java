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

import java.util.Scanner;

/**
 *
 * @author nikit
 */
public class PurchaseManager {
  private Scanner scanner;
  private final Customer[] customers;
  private Product[] products;
  private CustomerManager customerManager;
  private ProductManager  productManager;
  
    public PurchaseManager(Scanner scanner, ProductManager productManager, CustomerManager customerManager) {
        this.scanner = scanner;
        this.products = new Product[0];
        this.customers = new Customer[0];
        this.customerManager = customerManager;
        this.productManager = productManager;
    }
  public Purchase sellProduct(Product[] products, Customer[] customers){
      Purchase purchase = new Purchase();
       /*
            1.Вывести список покупателей
            2.Выбрать покупателя
            3.Вывести список товаров
            4.Покупатель вводит номер товара
            5.Покупатель вводит количество количество товара 
            5.Проверка на количества денег у покупателя
            6.Добавить в purchase дату покупки товара
       */
       customerManager.printListCustomers(customers);
       System.out.print("input number customer: ");
       int selectedCustomerNumber = scanner.nextInt();scanner.nextLine();
       purchase.setCustomer(customers[selectedCustomerNumber-1]);
       productManager.printListProducts(products); 
       int selectedProductNumber = scanner.nextInt();scanner.nextLine();
       if (product.price <= purchase.getCustomer().money) { // проверка на достаточность денег
                    purchase.setProduct(product);
                    purchase.setDate(new GregorianCalendar().getTime());
      }else{
           System.out.println("Not enouth money");
       } 
      return purchase;
  }
  
  
  
  
}
