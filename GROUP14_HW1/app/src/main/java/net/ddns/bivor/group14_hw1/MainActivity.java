package net.ddns.bivor.group14_hw1;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ToggleButton tb;
    TextView textViewAlcohol, textViewBACLevel;
    SeekBar sb;
    EditText editTextWeight;
    Button buttonSave;
    Button buttonAddDrink;
    Button buttonReset;
    TextView t;
    double gender;
    float weight;
    double BAC;
    double percent;
    float size;
    RadioGroup rg;
    RadioButton rb;
    int rbID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("BAC Calculator");

        //toolbar.setLogo(R.mipmap.ic_launcher_foreground);


        editTextWeight = findViewById(R.id.editText);
        buttonSave = findViewById(R.id.buttonSave);
        buttonAddDrink = findViewById(R.id.buttonAddDrink);
        buttonReset = findViewById(R.id.buttonReset);
        tb = findViewById(R.id.toggleButtonGender);
        sb = findViewById(R.id.seekBar);
        textViewAlcohol = findViewById(R.id.textViewAlcohol);
        tb.setTextOff("Female"); tb.setTextOn("Male");
        t = findViewById(R.id.textView4);
        textViewBACLevel = findViewById(R.id.textViewBACLevel);



        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked == false)t.setText("F");
                    else t.setText("M");
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextWeight.getText().toString().equals("")){
                    editTextWeight.setError("Enter Weight in Lbs!");
                }

                else {
                    weight = Float.parseFloat(editTextWeight.getText().toString());
                }

                if(t.getText().equals("M")){
                    gender = .68;
                }
                else {
                    gender = .55;
                }


            }
        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress*5;
                textViewAlcohol.setText(""+progressChangedValue+" %");
                percent = progressChangedValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }


        });

        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == -1){

                }
                else {
                    rb = findViewById(checkedId);
                    size = Float.parseFloat(rb.getText().toString());
                    rbID = checkedId;
                }

            }
        });

        buttonAddDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double drinkBAC;
                drinkBAC = (size * percent)/(weight * gender);
                BAC = BAC + drinkBAC;

                textViewBACLevel.setText("" + BAC);

            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BAC = 0;

                textViewBACLevel.setText("" + BAC);

            }
        });


    }
}
