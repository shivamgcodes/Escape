package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
/*getSize() methods

Display screensize = getWindowManager().getDefaultDisplay(); Point size = new Point(); screensize.getSize(size); int width = size.x; int height = size.y;

Ge default display size:

WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE); Display screensize = wm.getDefaultDisplay();

getWidth() and getHeight() methods

Display display = getWindowManager().getDefaultDisplay();  int width = display.getWidth();  // deprecated int height = display.getHeight();  // deprecated

DisplayMetrics() method:

DisplayMetrics displaymetrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(displaymetrics); int height = displaymetrics.heightPixels;  int width = displaymetrics.widthPixels;

Hope this helps you! :)*/

import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.flappybird.gameview.timer;


public class MainActivity extends AppCompatActivity {
 public static int widthscreen;
 public static int heigthscreen;
 public static Button pause;
 public static Button resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

         pause = findViewById(R.id.pause);
         resume = findViewById(R.id.resume);
        pause.setEnabled(true);
        resume.setEnabled(false);
        TextView scoreview = findViewById(R.id.scoredisplayer);
        TimerTask scoredisplayer = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override

                    public void run() {

                        if(!gameview.defeat){
                        scoreview.setText(""+gameview.score);
                    }}
                });
            }
        };
        Timer forscore = new Timer();
        forscore.scheduleAtFixedRate(scoredisplayer , 1000 , 1000);

    }
    public void Reseteverything(View view) {

        gameview gm = null;
         gm.cordinate_X_ = 475;
         gm.cordinate_Y_ = 900;
         gm.velocity_Y_ = 0;
         gm.velocity_X_ = 0;
         gm.defeat = false;
         gm.score = 0;
         gameview.timer.cancel();
         gameview.timer.purge();
         Intent in = new Intent(this, MainActivity.class);
         startActivity(in);
         
    }

    public void resume(View view) {
        view.setEnabled(false);
        view.setAlpha(0);
        pause.setAlpha(1f);
        timer.notify();
    }

    public void pause(View view) throws InterruptedException {
    view.setEnabled(false);
    view.setAlpha(0);
    resume.setAlpha(1f);
    timer.wait();
    }
}