/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import facades.PurchaseFacade;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import managers.PurchaseManager;

/**
 *
 * @author nikit
 */
public class PurchaseManagerUtils {
    private final PurchaseFacade purchaseFacade;

    public PurchaseManagerUtils() {
        this.purchaseFacade = new PurchaseFacade() {};
    }
                             /*Rating customer*/
   public Map<Customer, Long> calculateCustomerRatingForDay(int numYear, int numMonth, int numDay) {
       
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
        return customerRating;
       
    }
   
    public Map<Customer, Long> calculateCustomerRatingForMonth(int numYear, int numMonth) {
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
        return customerRating;
    }
    public Map<Customer, Long> calculateCustomerRatingForYear(int numYears) {
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
        return customerRating;
    } 
    
                                    /*Rating products*/
    public Map<Product, Long> calculateProductRatingForDay(int numYear, int numMonth, int numDay) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfDay(numYear, numMonth, numDay);

        // Создаем Map для хранения рейтинга покупателей
        Map<Product, Long> productRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getProduct, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        productRating = productRating.entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYear + "/" + numMonth + "/" + numDay + ":");
        for (Map.Entry<Product, Long> entry : productRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getBrand(),
                    entry.getKey().getModel(),
                    entry.getValue()
            );
        }
        return productRating;
    }
   
    public Map<Product, Long> calculateProductRatingForMonth(int numYear, int numMonth) {
         List<Purchase> purchases = purchaseFacade.findPurchaseOfMonth(numYear,numMonth);
        // Создаем Map для хранения рейтинга покупателей
        Map<Product, Long> productRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getProduct, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        productRating = productRating.entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYear + "/" + numMonth + "/");
        for (Map.Entry<Product, Long> entry : productRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getBrand(),
                    entry.getKey().getModel(),
                    entry.getValue()
            );
       
        }
        return productRating;
    }

    public Map<Product, Long> calculateProductRatingForYear(int numYears) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfYear(numYears);
        // Создаем Map для хранения рейтинга покупателей
        Map<Product, Long> productRating = purchases.stream()
                .collect(Collectors.groupingBy(Purchase::getProduct, Collectors.counting()));

        // Сортируем Map по убыванию количества покупок
        productRating = productRating.entrySet().stream()
                .sorted(Map.Entry.<Product, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // Выводим результаты
        System.out.println("Customer Rating for " + numYears + "/");
        for (Map.Entry<Product, Long> entry : productRating.entrySet()) {
            System.out.printf("%s %s: Purchases %d%n",
                    entry.getKey().getBrand(),
                    entry.getKey().getModel(),
                    entry.getValue()
            );
       
        }
        return productRating;
    }
}
