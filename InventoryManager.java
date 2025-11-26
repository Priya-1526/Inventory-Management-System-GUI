import java.io.*;
import java.util.*;

public class InventoryManager {
    private static final String FILE_NAME = "inventory.data";
    private HashMap<Integer, Product> inventory = new HashMap<>();

    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            inventory = (HashMap<Integer, Product>) ois.readObject();
        } catch (Exception e) {
            inventory = new HashMap<>();
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Product> getInventory() {
        return inventory;
    }

    public void addProduct(Product p) {
        inventory.put(p.id, p);
    }

    public void deleteProduct(int id) {
        inventory.remove(id);
    }

    public Product searchById(int id) {
        return inventory.get(id);
    }

    public List<Product> searchByName(String name) {
        List<Product> results = new ArrayList<>();
        for (Product p : inventory.values()) {
            if (p.name.toLowerCase().contains(name.toLowerCase())) results.add(p);
        }
        return results;
    }
}
