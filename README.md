#  Inventory Management System (Java Swing)

A simple and clean **desktop-based inventory management application** built using **Java Swing**, featuring product addition, deletion, searching, and automatic data persistence.

---

## 🚀 Features

* Add new products with ID, name, quantity, and price
* Delete selected products from the table
* Search products by ID or name
* Automatically calculates total price (Quantity × Price)
* Stores data locally in a file (`inventory.data`)
* Loads previously saved data on startup
* Clean and responsive Swing UI

---

## 🛠️ Technologies Used

* **Java** (JDK 8+)
* **Java Swing** (GUI)
* **Object Serialization** for saving data
* **HashMap** for inventory storage

---

## 📁 Project Structure

```
├── InventoryGUI.java        # Main GUI window
├── InventoryManager.java    # Handles saving/loading & inventory logic
├── Product.java             # Product model class
├── inventory.data           # Auto-created data file
└── README.md
```

---

## ▶ How to Run

### **1️⃣ Compile the program**

```bash
javac InventoryGUI.java InventoryManager.java Product.java
```

### **2️⃣ Run the application**

```bash
java InventoryGUI
```

### ✔ Requirements

* Java JDK 8 or higher installed

---


## 📌 Future Enhancements

* Edit/Update product details
* Sorting by price, quantity, or name
* Better validation for inputs
* Enhanced UI using JavaFX
* CSV export/import

---


## ⭐ If you like this project

Feel free to star the repository!
