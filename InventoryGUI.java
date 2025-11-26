import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class InventoryGUI extends JFrame {
    private InventoryManager manager;
    private JTable table;
    private DefaultTableModel model;

    private JTextField idField, nameField, qtyField, priceField;

    public InventoryGUI() {
        manager = new InventoryManager();
        manager.load();

        setTitle("Inventory Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table
        String[] cols = {"ID", "Name", "Quantity", "Price", "Total"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        refreshTable();

        JScrollPane scrollPane = new JScrollPane(table);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        idField = new JTextField();
        nameField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(qtyField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);

        // Button Panel
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton saveBtn = new JButton("Save");

        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(saveBtn);

        // Add Listeners
        addBtn.addActionListener(e -> addProduct());
        deleteBtn.addActionListener(e -> deleteProduct());
        searchBtn.addActionListener(e -> searchProduct());
        saveBtn.addActionListener(e -> {
            manager.save();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        });

        // Layout
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addProduct() {
    try {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int qty = Integer.parseInt(qtyField.getText());
        double price = Double.parseDouble(priceField.getText());

        manager.addProduct(new Product(id, name, qty, price));
        refreshTable();

        // Clear input fields
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input!");
    }
}

    private void deleteProduct() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a product to delete!");
            return;
        }
        int id = Integer.parseInt(model.getValueAt(row, 0).toString());
        manager.deleteProduct(id);
        refreshTable();
    }

    private void searchProduct() {
        String input = JOptionPane.showInputDialog(this, "Enter ID or Name:");
        if (input == null || input.isEmpty()) return;

        model.setRowCount(0);
        if (input.matches("\\d+")) {
            Product p = manager.searchById(Integer.parseInt(input));
            if (p != null)
                model.addRow(new Object[]{p.id, p.name, p.quantity, p.price, p.quantity * p.price});
            else
                JOptionPane.showMessageDialog(this, "No product found!");
        } else {
            java.util.List<Product> results = manager.searchByName(input);
            for (Product p : results)
                model.addRow(new Object[]{p.id, p.name, p.quantity, p.price, p.quantity * p.price});
            if (results.isEmpty()) JOptionPane.showMessageDialog(this, "No product found!");
        }
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Product p : manager.getInventory().values()) {
            model.addRow(new Object[]{p.id, p.name, p.quantity, p.price, p.quantity * p.price});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryGUI::new);
    }
}
