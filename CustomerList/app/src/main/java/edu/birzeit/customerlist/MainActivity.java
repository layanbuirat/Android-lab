package edu.birzeit.customerlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addButton;
    private LinearLayout secondLinearLayout;
    public static List<Customer> customersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout firstLinearLayout = new LinearLayout(this);
        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);

        addButton = new Button(this);
        addButton.setText("ADD CUSTOMER");
        addButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        secondLinearLayout = new LinearLayout(this);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(secondLinearLayout);
        firstLinearLayout.addView(addButton);
        firstLinearLayout.addView(scrollView);

        setContentView(firstLinearLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayCustomers();
    }

    private void displayCustomers() {
        secondLinearLayout.removeAllViews();

        for (Customer customer : customersList) {
            TextView textView = new TextView(this);
            textView.setText(customer.toString());
            textView.setTextSize(16);
            textView.setPadding(20, 10, 20, 10);
            secondLinearLayout.addView(textView);
        }

        if (customersList.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("No customers added yet");
            emptyText.setTextSize(18);
            emptyText.setPadding(20, 20, 20, 20);
            secondLinearLayout.addView(emptyText);
        }
    }

    public void addCustomer(Customer customer) {
        customersList.add(customer);
        displayCustomers();
    }
}