package com.example.nguyenthanhxuan.gamecuoiki.state;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by User on 18/05/2017.
 */

public abstract class State {
    Bitmap bitmapBackground;
    Paint paint;
    Rect rectSrc;
    Rect rectDst;

    public abstract void setCurrentState();
    public abstract void init();
    public abstract void update();
    public abstract void render(Canvas canvas);
    public abstract void onTouch(MotionEvent motionEvent);
    public abstract void destroy();
}


