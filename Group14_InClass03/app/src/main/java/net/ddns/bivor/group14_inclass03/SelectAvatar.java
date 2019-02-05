package net.ddns.bivor.group14_inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectAvatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle("Select Avatar");

        ImageView f1 = (ImageView) findViewById(R.id.f1);
        ImageView m1 = (ImageView) findViewById(R.id.m1);
        ImageView f2 = (ImageView) findViewById(R.id.f2);
        ImageView m2 = (ImageView) findViewById(R.id.m2);
        ImageView f3 = (ImageView) findViewById(R.id.f3);
        ImageView m3 = (ImageView) findViewById(R.id.m3);

        findViewById(R.id.f1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.f1;
                String key = avatar.toString();
                if(avatar == 0){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });

        findViewById(R.id.f2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.f2;
                String key = avatar.toString();
                if(key  == null){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });

        findViewById(R.id.f3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.f3;
                String key = avatar.toString();
                if(key  == null){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });

        findViewById(R.id.m1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.m1;
                String key = avatar.toString();
                if(key  == null){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });

        findViewById(R.id.m2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.m2;
                String key = avatar.toString();
                if(key  == null){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });

        findViewById(R.id.m3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer avatar = R.id.m3;
                String key = avatar.toString();
                if(key  == null){
                    setResult(RESULT_CANCELED);
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.AVATAR_KEY, avatar);
                    setResult(RESULT_OK, intent);
                }
            }
        });
    }
}
