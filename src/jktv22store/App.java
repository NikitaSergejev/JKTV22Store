/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv22store;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import java.util.Arrays;
import java.util.Scanner;
import managers.CustomerManager;
import managers.ProductManager;
import managers.PurchaseManager;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
class App {
    private Product[] products;
    private Customer[] customers;
    private Purchase[] purchaies;
    private final Scanner scanner;
    private final ProductManager productManager;
    private final CustomerManager customerManager;
    private final PurchaseManager purchaseManager;
    
    public App(){
      this.products = new Product[0];
      this.customers = new Customer[0];
      this.purchaies = new Purchase[0];
      this.scanner = new Scanner(System.in);  
      this.productManager = new ProductManager(scanner);  
      this.customerManager = new CustomerManager(scanner);
      this.purchaseManager = new PurchaseManager(scanner, productManager, customerManager);
    }
    void run() {
         boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------Photography world------------");
        System.out.println("Hello it`s shop where sold photo camera");
        do {
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Add new product");
            System.out.println("2.Add new customer");
            System.out.println("3.Print list products");            
            System.out.println("4.Print list customers");
            System.out.println("5.Sold a product to customer");            
            System.out.println("6.Print list sold out products");
            System.out.println("7.Add money customer");
            System.out.println("8.All price all sold products for all the time");
            System.out.print("Set task: ");
            int task = KeyboardInput.inputNumber(0, 8);             
            switch (task) {
                case 0:
                    System.out.println("Good buy");
                    repeat = false;
                    break;
                case 1:
                    addProductToArray(productManager.addProduct());                        
                    break;
                case 2:                   
                     addCustomerToArray(customerManager.addCustomer());
                    break;
                case 3:
                    productManager.printListProducts(products);
                    break;
                case 4:                 
                    customerManager.printListCustomers(customers);
                    break;
                case 5:
                    addPurchaseToArray(purchaseManager.sellProduct(products, customers)); 
                    break;
                case 6:
                    //productManager.printListSoldProducts(purchaies);
                    break;
                case 7:
                    //customerManager.addMoneyToCustomer(customers);
                    
                    break;
                case 8:
                    //purchaseManager.printAmoundPriceForAllTheTime();
                    
                    break;
                default:
                    System.out.println("Choice number from list !");;
            }
        } while (repeat);
    }

    private void addProductToArray(Product product) { //Добавление продукта в массив
      this.products = Arrays.copyOf(products, products.length + 1);
      this.products[products.length -1 ] = product;   
    }

    private void addCustomerToArray(Customer customer) {
      this.customers = Arrays.copyOf(customers, customers.length + 1);
        this.customers[customers.length -1 ] = customer;
    }

    private void addPurchaseToArray(Purchase purchase) {     
        this.purchaies = Arrays.copyOf(purchaies, purchaies.length + 1);
        this.purchaies[purchaies.length -1] = purchase;
    }
}
    
