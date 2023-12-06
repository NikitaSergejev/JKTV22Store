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
        printAmoundPriceForAllTheTime();
        printAmountPriceForYear();
        printAmountPriceForMonth();
        printAmountPriceForDay();
    }
    public int printAmoundPriceForAllTheTime() {
        List<Purchase> purchaies = purchaseFacade.findAll();
        int totalSpentAmount = 0;

    for (Purchase purchase : purchaies) {
            totalSpentAmount += purchase.getProduct().getPrice()*purchase.getQuantity();
    }
        System.out.println("\n Total amount spent: " + totalSpentAmount + "€ \n" );
        return totalSpentAmount;
            
        }                  
      public int printAmountPriceForYear() {
        List<Purchase> purchases = getFilteredPurchases(Calendar.YEAR);
        int totalSpentAmount = calculateTotalAmount(purchases);
        System.out.println("\nTotal amount spent for the year: " + totalSpentAmount + "€\n");
        return totalSpentAmount;
    }

    public int printAmountPriceForMonth() {
        List<Purchase> purchases = getFilteredPurchases(Calendar.MONTH);
        int totalSpentAmount = calculateTotalAmount(purchases);
        System.out.println("\nTotal amount spent for the month: " + totalSpentAmount + "€\n");
        return totalSpentAmount;
    }

    public int printAmountPriceForDay() {
        List<Purchase> purchases = getFilteredPurchases(Calendar.DAY_OF_MONTH);
        int totalSpentAmount = calculateTotalAmount(purchases);
        System.out.println("\nTotal amount spent for the day: " + totalSpentAmount + "€\n");
        return totalSpentAmount;
    }

    private List<Purchase> getFilteredPurchases(int field) {
        List<Purchase> purchases = purchaseFacade.findAll();
        Calendar today = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String todayString = dateFormat.format(today.getTime());

        return purchases.stream()
                .filter(purchase -> dateFormat.format(purchase.getDate()).equals(todayString))
                .collect(Collectors.toList());
    }

    private int calculateTotalAmount(List<Purchase> purchases) {
        int totalSpentAmount = 0;

        for (Purchase purchase : purchases) {
            totalSpentAmount += purchase.getProduct().getPrice()*purchase.getQuantity();
        }

        return totalSpentAmount;
    } 
}
