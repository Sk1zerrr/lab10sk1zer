package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();

        // Для тестування розкоментуй потрібні строки:

        // 1
        // tasks.getBooksWithPrice().forEach(System.out::println);

        // 2
        // tasks.getOrdersWithBabyProducts().forEach(System.out::println);

        // 3
        // tasks.applyDiscountToToys().forEach(System.out::println);

        // 4
        // System.out.println(tasks.getCheapestBook().get());

        // 5
        // tasks.getRecentOrders().forEach(System.out::println);

        // 6
        // System.out.println(tasks.getBooksStats());

        // 7
        // tasks.getOrdersProductsMap().forEach((id, count) -> System.out.println(id + " : " + count));

        // 8
        // tasks.getProductsByCategory().forEach((cat, ids) -> System.out.println(cat + " : " + ids));
    }

    // ---------- TASK 1 ----------
    public List<Product> getBooksWithPrice() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .toList();
    }

    // ---------- TASK 2 ----------
    public List<Order> getOrdersWithBabyProducts() {
        return orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> "Baby".equals(p.getCategory())))
                .toList();
    }

    // ---------- TASK 3 ----------
    public List<Product> applyDiscountToToys() {
        return products.stream()
                .filter(p -> "Toys".equals(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.5))
                .toList();
    }

    // ---------- TASK 4 ----------
    public Optional<Product> getCheapestBook() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .min(Comparator.comparing(Product::getPrice));
    }

    // ---------- TASK 5 ----------
    public List<Order> getRecentOrders() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();
    }

    // ---------- TASK 6 ----------
    public DoubleSummaryStatistics getBooksStats() {
        return products.stream()
                .filter(p -> "Books".equals(p.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    // ---------- TASK 7 ----------
    public Map<Integer, Integer> getOrdersProductsMap() {
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size()
                ));
    }

    // ---------- TASK 8 ----------
    public Map<String, List<Integer>> getProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}
