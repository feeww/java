package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        List<Product> catalog = List.of(product1, product2, product3);
        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Видалити товар з кошика");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів за назвою або категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Product p : catalog) System.out.println(p);
                    break;
                case 2:
                    System.out.print("Введіть ID товару для додавання: ");
                    int addId = scanner.nextInt();
                    Product toAdd = null;
                    for (Product p : catalog) {
                        if (p.getId() == addId) {
                            toAdd = p;
                            break;
                        }
                    }
                    if (toAdd != null) {
                        cart.addProduct(toAdd);
                        System.out.println("Товар успішно додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;
                case 3:
                    System.out.println(cart);
                    break;
                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик уже порожній.");
                    } else {
                        System.out.print("Введіть ID товару для видалення з кошика: ");
                        int removeId = scanner.nextInt();
                        if (cart.removeProductById(removeId)) {
                            System.out.println("Товар видалено з кошика.");
                        } else {
                            System.out.println("Товар з таким ID у кошику відсутній.");
                        }
                    }
                    break;
                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("\nІсторія замовлень (" + orderHistory.size() + "):");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("\n--- Замовлення №" + (i + 1) + " ---");
                            System.out.println(orderHistory.get(i));
                        }
                    }
                    break;
                case 7:
                    System.out.print("Введіть пошуковий запит (назва або категорія): ");
                    String query = scanner.nextLine().trim().toLowerCase();
                    boolean found = false;
                    System.out.println("\nРезультати пошуку:");
                    for (Product p : catalog) {
                        if (p.getName().toLowerCase().contains(query)
                                || p.getCategory().getName().toLowerCase().contains(query)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("За вашим запитом нічого не знайдено.");
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}