import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

// Item class
class Item {
    private final String itemId;
    private final String name;
    private final String description;
    private final double price;

    public Item(String itemId, String name, String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(itemId, item.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}

// ShoppingCart class
class ShoppingCart {
    private final Map<Item, Integer> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public void addToCart(Item item, int quantity) {
        cart.put(item, cart.getOrDefault(item, 0) + quantity);
        System.out.println(quantity + " " + item.getName() + "(s) added.");
    }

    public Integer displayQty(String itemId) {
        for (Item item : cart.keySet()) {
            if (item.getItemId().equals(itemId)) return cart.get(item);
        }
        return 0;
    }

    public void updateQty(String itemId, int quantity) {
        for (Item item : cart.keySet()) {
            if (item.getItemId().equals(itemId)) {
                cart.put(item, quantity);
                System.out.println("Updated " + item.getName() + " to " + quantity);
                return;
            }
        }
        System.out.println("Item not found.");
    }

    public void deleteItem(String itemId) {
        Item toRemove = null;
        for (Item item : cart.keySet()) {
            if (item.getItemId().equals(itemId)) {
                toRemove = item;
                break;
            }
        }
        if (toRemove != null) {
            cart.remove(toRemove);
            System.out.println(toRemove.getName() + " removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    public double displayBill() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}


public class ShoppingCartApp {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Item");
            System.out.println("2. Display Quantity");
            System.out.println("3. Update Quantity");
            System.out.println("4. Delete Item");
            System.out.println("5. Display Total");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();

                    Item newItem = new Item(id, name, desc, price);
                    cart.addToCart(newItem, qty);
                    break;

                case 2:
                    System.out.print("Enter Item ID: ");
                    String itemIdQty = scanner.nextLine();
                    System.out.println("Quantity: " + cart.displayQty(itemIdQty));
                    break;

                case 3:
                    System.out.print("Enter Item ID: ");
                    String itemIdUpdate = scanner.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int newQty = scanner.nextInt();
                    cart.updateQty(itemIdUpdate, newQty);
                    break;

                case 4:
                    System.out.print("Enter Item ID: ");
                    String itemIdDelete = scanner.nextLine();
                    cart.deleteItem(itemIdDelete);
                    break;

                case 5:
                    System.out.println("Total Bill: $" + cart.displayBill());
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}


