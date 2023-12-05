/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Customer;
import facades.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import tools.CustomerManagerUtils;
import tools.KeyboardInput;

/**
 *
 * @author nikit
 */
public class CustomerManager {
    private final Scanner scanner;
    private final CustomerFacade customerFacade;
    private final CustomerManagerUtils utils;
  
    
    public CustomerManager(Scanner scanner) {
        this.scanner = scanner;
        this.customerFacade = new CustomerFacade();
        this.utils = new CustomerManagerUtils(scanner);
    }
    
    public void addCustomer(){
       Customer customer = new Customer();
        System.out.print("Input firstname: ");
        customer.setFirstname(scanner.nextLine());
        System.out.print("Enter Lastname: ");
        customer.setLastname(scanner.nextLine());
        System.out.print("Enter phone number: ");
        customer.setPhone(scanner.nextLine());
        System.out.print("Input your money: ");
        customer.setMoney(KeyboardInput.inputNumber(1, 50000));       
        System.out.println("Added customer: ");
        System.out.println(customer.toString()); 
        customerFacade.create(customer);
    }

    public List<Integer> printListCustomers() {
       System.out.println("-----List customers ------");
       List<Customer> customers = customerFacade.findAll();
       List<Integer> arrayCustomerId = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            System.out.printf("%d. %s %s. %s. %s%n \n",
                    i+1,
                    customers.get(i).getFirstname(),
                    customers.get(i).getLastname(),
                    customers.get(i).getPhone(),
                    customers.get(i).getMoney()
            );            
         arrayCustomerId.add(customers.get(i).getId().intValue());
        }
         return arrayCustomerId;
    }

    public void addMoneyToCustomer() {
        // Выводим список покупателей для выбора
        List<Customer> customers = customerFacade.findAll();
        List<Integer> listIdCustomers = printListCustomers();
        System.out.print("input number customer: ");
        int selectedCustomerNumber =(KeyboardInput.inputNumber(1, customers.size()));
        System.out.print("input amount money for add: ");
        int amountMoneyForAdd = (KeyboardInput.inputNumber(1, 5000));
        Customer selectedCustomer = customers.get(selectedCustomerNumber - 1);
        int currentMoney = selectedCustomer.getMoney();
        selectedCustomer.setMoney(currentMoney + amountMoneyForAdd);
          customerFacade.edit(selectedCustomer);
    }
   
    public void editCustomer() {
         // Выводим список покупателей для выбора    
        List<Customer> customers = customerFacade.findAll();
        List<Integer> listIdCustomers = printListCustomers();
        System.out.print("input number customer: ");
        int selectedCustomerNumber =(KeyboardInput.inputNumber(1, customers.size()));
        Customer selectedCustomer = customers.get(selectedCustomerNumber - 1);
        utils.updateCustomerField(selectedCustomer, "input name: ", Customer::getFirstname, Customer::setFirstname);
        utils.updateCustomerField(selectedCustomer, "input lastname: ", Customer::getLastname, Customer::setLastname);
        utils.updateCustomerField(selectedCustomer, "input phone number: ", Customer::getPhone, Customer::setPhone);
        utils.updateMoney(selectedCustomer);
        customerFacade.edit(selectedCustomer);
    }
    
    public List<Customer> customers(){
    return customerFacade.findAll();
    }
    
    public Customer findById(int id){
    return customerFacade.find((long)id);
    }

}
