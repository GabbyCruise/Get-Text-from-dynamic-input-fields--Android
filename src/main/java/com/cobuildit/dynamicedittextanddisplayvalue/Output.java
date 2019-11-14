package com.cobuildit.dynamicedittextanddisplayvalue;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Output extends AppCompatActivity {
    //the static ids
    private TextView StaticTitle, StaticPName, StaticPNumber;

    //dynamic ids
    private TextView DynamicPTitle, DynamicPName, DynamicPNumber;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        //getting the ids

        //For static ids
        StaticTitle = (TextView) findViewById(R.id.static_title);
        StaticPName = (TextView) findViewById(R.id.static_pname);
        StaticPNumber = (TextView) findViewById(R.id.static_pnumber);


        //For The Dynamic ids
        DynamicPTitle = (TextView) findViewById(R.id.dynamic_ptitle);
        DynamicPName = (TextView) findViewById(R.id.dynamic_pname);
        DynamicPNumber = (TextView) findViewById(R.id.dynamic_pnumber);

        /*GETTING ITEMS FROM THE MAINACTIVITY*/
        //for the static texts
        String StaticProductTitle = getIntent().getExtras().getString("title");
        String StaticProductName = getIntent().getExtras().getString("pname");
        double StaticProductNumber = getIntent().getExtras().getDouble("pnumber");


        //getting the dynamic results
        Intent collectDynamicData = getIntent();
        String[] dynamicItems = collectDynamicData.getStringArrayExtra("dynamicData");
        ArrayList<String> alldynamicTexts = new ArrayList<>(Arrays.asList(dynamicItems));
        String newText = (String.valueOf(alldynamicTexts));
        DynamicPTitle.setText(newText);
        //This code above works partially, but not completely. Still tweaking



        /*FORMATING THE NUMBERS TO DISPLAY IN A NUMBER PATTERN*/
        DecimalFormat formate = new DecimalFormat();//"#,###,###"
        String FormateStaticNumber;
        String FormateDynamicNumber;
        FormateStaticNumber = formate.format(StaticProductNumber);

        //Setting the Results to the Static OutPut
        StaticTitle.setText(StaticProductTitle);
        StaticPName.setText(StaticProductName);
        StaticPNumber.setText(FormateStaticNumber);



        /*SETTING THE DYNAMIC RESULTS*/

        //DynamicPTitle.setText(myStrings);
        //DynamicPTitle.setText((CharSequence) alldynamicTexts);
        //DynamicPTitle.setText(array);
        //DynamicPName.setText(DynamicProductName);
        //DynamicPNumber.setText(FormateDynamicNumber);

    }
}
