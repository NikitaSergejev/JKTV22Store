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
import tools.KeyboardInput;

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
            7.Вычисления денег у покупателя после покупки
       */
       customerManager.printListCustomers(customers);
       System.out.print("input number customer: ");
       int selectedCustomerNumber =(KeyboardInput.inputNumber(1, 100));
       purchase.setCustomer(customers[selectedCustomerNumber-1]);
       productManager.printListProducts(products); 
       int selectedProductNumber = (KeyboardInput.inputNumber(1, 500));
       Product selectedProduct = products[selectedProductNumber - 1];
       if (selectedProduct != null) {
       System.out.print("input quantity: ");   
       int selectedQuantityProduct = (KeyboardInput.inputNumber(1, 500));
       if (selectedProduct.getQuantity() >= selectedQuantityProduct){
       selectedProduct.setQuantity(selectedProduct.getQuantity() - selectedQuantityProduct);
        System.out.println("Quantity product enough");
       }else{
       System.out.println("Not enough, sold out");
       }     
       if (selectedProduct.getPrice() <= purchase.getCustomer().getMoney()) {           
        purchase.setProduct(selectedProduct);
        purchase.setDate(new GregorianCalendar().getTime());
        purchase.getCustomer().setMoney(purchase.getCustomer().getMoney() - selectedProduct.getPrice());
    } else {   
        System.out.println("Not enough money, or quantity product");
    }    
  }   else {
    System.out.println("Invalid product selection");
}    
  
  return purchase;
  }
}
