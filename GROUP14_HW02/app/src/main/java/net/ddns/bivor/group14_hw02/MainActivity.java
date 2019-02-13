package net.ddns.bivor.group14_hw02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCountThread, textViewCountAsync, textViewLengthThread, getTextViewLengthAsync;
    SeekBar sbCountThread, sbLengthThread, sbCountAsync, sbLengthAsync;
    Button buttonGenerate;
    ProgressDialog pd;
    Handler handler;
    int countThread=1, countAsync=1, lengthThread=7, lengthAsync=7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Password Generator");

        textViewCountThread = findViewById(R.id.textViewCountThread);
        textViewLengthThread = findViewById(R.id.textViewLengthThread);
        textViewCountAsync = findViewById(R.id.textViewCountAsync);
        getTextViewLengthAsync = findViewById(R.id.textViewLengthAsync);

        sbCountThread = findViewById(R.id.seekBarCountThread);
        sbLengthThread = findViewById(R.id.seekBarLengthThread);
        sbCountAsync = findViewById(R.id.seekBarCountAsync);
        sbLengthAsync = findViewById(R.id.seekBarLengthAsync);

        buttonGenerate = findViewById(R.id.buttonGenerate);

        pd = new ProgressDialog(this);
        pd.setMessage("Generating Passwords");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setProgress(0);
        pd.setCancelable(false);


        sbCountThread.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textViewCountThread.setText(""+(progress+1));
                    countThread = progress+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbLengthThread.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textViewLengthThread.setText(""+(progress+7));
                    lengthThread = progress+7;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbCountAsync.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    textViewCountAsync.setText(""+(progress+1));
                    countAsync = progress+1;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbLengthAsync.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    getTextViewLengthAsync.setText(""+(progress+7));
                    lengthAsync = progress+7;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMax(countThread+countAsync);
                //pd.show();
                Intent intent = new Intent(MainActivity.this,GeneratedPasswordsActivity.class);
                startActivity(intent);

            }
        });

    }
}
