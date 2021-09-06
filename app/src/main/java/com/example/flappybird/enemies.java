package com.example.flappybird;

import android.util.DisplayMetrics;

import java.lang.Math;

import static com.example.flappybird.MainActivity.heigthscreen;
import static com.example.flappybird.MainActivity.widthscreen;



public class enemies {
    protected  boolean distance_X_ispositive;
    private  boolean distance_Y_ispositive;

    protected int cordinate_Y_ = - 50;
     protected  int cordinate_X_ = -50;
 protected  int degrees = 0;
    protected  int velocity_X_static = 15;
    protected int velocity_X_;
    protected int velocity_Y_;


    protected int goto_X_;
    protected int goto_Y_;
    private boolean parabltol = true;

    private boolean parabrtor = true;
    protected int distance_X_ , distance_Y_;
    protected int totalvelocity;
    protected double forhelicopter = 1;
    protected long initiationtime;


    enum State  {ALIVE , DEAD};
    State state;


    enum methodtobeused { chase,assignsin , assignsinrev , gostraight , gostraightrev ,gologlefttobottom , gologlefttotop , gologrighttobottom , gologrighttotop  , goparabolalefttoleft , goprabolatightoright}
    methodtobeused themethodtobeused;

    enemies(){
        state = State.DEAD;
    }
    public  void assignvelocities(int goto_X_ , int goto_Y_){

        distance_X_ = goto_X_ - cordinate_X_;
        distance_Y_ = goto_Y_ - cordinate_Y_;

        if(distance_X_ < 0 ){distance_X_ispositive = false;}
        else{ distance_X_ispositive = true;}
        if(distance_Y_ < 0 ){distance_Y_ispositive = false;}
        else{ distance_Y_ispositive = true;}
        if(!distance_X_ispositive){ distance_X_ = distance_X_*-1;}

        if(!distance_Y_ispositive){ distance_Y_ = distance_Y_*-1;}

        velocity_X_ = distance_X_;
        velocity_Y_ = distance_Y_;
        totalvelocity = velocity_X_ + velocity_Y_;
        velocity_X_ = velocity_X_*12/totalvelocity;
        velocity_Y_ = velocity_Y_*12/totalvelocity;
        if(!distance_X_ispositive){velocity_X_ = velocity_X_*-1;}
        if(!distance_Y_ispositive){velocity_Y_ = velocity_Y_*-1;}
        cordinate_X_ += velocity_X_;
        cordinate_Y_ += velocity_Y_;

    }
    public void assignsin(int cordinate_Xa , double mainY){
        if(cordinate_Xa < widthscreen)
        {
         cordinate_X_ = cordinate_Xa + 8;
         cordinate_Y_ = (int) (mainY + 100*Math.sin(Math.toRadians(degrees)));
         degrees += 5;
        }
        else {state = State.DEAD;
             }
    }
    public void assignsinrev(int cordinate_Xa , double mainY){
        if(cordinate_Xa > 0)
        {
            cordinate_X_ = cordinate_Xa - 8;
            cordinate_Y_ = (int) (mainY + 200*Math.sin(Math.toRadians(degrees)));
            degrees += 5;
        }
        else {state = State.DEAD;
             }


    }
    public void gostraight(int cordinate_Xa){
        cordinate_X_ = cordinate_Xa;
        cordinate_X_+=7;
        if(cordinate_X_ > widthscreen){state = State.DEAD;
             }
    }
    public void gostraightrev(int cordinate_Xa){
        cordinate_X_ = cordinate_Xa;
        cordinate_X_-=7;
        if(cordinate_X_ < 0){state = State.DEAD;
             }
    }

    public void gologlefttobottom(int cordinate_Xa ){

        cordinate_Y_ = 50 +(int) Math.pow(2, forhelicopter);

       cordinate_X_ = cordinate_Xa + 8;
       forhelicopter += 0.08;
       if(cordinate_Y_ > heigthscreen){state = State.DEAD;
            

       forhelicopter = 1;
       }
    }

