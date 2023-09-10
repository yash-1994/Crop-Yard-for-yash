package com.example.cropyard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HelpCenter extends AppCompatActivity {

    EditText help_text, help_phone;
    String name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_help_center);

        help_text = findViewById(R.id.help_text);
        help_phone = findViewById(R.id.help_phone);

    }


    public void onSubmitClicked(View view) {
        // Get the values from the EditText fields
        name = help_text.getText().toString();
        number = help_phone.getText().toString();



        // Check if any of the fields are empty or if no image is selected
        if (name.isEmpty() || number.length() != 10) {
            // Display an alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields are required to submit.");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            // All fields are filled and an image is selected, you can proceed with saving the data
            // Add your code to save the data or perform further actions here.
        }
    }
}