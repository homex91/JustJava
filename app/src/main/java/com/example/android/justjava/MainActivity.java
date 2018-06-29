/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 10;
    int price;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox ChocoCream = (CheckBox) findViewById(R.id.chocolate_cream);
        Boolean hasWhippedCream = WhippedCream.isChecked();
        Boolean hasChocolateCream = ChocoCream.isChecked();
        String name = getTextInput();
        int priceOrder = calculatePrice(hasWhippedCream, hasChocolateCream);
        String summaryOrder = createOrderSummary(priceOrder, hasWhippedCream, hasChocolateCream, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java App for " + name );
        intent.putExtra(Intent.EXTRA_TEXT, summaryOrder);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        //displayMessage(createOrderSummary(calculatePrice(hasWhippedCream, hasChocolateCream), hasWhippedCream, hasChocolateCream, getTextInput()));

    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocoCream){
        int basePrice = 5;
        if (addWhippedCream){
            basePrice += 1;
        }

        if (addChocoCream) {
            basePrice += 2;
        };

        return quantity * basePrice;
    }


    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocoCream, String text){
        String priceMessage = "Name: " + text;
        priceMessage += "\nQuantity of Coffee bought: " + quantity;
        priceMessage += "\nAdd Whipped Cream Toppings: " + hasWhippedCream;
        priceMessage += "\nAdd Chocolate Cream Toppings: " + hasChocoCream;
        priceMessage += "\nTotal price is: " + price;
        priceMessage += "\nThank you";
        return priceMessage;
    }

    public void increment(View view) {

        if (quantity == 100){

            Toast.makeText(getApplicationContext(), "You cannot order beyond 100 cups of coffee, Thank you", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            Toast.makeText(getApplicationContext(), "You cannot order below 1 cup of coffee, Thank you", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }


    private String getTextInput(){
        EditText txtInput = (EditText) findViewById(R.id.text_input);
        String textInput = txtInput.getText().toString();
        return textInput;
    }

    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}