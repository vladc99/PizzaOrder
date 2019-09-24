package com.vladc.vlad_crihan_a_1.model.model;

import java.util.Arrays;

/**
 *
 * @author Vlad Crihan
 * @date Sept 17,2019
 * Description : A pizzaOrder object that stores the toppings,size and if
 * it's a delivery.
 * When the object is initiated the default value for the delivery is false.
 * Note: The toppings will be saved even if the array is empty
 */
public class PizzaOrder {

    private String[] toppings;
    private String size;
    private boolean delivery;

    public PizzaOrder() {
        this.delivery = false;
    };
    
    public String[] getToppings() {
        return toppings;
    };
    
    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    };

    public String getSize() {
        String temp;
        //Changing the size "To readable english"
        switch (this.size) {
            case ("smallPizza"):
                temp = "Small Pizza";
                break;
            case ("mediumPizza"):
                temp = "Medium Pizza";
                break;
            case ("largePizza"):
                temp = "Large Pizza";
                break;
            default:
                temp = "Size not found";
                break;
        }
        return temp;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public double getPrice() {
        double price = 0;
        
        //adding the price depeding on the size
        switch (this.size) {
            case ("smallPizza"):
                price += 5;
                break;
            case ("mediumPizza"):
                price += 7;
                break;
            case ("largePizza"):
                price += 9;
                break;
        }
        //check for delivery, if applicable add delivery fee
        if (this.delivery) {
            price += 2;
        }
        //check if the array is empty, if not add $1/topping
        if (!Arrays.toString(this.toppings).equals("null")) {
            for (int i = 0; i < toppings.length; i++) {
                if (toppings[i] != null) {
                    price += 1;
                }
            }
        }

        return price;
    }

}
