package com.example.a800709620_inclass6;

import android.app.Activity;
import android.location.Address;
import android.os.AsyncTask;
import android.view.View;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class AsyncTaskGetHeadlines extends AsyncTask <String,Void, ArrayList<Article>> {
    MainActivity activity;

    public AsyncTaskGetHeadlines(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public ArrayList<Article> doInBackground(String[] strings) {
        HttpURLConnection connection = null;
        ArrayList<Article> result = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                JSONObject root = new JSONObject(json);
                JSONArray articles = root.getJSONArray("articles");
                for (int i = 0; i < articles.length(); i++) {
                    JSONObject articleJson = articles.getJSONObject(i);
                    Article article = new Article();
                    article.title = articleJson.getString("title");
                    article.publishedAt = articleJson.getString("publishedAt");
                    article.description = articleJson.getString("description");
                    article.urToImage = articleJson.getString("urlToImage");

                    result.add(article);
                }
            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }
        return result;
    }

    @Override
    public void onPostExecute(ArrayList<Article> articles) {
        activity.articleList.clear();
        activity.articleList.addAll(articles);
        activity.articleNum = 0;


        if(activity.articleList.isEmpty()){
            activity.imageViewGallery.setImageResource(R.drawable.ic_launcher_foreground);
            activity.imageViewGallery.setVisibility(View.VISIBLE);
            activity.pb.setVisibility(View.INVISIBLE);
        }
        else{
            activity.textViewHeader.setText(articles.get(activity.articleNum).title);
            activity.textViewPublished.setText(articles.get(activity.articleNum).publishedAt);
            activity.textViewOutOf.setText("Out Of");

        }

    }

    public static interface IData {
        public void handleArticles(ArrayList<Article> articles);
        public void loadImage(Article article);
    }


}
