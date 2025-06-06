package com.example.project55;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SideCustomization extends AppCompatActivity {

    private Spinner sizeSpinner;
    private Button decreaseButton, increaseButton, addOrderButton;
    private TextView quantityLabel, sideTitle;

    private int quantity = 1;
    private SideType sideType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_customization);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get the SideType passed from MainActivity
        sideType = (SideType) getIntent().getSerializableExtra("sideType");

        sizeSpinner = findViewById(R.id.sizeSpinner);
        decreaseButton = findViewById(R.id.decreaseButton);
        increaseButton = findViewById(R.id.increaseButton);
        addOrderButton = findViewById(R.id.addOrderButton);
        quantityLabel = findViewById(R.id.quantityLabel);
        sideTitle = findViewById(R.id.sideTitle);

        sideTitle.setText("Customize " + sideType.name().replace("_", " ").toLowerCase());

        // Setup the Spinner
        String[] sizes = {"Small", "Medium", "Large"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);

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

        addOrderButton.setOnClickListener(v -> addToOrder());
    }

    private void addToOrder() {
        Size size = Size.SMALL;
        String selectedSize = sizeSpinner.getSelectedItem().toString();
        if (selectedSize.equals("Medium")) size = Size.MEDIUM;
        else if (selectedSize.equals("Large")) size = Size.LARGE;

        Side side = new Side(size, sideType, quantity);
        OrderManager.getInstance().getCurrentOrder().addItem(side);

        Toast.makeText(this, sideType.name().replace("_", " ") + " added to order!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
