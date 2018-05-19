package com.example.nguyenthanhxuan.gamecuoiki.thread;

import android.util.Log;

import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.count;

/**
 * Created by User on 08/06/2017.
 */

public class WaitingTimeFirstCardTheard extends Thread {
    boolean runing=true;
    int sleptTime=0;
    boolean flag= false;
    @Override
    public void run() {
        while (runing){

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleptTime= sleptTime+10;
            if(sleptTime==1500) break;
        }
        if(!flag) count=3;
    }

    public void stopThread(){
        runing= false;
        flag= true;
    }
}
