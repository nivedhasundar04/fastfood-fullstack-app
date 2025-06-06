package com.example.project55;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.ArrayList;

public class BurgerCustomizationActivity extends AppCompatActivity {

    private CheckBox avocadoOption, cheeseOption, lettuceOption, onionsOption, tomatoOption;
    private RadioButton briocheButton, wheatButton, pretzelButton;
    private RadioButton singleOption, doubleOption;
    private Button decreaseButton, increaseButton, addOrderButton;
    private TextView quantityLabel;

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_customization);

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

        singleOption = findViewById(R.id.singleOption);
        doubleOption = findViewById(R.id.doubleOption);

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

        Bread bread = Bread.BRIOCHE;
        if (wheatButton.isChecked()) bread = Bread.WHEAT;
        else if (pretzelButton.isChecked()) bread = Bread.PRETZEL;

        ArrayList<AddOns> addOns = new ArrayList<>();
        if (lettuceOption.isChecked()) addOns.add(AddOns.LETTUCE);
        if (tomatoOption.isChecked()) addOns.add(AddOns.TOMATOES);
        if (onionsOption.isChecked()) addOns.add(AddOns.ONIONS);
        if (avocadoOption.isChecked()) addOns.add(AddOns.AVOCADO);
        if (cheeseOption.isChecked()) addOns.add(AddOns.CHEESE);

        boolean isDouble = doubleOption.isChecked();

        Burger burger = new Burger(bread, Protein.ROAST_BEEF, addOns, quantity, isDouble);

        // Add to shared order using Singleton
        OrderManager.getInstance().getCurrentOrder().addItem(burger);

        Toast.makeText(this, "Burger added to order!", Toast.LENGTH_SHORT).show();
        finish();  // Close activity
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();  // Go back to main menu
        return true;
    }
}
