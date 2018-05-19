package com.example.nguyenthanhxuan.gamecuoiki.asset;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.nguyenthanhxuan.gamecuoiki.GameView.heightGameView;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.widthGameView;

/**
 * Created by User on 23/05/2017.
 */

public class ButtonPlay {
    boolean pressing=false;
    boolean checked=false;
    int x,y;
    Rect buttonSize;
    Paint paint;

    public ButtonPlay(int x, int y){
        this.x= (int) (1.0*x*widthGameView/100);
        this.y= (int)(1.0*y*heightGameView/100);
    }
    public void init(){
        buttonSize= new Rect(x,y,x+260,y+110);
        paint= new Paint();
    }

    public boolean isChecked(){
        return checked;
    }

    public void setText(String string, Canvas canvas){
        paint.setARGB(255,255,255,255);
        paint.setTextSize(50);
        canvas.drawText(string,x+43,y+70,paint );
    }

    public void drawButtonUp (Canvas canvas){
        paint.setARGB(255,0,255,0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(buttonSize,paint);
        setText("Bắt đầu",canvas);
    }

    public void drawButtonDown(Canvas canvas){
        paint.setARGB(255,0,0,255);
        canvas.drawRect(buttonSize,paint);
        setText("Bắt đầu",canvas);
    }

    public boolean getPressing(){
        return pressing;
    }
    public boolean getChecked(){
        return checked;
    }
    public void update(){

    }
    public void render(Canvas canvas){
        if(pressing) drawButtonDown(canvas);
        else drawButtonUp(canvas);
    }

    public void ontouch(MotionEvent event){
        int x= (int) event.getX();
        int y= (int) event.getY();
        if(x >= buttonSize.left && x<=buttonSize.right && y>=buttonSize.top && y<=buttonSize.bottom){
            if (event.getAction()==MotionEvent.ACTION_DOWN){
                pressing=true;
            }
            if (event.getAction()==MotionEvent.ACTION_UP){
                checked=true;
            }
            if(event.getAction()== MotionEvent.ACTION_MOVE){
                pressing=true;
            }
        } else{
            pressing=false;
            checked=false;
        }

    }

}
