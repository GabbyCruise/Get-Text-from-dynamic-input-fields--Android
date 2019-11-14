package com.cobuildit.dynamicedittextanddisplayvalue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    List<EditText> dynamicEditTexts;
    ArrayList<EditText> allEds;

    //the main input ids
    private EditText titleM, productNameM, productNumberM;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);

    }

    //On Add Fields Click, adds more input fields below

    public void onAddField(View v) {
        dynamicEditTexts = new ArrayList<EditText>();//added this

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        EditText dynamicText = new EditText(this);
        dynamicEditTexts.add(dynamicText);
    }


    public void onDelete(View v) {
        parentLinearLayout.removeView((View) v.getParent());
    }

    //Sending inputs to the Output class
    public void getValue(View view) {
        Intent intent = new Intent(MainActivity.this, Output.class);
        titleM = findViewById(R.id.title_main);
        productNameM = findViewById(R.id.product_name_main);
        productNumberM = findViewById(R.id.product_number_main);

        //getting the texts

        if (titleM == null || productNameM == null) {
            Toast.makeText(getApplicationContext(), "Please enter a value to continue", Toast.LENGTH_SHORT).show();
        } else if(productNumberM.getText().length()==0){
            Toast.makeText(getApplicationContext(), "Please enter a value to continue", Toast.LENGTH_SHORT).show();
        }else if(dynamicEditTexts.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a value to continue", Toast.LENGTH_SHORT).show();
        }else{
            String Title = titleM.getText().toString();
            String PName = productNameM.getText().toString();
            double PNumber = Double.parseDouble(productNumberM.getText().toString());

            // ******** FOR THE DYNAMIC EDITEXT FIELDS ************
            String[] inputItems = new String[dynamicEditTexts.size()];
            for (int j = 0; j < dynamicEditTexts.size(); j++) {
                inputItems[j] = dynamicEditTexts.get(j).getText().toString();
            }
            intent.putExtra("dynamicData", inputItems);


            //Sending the Static fields to the output
            intent.putExtra("title", Title);
            intent.putExtra("pname", PName);
            intent.putExtra("pnumber", PNumber);
            startActivity(intent);
            finish();
        }
    }
}