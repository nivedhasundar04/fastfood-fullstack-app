package com.example.project55;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;

public class DrinkCustomization extends AppCompatActivity {

    private RecyclerView flavorRecyclerView;
    private RadioButton smallButton, mediumButton, largeButton;
    private Button increaseButton, decreaseButton, addOrderButton;
    private TextView quantityLabel;

    private Flavor selectedFlavor;
    private int quantity = 1; // Start at 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_customization);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enables back button

        flavorRecyclerView = findViewById(R.id.flavorRecyclerView);
        smallButton = findViewById(R.id.smallButton);
        mediumButton = findViewById(R.id.mediumButton);
        largeButton = findViewById(R.id.largeButton);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);
        addOrderButton = findViewById(R.id.addOrderButton);
        quantityLabel = findViewById(R.id.quantityLabel);

        setupFlavorRecyclerView();

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

        addOrderButton.setOnClickListener(v -> addDrinkToOrder());
    }

    private void setupFlavorRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3); // 3 columns
        flavorRecyclerView.setLayoutManager(layoutManager);
        FlavorAdapter adapter = new FlavorAdapter(Arrays.asList(Flavor.values()), flavor -> {
            selectedFlavor = flavor;
            Toast.makeText(this, flavor.name().replace("_", " ") + " selected", Toast.LENGTH_SHORT).show();
        });
        flavorRecyclerView.setAdapter(adapter);
    }

    private void addDrinkToOrder() {
        if (selectedFlavor == null) {
            Toast.makeText(this, "Please select a flavor!", Toast.LENGTH_SHORT).show();
            return;
        }

        Size size = Size.SMALL;
        if (mediumButton.isChecked()) size = Size.MEDIUM;
        else if (largeButton.isChecked()) size = Size.LARGE;

        Beverage beverage = new Beverage(size, selectedFlavor, quantity);

        OrderManager.getInstance().getCurrentOrder().addItem(beverage);

        Toast.makeText(this, "Drink added to order!", Toast.LENGTH_SHORT).show();
        finish();  // Go back to Main Screen
    }

    // Handle toolbar back button
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
