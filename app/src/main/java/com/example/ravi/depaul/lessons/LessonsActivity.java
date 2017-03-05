package com.example.ravi.depaul.lessons;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.example.ravi.depaul.R;

import java.util.Arrays;
import java.util.List;

/**
 * Sample Activity for the vertical linear RecyclerView sample.
 * Uses ButterKnife to inject view resources.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class LessonsActivity extends AppCompatActivity {

    private ClassAdapter mAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, LessonsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyRandomTheme1);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        setContentView(R.layout.lesson1);

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


        Log.d("TAAAAAAAAAAAAAAAAA","Vertcall");
        Subject beef = new Subject("English");
        Subject cheese = new Subject("Mathmatics");
        Subject salsa = new Subject("Hindi");
        Subject tortilla = new Subject("Physics");
        Subject ketchup = new Subject("Chemistry");
        Subject bun = new Subject("Computer");

        Class c12 = new Class("Class XII", Arrays.asList(beef, cheese, salsa, tortilla));
        Class c11 = new Class("Class XI", Arrays.asList(cheese, tortilla));
        Class c10 = new Class("Class X", Arrays.asList(beef, cheese, ketchup, bun));
        Class c9 = new Class("Class IX", Arrays.asList(beef, cheese, ketchup, bun));
        Class c8 = new Class("Class VIII", Arrays.asList(beef, cheese, ketchup, bun));
        Class c7 = new Class("Class VII", Arrays.asList(beef, cheese, ketchup, bun));
        Class c6 = new Class("Class VI", Arrays.asList(beef, cheese, ketchup, bun));
        Class c5 = new Class("Class V", Arrays.asList(beef, cheese, ketchup, bun));
        Class c4 = new Class("Class IV", Arrays.asList(beef, cheese, ketchup, bun));
        Class c3 = new Class("Class III", Arrays.asList(beef, cheese, ketchup, bun));
        Class c2 = new Class("Class II", Arrays.asList(beef, cheese, ketchup, bun));
        Class c1 = new Class("Class I", Arrays.asList(beef, cheese, ketchup, bun));
        Class cUkg = new Class("Class UKG", Arrays.asList(beef, cheese, ketchup, bun));
        Class cLkG = new Class("Class LKG", Arrays.asList(beef, cheese, ketchup, bun));



        final List<Class> aClasses = Arrays.asList(c12,c11,c10,c9,c8,c7,c6,c5,c4,c3,c2,c1);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new ClassAdapter(this, aClasses);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                Class expandedClass = aClasses.get(position);

                String toastMsg = getResources().getString(R.string.expanded, expandedClass.getName());
                Toast.makeText(LessonsActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                Class collapsedClass = aClasses.get(position);

                String toastMsg = getResources().getString(R.string.collapsed, collapsedClass.getName());
                Toast.makeText(LessonsActivity.this,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}



