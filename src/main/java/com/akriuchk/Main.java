package com.akriuchk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Item> list = Arrays.asList(new Item(0.5, "SPB"),
                new Item(0.3, "Berlin"),
                new Item(0.15, "Munchen"),
                new Item(0.05, "Tokyo"));

        List<Item> orderList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            orderList.add(getRandomItem(list));
        }

        for (Item item : list) {
            System.out.println(item.name + ": " + orderList.stream()
                    .filter(order -> order.equals(item))
                    .count());
        }

    }

    static Item getRandomItem(List<Item> items) {
        double p = Math.random();
        double cumulativeProbability = 0.0;
        for (Item item : items) {
            cumulativeProbability += item.probability;
            if (p <= cumulativeProbability) {
                return item;
            }
        }
        return null;
    }



    static class Item {
        double probability;
        String name;

        public Item(double probability, String name) {
            this.probability = probability;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Double.compare(item.probability, probability) == 0 &&
                    Objects.equals(name, item.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(probability, name);
        }
    }
}
