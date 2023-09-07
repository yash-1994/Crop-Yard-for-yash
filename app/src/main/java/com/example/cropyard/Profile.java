package com.example.cropyard;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupMenu;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    ConstraintLayout userLanguageBtn, userPrivacyPolicyBtn, userAboutUsBtn;
    String selectedLanguage = "English";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Call the method to change status bar color
        changeStatusBarColor(getResources().getColor(R.color.green));

        userPrivacyPolicyBtn = findViewById(R.id.userPrivacyPolicyBtn);
        userAboutUsBtn = findViewById(R.id.userAboutUsBtn);


        // Language Selection Button
        userLanguageBtn = findViewById(R.id.userLanguageBtn);
        userLanguageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguagePopUpMenu(view);

            }
        });


                                // change Fragements
        // go to AboutUs
        userAboutUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, ProfileAboutUs.class));
            }
        });

        // go to PrivacyPolicy
        userPrivacyPolicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, ProfileAboutUs.class));
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }


    // Language On Click Method
    public void showLanguagePopUpMenu(View view) {
        // Initializing the popup menu and giving the reference as current context
        PopupMenu popupMenu = new PopupMenu(Profile.this, userLanguageBtn);

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.language_change_pop_up_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                selectedLanguage = (String) menuItem.getTitle();
                if(selectedLanguage.equals("English")) {

                    Toast.makeText(Profile.this, "Yessss", Toast.LENGTH_SHORT).show();
                    // chage language YASHHHHHHHHHHHHHHHHHHHHHHHH

                } else if(selectedLanguage.equals("हिंदी")) {

                    // chage language YASHHHHHHHHHHHHHHHHHHHHHHHH

                } else if (selectedLanguage.equals("ગુજરાતી")) {

                    // chage language YASHHHHHHHHHHHHHHHHHHHHHHHH

                }
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();



    }
}