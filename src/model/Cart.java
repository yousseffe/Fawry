package model;

import interfaces.IExpirable;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quntity) {
        if (quntity > product.getQuantity()) {
            throw new IllegalArgumentException("Quantity not available for " + product.getName());
        }
        if (product instanceof IExpirable && ((IExpirable) product).isExpired()) {
            throw new IllegalArgumentException(product.getName() + " is expired");
        }
        CartItem cartItem = new CartItem(product , quntity);
        items.add(cartItem);
    }
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }
    public boolean isEmpty() {
        return items.isEmpty();
    }
    public List<CartItem> getItems() {
        return items;
    }
}
