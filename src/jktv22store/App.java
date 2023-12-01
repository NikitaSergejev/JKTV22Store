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
import java.util.List;
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
    private final Scanner scanner;
    private final ProductManager productManager;
    private final CustomerManager customerManager;
    private final PurchaseManager purchaseManager;
    
    public App(){
      this.scanner = new Scanner(System.in);  
      this.customerManager = new CustomerManager(scanner);
      this.productManager = new ProductManager(scanner);      
      this.purchaseManager = new PurchaseManager(scanner);
      //this.customerManager.setCustomerManager(this.customerManager);
    }
    
    void run() {
         boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------Photography world------------");
        System.out.println("Hello it`s shop where sold photo camera");
        System.out.println("---------------------------------------");
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
            System.out.println("9.Rating most popular customer");
            System.out.println("10.Rating most popular products");
            System.out.print("Set task: ");
            int task = KeyboardInput.inputNumber(0, 10);             
            switch (task) {
                case 0:
                    System.out.println("Good buy");
                    repeat = false;
                    break;
                case 1:
                    productManager.addProduct();
                                          
                    break;
                case 2:                   
                    customerManager.addCustomer();
                    
                    break;
                    case 3:
                    productManager.printListProducts();
                    break;
                   /*  case 4:
                    customerManager.printListCustomers();
                    break;
                    case 5:
                    purchaseManager.sellProduct();
                    break;
                    case 6:
                    productManager.printListSoldProducts();
                    break;
                    case 7:
                    customerManager.addMoneyToCustomer();
                    break;
                    case 8:
                    purchaseManager.printAmoundPriceForAllTheTime();
                    break;
                    case 9:
                    purchaseManager.RatingMostPopularCustomer();
                    
                    break;
                    case 10:
                    purchaseManager.RatingMostPopularProducts();
                    
                    break;*/
                default:
                    System.out.println("Choice number from list !");;
            }
        } while (repeat);
    }

    
}
    
