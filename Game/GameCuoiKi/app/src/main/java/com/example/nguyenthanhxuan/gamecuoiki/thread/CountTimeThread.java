package com.example.nguyenthanhxuan.gamecuoiki.thread;

import android.util.Log;

import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.countTime;

/**
 * Created by User on 09/06/2017.
 */

public class CountTimeThread extends Thread {
    boolean runing=true;
    @Override
    public void run() {
        while (countTime>0 &&runing){
            if (!runing) break;
            try {
                Thread.sleep(1000);
                if (!runing) break;
                if(countTime > 0) countTime--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        runing=false;
    }
}
