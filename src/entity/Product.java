/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author nikit
 */
public class Product {
   private String type;
   private int price;
   private int quantity;
   private String brand;
   private String Model;

    public Product() {
    }

    public Product(String type, int price, int quantity, String brand, String Model) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.Model = Model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + this.price;
        hash = 79 * hash + this.quantity;
        hash = 79 * hash + Objects.hashCode(this.brand);
        hash = 79 * hash + Objects.hashCode(this.Model);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.price != other.price) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (!Objects.equals(this.Model, other.Model)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "type=" + type + ", price=" + price + ", quantity=" + quantity + ", brand=" + brand + ", Model=" + Model + '}';
    }

}

