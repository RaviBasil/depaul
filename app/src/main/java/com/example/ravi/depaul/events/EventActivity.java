package com.example.ravi.depaul.events;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.ravi.depaul.Config;
import com.example.ravi.depaul.MainActivity;
import com.example.ravi.depaul.R;
import com.example.ravi.depaul.app.AppController;
import com.example.ravi.depaul.teacher.DividerItemDecoration;
import com.example.ravi.depaul.teacher.Movie;
import com.example.ravi.depaul.teacher.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class EventActivity extends MainActivity{

    //private String URL_FEED = "http://ravibasil.comlu.com/mit/events.php";


    private static final String TAG =EventActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private FeedListAdapter mAdapter;
    private List<FeedItem> feedItems=new ArrayList<>();
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyRandomTheme1);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);
        //getSupportActionbar
        if (Build.VERSION.SDK_INT >= 21) {
            toolbar.setNavigationIcon(getResources().getDrawable(
                    R.drawable.ic_arrow_back_black_24dp, getTheme()));
        } else {
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        }
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view12);

        mAdapter = new FeedListAdapter(getApplicationContext(), feedItems);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        freshjson();
    }
        private void freshjson() {
            final ProgressDialog loading = ProgressDialog.show(this,"Loading Events", "Please wait...",false,false);
            loading.show();
            // making fresh volley request and getting json
            StringRequest movieReq = new StringRequest(Request.Method.GET,
                    Config.Event_FEED,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    //Toast.makeText(getApplication(),  response, Toast.LENGTH_LONG).show();
                    if (response != null) {
                        loading.hide();
                        parseJsonFeed(response);
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    loading.hide();
                    // stopping swipe refresh

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
            //Adding the string request to the queue
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(movieReq);
            AppController.getInstance().addToRequestQueue(movieReq);
        loading.dismiss();
        }

        /**
         * Parsing json response and passing the data to feed view list adapter
         */
        private void parseJsonFeed(String response) {
            try {
                JSONObject jsonObject=new JSONObject(response);
                JSONArray feedArray = jsonObject.getJSONArray("feed");

                for (int i = 0; i < feedArray.length(); i++) {
                    JSONObject feedObj = (JSONObject) feedArray.get(i);

                    FeedItem item = new FeedItem();
                    item.setName(feedObj.getString("name"));


                    item.setImge(feedObj.getString("image"));
                    item.setStatus(feedObj.getString("status"));
                    item.setTimeStamp(feedObj.getString("timestamp"));

                    feedItems.add(item);
                }

                // notify data changes to list adapater
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }