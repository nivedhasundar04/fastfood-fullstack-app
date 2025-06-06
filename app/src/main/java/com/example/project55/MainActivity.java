package com.example.project55;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar with cart icon
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /** Inflate the menu (adds items to the action bar) */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /** Handle toolbar item clicks */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            startActivity(new Intent(this, OrderSummaryActivity.class));
            return true;
        } else if (id == R.id.action_view_orders) {
            startActivity(new Intent(this, ViewOrdersActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /** Called when Burger "Customize" button is clicked */
    public void openBurgerCustomization(android.view.View view) {
        startActivity(new Intent(this, BurgerCustomizationActivity.class));
    }

    /** Example: Called when Sandwich "Customize" button is clicked */
    public void openSandwichCustomization(View view) {
        Intent intent = new Intent(this, SandwichCustomization.class);
        startActivity(intent);
    }

    public void openComboCustomization(View view) {
        Intent intent = new Intent(this, ComboCustomization.class);
        startActivity(intent);
    }

    public void openDrinksSelection(View view) {
        Intent intent = new Intent(this, DrinkCustomization.class);
        startActivity(intent);
    }

    public void openFriesCustomization(View view) {
        openSideCustomization(SideType.FRIES);
    }

    public void openChipsCustomization(View view) {
        openSideCustomization(SideType.CHIPS);
    }

    public void openOnionRingsCustomization(View view) {
        openSideCustomization(SideType.ONION_RINGS);
    }

    public void openAppleSlicesCustomization(View view) {
        openSideCustomization(SideType.APPLE_SLICES);
    }

    private void openSideCustomization(SideType type) {
        Intent intent = new Intent(this, SideCustomization.class);
        intent.putExtra("sideType", type);
        startActivity(intent);
    }




    // Similarly, implement other customization navigation methods...

}
