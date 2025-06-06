package com.example.project55;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



import java.util.ArrayList;

public class SandwichCustomization extends AppCompatActivity {

    private CheckBox avocadoOption, cheeseOption, lettuceOption, onionsOption, tomatoOption;
    private RadioButton briocheButton, wheatButton, pretzelButton, bagelButton, sourdoughButton;
    private RadioButton roastBeefOption, salmonOption, chickenOption;
    private Button decreaseButton, increaseButton, addOrderButton;
    private TextView quantityLabel;

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_customization); // ⚡ Make sure your XML is named correctly!

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI components
        avocadoOption = findViewById(R.id.avocadoOption);
        cheeseOption = findViewById(R.id.cheeseOption);
        lettuceOption = findViewById(R.id.lettuceOption);
        onionsOption = findViewById(R.id.onionsOption);
        tomatoOption = findViewById(R.id.tomatoOption);

        briocheButton = findViewById(R.id.briocheButton);
        wheatButton = findViewById(R.id.wheatButton);
        pretzelButton = findViewById(R.id.pretzelButton);
        bagelButton = findViewById(R.id.bagelButton);
        sourdoughButton = findViewById(R.id.sourdoughButton);

        roastBeefOption = findViewById(R.id.roastBeefOption);
        salmonOption = findViewById(R.id.salmonOption);
        chickenOption = findViewById(R.id.chickenOption);

        decreaseButton = findViewById(R.id.decreaseButton);
        increaseButton = findViewById(R.id.increaseButton);
        addOrderButton = findViewById(R.id.addOrderButton);

        quantityLabel = findViewById(R.id.quantityLabel);

        // Set listeners
        increaseButton.setOnClickListener(v -> increaseQuantity());
        decreaseButton.setOnClickListener(v -> decreaseQuantity());
        addOrderButton.setOnClickListener(v -> addToOrder());
    }

    private void increaseQuantity() {
        quantity++;
        quantityLabel.setText(String.valueOf(quantity));
    }

    private void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
        }
    }

    private void addToOrder() {
        if (quantity <= 0) {
            Toast.makeText(this, "Quantity must be at least 1.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Bread selection
        Bread bread = Bread.BRIOCHE;
        if (wheatButton.isChecked()) bread = Bread.WHEAT;
        else if (pretzelButton.isChecked()) bread = Bread.PRETZEL;
        else if (bagelButton.isChecked()) bread = Bread.BAGEL;
        else if (sourdoughButton.isChecked()) bread = Bread.SOURDOUGH;

        // Protein selection
        Protein protein = Protein.ROAST_BEEF;
        if (salmonOption.isChecked()) protein = Protein.SALMON;
        else if (chickenOption.isChecked()) protein = Protein.CHICKEN;

        // Add-ons selection
        ArrayList<AddOns> addOns = new ArrayList<>();
        if (lettuceOption.isChecked()) addOns.add(AddOns.LETTUCE);
        if (tomatoOption.isChecked()) addOns.add(AddOns.TOMATOES);
        if (onionsOption.isChecked()) addOns.add(AddOns.ONIONS);
        if (avocadoOption.isChecked()) addOns.add(AddOns.AVOCADO);
        if (cheeseOption.isChecked()) addOns.add(AddOns.CHEESE);

        // Create the Sandwich object
        Sandwich sandwich = new Sandwich(bread, protein, addOns, quantity);

        // Add to the current order
        OrderManager.getInstance().getCurrentOrder().addItem(sandwich);

        Toast.makeText(this, "Sandwich added to order!", Toast.LENGTH_SHORT).show();
        finish();  // Close this screen and go back
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();  // Go back to main menu
        return true;
    }

}