    public void gologlefttotop(int cordinate_Xa ){

        cordinate_Y_ = 50 +(int) Math.pow(2, forhelicopter);
        cordinate_Y_= heigthscreen - cordinate_Y_ - 100;
        cordinate_X_ = cordinate_Xa + 8;
        forhelicopter += 0.08;
        if(cordinate_Y_ < 0){state = State.DEAD;
             
            forhelicopter = 1;}
    }
    public void gologrighttobottom(int cordinate_Xa ){

        cordinate_Y_ = 50 +(int) Math.pow(2, forhelicopter);

        cordinate_X_ = cordinate_Xa - 8;
        forhelicopter += 0.08;
        if(cordinate_Y_ > heigthscreen){state = State.DEAD;
             
            forhelicopter = 1;}
    }
    public void gologrighttotop(int cordinate_Xa ){

        cordinate_Y_ = 50 +(int) Math.pow(2, forhelicopter);
cordinate_Y_ = heigthscreen  - cordinate_Y_ - 100;
        cordinate_X_ = cordinate_Xa - 8;
        forhelicopter += 0.08;
        if(cordinate_Y_ < 0){state = State.DEAD;
             
            forhelicopter = 1;}
    }
    public void goparabolalefttoleft(double vertex_Y_ , int cordinate_Xa){

        if( parabltol){
            cordinate_X_ = cordinate_Xa + 12;
            cordinate_Y_ = (int) (vertex_Y_ + 10*Math.sqrt((0.75*widthscreen - cordinate_X_ )));
            if(cordinate_X_ > 0.75*widthscreen){parabltol = false;
            }
        }
        else {

            cordinate_Y_ = (int) (vertex_Y_ - (int)(10*Math.sqrt((0.75*widthscreen - cordinate_X_ ))));
            cordinate_X_ = cordinate_Xa - 12;
            if(cordinate_X_ < 0 ) {parabltol = true;
            state = State.DEAD;
                 }
        }

    }

    public void goparabolarighttoright(double vertex_Y_ , int cordinate_Xa_)
    {

        if (parabrtor) {

            cordinate_X_ = cordinate_Xa_ - 12;
            cordinate_Y_ = (int)(vertex_Y_ + (int)(10*Math.sqrt(cordinate_X_ - 0.25*widthscreen)));
            if(cordinate_X_ < 0.25*widthscreen){ parabrtor = false;}

        }
        else {
            cordinate_Y_ = (int)(vertex_Y_ - (int)(10*Math.sqrt(cordinate_X_ - 0.25*widthscreen)));
            cordinate_X_ = cordinate_Xa_ + 12;
            if(cordinate_X_ > widthscreen ){parabrtor = true;
            state = State.DEAD;
                 }
        }

    }



}
//public Canvas drawCircles(Canvas canvas) {
//		circlePaint = new Paint();
//		circlePaint.setDither(true);
//		circlePaint.setColor(Color.BLACK);  // alpha.r.g.b
//		circlePaint.setStyle(Paint.Style.STROKE);
//		circlePaint.setStrokeJoin(Paint.Join.ROUND);
//		circlePaint.setStrokeCap(Paint.Cap.ROUND);
//		circlePaint.setStrokeWidth(2);
//
//		int numCircles = screenWi / 100;
//		int circleDia = screenWi / numCircles;
//		for (int i = 0; i < numCircles; i++) {
//			for (int j = 0; i < numCircles; j++) {
//				circleLocations.add(new Point(screenWi - (j * (circleDia / 2)), screenHi - (i * (circleDia / 2))));
//			}
//		}
//
//		for (Point point : circleLocations) {
//			canvas.drawCircle(point.x, point.y, circleDia / 2, circlePaint);
//		}
//		return canvas;
//	}
//can also make such a method and then use the canvas to draw in onDraw;