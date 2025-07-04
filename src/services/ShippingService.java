package services;

import interfaces.IShippable;

import java.util.*;

public class ShippingService {
    public static double ship(List<IShippable> items) {
        if (items.isEmpty()) return 0;

        System.out.println("** Shipment notice **");
        Map<String, Integer> countMap = new LinkedHashMap<>();
        Map<String, Double> weightMap = new LinkedHashMap<>();
        double totalWeight = 0;

        for (IShippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
            weightMap.put(name, weight);
            totalWeight += weight;
        }

        for (String name : countMap.keySet()) {
            int quantity = countMap.get(name);
            double unitWeight = weightMap.get(name);
            double totalWeightForItem = unitWeight * quantity;

            System.out.printf("%-15s %.00fg%n", quantity + "x " + name, totalWeightForItem);
        }


        if (totalWeight >= 1000) {
            System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000);
        } else {
            System.out.printf("Total package weight %.0fg\n", totalWeight);
        }

        return countMap.values().stream().mapToInt(q -> q).sum() * 10;
    }
}
