/*
800709620_inClass06
AsyncTaskGetArticle.java
Jacob Greene
 */

package com.example.a800709620_inclass6;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class AsyncTaskGetArticle extends AsyncTask <Integer, Void, Article> {
    Article article;
    MainActivity activity;
    int index;

    public AsyncTaskGetArticle(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Article doInBackground(Integer... integers) {
        index = integers[0];
        article = (Article)activity.articleList.get(index);
        return article;
    }

    @Override
    protected void onPostExecute(Article article) {
            activity.textViewPublished.setVisibility(View.VISIBLE);
            activity.textViewHeader.setText(article.title);
            activity.textViewPublished.setText(article.publishedAt);
            activity.textViewDesc.setText(article.description);
            activity.textViewOutOf.setText( index + 1 +" Out Of " + activity.articleList.size());

            new AsyncTaskGetImage(activity).execute(article.urToImage);




    }
}
