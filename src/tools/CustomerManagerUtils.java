/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Customer;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 *
 * @author pupil
 */
public class CustomerManagerUtils {
    private final Scanner scanner;

    public CustomerManagerUtils(Scanner scanner) {
        this.scanner = scanner;
    }
    private String getInput(String prompt, String currentValue) {
        System.out.print(prompt);
        String newValue = scanner.nextLine();
        return newValue.isEmpty() ? currentValue : newValue;
    }
    public void updateCustomerField(Customer customer, String prompt, Function<Customer, String> getter, BiConsumer<Customer, String> setter) {
        String newValue = getInput(prompt, getter.apply(customer));
        if (!newValue.equals(getter.apply(customer))) {
            setter.accept(customer, newValue);
        }
    }
    public void updateMoney(Customer customer) {
        System.out.print("input amount money for add: ");
        int amountMoneyForAdd = (KeyboardInput.inputNumber(1, 5000));
        int currentMoney = customer.getMoney();
        customer.setMoney(currentMoney + amountMoneyForAdd);
    }
    
}
