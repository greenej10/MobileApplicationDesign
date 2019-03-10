/*
800709620_inClass06
MainActivity.java
Jacob Greene
 */

package com.example.a800709620_inclass6;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity<articleList> extends AppCompatActivity {
    Button buttonGo;
    TextView textViewHeader, textViewPublished, textViewCategories, textViewOutOf;
    ImageView imageViewGallery, imageViewBack, imageViewNext;
    ProgressBar pb;
    ArrayList<String> categories = new ArrayList<String>(Arrays.asList("Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"));
    ArrayList<String> categoryURL = new ArrayList<String>(Arrays.asList(
            "https://newsapi.org/v2/top-headlines?country=us&category=Business&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=Entertainment&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=General&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=Health&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=Science&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=Sports&apiKey=3034da0a1e9e4c0aa5a57f4101d17947",
            "https://newsapi.org/v2/top-headlines?country=us&category=Technology&apiKey=3034da0a1e9e4c0aa5a57f4101d17947"
    ));
    int articleNum;
    int maxArticles;
    ArrayList<Article> articleList;
    TextView textViewDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        buttonGo = findViewById(R.id.buttonGo);
        textViewCategories = findViewById(R.id.textViewCategories);
        textViewHeader = findViewById(R.id.textViewHeader);
        textViewPublished = findViewById(R.id.textViewPublished);
        textViewOutOf = findViewById(R.id.textViewOutOf);
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewGallery = findViewById(R.id.imageViewGallery);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        articleList = new ArrayList<Article>();
        textViewDesc = findViewById(R.id.textViewDesc);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Choose a Category")
                            .setItems(categories.toArray(new CharSequence[categories.size()]), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    textViewCategories.setText(categories.get(which));
                                    new AsyncTaskGetHeadlines(MainActivity.this).execute(categoryURL.get(which));
                                    textViewHeader.setVisibility(View.VISIBLE);
                                    imageViewGallery.setVisibility(View.INVISIBLE);
                                    pb.setVisibility(View.VISIBLE);
                                }
                            });
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(MainActivity.this, "There is no internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                imageViewGallery.setVisibility(View.INVISIBLE);
                articleNum-=1;
                if(articleNum<0)articleNum=articleList.size()-1;

                new AsyncTaskGetArticle(MainActivity.this).execute(articleNum);


            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);
                imageViewGallery.setVisibility(View.INVISIBLE);
                articleNum+=1;
                if(articleNum>articleList.size()-1)articleNum=0;

                new AsyncTaskGetArticle(MainActivity.this).execute(articleNum);
            }
        });
    }


    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
}

