/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktv22store;

import java.util.Scanner;

/**
 *
 * @author nikit
 */
class App {

    void run() {
         boolean repeat = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Select task: ");
            System.out.println("0. Exit");
            System.out.println("1.Add new item");
            System.out.println("2.Add new customer");
            System.out.println("3.Sold a item to customer");
            System.out.println("4.Print list customers");
            System.out.println("5.Print list items");
            System.out.println("6.Print list sold out items");
            System.out.print("Set task: ");
            int task = scanner.nextInt();scanner.nextLine();            
            switch (task) {
                case 0:
                    System.out.println("Good buy");
                    repeat = false;
                    break;
                case 1:
                                   
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
}
    
