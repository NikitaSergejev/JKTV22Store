/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Product;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 *
 * @author pupil
 */
public class ProductManagerUtils {
    private final Scanner scanner;

    public ProductManagerUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getInput(String prompt, String currentValue) {
        System.out.print(prompt);
        String newValue = scanner.nextLine();
        return newValue.isEmpty() ? currentValue : newValue;
    }

    public void updateProductField(Product product, String prompt, Function<Product, String> getter, BiConsumer<Product, String> setter) {
        String newValue = getInput(prompt, getter.apply(product));
        if (!newValue.equals(getter.apply(product))) {
            setter.accept(product, newValue);
        }
    }
     public void addQuantity(Product product, int quantityToAdd) {
        int currentQuantity = product.getQuantity();
        product.setQuantity(currentQuantity + quantityToAdd);
    }
}
