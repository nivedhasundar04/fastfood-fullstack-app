package com.example.project55;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

public class ViewOrdersActivity extends AppCompatActivity {

    private ListView pastOrdersListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Enables back button

        pastOrdersListView = findViewById(R.id.pastOrdersListView);

        loadPastOrders();
    }

    private void loadPastOrders() {
        ArrayList<String> ordersList = new ArrayList<>();
        for (Order order : OrderManager.getInstance().getPastOrders()) {
            ordersList.add(order.toString()); // Assuming your Order class has a good toString()!
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ordersList);
        pastOrdersListView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
