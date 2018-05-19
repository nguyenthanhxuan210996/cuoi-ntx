package com.example.nguyenthanhxuan.gamecuoiki.asset;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by User on 08/06/2017.
 */

public class ButtonRePlay {
    boolean pressing;
    boolean checked;
    int x,y;
    Rect buttonSize;
    Paint paint;
    final String TAB="ButtonPlay";

    public ButtonRePlay(int x, int y){
        this.x= x;
        this.y= y;
    }
    public void init(){
        buttonSize= new Rect(x,y,x+260,y+110);
        paint= new Paint();
        pressing=false;
        boolean checked=false;
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
        setText("Chơi lại",canvas);
    }

    public void drawButtonDown(Canvas canvas){
        paint.setARGB(255,0,0,255);
        canvas.drawRect(buttonSize,paint);
        setText("Chơi lại",canvas);
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

    public void setChecked(){

    }
    public void ontouch(MotionEvent event){
        int x= (int) event.getX();
        int y= (int) event.getY();
        if(x >= buttonSize.left && x<=buttonSize.right && y>=buttonSize.top && y<=buttonSize.bottom){
            if (event.getAction()==MotionEvent.ACTION_DOWN){
                pressing=true;
            }
            if (event.getAction()==MotionEvent.ACTION_UP){
                pressing= false;
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
