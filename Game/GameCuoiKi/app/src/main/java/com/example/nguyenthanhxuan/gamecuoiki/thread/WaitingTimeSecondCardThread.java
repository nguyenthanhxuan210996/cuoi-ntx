package com.example.nguyenthanhxuan.gamecuoiki.thread;

import static com.example.nguyenthanhxuan.gamecuoiki.state.PlayState.count;

/**
 * Created by User on 08/06/2017.
 */

public class WaitingTimeSecondCardThread extends Thread {
    int sleptTime= 0;
    @Override
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count= 5;

    }


}
