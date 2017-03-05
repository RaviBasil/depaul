package com.example.ravi.depaul.photos;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.example.ravi.depaul.Config;
import com.example.ravi.depaul.MainActivity;
import com.example.ravi.depaul.R;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ravi.depaul.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotosActivity extends MainActivity {


        private String TAG = MainActivity.class.getSimpleName();
        private ArrayList<Image> images;
        private GalleryAdapter mAdapter;
        private RecyclerView recyclerView;
        Context context=this;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setTheme(R.style.MyRandomTheme1);
            if (Build.VERSION.SDK_INT >= 21) {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
            setContentView(R.layout.activity_photos);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
            setSupportActionBar(toolbar);
            //getSupportActionbar
            if(Build.VERSION.SDK_INT>=21) {
                toolbar.setNavigationIcon(getResources().getDrawable(
                        R.drawable.ic_arrow_back_black_24dp, getTheme()));
            }else
            {
                toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
            }
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);


            images = new ArrayList<>();
            mAdapter = new GalleryAdapter(getApplicationContext(), images);

            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

         recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", images);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


            fetchImages();
        }

        private void fetchImages() {
            final ProgressDialog pDialog = ProgressDialog.show(this,"Loading Gallery", "Please wait...",false,false);
            JsonArrayRequest req = new JsonArrayRequest(Config.PHOTOS_URL,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            pDialog.hide();

                            images.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject object = response.getJSONObject(i);
                                    Image image = new Image();
                                    image.setName(object.getString("name"));
                                    image.setLarge(object.getString("url"));
                                    image.setTimestamp(object.getString("timestamp"));

                                    images.add(image);

                                } catch (JSONException e) {
                                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                                }
                            }

                            mAdapter.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    pDialog.hide();
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found. Please try again after some time!!";
                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (error instanceof NoConnectionError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(req);
        }
    }

