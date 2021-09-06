package com.example.flappybird;
//timer runs on its own thread

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import android.os.Handler;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import static com.example.flappybird.MainActivity.heigthscreen;
import static com.example.flappybird.MainActivity.widthscreen;
import static java.lang.Thread.sleep;


class invalidatemethod extends Thread {

    View view;

    invalidatemethod(View view) {

        this.view = view;
    }

    public void run() {
        view.invalidate();

    }
}

public class gameview extends View {
    public static int cordinate_X_ = 475;
    private static boolean running = true;
    public static int cordinate_Y_ = 900;
    public static Timer timer;
    private Runnable detectcollisions;
    private Runnable assignvelocities;

    private long currenttime;
    private long lasttime;
    private boolean rushhour = false;
    private boolean printbird = true;
    protected static boolean defeat = false;
    private boolean usecorona1;
    private TimerTask sendcorona;
    private static int distance_X_;
    private static int distance_Y_;
    public static int goto_X_ = -1;
    public static int goto_Y_ = -1;
    public static int velocity_X_ = -259;
    public static int velocity_Y_ = -259;
    private static boolean distance_X_ispositive;
    public static boolean distance_Y_ispositive;
    public static int totalvelocity;

    public static int score = 0;
    public static Bitmap bm;
    public static Bitmap bm1, bm2, cr1, lose1, warning1, warning2, cr2, cr3, cr4, cr5, cr6 , crchase;
    private static boolean b = true;
    private static boolean c = true;
    private Object object;
    enemies corona1 = new enemies();
    enemies corona2 = new enemies();
    enemies corona3 = new enemies();
    enemies corona4 = new enemies();
    enemies corona5 = new enemies();
    enemies corona6 = new enemies();
    enemies corona7 = new enemies();
    // use crow 4 and 5 only and only during the rush hour
    static Handler handler = new Handler();

    public Runnable r;
    Paint redstroke;
 public void forcollisions(enemies CORONA ){

     if (CORONA.cordinate_X_ + 10 > cordinate_X_ && CORONA.cordinate_X_ + 10 < cordinate_X_ + (widthscreen / 11)) {
         if (CORONA.cordinate_Y_ + 10 > cordinate_Y_ && CORONA.cordinate_Y_ + 10 < cordinate_Y_ + (heigthscreen / 18)) {

             defeat = true;
         }
         if (CORONA.cordinate_Y_ + (heigthscreen / 18) - 10 < cordinate_Y_ + (heigthscreen / 18) && CORONA.cordinate_Y_ - 10 + (heigthscreen / 18) > cordinate_Y_) {

             defeat = true;
         }
     }
 }
    public gameview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);



        timer = new Timer();
        Random random = new Random();

        sendcorona = new TimerTask() {
            @Override
            public void run() {
                score++;

                if(corona7.state == enemies.State.ALIVE && corona7.themethodtobeused == enemies.methodtobeused.chase){corona7.state = enemies.State.DEAD;}
                if (score % 25 == 0 && score / 25 == 1) {

                    rushhour = true;
                    corona1.state = enemies.State.ALIVE;
                    corona2.state = enemies.State.ALIVE;
                    corona5.state = enemies.State.ALIVE;
                    corona6.state = enemies.State.ALIVE;
                    corona1.themethodtobeused = enemies.methodtobeused.assignsin;
                    corona2.themethodtobeused = enemies.methodtobeused.assignsin;
                    corona5.themethodtobeused = enemies.methodtobeused.assignsinrev;
                    corona6.themethodtobeused = enemies.methodtobeused.assignsinrev;
                    corona5.cordinate_X_ = widthscreen;
                    corona6.cordinate_X_ = widthscreen;

                }
                else if (score % 25 == 0 && score / 25 == 2) {
                    rushhour = true;
                    corona1.themethodtobeused = enemies.methodtobeused.assignsin;
                    corona2.themethodtobeused = enemies.methodtobeused.assignsin;
                    corona3.themethodtobeused = enemies.methodtobeused.assignsin;
                    corona4.themethodtobeused = enemies.methodtobeused.assignsinrev;
                    corona5.themethodtobeused = enemies.methodtobeused.assignsinrev;
                    corona6.themethodtobeused = enemies.methodtobeused.assignsinrev;
                    corona1.state = enemies.State.ALIVE;
                    corona2.state = enemies.State.ALIVE;
                    corona3.state = enemies.State.ALIVE;
                    corona4.state = enemies.State.ALIVE;
                    corona5.state = enemies.State.ALIVE;
                    corona6.state = enemies.State.ALIVE;
                    corona4.cordinate_X_ = widthscreen;
                    corona5.cordinate_X_ = widthscreen;
                    corona6.cordinate_X_ = widthscreen;
                }
                else if (score % 25 == 0 && score / 25 == 3) {
                    rushhour = true;
                    corona1.themethodtobeused = enemies.methodtobeused.goparabolalefttoleft;
                    corona2.themethodtobeused = enemies.methodtobeused.goparabolalefttoleft;
                    corona5.themethodtobeused = enemies.methodtobeused.goprabolatightoright;
                    corona6.themethodtobeused = enemies.methodtobeused.goprabolatightoright;
                    corona1.state = enemies.State.ALIVE;
                    corona2.state = enemies.State.ALIVE;
                    corona5.state = enemies.State.ALIVE;
                    corona6.state = enemies.State.ALIVE;
                    // when 1 and 2 are dead only then 5 and 6 will work

                }
                 else if (score % 25 == 0 && score / 25 == 4){
                    rushhour = true;
                    corona1.themethodtobeused = enemies.methodtobeused.gologlefttobottom;
                    corona3.themethodtobeused = enemies.methodtobeused.gologlefttotop;
                    corona4.themethodtobeused = enemies.methodtobeused.gologrighttobottom;
                    corona6.themethodtobeused = enemies.methodtobeused.gologrighttotop;
                    corona1.state = enemies.State.ALIVE;
                    corona3.state = enemies.State.ALIVE;
                    corona4.state = enemies.State.ALIVE;
                    corona6.state = enemies.State.ALIVE;

                }
               else if(!rushhour && score%5 == 0)
                {

                        corona7.themethodtobeused = enemies.methodtobeused.chase;
                        corona7.state = enemies.State.ALIVE;
                        corona7.cordinate_X_ = 500;
                        corona7.cordinate_Y_ = 600;

                    }
                else if(!rushhour && score%3 == 0){
                    switch ((score/3) %3){
                        case (0):{
                            corona1.state = enemies.State.ALIVE;
                            corona2.state = enemies.State.ALIVE;
                            corona3.state = enemies.State.ALIVE;
                            corona1.themethodtobeused = enemies.methodtobeused.gostraight;
                            corona2.themethodtobeused = enemies.methodtobeused.gostraight;
                            corona3.themethodtobeused = enemies.methodtobeused.gostraight;
                            corona1.cordinate_X_ = -30;
                            corona2.cordinate_X_ = -30;
                            corona3.cordinate_X_ = -30;
                            corona1.cordinate_Y_ = (int)(0.25*heigthscreen) - 100 -50;
                            corona2.cordinate_Y_ = (int)(0.5*heigthscreen)- 100;
                            corona3.cordinate_Y_ = (int)(0.75*heigthscreen)- 100 + 50;
                            break;
                        }
                        case(1):{
                            corona1.state = enemies.State.ALIVE;
                            corona2.state = enemies.State.ALIVE;
                            corona3.state = enemies.State.ALIVE;
                            corona1.themethodtobeused = enemies.methodtobeused.gostraight;
                            corona2.themethodtobeused = enemies.methodtobeused.gostraightrev;
                            corona3.themethodtobeused = enemies.methodtobeused.gostraight;
                            corona1.cordinate_X_ = -30;
                            corona2.cordinate_X_ = widthscreen + 30;
                            corona3.cordinate_X_ = -30;
                            corona1.cordinate_Y_ = (int)(0.25*heigthscreen)- 100 -  50;
                            corona2.cordinate_Y_ = (int)(0.5*heigthscreen)- 100;
                            corona3.cordinate_Y_ = (int)(0.75*heigthscreen)- 100 + 50;
                            break;
                        }
                        case 2:
                        {
                            corona1.state = enemies.State.ALIVE;
                            corona2.state = enemies.State.ALIVE;
                            corona3.state = enemies.State.ALIVE;
                            corona1.themethodtobeused = enemies.methodtobeused.gostraightrev;
                            corona2.themethodtobeused = enemies.methodtobeused.gostraightrev;
                            corona3.themethodtobeused = enemies.methodtobeused.gostraightrev;
                            corona1.cordinate_X_ = widthscreen + 30;
                            corona2.cordinate_X_ = widthscreen + 30;
                            corona2.cordinate_X_ = widthscreen + 30;
                            corona1.cordinate_Y_ = (int)(0.25*heigthscreen)- 100 - 50;
                            corona2.cordinate_Y_ = (int)(0.5*heigthscreen)- 100;
                            corona3.cordinate_Y_ = (int)(0.75*heigthscreen)- 100 + 50;
                            break;
                        }
                    }
                }
                 else{
                    if (usecorona1) {
                        corona1.state = enemies.State.ALIVE;
                        corona2.state = enemies.State.DEAD;
                        corona1.cordinate_Y_ = random.nextInt(1800);
                        corona1.cordinate_Y_ += 100;
                        corona1.cordinate_X_ = -30;
                        corona1.initiationtime = System.currentTimeMillis();
                        corona1.themethodtobeused = enemies.methodtobeused.gostraight;
                        usecorona1 = false;
                    } if(!usecorona1) {
                        corona2.state = enemies.State.ALIVE;
                        corona1.state = enemies.State.DEAD;
                        corona2.themethodtobeused = enemies.methodtobeused.gostraight;
                        corona2.cordinate_Y_ = random.nextInt(1800);
                        corona2.cordinate_Y_ += 100;
                        corona2.cordinate_X_ = -30;
                        corona2.initiationtime = System.currentTimeMillis();
                        usecorona1 = true;
                    }
                }
            }
        };



        timer.scheduleAtFixedRate(sendcorona, 2000, 5000);
        detectcollisions = new Runnable() {
            @Override
            public void run() {
                while(!defeat) {
                    if (corona1.state == enemies.State.ALIVE) {
                        forcollisions(corona1);
                    }
                    if (corona2.state == enemies.State.ALIVE) {
                        forcollisions(corona2);
                    }
                    if (corona3.state == enemies.State.ALIVE) {
                        forcollisions(corona3);
                    }
                    if (corona4.state == enemies.State.ALIVE) {
                        forcollisions(corona4);
                    }
                    if (corona5.state == enemies.State.ALIVE) {
                        forcollisions(corona5);
                    }
                    if (corona6.state == enemies.State.ALIVE) {
                        forcollisions(corona6);
                    }
                    if (corona7.state == enemies.State.ALIVE) {
                        forcollisions(corona7);
                    }

                }

            }
        };


        new Thread(detectcollisions).start();


        DisplayMetrics dm = new DisplayMetrics();
        dm = this.getResources().getDisplayMetrics();
        widthscreen = dm.widthPixels;
        heigthscreen = dm.heightPixels;
        bm1 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.human11);
        bm1 = Bitmap.createScaledBitmap(bm1, widthscreen / 11, heigthscreen / 18, false);
        bm2 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.human12);
        bm2 = Bitmap.createScaledBitmap(bm2, widthscreen / 11, heigthscreen / 18, false);
        cr1 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr1 = Bitmap.createScaledBitmap(cr1, widthscreen / 13, heigthscreen / 18, false);
        cr2 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr2 = Bitmap.createScaledBitmap(cr2, widthscreen / 13, heigthscreen / 18, false);
        cr3 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr3 = Bitmap.createScaledBitmap(cr2, widthscreen / 13, heigthscreen / 18, false);
        cr4 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr4 = Bitmap.createScaledBitmap(cr2, widthscreen / 13, heigthscreen / 18, false);
        cr5 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr5 = Bitmap.createScaledBitmap(cr2, widthscreen / 13, heigthscreen / 18, false);
        cr6 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronanorma);
        cr6 = Bitmap.createScaledBitmap(cr2, widthscreen / 13, heigthscreen / 18, false);
        crchase = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.coronachasea);
        crchase = Bitmap.createScaledBitmap(crchase, widthscreen / 13, heigthscreen / 18, false);
        lose1 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.lose);
        lose1 = Bitmap.createScaledBitmap(lose1, widthscreen / 2, heigthscreen / 4, false);
        lasttime = System.currentTimeMillis();
        warning1 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.warningsimple);
        warning1 = Bitmap.createScaledBitmap(warning1, widthscreen / 15, widthscreen / 15, false);
        warning2 = BitmapFactory.decodeResource(this.getContext().getResources(), R.drawable.warningheatseeker);
        warning2 = Bitmap.createScaledBitmap(warning2, widthscreen / 15, widthscreen / 15, false);




        assignvelocities = new Runnable() {
            @Override
            public void run() {

                distance_X_ = goto_X_ - cordinate_X_;
                distance_Y_ = goto_Y_ - cordinate_Y_;

                if (distance_X_ < 0) {
                    distance_X_ispositive = false;
                } else {
                    distance_X_ispositive = true;
                }
                if (distance_Y_ < 0) {
                    distance_Y_ispositive = false;
                } else {
                    distance_Y_ispositive = true;
                }

                if (!distance_X_ispositive) {
                    distance_X_ = distance_X_ * -1;
                }

                if (!distance_Y_ispositive) {
                    distance_Y_ = distance_Y_ * -1;
                }

                velocity_X_ = distance_X_;
                velocity_Y_ = distance_Y_;
                totalvelocity = velocity_X_ + velocity_Y_;
                if(totalvelocity != 0){
                velocity_X_ = velocity_X_ * 18 / totalvelocity;
                velocity_Y_ = velocity_Y_ * 18 / totalvelocity;}
                if(totalvelocity == 0){totalvelocity = 1;}
                if (!distance_X_ispositive) {
                    velocity_X_ = velocity_X_ * -1;
                }
                if (!distance_Y_ispositive) {
                    velocity_Y_ = velocity_Y_ * -1;
                }


            }
        };


        r = new Runnable() {
            @Override
            public void run() {
                if (running) {
                    if (velocity_X_ != -259 && velocity_Y_ != -259) {


                        cordinate_X_ += velocity_X_;
                        cordinate_Y_ += velocity_Y_;

                        if (cordinate_X_ < 0 || cordinate_X_ > widthscreen - widthscreen / 11) {
                            velocity_X_ = velocity_X_ * -1;


                        }
                        if (cordinate_Y_ < 0 || cordinate_Y_ > heigthscreen - heigthscreen / 18) {
                            velocity_Y_ = velocity_Y_ * -1;
                        }

                    } else {
                        if (cordinate_X_ < 0) {
                            b = true;
                        }
                        if (cordinate_X_ > 925) {
                            b = false;
                        }
                        if (b) {
                            cordinate_X_ += 5;
                        }
                        if (!b) {
                            cordinate_X_ -= 5;
                        }

                        if (cordinate_Y_ < 0) {
                            c = true;
                        }
                        if (cordinate_Y_ > 1800) {
                            c = false;
                        }
                        if (c) {
                            cordinate_Y_ += 7;
                        }
                        if (!c) {
                            cordinate_Y_ -= 7;
                        }
                    }




                }
                postInvalidate();



            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerId = event.getPointerId(0);
        int pointerIndex = event.findPointerIndex(pointerId);
        goto_X_ = (int) event.getX(pointerIndex);
        goto_Y_ = (int) event.getY(pointerIndex);

        new Thread(assignvelocities).start();
        return true;
    }

    public void onDraw(Canvas canvas) {

        canvas.drawColor(Color.BLUE);



        currenttime = System.currentTimeMillis();

        if (currenttime - lasttime > 100) {
            if (printbird) {
                printbird = false;
                lasttime = System.currentTimeMillis();
            } else {
                printbird = true;
                lasttime = System.currentTimeMillis();
            }
        }

        if (printbird) {
            canvas.drawBitmap(bm2, cordinate_X_, cordinate_Y_, null);
        } else if (!printbird) {
            canvas.drawBitmap(bm1, cordinate_X_, cordinate_Y_, null);

        }

        if (rushhour) {
            if (corona1.state == enemies.State.ALIVE) {


                switch (corona1.themethodtobeused) {
                    case assignsin:
                        corona1.assignsin(corona1.cordinate_X_, 0.25 * heigthscreen);
                        canvas.drawBitmap(cr1, corona1.cordinate_X_, corona1.cordinate_Y_, null);
                        break;


                    case gologlefttobottom:
                        corona1.gologlefttobottom(corona1.cordinate_X_);
                        canvas.drawBitmap(cr1, corona1.cordinate_X_, corona1.cordinate_Y_, null);
                        break;

                    case goparabolalefttoleft:
                        corona1.goparabolalefttoleft(0.3 * heigthscreen, cordinate_X_);
                        canvas.drawBitmap(cr1, corona1.cordinate_X_, corona1.cordinate_Y_, null);
                        break;


                }

            }
            if (corona2.state == enemies.State.ALIVE) {
                switch (corona2.themethodtobeused) {
                    case assignsin:
                        corona2.assignsin(corona1.cordinate_X_, 0.5 * heigthscreen);
                        canvas.drawBitmap(cr2, corona2.cordinate_X_, corona2.cordinate_Y_, null);
                        break;

                    case goparabolalefttoleft:
                        corona2.goparabolalefttoleft(0.6 * heigthscreen, cordinate_X_);
                        canvas.drawBitmap(cr2, corona2.cordinate_X_, corona2.cordinate_Y_, null);
                        break;
                }

            }
            if (corona3.state == enemies.State.ALIVE) {
                switch (corona3.themethodtobeused) {
                    case assignsin:
                        corona3.assignsin(corona3.cordinate_X_, 0.75 * heigthscreen);
                        canvas.drawBitmap(cr3, corona3.cordinate_X_, corona3.cordinate_Y_, null);

                        break;

                    case gologlefttotop:
                        corona3.gologlefttotop(corona3.cordinate_X_);
                        canvas.drawBitmap(cr3, corona3.cordinate_X_, corona3.cordinate_Y_, null);
                        break;
                }

            }
            if (corona4.state == enemies.State.ALIVE) {
                switch (corona4.themethodtobeused) {
                    case assignsinrev:
                        if (corona1.state == enemies.State.DEAD) {
                            corona4.assignsinrev(corona4.cordinate_X_, 0.15 * heigthscreen);
                            canvas.drawBitmap(cr4, corona4.cordinate_X_, corona4.cordinate_Y_, null);
                        }
                        break;

                    case goprabolatightoright:
                        if (corona1.state == enemies.State.DEAD) {
                            corona4.goparabolarighttoright(0.45 * heigthscreen, corona4.cordinate_X_);
                            canvas.drawBitmap(cr4, corona4.cordinate_X_, corona4.cordinate_Y_, null);
                        }
                        break;
                    case gologrighttobottom:{
                        corona4.gologrighttobottom(corona4.cordinate_X_);
                        canvas.drawBitmap(cr4, corona4.cordinate_X_, corona4.cordinate_Y_, null);
                        break;}

                }

            }
            if (corona5.state == enemies.State.ALIVE) {
                switch (corona5.themethodtobeused) {

                    case goprabolatightoright:
                        if (corona2.state == enemies.State.DEAD) {
                            corona5.goparabolarighttoright(0.75 * heigthscreen, corona5.cordinate_X_);
                            canvas.drawBitmap(cr5, corona5.cordinate_X_, corona5.cordinate_Y_, null);
                        }
                        break;
                    case assignsinrev:
                        if (corona2.state == enemies.State.DEAD) {
                            corona5.assignsinrev(corona5.cordinate_X_, 0.45 * heigthscreen);
                            canvas.drawBitmap(cr5, corona5.cordinate_X_, corona5.cordinate_Y_, null);

                        }
                        break;

                }

            }
            if (corona6.state == enemies.State.ALIVE) {
                switch (corona6.themethodtobeused) {

                    case assignsinrev:
                        if (corona2.state == enemies.State.DEAD) {
                            corona6.assignsinrev(corona6.cordinate_X_, 0.67 * heigthscreen);
                            canvas.drawBitmap(cr6, corona6.cordinate_X_, corona6.cordinate_Y_, null);

                        }
                        break;

                    case gologrighttotop:
                        if (corona3.state == enemies.State.DEAD) {
                            corona6.gologrighttotop(corona6.cordinate_X_);
                            canvas.drawBitmap(cr6, corona6.cordinate_X_, corona6.cordinate_Y_, null);
                        }
                        break;
                }

            }
            else if( corona1.state == corona2.state && corona2.state == corona3.state && corona3.state == corona4.state && corona4.state == corona5.state && corona5.state == corona6.state && corona6.state == enemies.State.DEAD) {
                rushhour = false;
            }
        }
        if(corona7.state == enemies.State.ALIVE){
            corona7.assignvelocities(cordinate_X_ , cordinate_Y_);
            canvas.drawBitmap(crchase , corona7.cordinate_X_ , corona7.cordinate_Y_ , null);
        }
        if(corona1.state == enemies.State.ALIVE){
            if(corona1.themethodtobeused == enemies.methodtobeused.gostraight){
                corona1.gostraight(corona1.cordinate_X_);

            }
            else if(corona1.themethodtobeused == enemies.methodtobeused.gostraightrev){
                corona1.gostraightrev(corona1.cordinate_X_);

            }

             canvas.drawBitmap(cr1 , corona1.cordinate_X_ , corona1.cordinate_Y_ , null);
        }
        if(corona2.state == enemies.State.ALIVE){
            if(corona2.themethodtobeused == enemies.methodtobeused.gostraight){
                corona2.gostraight(corona2.cordinate_X_);
            }
            else if(corona2.themethodtobeused == enemies.methodtobeused.gostraightrev){
                corona2.gostraightrev(corona2.cordinate_X_);
            }
            canvas.drawBitmap(cr2 , corona2.cordinate_X_ , corona2.cordinate_Y_ , null);

        }
        if(corona3.state == enemies.State.ALIVE){
            if(corona3.themethodtobeused == enemies.methodtobeused.gostraight){
                corona3.gostraight(corona3.cordinate_X_);
            }
            else if(corona3.themethodtobeused == enemies.methodtobeused.gostraightrev){
                corona3.gostraightrev(corona3.cordinate_X_);
            }
            canvas.drawBitmap(cr3 , corona3.cordinate_X_ , corona3.cordinate_Y_ , null);

        }


        if (defeat) {

            canvas.drawBitmap(lose1, widthscreen / 3, heigthscreen / 4, null);


        } else {
            r.run();
        }


    }

}
