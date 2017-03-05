package com.example.ravi.depaul;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ravi.depaul.contacts.ContactActivity;
import com.example.ravi.depaul.lessons.LessonsActivity;
import com.example.ravi.depaul.events.EventActivity;
import com.example.ravi.depaul.curri.CurrActivity;
import com.example.ravi.depaul.news.NewsActivity;
import com.example.ravi.depaul.news.ParentLogin;
import com.example.ravi.depaul.photos.PhotosActivity;
import com.example.ravi.depaul.policies.PolicyActivity;
import com.example.ravi.depaul.aboutUs.AboutUsActivity;
import com.example.ravi.depaul.teacher.TeacherActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyRandomTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusColor));
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusColor));
        }
        setContentView(R.layout.activity_main);


    }


    public void startTeacherFragment(View view) {
        Intent intent=new Intent(MainActivity.this, TeacherActivity.class);
        startActivity(intent);
    }

    public void startAboutActivity(View view) {
        Intent intent=new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void startContactActivity(View view) {
        Intent intent=new Intent(MainActivity.this, ContactActivity.class);
        startActivity(intent);
    }



    public void startPhotosActivity(View view) {
        Intent intent=new Intent(MainActivity.this, PhotosActivity.class);
        startActivity(intent);
    }


    public void startPolicyActivity(View view) {
        Intent intent=new Intent(MainActivity.this, PolicyActivity.class);
        startActivity(intent);
    }

    public void startNewsActivity(View view) {
        Intent intent=new Intent(MainActivity.this, ParentLogin.class);
        startActivity(intent);
    }

    public void startLessonsActivity(View view) {
        Intent intent=new Intent(MainActivity.this, LessonsActivity.class);
        startActivity(intent);
    }

    public void startEventActivity(View view) {
        Intent intent=new Intent(MainActivity.this, EventActivity.class);
        startActivity(intent);
    }

    public void startCurriculumtActivity(View view) {

        Intent intent=new Intent(MainActivity.this, CurrActivity.class);
        startActivity(intent);
    }

}
