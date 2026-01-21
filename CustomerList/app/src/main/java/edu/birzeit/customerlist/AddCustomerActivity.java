package edu.birzeit.customerlist;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextPhone;
    private Spinner spinnerGender;
    private Button buttonAddCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(50, 50, 50, 50);

        // TextView "Customer Info"
        TextView textViewTitle = new TextView(this);
        textViewTitle.setText("Customer Info");
        textViewTitle.setTextSize(20);
        textViewTitle.setTypeface(null, Typeface.BOLD);
        mainLayout.addView(textViewTitle);

        mainLayout.addView(createInputSection("ID", "editText_Id", android.text.InputType.TYPE_CLASS_NUMBER));
        mainLayout.addView(createInputSection("Name", "editText_Name", android.text.InputType.TYPE_CLASS_TEXT));
        mainLayout.addView(createInputSection("Phone", "editText_Phone", android.text.InputType.TYPE_CLASS_PHONE));

        mainLayout.addView(createGenderSection());

        buttonAddCustomer = new Button(this);
        buttonAddCustomer.setText("Add Customer");
        buttonAddCustomer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        mainLayout.addView(buttonAddCustomer);

        buttonAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer();
            }
        });

        setContentView(mainLayout);
    }

    private LinearLayout createInputSection(String label, String tag, int inputType) {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView textView = new TextView(this);
        textView.setText(label);
        textView.setWidth(200);
        layout.addView(textView);

        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        editText.setInputType(inputType);
        editText.setTag(tag);

        if (tag.equals("editText_Id")) editTextId = editText;
        else if (tag.equals("editText_Name")) editTextName = editText;
        else if (tag.equals("editText_Phone")) editTextPhone = editText;

        layout.addView(editText);
        return layout;
    }

    private LinearLayout createGenderSection() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView textView = new TextView(this);
        textView.setText("Gender");
        textView.setWidth(200);
        layout.addView(textView);

        spinnerGender = new Spinner(this);
        String[] genders = {"Male", "Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);
        layout.addView(spinnerGender);

        return layout;
    }

    private void addCustomer() {
        String id = editTextId.getText().toString();
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String gender = spinnerGender.getSelectedItem().toString();

        Customer customer = new Customer();
        if(!id.isEmpty()) customer.setmCustomerId(Long.parseLong(id));
        customer.setmName(name.isEmpty() ? "No Name" : name);
        customer.setmPhone(phone.isEmpty() ? "No Phone" : phone);
        customer.setmGender(gender);

        MainActivity.customersList.add(customer);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}