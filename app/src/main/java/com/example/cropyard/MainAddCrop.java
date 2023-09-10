package com.example.cropyard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Arrays;

public class MainAddCrop extends AppCompatActivity {

    Spinner spinner, citySelecterSpinner;
    String selectedItemName = "Nothing", selectedCityName = "Nothing", title = null, description = null,  cropProce = null, number = null;
    boolean isUserAddImage = false;
    private static final int PICK_IMAGE_FROM_GALLERY = 1;
    private static final int PICK_IMAGE_FROM_CAMERA = 2;
    private ImageView uploadCropImage;
    private Uri selectedImageUri;
    EditText mainAddCropTitle, mainAddCropDescription, mainAddCropPrice, mainAddCropNumber;
    LinearLayout uploadImageTextShownLinearLayout;
    ImageView upload_image_new_crop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_crop);

        uploadCropImage = findViewById(R.id.mainUploadCropImage);
        mainAddCropTitle = findViewById(R.id.mainAddCropTitle);
        mainAddCropDescription = findViewById(R.id.mainAddCropDescription);

        mainAddCropPrice = findViewById(R.id.mainAddCropPrice);
        mainAddCropNumber = findViewById(R.id.mainAddCropNumber);

        uploadImageTextShownLinearLayout = findViewById(R.id.upload_image_icon_txt_linearlayout);
        upload_image_new_crop = findViewById(R.id.upload_image_new_crop);


//         Get a reference to the Spinner
        spinner = findViewById(R.id.spinner);

        String[] items = {"Cotton", "Lentil", "Millet", "Mung Beans", "Peanut", "Rice", "Soybean", "Wheat", "Other"};
        int[] imageIds = {R.drawable.li_cotton_icon, R.drawable.li_lentil_icon, R.drawable.li_millet_icon, R.drawable.li_mung_beans_icon, R.drawable.li_peanut_icon, R.drawable.li_rice_icon, R.drawable.li_soybean_icon, R.drawable.li_wheat_icon, R.drawable.user_arrow_forward};

        CropIListtemAdapter adapter = new CropIListtemAdapter(this, items, imageIds);
        spinner.setAdapter(adapter);

        // Set an OnItemSelectedListener for the Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected item text
                selectedItemName = items[position];

                // Display a Toast message with the selected item text

                if(selectedItemName.equals("Other")) {
                    mainAddCropTitle.setEnabled(true);
                    mainAddCropTitle.setHint("Enter Title");
                    mainAddCropTitle.setText("");
                    mainAddCropTitle.requestFocus(); // Set focus to EditText if enabled
                }
                else {
                    mainAddCropTitle.setEnabled(false);
                    mainAddCropTitle.setHint(selectedItemName);
                    mainAddCropTitle.setText(selectedItemName);
                }
                // changing upload icon
                upload_image_new_crop.setImageResource(imageIds[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing here
            }
        });


        // city address spinner
        citySelecterSpinner = findViewById(R.id.cityChosserSpinner);

        String[] cityList = {"Ahmedabad", "Amreli", "Anand", "Aravalli", "Banaskantha", "Bharuch", "Bhavnagar", "Botad", "Chhota Udaipur", "Dahod", "Dang", "Devbhoomi Dwarka", "Gandhinagar", "Gir Somnath", "Jamnagar", "Junagadh", "Kheda", "Kutch", "Mahisagar", "Mehsana", "Morbi", "Narmada", "Navsari", "Panchmahal", "Patan", "Porbandar", "Rajkot", "Sabarkantha", "Surat", "Surendranagar", "Tapi", "Valsad"};
        int temp = R.drawable.ic_city_icon;
        int[] cityImage = new int[32];
        Arrays.fill(cityImage, temp);

        CropIListtemAdapter cityAdapter = new CropIListtemAdapter(this, cityList, cityImage);
        citySelecterSpinner.setAdapter(cityAdapter);

        citySelecterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Retrieve the selected item text
                selectedCityName = cityList[position];

                // Display a Toast message with the selected item text

                Toast.makeText(MainAddCrop.this, "Selected Item: " + selectedCityName, Toast.LENGTH_SHORT).show();
                // changing upload icon

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing here
            }
        });
    }


    // Save given data....
    public void saveCrop(View view) {
        // Get the values from the EditText fields
        title = mainAddCropTitle.getText().toString();
        description = mainAddCropDescription.getText().toString();

        cropProce = mainAddCropPrice.getText().toString();
        number = mainAddCropNumber.getText().toString();



        // Check if any of the fields are empty or if no image is selected
        if (title.isEmpty() || description.isEmpty()  || cropProce.isEmpty() || !isUserAddImage || number.length() != 10) {
            // Display an alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("All fields and an image are required to save the crop.");
            builder.setPositiveButton("OK", null);
            builder.show();
        } else {
            // All fields are filled and an image is selected, you can proceed with saving the data
            // Add your code to save the data or perform further actions here.
        }
    }


    // image seliction
    // image selection
    public void selectImage(View view) {
        // Create an AlertDialog to let the user choose the image source
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        // Choose from the gallery
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_IMAGE_FROM_GALLERY);
                        break;
                    case 1:
                        // Take a photo with the camera
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, PICK_IMAGE_FROM_CAMERA);
                        break;
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_FROM_GALLERY) {
                if (data != null) {
                    Uri imageUri = data.getData();
                    isUserAddImage = true;
                    uploadImageTextShownLinearLayout.setVisibility(View.GONE);
                    // Set the selected image in the ImageView
                    uploadCropImage.setImageURI(imageUri);
                }
            } else if (requestCode == PICK_IMAGE_FROM_CAMERA) {
                if (data != null && data.getExtras() != null) {
                    // Get the captured image
                    Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), (android.graphics.Bitmap) data.getExtras().get("data"), null, null));
                    isUserAddImage = true;
                    // Set the selected image in the ImageView
                    uploadCropImage.setImageURI(imageUri);
                }
            }
        }
    }


    public void onExit(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit Confirmation")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Perform exit action here, for example, finish the activity
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Dismiss the dialog
                            dialog.dismiss();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();

    }


}