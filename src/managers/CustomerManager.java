/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import java.util.List;
import java.util.Scanner;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
public class CustomerManager {
    private Scanner scanner;
    public CustomerManager(Scanner scanner) {
         this.scanner = scanner;
    }
    public Customer addCustomer(){
       Customer customer = new Customer();
        System.out.print("Input firstname: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Enter Lastname: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Enter phone number: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Input your money: ");
        customer.setMoney(KeyboardInput.inputNumber(1, 50000));
        System.out.println("Added reader: ");
        System.out.println(customer.toString());
        return customer;
    }

    public void printListCustomers(List<Customer> customers) {
       System.out.println("-----List readers ------");
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s %s. %s. %s%n",
                    i+1,
                    customers.get(i).getFirstname(),
                    customers.get(i).getLastname(),
                    customers.get(i).getPhone(),
                    customers.get(i).getMoney()
            );            
        } 
    }
    
}
