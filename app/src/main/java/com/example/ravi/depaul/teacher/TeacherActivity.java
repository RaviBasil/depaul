package com.example.ravi.depaul.teacher;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravi.depaul.Config;
import com.example.ravi.depaul.MainActivity;
import com.example.ravi.depaul.R;
import com.example.ravi.depaul.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends MainActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private Context context;
    //Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.MyRandomTheme1);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.activity_teacher_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
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



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MovieAdapter(movieList,getApplicationContext());

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
               // Toast.makeText(getApplicationContext(), movie.getname() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

       // prepareMovieData();
        freshjson();
    }
    private  void freshjson() {
        //Showing a progress dialog
        final ProgressDialog pDialog = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);
        JsonArrayRequest req = new JsonArrayRequest(Config.TEACHER_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();

                        movieList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                Movie movie=new Movie(object.getString("name"),object.getString("photo"),object.getString("quali"),
                                        object.getString("exp"));

                                movieList.add(movie);

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


                                public interface ClickListener {
                                    void onClick(View view, int position);

                                    void onLongClick(View view, int position);
                                }

                                public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

                                    private GestureDetector gestureDetector;
                                    private TeacherActivity.ClickListener clickListener;

                                    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final TeacherActivity.ClickListener clickListener) {
                                        this.clickListener = clickListener;
                                        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                                            @Override
                                            public boolean onSingleTapUp(MotionEvent e) {
                                                return true;
                                            }

                                            @Override
                                            public void onLongPress(MotionEvent e) {
                                                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                                                if (child != null && clickListener != null) {
                                                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                                        View child = rv.findChildViewUnder(e.getX(), e.getY());
                                        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                                            clickListener.onClick(child, rv.getChildPosition(child));
                                        }
                                        return false;
                                    }

                                    @Override
                                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                                    }

                                    @Override
                                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                                    }
                                }
                            }