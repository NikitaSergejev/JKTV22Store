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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.Scanner;
import java.util.stream.Collectors;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
public class PurchaseManager {
  private final Scanner scanner;  
  private final CustomerFacade customerFacade;
  private final ProductFacade  productFacade;
  private final PurchaseFacade purchaseFacade;
  private final CustomerManager customerManager;
  private final ProductManager productManager;
  
    public PurchaseManager(Scanner scanner) {
        this.scanner = scanner;      
        this.customerFacade = new CustomerFacade();
        this.productFacade = new ProductFacade();
        this.purchaseFacade = new PurchaseFacade();
        this.customerManager = new CustomerManager(scanner);
        this.productManager = new ProductManager(scanner);
    }
  public void sellProduct(){
      Purchase purchase = new Purchase();
      List<Customer> customers = customerFacade.findAll();
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
       customerManager.printListCustomers();    
       System.out.print("input number customer: ");
       int selectedCustomerNumber =(KeyboardInput.inputNumber(1, customers.size()));
       purchase.setCustomer(customers.get(selectedCustomerNumber-1));
       List<Product> products = productFacade.findAll();
       productManager.printListProducts(); 
       int selectedProductNumber = (KeyboardInput.inputNumber(1, products.size()));
      if (products.get(selectedProductNumber-1).getQuantity() > 0)
      if (products.get(selectedProductNumber-1).getPrice() <= purchase.getCustomer().getMoney())    
      {
          purchase.setProduct(products.get(selectedProductNumber-1));
          
          System.out.print("input quantity of the product: ");
          int quantity = (KeyboardInput.inputNumber(1, products.get(selectedProductNumber - 1).getQuantity()));
          purchase.setQuantity(quantity);
            
          products.get(selectedProductNumber-1).setQuantity(products.get(selectedProductNumber-1).getQuantity()-1);
          purchase.setDate(new GregorianCalendar().getTime());         
          purchase.getCustomer().setMoney(purchase.getCustomer().getMoney() - products.get(selectedProductNumber-1).getPrice());
          purchaseFacade.create(purchase);
          System.out.println("Purchase saved successfully!");
      
      }else{
          System.out.println("Foto camera not enought or no money");         
      }  
  
  
  }
 

    public int printAmoundPriceForAllTheTime(List<Purchase> purchaies) {
        int totalSpentAmount = 0;

        for (Purchase purchase : purchaies) {
            totalSpentAmount += purchase.getProduct().getPrice();
        }
        System.out.println("Total amount spent: " + totalSpentAmount + "€");
        return totalSpentAmount;
            
        }
     /**
     * Алгоритм метода
     * 1.Создание mapProducts
     * 2.Проходим по всему товару purchaies
     * и если в mapProducts нет ключа с товаром из истории
     *  добавляем ключ и устанавливаем значение 1
     * иначе
     * по ключу обновляем значение увеличивая его на 1
     * 3.Отсортировать mapProducts по значениям 
     * 4. Ввывести ключ и значение сортированного sortedMapProducts
     * 
     */

    /* public void RatingMostPopularCustomer(List<Purchase> purchaies) {
    Map<Customer,Integer> mapCustomers = new HashMap<>();
    for(int i=0; i< purchaies.size(); i++) {
    if(!mapCustomers.containsKey(purchaies.get(i).getCustomer())){
    mapCustomers.put(purchaies.get(i).getCustomer(), 1);
    }else {
    mapCustomers.put(purchaies.get(i).getCustomer(), mapCustomers.get(purchaies.get(i).getCustomer())+1);
    }
    }
    //sort
    Map<Customer,Integer> sortedMapCustomers = mapCustomers.entrySet()
    .stream()
    .sorted(Map.Entry.<Customer,Integer>comparingByValue().reversed())
    .collect(Collectors.toMap(
    Map.Entry:: getKey,
    Map.Entry::getValue,
    (oldValue, newValue) -> oldValue,
    LinkedHashMap::new));
    int n = 1;
    for (Map.Entry<Customer,Integer> entry : sortedMapCustomers.entrySet()) {
    System.out.printf("%d. %s: %s: Purchases %d%n",
    n,
    entry.getKey().getFirstname(),
    entry.getKey().getLastname(),
    entry.getValue()
    );
    n++;
    
    }
    }
    
    public void RatingMostPopularProducts(List<Purchase> purchaies) {
    Map<Product,Integer> mapProducts = new HashMap<>();
    for(int i=0; i< purchaies.size(); i++) {
    if(!mapProducts.containsKey(purchaies.get(i).getProduct())){
    mapProducts.put(purchaies.get(i).getProduct(), 1);
    }else {
    mapProducts.put(purchaies.get(i).getProduct(), mapProducts.get(purchaies.get(i).getProduct())+1);
    }
    }
    //sort
    Map<Product,Integer> sortedMapBooks = mapProducts.entrySet()
    .stream()
    .sorted(Map.Entry.<Product,Integer>comparingByValue().reversed())
    .collect(Collectors.toMap(
    Map.Entry:: getKey,
    Map.Entry::getValue,
    (oldValue, newValue) -> oldValue,
    LinkedHashMap::new));
    int n = 1;
    for (Map.Entry<Product,Integer> entry : sortedMapBooks.entrySet()) {
    System.out.printf("%d. %s: %s: Quantity: %d%n",
    n,
    entry.getKey().getBrand(),
    entry.getKey().getModel(),
    entry.getValue()
    );
    n++;
    
    }
    
    }*/
    /*public List<Product> products(){
    return productFacade.findAll();
    }
    
    public Product findById(int id){
    return productFacade.find((long)id);
    }
    public List<Customer> customers(){
    return customerFacade.findAll();
    }
    
    public Customer findId(int id){
    return customerFacade.find((long)id);
    }      */
    
}
