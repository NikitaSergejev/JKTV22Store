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
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
  private final Purchase purchases;
  
    public PurchaseManager(Scanner scanner) {
        this.scanner = scanner;      
        this.customerFacade = new CustomerFacade();
        this.productFacade = new ProductFacade() {};
        this.purchaseFacade = new PurchaseFacade() {};
        this.customerManager = new CustomerManager(scanner);
        this.productManager = new ProductManager(scanner);
        this.purchases = new Purchase();
    }
  public void sellProduct(){
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
       Purchase purchase = new Purchase();
        List<Customer> customers = customerManager.customers();
        List<Product> products = productManager.products();

        List<Integer> listIdCustomers = customerManager.printListCustomers();
        System.out.print("Input number customer: ");
        int selectedCustomerNumber = (KeyboardInput.inputNumberFromRange(listIdCustomers));
        purchase.setCustomer(customers.get(selectedCustomerNumber - 1));

        List<Integer> listIdProducts = productManager.printListProducts();
        System.out.print("Input number product: ");
        int selectedProductNumber = (KeyboardInput.inputNumberFromRange(listIdProducts));
        Product selectedProduct = products.get(selectedProductNumber - 1);

        if (selectedProduct.getQuantity() > 0 && selectedProduct.getPrice() <= purchase.getCustomer().getMoney()) {
            System.out.print("Input quantity of the product: ");
            int quantity = (KeyboardInput.inputNumber(1, selectedProduct.getQuantity()));
            
            purchase.setProduct(selectedProduct);
            purchase.setQuantity(quantity);
            purchase.setDate(new GregorianCalendar().getTime());
            double totalPrice = selectedProduct.getPrice() * quantity;
            purchase.getCustomer().setMoney((int) (purchase.getCustomer().getMoney() - totalPrice));

            
            selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
            purchaseFacade.create(purchase);
            
            // Обновление баланса покупателя в базе данных
            customerFacade.edit(purchase.getCustomer());
            
            // Обновление информации о продукте в базе данных
            productFacade.edit(selectedProduct);
            System.out.println("Purchase saved successfully!");
        } else {
            System.out.println("Not enough quantity or not enough money");
        }
    }
      
  
                            /*Rating customer*/
   public void calculateCustomerRatingForDay(int numYear, int numMonth, int numDay) {
       
        List<Purchase> purchases = purchaseFacade.findPurchaseOfDay(numYear, numMonth, numDay);

        // Создаем Map для хранения рейтинга покупателей
        Map<Customer, Long> customerRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getCustomer, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        customerRating = customerRating.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYear + "/" + numMonth + "/" + numDay + ":");
        for (Map.Entry<Customer, Long> entry : customerRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getFirstname(),
                    entry.getKey().getLastname(),
                    entry.getValue()
            );
        }
       
    }
   
    public void calculateCustomerRatingForMonth(int numYear, int numMonth) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfMonth(numYear,numMonth);
        // Создаем Map для хранения рейтинга покупателей
        Map<Customer, Long> customerRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getCustomer, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        customerRating = customerRating.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYear + "/" + numMonth + "/");
        for (Map.Entry<Customer, Long> entry : customerRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getFirstname(),
                    entry.getKey().getLastname(),
                    entry.getValue()
            );
       
        }
    }
    
    public void calculateCustomerRatingForYear(int numYears) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfYear(numYears);
        // Создаем Map для хранения рейтинга покупателей
        Map<Customer, Long> customerRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getCustomer, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        customerRating = customerRating.entrySet().stream()
                .sorted(Map.Entry.<Customer, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYears + "/");
        for (Map.Entry<Customer, Long> entry : customerRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getFirstname(),
                    entry.getKey().getLastname(),
                    entry.getValue()
            );
       
        }
    }
   
    

    public void RatingMostPopularCustomer() {
        System.out.println("\n");
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
         do {
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Rating for the year");
            System.out.println("2.Rating for the month");
            System.out.println("3.Rating for the day");                          
            System.out.print("Set task: ");
            System.out.println("\n");
            int task = KeyboardInput.inputNumber(0, 3);             
            switch (task) {
                case 0:
                    System.out.println("Rating closes \n");
                    repeat = false;
                    break;
                case 1:
                      System.out.print("Введите год: ");
                      int numYears = (KeyboardInput.inputNumber(1, 2050));
                      
                      calculateCustomerRatingForYear(numYears); // Рейтинг за год                                          
                      break;
                case 2:     
                      System.out.print("Введите год: ");
                      int numYear = (KeyboardInput.inputNumber(1, 2050));

                      System.out.print("Введите месяц: ");
                      int numMonth = (KeyboardInput.inputNumber(1, 12));
                      
                      calculateCustomerRatingForMonth(numYear,numMonth); // Рейтинг за месяц                   
                    break;
                case 3:
                      System.out.print("Введите год: ");
                      int year = (KeyboardInput.inputNumber(1, 2050));

                      System.out.print("Введите месяц: ");
                      int month = (KeyboardInput.inputNumber(1, 12));

                      System.out.print("Введите день: ");
                      int day = (KeyboardInput.inputNumber(1, 31));

                      calculateCustomerRatingForDay(year, month, day); // Рейтинг за день
                      break;
                 default:
                    System.out.println("Choice number from list !");
               
            }
        
        }while (repeat);
    }
    
                                    /*Rating products*/
   /* public void calculateProductRatingForDay(int numYear, int numMonth, int numDay) {
        List<Purchase> purchases = calculatePurchasesForDay(numYear, numMonth, numDay);
        // ... (остальной код)
    }
   
    public void calculateProductRatingForMonth(int numYear, int numMonth) {
        List<Purchase> purchases = calculatePurchasesForMonth(numYear, numMonth);
        // ... (остальной код)
    }

    public void calculateProductRatingForYear(int numYear) {
        List<Purchase> purchases = calculatePurchasesForYear(numYear);
        // ... (остальной код)
    }*/
    
    
   public void RatingMostPopularProducts() {
       /*  System.out.println("\n");
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Rating for the year");
            System.out.println("2.Rating for the month");
            System.out.println("3.Rating for the day");                          
            System.out.print("Set task: ");
            System.out.println("\n");
            int task = KeyboardInput.inputNumber(0, 3);
            switch (task) {
                case 0:
                     System.out.println("Rating closes \n");
                    repeat = false;
                    break;
                    
                case 1:
                    calculateProductRating(Calendar.YEAR); // Рейтинг за год
                    break;
                case 2:
                    calculateProductRating(Calendar.MONTH); // Рейтинг за месяц
                    break;
                case 3:
                    calculateProductRating(Calendar.DAY_OF_MONTH); // Рейтинг за день
                    break;
                default:
                    System.out.println("Choice number from list !");
            }
            
        }while(repeat);*/
       
        }
    
    }


    
    
