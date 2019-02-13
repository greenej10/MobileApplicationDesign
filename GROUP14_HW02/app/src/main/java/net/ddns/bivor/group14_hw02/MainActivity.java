package net.ddns.bivor.group14_hw02;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.util.*;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView textViewCountThread, textViewCountAsync, textViewLengthThread, getTextViewLengthAsync;
    SeekBar sbCountThread, sbLengthThread, sbCountAsync, sbLengthAsync;
    Button buttonGenerate;
    ProgressDialog pd;
    Handler handler;
    ExecutorService threadPool;
    int countThread=1, countAsync=1, lengthThread=7, lengthAsync=7, progressValue=0;
    int FLAG_THREAD =0, FLAG_ASYNC = 0;

    ArrayList<String> arrayListPasswordThread;
    ArrayList<String> arrayListPasswordAsync;

    static final String PASSWORD_THREAD ="PASSWORD_THREAD";
    static final String PASSWORD_ASYNC  = "PASSWORD_ASYNC";
    Intent intent;

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
        pd.setCancelable(false);

        intent = new Intent(MainActivity.this,GeneratedPasswordsActivity.class);

        threadPool = Executors.newFixedThreadPool(2);


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
                pd.setProgress(0);
                //pd.show();

                handler =new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        switch (msg.what){

                            case DoWork.STATUS_START:
                                pd.show();
                                break;

                            case DoWork.STATUS_STOP:
                                Passwords passwordThread = new Passwords(arrayListPasswordThread);
                                intent.putExtra(PASSWORD_THREAD,passwordThread);

                                if (FLAG_ASYNC==1){
                                    pd.dismiss();
                                    startActivity(intent);
                                    finish();
                                }

                                break;

                            case DoWork.STATUS_PROGRESS:
                                progressValue+= arrayListPasswordThread.size();
                                pd.setProgress(progressValue);
                                break;
                        }
                        return false;
                    }
                });

                threadPool.execute(new DoWork());
                new DoWorkAsync().execute(countAsync, lengthAsync);

            }
        });

    }

    class DoWork implements Runnable{

        static final int STATUS_START = 0x00;
        static final int STATUS_PROGRESS = 0x01;
        static final int STATUS_STOP = 0x02;

        @Override
        public void run() {

            arrayListPasswordThread = new ArrayList<>(countThread);

            Message startMessage = new Message();
            startMessage.what = STATUS_START;
            handler.sendMessage(startMessage);

            for (int i=0; i<countThread; i++){
//                Util generator = new Util();
                String password = Util.getPassword(lengthThread);
                arrayListPasswordThread.add(password);
                Message message = new Message();
                message.what = STATUS_PROGRESS;
                handler.sendMessage(message);
            }

            FLAG_THREAD =1;

            Message stopMessage = new Message();
            stopMessage.what = STATUS_STOP;
            handler.sendMessage(stopMessage);

        }
    }

    class DoWorkAsync extends AsyncTask<Integer, Integer, ArrayList<String>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            FLAG_ASYNC =1;

            Passwords passwordAsync = new Passwords(arrayListPasswordAsync);
            intent.putExtra(PASSWORD_ASYNC,passwordAsync);

            if (FLAG_THREAD==1){
                pd.dismiss();
                startActivity(intent);
                finish();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressValue+=values[0];
            pd.setProgress(progressValue);
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {
            int count = integers[0];
            int length = integers[1];

            arrayListPasswordAsync = new ArrayList<>(count);

            for (int i=0; i<count; i++){
                arrayListPasswordAsync.add(Util.getPassword(length));
                publishProgress(i);
            }

            return arrayListPasswordAsync;
        }
    }
}
