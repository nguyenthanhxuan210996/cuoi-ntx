package com.example.nguyenthanhxuan.gamecuoiki;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.nguyenthanhxuan.gamecuoiki.asset.Asset;
import com.example.nguyenthanhxuan.gamecuoiki.factory.StateFactory;
import com.example.nguyenthanhxuan.gamecuoiki.state.PlayState;
import com.example.nguyenthanhxuan.gamecuoiki.state.State;
import com.example.nguyenthanhxuan.gamecuoiki.thread.GameThread;

/**
 * Created by Nguyen Thanh Xuan on 29/04/2018.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    GameThread gameThread;
    volatile public static State currentState;
    public static Asset asset;
    public static final int MENU_STATE=1;
    public static final int PLAY_STATE=2;
    public static State playState;
    Paint paint;
    public static int heightGameView;
    public static int widthGameView;

    public GameView(Context context) {
        super(context);
        // Đảm bảo GameView có thể focus để điều khiển các sự kiện.
        this.setFocusable(true);
        // Sét đặt các sự kiện liên quan tới Game.
        this.getHolder().addCallback(this);
    }
    public void init(){

        asset= new Asset(getContext());
        asset.initSounds();
        asset.playBackgroundSound();
        heightGameView= this.getHeight();
        widthGameView= this.getWidth();
        currentState.init();

    }
    public void update(){
        currentState.update();
    }
    public void render(Canvas canvas){
        currentState.render(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentState.onTouch(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        StateFactory stateFactory= new StateFactory();
        currentState= stateFactory.createState(MENU_STATE);
        playState= new PlayState();
        init();
        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        asset.stopBackgroundSound();
        currentState.destroy();
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);
                // Luồng cha, cần phải tạm dừng chờ GameThread kết thúc.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= false;
        }
    }
}
