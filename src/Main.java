import model.*;
import services.CheckOutService;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product cheese = new ExpirableShippableProduct("Cheese", 100, 5, LocalDate.of(2025, 11, 1), 200);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 150, 3, LocalDate.of(2026, 1, 1), 700);
        Product scratchCard = new Product("Mobile Scratch Card", 50, 10);

        Customer customer = new Customer("Ahmed", 1000);
        Cart cart = new Cart();

        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);

        CheckOutService.checkout(customer, cart);
    }
}