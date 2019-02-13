package net.ddns.bivor.group14_hw02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GeneratedPasswordsActivity extends AppCompatActivity {

    Button buttonFinish;
    TextView textViewPassword, textViewPassword2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_passwords);
        setTitle("Generated Passwords");

        buttonFinish = findViewById(R.id.buttonFinish);

        LinearLayout threadLayout = findViewById(R.id.threadLayout);
        LinearLayout asyncLayout = findViewById(R.id.asyncLayout);
        LayoutInflater inflaterThread = LayoutInflater.from(this);
        LayoutInflater inflaterAsync = LayoutInflater.from(this);

        for (int i=0; i<20; i++){

            View viewThread = inflaterThread.inflate(R.layout.password,threadLayout,false);
            View viewAsync = inflaterAsync.inflate(R.layout.password, asyncLayout, false);
            textViewPassword = viewThread.findViewById(R.id.textViewPassword);
            textViewPassword2 = viewAsync.findViewById(R.id.textViewPassword2);
            threadLayout.addView(viewThread);
            asyncLayout.addView(viewAsync);
        }


        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
