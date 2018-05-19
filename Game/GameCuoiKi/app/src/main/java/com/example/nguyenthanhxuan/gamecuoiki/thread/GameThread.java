package com.example.nguyenthanhxuan.gamecuoiki.thread;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.nguyenthanhxuan.gamecuoiki.GameView;

/**
 * Created by User on 17/05/2017.
 */

public class GameThread  extends Thread{
    private boolean running= true;
    private GameView gameView;
    private SurfaceHolder surfaceHolder;
    public GameThread(GameView gameView, SurfaceHolder surfaceHolder)  {
        this.gameView= gameView;
        this.surfaceHolder= surfaceHolder;
    }

    @Override
    public void run()  {
        long startTime = System.nanoTime();
        Canvas canvas= null;
        while(running)  {
            try {
                canvas = this.surfaceHolder.lockCanvas();
                // Đồng bộ hóa.
                synchronized(canvas) {
                    this.gameView.update();
                    this.gameView.render(canvas);
                }
            }catch(Exception e)  {
                // Không làm gì
            } finally {
                if(canvas!= null)  {
                    // Mở khóa cho Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime() ;
            // Thời gian cập nhập lại giao diện Game
            // (Đổi từ Nanosecond ra milisecond).
            long waitTime = (now - startTime)/1000000;
            if(waitTime < 10)  {
                waitTime= 10; // Millisecond.
            }
            System.out.print(" Wait Time="+ waitTime);

            try {
                // Ngừng chương trình một chút.
                this.sleep(waitTime);
            } catch(InterruptedException e)  {

            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean running)  {
        this.running= running;
    }
}
