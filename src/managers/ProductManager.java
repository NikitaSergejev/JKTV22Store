/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Product;
import java.util.Scanner;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 *  private String type;
   private int price;
   private int quantity;
   private String brand;
   private String Model;
 */
public class ProductManager {
    private Scanner scanner;
    public ProductManager(Scanner scanner) {
        this.scanner = scanner;
    }
    public Product addProduct() {
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
        return product;
    }
}
