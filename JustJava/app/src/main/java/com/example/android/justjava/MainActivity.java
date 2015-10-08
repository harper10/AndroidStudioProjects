package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        if (quantity != 100){
            quantity = quantity + 1;
            display(quantity);
        }
    }

    /**
     * This method decrements the quantity
     */
    public void decrement(View View) {
        if (quantity != 1){
            quantity = quantity - 1;
            display(quantity);
        }

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //String priceMessage = "FREE" + " + $" +(quantity*5);

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamcheckbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.userNameText);
        String name = nameEditText.getText().toString();
        int price = calculatePrice(hasChocolate, hasWhippedCream);
        String message = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        //displayMessage(message);
        emailMessage(message);
    }

    private void emailMessage(String subject){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order");
        intent.putExtra(Intent.EXTRA_TEXT, subject);
        intent.setData(Uri.parse("mailto:"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        int base = 5;
        if (hasChocolate){
            base += 2;
        }
        if (hasWhippedCream){
            base += 1;
        }
        return quantity*base;
    }

    private String createOrderSummary(int total, boolean whippedCream, boolean chocolate,
                                      String name) {

        String output = "Name = "+ name+"\n" +
                "Add whipped cream? "+ whippedCream + "\n" +
                "Add chocolate? "+ chocolate + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: $" + total + "\n" +
                getString(R.string.thank_you);
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
    /*
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/
}