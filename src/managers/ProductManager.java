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
import facades.PurchaseFacade;
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
    private final CustomerManager customerManager;
    private final PurchaseFacade purchaseFacade;
    
   public ProductManager(Scanner scanner) {
    this.scanner = scanner;
    this.productFacade = new ProductFacade();
    this.customerFacade = new CustomerFacade();
    this.purchaseFacade = new PurchaseFacade();
    this.customerManager = new CustomerManager(scanner);
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
    public void printListSoldProducts() {
        
         System.out.println("----- List sold products ------");
        // Выводим список покупателей для выбора
        List<Customer> customers = customerFacade.findAll();
        List<Integer> listIdCustomers = customerManager.printListCustomers();
        
        // Запрашиваем у пользователя номер выбранного покупателя
        System.out.print("Input the customer's ID: ");
        int selectedCustomerId = KeyboardInput.inputNumberFromRange(listIdCustomers);

        // Получаем информацию о выбранном покупателе по идентификатору
        Customer selectedCustomer = customers.get(selectedCustomerId - 1);

        // Выводим список продуктов, которые купил выбранный покупатель
        List<Purchase> purchases = purchaseFacade.findAll();
        boolean foundPurchase = false; // Флаг для отслеживания наличия хотя бы одной покупки

        for (int i = 0; i < purchases.size(); i++) {
            if (purchases.get(i).getCustomer().getId().equals(selectedCustomer.getId())) {
                System.out.printf("%d. Brand: %s Model %s Type: %s. Price: %s, " + 
                        "Over quantity in store: %s. \n Customer: %s %s, Phone: %s%n",
                        i + 1,
                        purchases.get(i).getProduct().getBrand(),
                        purchases.get(i).getProduct().getModel(),
                        purchases.get(i).getProduct().getType(),
                        purchases.get(i).getProduct().getPrice(),
                        purchases.get(i).getProduct().getQuantity(),
                        purchases.get(i).getCustomer().getFirstname(),
                        purchases.get(i).getCustomer().getLastname(),
                        purchases.get(i).getCustomer().getPhone()
                );
                foundPurchase = true; // Устанавливаем флаг в true, так как нашли хотя бы одну покупку
            }
        
    }

    // Проверяем флаг и выводим сообщение об ошибке, если покупок не найдено
    if (!foundPurchase) {
        System.out.println("No purchases for the selected customer.");
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

    public void addQuantity() {
         List<Product> products = productFacade.findAll();
        List<Integer> listIdProducts = printListProducts();
        System.out.print("input number product: ");
        int selectedProductNumber = (KeyboardInput.inputNumber(1, products.size()));
        System.out.print("input amount quantity for add: ");
        int quantityToAdd = (KeyboardInput.inputNumber(1, 5000));

        Product selectedProduct = products.get(selectedProductNumber - 1);
        int currentQuantity = selectedProduct.getQuantity();
        selectedProduct.setQuantity(currentQuantity + quantityToAdd);
        productFacade.edit(selectedProduct);
    }
    
}


