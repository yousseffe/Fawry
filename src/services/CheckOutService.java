package services;

import interfaces.*;
import model.Cart;
import model.CartItem;
import model.Customer;
import model.Product;

import java.time.LocalDate;
import java.util.*;

public  class CheckOutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        List<IShippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int qty = item.getQuantity();
            subtotal += qty * product.getPrice();

            if (product instanceof IShippable) {
                for (int i = 0; i < qty; i++) {
                    shippableItems.add((IShippable) product);
                }
            }
        }

        double shippingFee = ShippingService.ship(shippableItems);
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) throw new IllegalStateException("Insufficient balance");

        for (CartItem item : cart.getItems()) {
            item.getProduct().decreaseQuantity(item.getQuantity());
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            int quantity = item.getQuantity();
            String name = item.getProduct().getName();
            double totalPrice = quantity * item.getProduct().getPrice();

            System.out.printf("%-15s %.0f%n", quantity + "x " + name, totalPrice);
        }
        System.out.println("----------------------");
        System.out.printf("%-10s %.00f\n", "Subtotal", subtotal);
        System.out.printf("%-10s %.00f\n", "Shipping", shippingFee);
        System.out.printf("%-10s %.00f\n", "Amount", total);

        customer.deductBalance(total);
    }
}
