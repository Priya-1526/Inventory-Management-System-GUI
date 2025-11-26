import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String name;
    public int quantity;
    public double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        double totalValue = quantity * price;
        return String.format("ID: %-5d | Name: %-20s | Qty: %-5d | Price: %-7.2f | Total: %.2f",
                id, name, quantity, price, totalValue);
    }
}
