package com.example.project55;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;



import java.util.ArrayList;

public class OrderSummaryActivity extends AppCompatActivity {

    private ListView orderListView;
    private TextView subTotalLabel, taxTotalLabel, totalLabel;
    private Button placeOrderButton, clearOrderButton;

    private OrderManager orderManager;

    private RecyclerView orderRecyclerView;
    private OrderItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderManager = OrderManager.getInstance();

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        subTotalLabel = findViewById(R.id.subTotalLabel);
        taxTotalLabel = findViewById(R.id.taxTotalLabel);
        totalLabel = findViewById(R.id.totalLabel);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        clearOrderButton = findViewById(R.id.clearOrderButton);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrderItemAdapter(this, orderManager.getCurrentOrder().getItems(), this::updateTotals);
        orderRecyclerView.setAdapter(adapter);

        updateTotals();

        placeOrderButton.setOnClickListener(v -> placeOrder());
        clearOrderButton.setOnClickListener(v -> clearOrder());
    }


    private void loadOrderItems() {
        ArrayList<String> itemDescriptions = new ArrayList<>();
        for (MenuItem item : orderManager.getCurrentOrder().getItems()) {
            itemDescriptions.add(item.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemDescriptions);
        orderListView.setAdapter(adapter);

        updateTotals();

        clearOrderButton.setEnabled(!itemDescriptions.isEmpty());
    }



    private void updateTotals() {
        Order current = orderManager.getCurrentOrder();
        subTotalLabel.setText(String.format("Subtotal: $%.2f", current.getSubtotal()));
        taxTotalLabel.setText(String.format("Tax: $%.2f", current.getTax()));
        totalLabel.setText(String.format("Total: $%.2f", current.getTotal()));
    }

    private void placeOrder() {
        if (orderManager.getCurrentOrder().getItems().isEmpty()) {
            Toast.makeText(this, "Your order is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        orderManager.finalizeOrder();   // Save to past orders + reset current order
        Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_LONG).show();
        finish();  // Go back to main screen
    }


    private void clearOrder() {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Confirm Clear")
                .setMessage("Are you sure you want to clear the order?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    orderManager.getCurrentOrder().clearItems();  // Clear the items first ✅
                    loadOrderItems();  // Then reload the list ✅
                    Toast.makeText(this, "Order cleared.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }







    @Override
    public boolean onSupportNavigateUp() {
        finish();  // Handle back button in toolbar
        return true;
    }
}
