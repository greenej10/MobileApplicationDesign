/*
800709620_inClass06
AsyncTaskGetImage.java
Jacob Greene
 */

package com.example.a800709620_inclass6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskGetImage extends AsyncTask<String,Void, Void> {
    MainActivity activity;
    String imageURL;
    Bitmap image;
    public AsyncTaskGetImage(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... strings) {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL url;
            url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                image = BitmapFactory.decodeStream(connection.getInputStream());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Handle the exceptions
        } finally {
            //Close open connection
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (image != null && activity.imageViewGallery != null) {
            activity.imageViewGallery.setImageBitmap(image);
            activity.imageViewGallery.setVisibility(View.VISIBLE);
            activity.pb.setVisibility(View.INVISIBLE);
        }
    }


}
