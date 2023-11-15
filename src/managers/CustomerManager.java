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
    private List<Customer> customers;
    private CustomerManager customerManager;
    public CustomerManager(Scanner scanner, CustomerManager customerManager) {
        this.scanner = scanner;
        this.customerManager = customerManager;
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
       System.out.println("-----List customers ------");
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

    public Customer addMoneyToCustomer(List<Customer> customers) {
        // Выводим список покупателей для выбора
        customerManager.printListCustomers(customers);
        System.out.print("input number customer: ");
       int selectedCustomerNumber =(KeyboardInput.inputNumber(1, customers.size()));
       System.out.print("input amount money for add: ");
       int amountMoneyForAdd = (KeyboardInput.inputNumber(1, 5000));
        if (amountMoneyForAdd != 0) {
          Customer selectedCustomer = customers.get(selectedCustomerNumber - 1);
          int currentMoney = selectedCustomer.getMoney();
          selectedCustomer.setMoney(currentMoney + amountMoneyForAdd);
          return selectedCustomer;
            
        }
        return null;
    }
    
    public void setCustomerManager(CustomerManager customerManager) {
       this.customerManager = customerManager;
       
    }
    
}
