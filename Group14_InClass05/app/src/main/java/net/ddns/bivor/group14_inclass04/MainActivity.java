package net.ddns.bivor.group14_inclass04;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonGo;
    TextView textViewSearch;
    ImageView imageViewBack, imageViewNext, imageViewGallery;
    List<String> keywords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewSearch  = findViewById(R.id.textViewSearch);
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewGallery = findViewById(R.id.imageViewGallery);

        keywords = new ArrayList<>();
        new getKeywordAsync().execute("http://dev.theappsdr.com/apis/photos/keywords.php");
        
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Choose a Keyword")
                            .setItems(keywords.toArray(new CharSequence[keywords.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    textViewSearch.setText(keywords.get(which));
                                }
                            });
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    Toast.makeText(MainActivity.this, "There is no internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    //private class getURLAsync extends AsyncTask<>

    private class getKeywordAsync extends AsyncTask<String,Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {

            HttpURLConnection connection = null;
            String result = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    result = IOUtils.toString(connection.getInputStream(), "UTF8");
                    keywords = Arrays.asList(result.split(";"));
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(connection !=null)connection.disconnect();
            }
            return null;
        }

    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType()!=ConnectivityManager.TYPE_WIFI
                && networkInfo.getType()!=ConnectivityManager.TYPE_MOBILE)){
            return false;
        }
        return true;

    }
}
