/*
800709620_inClass06
AsyncTaskGetHeadlines.java
Jacob Greene
 */

package com.example.a800709620_inclass6;

import android.app.Activity;
import android.location.Address;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null)connection.disconnect();
        }

        return result;
    }

    @Override
    public void onPostExecute(ArrayList<Article> articles) {
        activity.articleList.clear();
        activity.articleList.addAll(articles);
        activity.articleNum = 0;

        if (activity.articleList.isEmpty()) {
            Toast.makeText(activity, "No News Found", Toast.LENGTH_SHORT).show();
            activity.imageViewGallery.setImageResource(R.drawable.ic_launcher_foreground);
            activity.imageViewGallery.setVisibility(View.VISIBLE);
            activity.pb.setVisibility(View.INVISIBLE);
        } else {
            new AsyncTaskGetArticle(activity).execute(activity.articleNum);
        }


    }


}
