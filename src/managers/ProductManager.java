/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import facades.CustomerFacade;
import facades.ProductFacade;
import java.util.ArrayList;
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
    private final Scanner scanner;
    private final ProductFacade productFacade;
    private final CustomerFacade customerFacade;
    
   public ProductManager(Scanner scanner) {
    this.scanner = scanner;
    this.productFacade = new ProductFacade();
    this.customerFacade = new CustomerFacade();
}
    public void addProduct() {
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
        System.out.println("Added product: \n");
        System.out.println(product.toString());
        productFacade.create(product);      
    }

    public List<Integer> printListProducts() {
        System.out.println("-----List products ------");
        List<Product> products = productFacade.findAll();
        List<Integer> arrayProductId = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {    
            System.out.printf("%d. Type: %s. Price: %s. Quantity: %s. %s. %s%n \n",
                    i+1,
                    products.get(i).getType(),
                    products.get(i).getPrice(),
                    products.get(i).getQuantity(),
                    products.get(i).getBrand(),
                    products.get(i).getModel()
            );             
        arrayProductId.add(products.get(i).getId().intValue());
        }
         return arrayProductId;
    }
    /*
    *1.Выбираем пользователя
    *2. Выводиться товар который купил пользователь
     */
    public void printListSoldProducts(List<Purchase> purchaies) {
        
         System.out.println("----- List sold products ------");
        // Выводим список покупателей для выбора
        List<Customer> customers = customerFacade.findAll();

        // Запрашиваем у пользователя номер выбранного покупателя        
        System.out.print("Input the customer's phone number: ");
        String selectedCustomerPhone = scanner.nextLine();
       
        boolean foundPurchase = false; // Флаг для отслеживания наличия хотя бы одной покупки
        foundPurchase = true; // Устанавливаем флаг в true, так как нашли хотя бы одну покупку 
        for (int i = 0; i < purchaies.size(); i++) {                
                if (purchaies.get(i).getCustomer().getPhone().equals(selectedCustomerPhone)) {  
                  
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
    public List<Product> products(){
        return productFacade.findAll();
    }
    
    public Product findById(int id){
        return productFacade.find((long)id);
    }
    /* public List<Customer> customers(){
    return customerFacade.findAll();
    }
    
    public Customer findId(int id){
    return customerFacade.find((long)id);
    }*/
    
}


