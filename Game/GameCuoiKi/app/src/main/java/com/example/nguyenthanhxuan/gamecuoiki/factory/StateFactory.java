package com.example.nguyenthanhxuan.gamecuoiki.factory;

import com.example.nguyenthanhxuan.gamecuoiki.state.MenuState;
import com.example.nguyenthanhxuan.gamecuoiki.state.PlayState;
import com.example.nguyenthanhxuan.gamecuoiki.state.State;

import static com.example.nguyenthanhxuan.gamecuoiki.GameView.MENU_STATE;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.PLAY_STATE;

/**
 * Created by User on 01/06/2017.
 */

public class StateFactory {
    public StateFactory(){

    }

    public State createState(int idState){
        switch (idState){
            case MENU_STATE: return new MenuState();
            case PLAY_STATE: return new PlayState();
            default: return null;
        }

    }
}
