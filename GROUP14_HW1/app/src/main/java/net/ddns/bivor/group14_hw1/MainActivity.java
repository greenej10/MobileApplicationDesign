package net.ddns.bivor.group14_hw1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    ToggleButton tb;
    TextView textViewAlcohol;
    SeekBar sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setTitle("BAC Calculator");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setIcon(R.mipmap.ic_launcher_foreground);

        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setTitle("BAC Calculator");

        toolbar.setLogo(R.drawable.wine);



        tb = findViewById(R.id.toggleButtonGender);
        sb = findViewById(R.id.seekBar);
        textViewAlcohol = findViewById(R.id.textViewAlcohol);
//        tb.setTextOff("Female"); tb.setTextOn("Male");
//        t = findViewById(R.id.textView4);
//        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if(isChecked == false)t.setText("F");
//                    else t.setText("M");
//            }
//        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress*5;
                textViewAlcohol.setText(""+progressChangedValue+" %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {



            }
        });

    }
}
