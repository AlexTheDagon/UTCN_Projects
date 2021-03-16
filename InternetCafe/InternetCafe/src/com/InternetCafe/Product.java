package com.InternetCafe;

public class Product {

    private String name;
    private String type;
    private int quantity = 0;
    private double price_buy;
    private double price_sell;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice_buy() {
        return price_buy;
    }

    public void setPrice_buy(double price_buy) {
        this.price_buy = price_buy;
    }

    public double getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(double price_sell) {
        this.price_sell = price_sell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product(String name, String type, int quantity, double price_buy, double price_sell){
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.price_buy = price_buy;
        this.price_sell = price_sell;
    }

    // Returns the amoung of money used to increase the stock
    public double buy(int quantity) {
        this.quantity += quantity;
        return quantity * this.price_buy;
    }

    //Returns the amount of money that we got form selling the product to customers
    public double sell(int quantity) {

        this.quantity -= quantity;
        return quantity * this.price_sell;
    }

}
