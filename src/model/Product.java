package model;

public class Product {
    public String name;
    public double price;
    public int quantity;
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int amount) {
        if (amount > this.quantity) {
            throw new IllegalArgumentException("insufficient amount to decrease");
        }
        this.quantity -= amount;
    }
    void increaseQuantity(int amount) {
        this.quantity += amount;
    }
}
