/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author nikit
 */
public class KeyboardInput {
   public static int inputNumber(int min, int max){
       Scanner scanner = new Scanner(System.in);
       int answer = 0;
       while(true){
           System.out.print("input number from next range" +min + "..." + max + ": ");
            try {
                 answer = scanner.nextInt();                
                if (answer >= min && answer <= max) {
                    break;
                } else {
                    System.out.println("Input out of range. Please try again.");
                }
            }catch(InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Очистите ввод, чтобы избежать зацикливания
            }
       }        
        return answer;        
    } 
}
