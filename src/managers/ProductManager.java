/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import java.util.List;
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
    private List<Customer> customers;
    private CustomerManager customerManager;
    
   public ProductManager(Scanner scanner, CustomerManager customerManager) {
    this.scanner = scanner;
    this.customerManager = customerManager;
}
    public Product addProduct() {
       Product product = new Product();
        System.out.print("Please input type photo camera: ");
        product.setType(scanner.nextLine());
        System.out.print("input price: ");
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

    public void printListProducts(List<Product> products) {
        System.out.println("-----List products ------");
        for (int i = 0; i < products.size(); i++) {    
            System.out.printf("%d. Type: %s. Price: %s. Quantity: %s. %s. %s%n",
                    i+1,
                    products.get(i).getType(),
                    products.get(i).getPrice(),
                    products.get(i).getQuantity(),
                    products.get(i).getBrand(),
                    products.get(i).getModel()
            );             
        }
    }
    /*
    *1.Выбираем пользователя
    *2. Выводиться товар который купил пользователь
     */
    public void printListSoldProducts(List<Purchase> purchaies, List<Customer> customers) {
        
         System.out.println("----- List sold products ------");
        // Выводим список покупателей для выбора
        customerManager.printListCustomers(customers);

        // Запрашиваем у пользователя номер выбранного покупателя        
        System.out.print("Input the customer's phone number: ");
        String selectedCustomerPhone = scanner.nextLine();
       
        boolean foundPurchase = false; // Флаг для отслеживания наличия хотя бы одной покупки

        for (int i = 0; i < purchaies.size(); i++) {                
                if (purchaies.get(i).getCustomer().getPhone().equals(selectedCustomerPhone)) {  
                   foundPurchase = true; // Устанавливаем флаг в true, так как нашли хотя бы одну покупку 
                System.out.printf("%d. Brand: %s Model %s Type: %s. Price: %s," + 
                     "Over quantity in store: %s. \n Customer: %s %s, Phone: %s%n",
                     i+1,
                     purchaies.get(i).getProduct().getBrand(),
                     purchaies.get(i).getProduct().getModel(),
                     purchaies.get(i).getProduct().getType(),
                     purchaies.get(i).getProduct().getPrice(),
                     purchaies.get(i).getProduct().getQuantity(),                     
                     purchaies.get(i).getCustomer().getFirstname(),
                     purchaies.get(i).getCustomer().getLastname(),
                     purchaies.get(i).getCustomer().getPhone()                   
                );
        
    }

    // Проверяем флаг и выводим сообщение об ошибке, если покупок не найдено
    if (!foundPurchase) {
        System.out.println("Invalid phone number or customer did not make any purchases.");
    }
        
    }
    }
}


