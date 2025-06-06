package com.example.project55;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ComboCustomization extends AppCompatActivity {

    // UI Components
    private LinearLayout burgerOptionsLayout, sandwichOptionsLayout;
    private RadioButton briocheBurgerButton, wheatBurgerButton, pretzelBurgerButton, sandwichOptionButton, burgerOptionButton;
    private RadioButton briocheSandwichButton, wheatSandwichButton, pretzelSandwichButton, bagelButton, sourdoughButton;
    private RadioButton roastBeefButton, salmonButton, chickenButton;
    private RadioButton singlePattyButton, doublePattyButton;
    private CheckBox lettuceOption, tomatoOption, onionsOption, avocadoOption, cheeseOption;
    private RadioButton friesButton, chipsButton, onionRingsButton, appleSlicesButton;
    private RadioButton colaButton, teaButton, juiceButton;
    private Button increaseButton, decreaseButton, addOrderButton;
    private TextView quantityLabel;

    private int quantity = 1; // Default starting quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_customization);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enables back button

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        burgerOptionsLayout = findViewById(R.id.burgerOptionsLayout);
        sandwichOptionsLayout = findViewById(R.id.sandwichOptionsLayout);

        burgerOptionButton = findViewById(R.id.burgerOptionButton);
        sandwichOptionButton = findViewById(R.id.sandwichOptionButton);

        // Burger Bread selection
        briocheBurgerButton = findViewById(R.id.briocheBurgerButton);
        wheatBurgerButton = findViewById(R.id.wheatBurgerButton);
        pretzelBurgerButton = findViewById(R.id.pretzelBurgerButton);

        // Sandwich Bread selection
        briocheSandwichButton = findViewById(R.id.briocheSandwichButton);
        wheatSandwichButton = findViewById(R.id.wheatSandwichButton);
        pretzelSandwichButton = findViewById(R.id.pretzelSandwichButton);
        bagelButton = findViewById(R.id.bagelButton);
        sourdoughButton = findViewById(R.id.sourdoughButton);

        // Protein options
        roastBeefButton = findViewById(R.id.roastBeefButton);
        salmonButton = findViewById(R.id.salmonButton);
        chickenButton = findViewById(R.id.chickenButton);

        // Add-ons
        lettuceOption = findViewById(R.id.lettuceOption);
        tomatoOption = findViewById(R.id.tomatoOption);
        onionsOption = findViewById(R.id.onionsOption);
        avocadoOption = findViewById(R.id.avocadoOption);
        cheeseOption = findViewById(R.id.cheeseOption);

        // Side options
        friesButton = findViewById(R.id.friesButton);
        chipsButton = findViewById(R.id.chipsButton);
        onionRingsButton = findViewById(R.id.onionRingsButton);
        appleSlicesButton = findViewById(R.id.appleSlicesButton);

        // Drink options
        colaButton = findViewById(R.id.colaButton);
        teaButton = findViewById(R.id.teaButton);
        juiceButton = findViewById(R.id.juiceButton);

        // Quantity controls
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        quantityLabel = findViewById(R.id.quantityLabel);

        addOrderButton = findViewById(R.id.addOrderButton);

        quantityLabel.setText(String.valueOf(quantity));
    }

    private void setupListeners() {
        burgerOptionButton.setOnClickListener(v -> showBurgerOptions());
        sandwichOptionButton.setOnClickListener(v -> showSandwichOptions());

        increaseButton.setOnClickListener(v -> {
            quantity++;
            quantityLabel.setText(String.valueOf(quantity));
        });

        decreaseButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });

        addOrderButton.setOnClickListener(v -> addComboToOrder());
    }

    private void showBurgerOptions() {
        burgerOptionsLayout.setVisibility(View.VISIBLE);
        sandwichOptionsLayout.setVisibility(View.GONE);
    }

    private void showSandwichOptions() {
        burgerOptionsLayout.setVisibility(View.GONE);
        sandwichOptionsLayout.setVisibility(View.VISIBLE);
    }

    private void addComboToOrder() {
        boolean burgerSelected = roastBeefButton.isChecked(); // default assumption
        Protein protein = Protein.ROAST_BEEF;

        // Determine protein
        if (salmonButton.isChecked()) {
            protein = Protein.SALMON;
            burgerSelected = false;
        } else if (chickenButton.isChecked()) {
            protein = Protein.CHICKEN;
            burgerSelected = false;
        }

        // Determine bread
        Bread bread;
        if (burgerSelected) {
            if (wheatBurgerButton.isChecked()) bread = Bread.WHEAT;
            else if (pretzelBurgerButton.isChecked()) bread = Bread.PRETZEL;
            else bread = Bread.BRIOCHE;
        } else {
            if (wheatSandwichButton.isChecked()) bread = Bread.WHEAT;
            else if (pretzelSandwichButton.isChecked()) bread = Bread.PRETZEL;
            else if (bagelButton.isChecked()) bread = Bread.BAGEL;
            else if (sourdoughButton.isChecked()) bread = Bread.SOURDOUGH;
            else bread = Bread.BRIOCHE;
        }

        // Add-ons
        ArrayList<AddOns> addOns = new ArrayList<>();
        if (lettuceOption.isChecked()) addOns.add(AddOns.LETTUCE);
        if (tomatoOption.isChecked()) addOns.add(AddOns.TOMATOES);
        if (onionsOption.isChecked()) addOns.add(AddOns.ONIONS);
        if (avocadoOption.isChecked()) addOns.add(AddOns.AVOCADO);
        if (cheeseOption.isChecked()) addOns.add(AddOns.CHEESE);

        // Side
        SideType sideType = SideType.FRIES;
        if (chipsButton.isChecked()) sideType = SideType.CHIPS;
        else if (onionRingsButton.isChecked()) sideType = SideType.ONION_RINGS;
        else if (appleSlicesButton.isChecked()) sideType = SideType.APPLE_SLICES;
        Side side = new Side(Size.SMALL, sideType, 1);

        // Drink
        Flavor flavor = Flavor.COLA;
        if (teaButton.isChecked()) flavor = Flavor.TEA;
        else if (juiceButton.isChecked()) flavor = Flavor.JUICE;
        Beverage drink = new Beverage(Size.MEDIUM, flavor, 1);

        // Create sandwich or burger
        Sandwich sandwich;
        if (burgerSelected) {
            boolean isDoublePatty = doublePattyButton.isChecked();
            sandwich = new Burger(bread, protein, addOns, 1, isDoublePatty);
        } else {
            sandwich = new Sandwich(bread, protein, addOns, 1);
        }

        // Assemble combo
        Combo combo = new Combo(sandwich, drink, side, quantity);

        OrderManager.getInstance().getCurrentOrder().addItem(combo);

        Toast.makeText(this, "Combo added to order!", Toast.LENGTH_SHORT).show();
        finish();
    }

    // Handle toolbar back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
