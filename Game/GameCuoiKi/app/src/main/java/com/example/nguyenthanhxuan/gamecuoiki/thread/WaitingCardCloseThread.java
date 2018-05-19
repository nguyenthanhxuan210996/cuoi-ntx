package com.example.nguyenthanhxuan.gamecuoiki.thread;

import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.flagCloseCard;
import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.flagOnTouch;
import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.gamestart;

/**
 * Created by User on 08/06/2017.
 */

public class WaitingCardCloseThread extends Thread {
    @Override
    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flagCloseCard=true;
        flagOnTouch=true;
        gamestart=true;
    }
}
