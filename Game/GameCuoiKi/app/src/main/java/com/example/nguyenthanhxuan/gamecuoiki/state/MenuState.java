package com.example.nguyenthanhxuan.gamecuoiki.state;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.nguyenthanhxuan.gamecuoiki.asset.ButtonPlay;

import static com.example.nguyenthanhxuan.gamecuoiki.GameView.asset;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.currentState;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.heightGameView;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.playState;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.widthGameView;


/**
 * Created by User on 18/05/2017.
 */

public class MenuState extends State {
    ButtonPlay buttonPlay;
    final String TAB = "MenuState";
    public MenuState() {
    }

    @Override
    public void setCurrentState() {
        currentState= playState;
        currentState.init();
    }

    @Override
    public void init() {
        bitmapBackground = asset.loadMenuBackground();
        paint = new Paint();
        paint.setTextSize(110);
        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(255, 255, 0, 0);
        rectSrc = new Rect(0, 0, bitmapBackground.getWidth(), bitmapBackground.getHeight());
        rectDst = new Rect(0, 0, widthGameView, heightGameView);
        buttonPlay = new ButtonPlay(33,32);
        buttonPlay.init();
    }

    @Override
    public void update() {
        if(buttonPlay.isChecked()){
            setCurrentState();
        }
    }

    @Override
    public void render(Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
        buttonPlay.render(canvas);
    }

    @Override
    public void onTouch(MotionEvent event) {
        buttonPlay.ontouch(event);
    }

    @Override
    public void destroy() {

    }

    public void drawText(Canvas canvas){
        paint.setARGB(255,255,183,0);
        paint.setTextSize(60);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("Game Trúc xanh",(widthGameView-430)/2,350*heightGameView/1280,paint);
    }

    public void drawBackground(Canvas canvas){
        canvas.drawBitmap(bitmapBackground,rectSrc,rectDst,paint);
    }
}
