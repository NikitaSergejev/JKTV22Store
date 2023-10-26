/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv22store;

import entity.Product;
import java.util.Arrays;
import java.util.Scanner;
import managers.ProductManager;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
class App {
    //private final Product[] products;
    private final Scanner scanner;
    private final ProductManager productManager;
    
    public App(){
      this.scanner = new Scanner(System.in);  
      this.productManager = new ProductManager(scanner);  
    }
    void run() {
         boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Add new product");
            System.out.println("2.Add new customer");
            System.out.println("3.Print list products");
            System.out.println("4.Sold a product to customer");
            System.out.println("5.Print list customers");
            System.out.println("6.Print list sold out products");
            System.out.print("Set task: ");
             int task = KeyboardInput.inputNumber(0, 6);             
            switch (task) {
                case 0:
                    System.out.println("Good buy");
                    repeat = false;
                    break;
                case 1:
                   //   addProductToArray(productManager.addProduct());    
                    Product product = new Product();
                    System.out.print("Please input type photo camera: ");
                    product.setType(scanner.nextLine());
                    System.out.print("input price (not over 5000): ");
                    product.setPrice(KeyboardInput.inputNumber(1, 5000));
                    System.out.print("Input quantity: ");
                    product.setQuantity(KeyboardInput.inputNumber(1, 3000));
                    System.out.print("Input brand: ");
                    product.setBrand(scanner.nextLine());
                    System.out.print("Input model: ");
                    product.setModel(scanner.nextLine());



                    System.out.println("Added product: ");
                    System.out.println(product.toString());
                    /*ProductManager productManager = new ProductManager(scanner);
                    addProductToArray(productManager.addProduct());*/
                    break;
                case 2:                   
                    
                    break;
                case 3:
                   
                    break;
                case 4:                 
                    
                    break;
                 case 5:
                    
                    break;
                 case 6:
                    
                    break;                
                default:
                    System.out.println("Choice number from list !");;
            }
        } while (repeat);
    }

   /* private void addProductToArray(Product product) { //Добавление продукта в массив
      this.products = Arrays.copyOf(products, products.length + 1);
      this.products[products.length -1 ] = product;   
    }*/
}
    
