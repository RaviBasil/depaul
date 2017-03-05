package com.example.ravi.depaul.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravi.depaul.Config;
import com.example.ravi.depaul.MainActivity;
import com.example.ravi.depaul.R;

import java.util.HashMap;
import java.util.Map;

public class ParentLogin extends AppCompatActivity implements View.OnClickListener{
AppCompatButton buttonLogin;
    EditText editTextEmail,editTextPassword;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar22);
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
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonLogin=(AppCompatButton) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
   //     login();

    }
    private void login() {
        //Getting value from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        if (email != null && password != null) {
            final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);
            loading.show();
            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Config.LOGIN_URl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.hide();
                            //If the server is not succes
                            //Displayin an error message on toast
                            if (response.trim().equalsIgnoreCase("fail")) {
                                Toast.makeText(getApplicationContext(), "Invalid username or password",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                //If we getting success from server
                                //Creating a shared preference
                                SharedPreferences sharedPreferences = ParentLogin.this.
                                        getSharedPreferences(Config.SHARED_PREF_NAME,
                                                Context.MODE_PRIVATE);
                                //Creating editor to store value to shared preferences
                                SharedPreferences.Editor editor =
                                        sharedPreferences.edit();

                                //Adding values to editor
                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.NAME_SHARED_PREF, response);
                                //Saving values to editor
                                editor.commit();
                                //Starting profile activity

                                Intent intent = new Intent(ParentLogin.this, NewsActivity.class);
                                intent.putExtra("json",response);
                                startActivity(intent);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
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
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Config.KEY_EMAIL, email);
                    params.put(Config.KEY_PASSWORD, password);
                    //returning parameter
                    return params;
                }
            };
            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}
