package com.example.ravi.depaul;

import android.app.Activity;
        import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
        import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyRandomTheme);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.statusColor));
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusColor));
        }
        setContentView(R.layout.activity_splash_screen);
        ImageView image2 = (ImageView)findViewById(R.id.imgLogo);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        image2.startAnimation(animation2);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        // Later.. stop the animation
    }

}
