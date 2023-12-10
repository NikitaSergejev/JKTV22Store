/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nikit
 */
public class KeyboardInput {
     public static int inputNumberFromRange(Integer min, Integer max) {
        
        Scanner scanner = new Scanner(System.in);
        int number = min - 1;
        boolean isNumber = true;
        boolean repeat;
        do{
            try {
                number = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                isNumber = false;
            }
            if(max == null ){
               if((number >= min) && isNumber){
                repeat = false; 
               }else{
                    System.out.printf("Enter number from next range: %d .. %d: ",min,max);
                    isNumber = true;
                    repeat = true;
                }
            }else{
                if((number >= min && number <= max) && isNumber){
                    repeat = false;
                }else{
                    System.out.printf("Enter number from next range: %d .. %d: ",min,max);
                    isNumber = true;
                    repeat = true;
                }
            }
        }while(repeat);
        return number;
    }

    public static String inputSymbolYesOrNo() {
        Scanner scanner = new Scanner(System.in);
        do{
            String symbol = scanner.nextLine();
            if(symbol.equals("n") || symbol.equals("y")){
                return symbol;
            }
            System.out.print("For continue press \"y\", exit press \"n\": ");
        }while(true);
    }
    
    public static int inputNumberFromRange(List<Integer> listId){
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        do{
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                if(!listId.contains(number)){
                    continue;
                }
                return number;
            } catch (Exception e) {
                scanner.nextLine();
            }
        }while(true);
        
    }
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
