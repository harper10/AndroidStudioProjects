package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method increments the quantity
     */
    public void increment(View View) {
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View View) {
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //String priceMessage = "FREE" + " + $" +(quantity*5);
        int price = calculatePrice();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(
                R.id.checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        displayMessage(createOrderSummary(price, hasWhippedCream));
    }

    private int calculatePrice() {
        return quantity*5;
    }

    private String createOrderSummary(int total, boolean whippedCream) {

        String output = "Name= Kaptain Kunal\n" +
                "Add whipped cream? "+ whippedCream + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + total + "\n" +
                "Thank you !";
        return output;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}