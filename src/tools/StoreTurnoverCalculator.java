/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Purchase;
import facades.PurchaseFacade;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author pupil
 */
public class StoreTurnoverCalculator {
    private final PurchaseFacade purchaseFacade; 
    
    public StoreTurnoverCalculator() {
    this.purchaseFacade = new PurchaseFacade() {};

    }
     public void printStoreTurnover() {     
        System.out.println("\n");
        boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Amount price for year");
            System.out.println("2.Amount price for month");
            System.out.println("3.Amount price for day"); 
            System.out.println("4.Amound price for all the time");
            System.out.print("Set task: ");
            System.out.println("\n");
            int task = KeyboardInput.inputNumber(0, 4);
            switch (task) {
                case 0:
                     System.out.println("Rating closes \n");
                    repeat = false;
                    break;
                    
                case 1:
                      System.out.print("Введите год: ");
                      int numYears = (KeyboardInput.inputNumber(2023, 2050));
                      
                      printAmountPriceForYear(numYears); // Рейтинг за год                                          
                      break;
                case 2:     
                      System.out.print("Введите год: ");
                      int numYear = (KeyboardInput.inputNumber(2023, 2050));

                      System.out.print("Введите месяц: ");
                      int numMonth = (KeyboardInput.inputNumber(1, 12));
                      
                      printAmountPriceForMonth(numYear,numMonth); // Рейтинг за месяц                   
                    break;
                case 3:
                      System.out.print("Введите год: ");
                      int year = (KeyboardInput.inputNumber(2023, 2050));

                      System.out.print("Введите месяц: ");
                      int month = (KeyboardInput.inputNumber(1, 12));

                      System.out.print("Введите день: ");
                      int day = (KeyboardInput.inputNumber(1, 31));

                      printAmountPriceForDay(year, month, day); // Рейтинг за день
                      break;
                case 4:
                    printAmoundPriceForAllTheTime();
                    break;
                default:
                    System.out.println("Choice number from list !");
            }
            
        }while(repeat);
       
        }
    
    public void printAmoundPriceForAllTheTime() {
        List<Purchase> purchaies = purchaseFacade.findAll();
        int totalSpentAmount = 0;

    for (Purchase purchase : purchaies) {
            totalSpentAmount += purchase.getProduct().getPrice()*purchase.getQuantity();
    }
        System.out.println("\n Total amount spent: " + totalSpentAmount + "€ \n" );
        
            
        }                  
      public long printAmountPriceForYear(int numYears) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfYear(numYears);
        long totalSpentAmount = calculateTotalAmount(purchases);
        System.out.println("\nTotal amount spent for the year: " + totalSpentAmount + "€\n");
        return totalSpentAmount;
    }

    public long printAmountPriceForMonth(int numYear, int numMonth) {
        List<Purchase> purchases = purchaseFacade.findPurchaseOfMonth(numYear,numMonth);
        long totalSpentAmount = calculateTotalAmount(purchases);
        System.out.println("\nTotal amount spent for the month: " + totalSpentAmount + "€\n");
        return totalSpentAmount;
    }

    public long printAmountPriceForDay(int numYear, int numMonth, int numDay) {
       List<Purchase> purchases = purchaseFacade.findPurchaseOfDay(numYear, numMonth, numDay);
        long totalSpentAmount = calculateTotalAmountForDay(purchases);
        System.out.println("\nTotal amount spent for the day: " + totalSpentAmount + "€\n");
        return  totalSpentAmount;
    }
    
    
    private long calculateTotalAmount(List<Purchase> purchases) {
        long totalSpentAmount = 0;

        for (Purchase purchase : purchases) {
            totalSpentAmount += purchase.getProduct().getPrice() * purchase.getQuantity();
        }

        return totalSpentAmount;
    }
    
    private long calculateTotalAmountForDay(List<Purchase> purchases) {
        long totalSpentAmount = 0;

        for (Purchase purchase : purchases) {
            totalSpentAmount += purchase.getProduct().getPrice()*purchase.getQuantity();
        }

        return totalSpentAmount;
    }
        
}
