# fastfood-fullstack-app
# RU-Burger Fullstack Application
This full-stack application features both a GUI and an Android interface, supported by a robust backend, to function as a fast food order management system. It allows users to place orders for a variety of items—including burgers, sides, drinks, and combo meals—while automatically calculating prices based on selections. The application includes dedicated screens for browsing menu items, reviewing orders, and completing the checkout process. It also offers interactive buttons to customize orders, clear current selections, and finalize purchases.

### Key Features
- Menu navigation across burgers, sandwiches, sides, and beverages
- Order customization (bread, protein, add-ons)
- Combo options with side and drink selections
- Quantity and size selection for all items
- Real-time price updates as you build your order
- GUI images dynamically update based on item selections
- Order summary includes tax (6.625%) and total
- Ability to add/remove items and view placed orders
- Export orders to a .txt file for recordkeeping


### Project Structure
```bash
fastfood-fullstack-app/
├── src/
│   ├── model/         # Java model classes (MenuItem, Burger, Sandwich, etc.)
│   ├── controller/    # JavaFX controller classes
│   ├── resources/
│   │   ├── *.fxml     # GUI layout files
│   │   └── image/     # Images used in GUI
├── test/              # JUnit test cases for Sandwich and Burger
├── doc/               # Javadoc files
├── diagram/           # Lucidchart class diagram
