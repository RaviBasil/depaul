package com.example.ravi.depaul.news;

import android.app.ProgressDialog;
import android.os.Build;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ravi.depaul.Config;
import com.example.ravi.depaul.MainActivity;
import com.example.ravi.depaul.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsActivity extends MainActivity {
    Bundle extras;
    String json,photo_url;
    TextView name,roll,class1,charac,attend,notice;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar21);
        setSupportActionBar(toolbar);
        if(Build.VERSION.SDK_INT>=21) {
            toolbar.setNavigationIcon(getResources().getDrawable(
                    R.drawable.ic_arrow_back_black_24dp, getTheme()));
        }else {
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        }
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
        photo=(ImageView) findViewById(R.id.student_image);
        name=(TextView) findViewById(R.id.name);
        class1=(TextView) findViewById(R.id.class1);
        roll=(TextView) findViewById(R.id.id1);
        charac=(TextView) findViewById(R.id.charac);
        notice=(TextView) findViewById(R.id.notice);
        attend=(TextView) findViewById(R.id.attend);
        extras = getIntent().getExtras();
        json=extras.getString("json");


    getjsonarray(json);
    }

    private void getjsonarray(String json)  {
        try {
            JSONObject jsonobject=new JSONObject(json);
            JSONArray feedArray = jsonobject.getJSONArray("feed");
            JSONObject obj = (JSONObject) feedArray.get(0);
            name.setText(obj.getString("name"));
            class1.setText(obj.getString("class"));
            roll.setText(obj.getString("roll"));
            charac.setText(obj.getString("charac"));
            attend.setText(obj.getString("attend"));
            photo_url= Config.PHOTOS+obj.getString("photo")+".jpg";

            Glide.with(this).load(photo_url)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}