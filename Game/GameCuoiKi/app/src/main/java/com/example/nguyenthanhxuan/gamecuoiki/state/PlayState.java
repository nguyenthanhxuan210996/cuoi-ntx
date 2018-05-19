package com.example.nguyenthanhxuan.gamecuoiki.state;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.nguyenthanhxuan.gamecuoiki.asset.ButtonRePlay;
import com.example.nguyenthanhxuan.gamecuoiki.asset.Point;
import com.example.nguyenthanhxuan.gamecuoiki.thread.CountTimeThread;
import com.example.nguyenthanhxuan.gamecuoiki.thread.WaitingCardCloseThread;
import com.example.nguyenthanhxuan.gamecuoiki.thread.WaitingTimeFirstCardTheard;
import com.example.nguyenthanhxuan.gamecuoiki.thread.WaitingTimeSecondCardThread;

import java.util.Random;

import static com.example.nguyenthanhxuan.gamecuoiki.GameView.asset;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.heightGameView;
import static com.example.nguyenthanhxuan.gamecuoiki.GameView.widthGameView;

/**
 * Created by User on 18/05/2017.
 */

public class PlayState extends State {
    private Bitmap spritePokemon;
    private Rect rectGameBoard;
    private int heightGameBoard;
    private int widthGameBoard;
    private int leftGameBoard;
    private int rightGameBoard;
    private int topGameBoard;
    private int bottomGameBoard;
    private int widthCard;
    private int heightCard;
    private int widthSrcCard;
    private int heightSrcCard;
    private int colOpenedCardBefore;
    private int rowOpenedCardBefore;
    private int countWin;
    public static volatile int countTime;
    public volatile static boolean flagCloseCard;
    private Rect[][] rectCloseCard;
    private Point[][] positionOpenCard;
    private Point positionOpenedCardBefore;
    private Point[] pairOpenedCard;
    ButtonRePlay buttonRePlay;
    Boolean[][] openedCard;
    boolean lose;
    volatile public static boolean  gamestart=false;
    public static final int NUM_COL=4;
    public static final int NUM_ROW=4;
    public static final int BORDER_WIDTH=3;
    volatile public static int count;
    volatile public static boolean flagOnTouch;
    WaitingTimeFirstCardTheard waitingTimeFirstCardTheard;
    CountTimeThread countTimeThread;
    @Override
    public void setCurrentState() {

    }

    @Override
    public void init() {
        countTime=30;
        lose=false;
        flagOnTouch= true;
        countTimeThread =new CountTimeThread();
        gamestart= false;
        rectCloseCard= new Rect[NUM_COL][NUM_ROW];
        positionOpenCard= new Point[NUM_COL][NUM_ROW];
        openedCard= new Boolean[NUM_COL][NUM_ROW];
        pairOpenedCard= new Point[2];
        flagCloseCard= false;
        count=1;
        countWin=0;
        buttonRePlay= new ButtonRePlay(260, 500);
        buttonRePlay.init();
        spritePokemon= asset.loadSpritePokemon();
        widthSrcCard= spritePokemon.getWidth()/4;
        heightSrcCard= spritePokemon.getHeight()/2;
        bitmapBackground= asset.loadMenuBackground();
        rectSrc= new Rect(0,0,bitmapBackground.getWidth(),bitmapBackground.getHeight());
        rectDst= new Rect(0,0,widthGameView,heightGameView);
        heightGameBoard= (int)(widthGameView*(9.0/10));
        if(heightGameBoard%2 != 0) heightGameBoard=heightGameBoard-1;
        widthGameBoard= heightGameBoard;
        leftGameBoard= (widthGameView-heightGameBoard)/2;
        topGameBoard= 150;//(widthGameView-heightGameBoard)/2;
        rightGameBoard= widthGameBoard+leftGameBoard;
        bottomGameBoard= heightGameBoard+topGameBoard;
        rectGameBoard= new Rect(leftGameBoard,topGameBoard, rightGameBoard, bottomGameBoard);
        widthCard= (int)((widthGameBoard-6)/4.0);
        heightCard= (int)((heightGameBoard-6)/4.0);
        for (int i = 0; i < NUM_COL; i++) {
            for (int j = 0; j < NUM_ROW; j++) {
                rectCloseCard[i][j]= new Rect(i*widthCard+BORDER_WIDTH+leftGameBoard,j*heightCard+BORDER_WIDTH+topGameBoard,i*widthCard+leftGameBoard+widthCard,j*heightCard+topGameBoard+heightCard);
                openedCard[i][j]=false;
            }
        }
        randomCard();
        paint= new Paint();
    }

    @Override
    public void update() {
        if(countTime==0){
            lose=true;
        }
        if (gamestart==true&&!countTimeThread.isAlive()){
            gamestart=false;
            if(!countTimeThread.isAlive())
                countTimeThread.start();
        }
        if (checkWin()){
            countTimeThread.stopThread();
        }
        if(count==3){
            openedCard[pairOpenedCard[0].getX()][pairOpenedCard[0].getY()]=false;
        }
        if (count==5) flagOnTouch=true;

        if (buttonRePlay.isChecked()) this.init();
        if(!flagCloseCard){
            flagOnTouch=false;
            WaitingCardCloseThread waitingCardCloseThread= new WaitingCardCloseThread();
            waitingCardCloseThread.start();
        }
    }
    @Override
    public void render(Canvas canvas) {
        paint.setARGB(255,255,0,0);
        canvas.drawBitmap(bitmapBackground,rectSrc,rectDst,paint);
        paint.setTextSize(50);
        paint.setARGB(255,255,0,0);
        canvas.drawText("Time: "+String.valueOf(countTime),520,100,paint);
        if(!lose) drawGameBoard(canvas);
        if (lose){
            paint.setTextSize(100);
            paint.setARGB(255,255,0,0);
            canvas.drawText("You Lose!",180,440,paint);
            buttonRePlay.render(canvas);
        }
        if (checkWin()&&flagOnTouch){
            paint.setTextSize(100);
            paint.setARGB(255,255,0,0);
            canvas.drawText("You Win!",200,440,paint);
            buttonRePlay.render(canvas);
        }
    }
    @Override
    public void onTouch(MotionEvent event)
    {
        if(flagOnTouch){
            int x= (int) event.getX();
            int y= (int) event.getY();
            int col, row;
            if (checkWin() || lose){
                buttonRePlay.ontouch(event);
            }else if(x>=leftGameBoard && x<=rightGameBoard && y>=topGameBoard && y<= bottomGameBoard && event.getAction()==MotionEvent.ACTION_DOWN ){
                col= (x-leftGameBoard)/widthCard;
                row= (y-topGameBoard)/heightCard;
                if(!openedCard[col][row]&&flagOnTouch){
                    asset.playOpenCardSound();
                    if (flagOnTouch)count=(count+1)%2;
                    if (count==1&&flagOnTouch){
                        flagOnTouch=false;
                        if(waitingTimeFirstCardTheard.isAlive()) waitingTimeFirstCardTheard.stopThread();
                        pairOpenedCard[count]= new Point(col,row);
                        if (positionOpenCard[col][row].getX()==  positionOpenedCardBefore.getX() && positionOpenCard[col][row].getY()== positionOpenedCardBefore.getY()){
                            openedCard[col][row]=true;
                            countWin++;
                        }else{
                            openedCard[colOpenedCardBefore][rowOpenedCardBefore]=false;
                        }
                        WaitingTimeSecondCardThread waitingTimeSecondCardThread= new WaitingTimeSecondCardThread();
                        waitingTimeSecondCardThread.start();

                    }
                    if(count==0&&flagOnTouch){
                        positionOpenedCardBefore= positionOpenCard[col][row];
                        pairOpenedCard[count]= new Point(col,row);
                        colOpenedCardBefore=col;
                        rowOpenedCardBefore=row;
                        openedCard[col][row]=true;
                        waitingTimeFirstCardTheard= new WaitingTimeFirstCardTheard();
                        waitingTimeFirstCardTheard.start();
                    }
                }

            }
        }
    }

    @Override
    public void destroy() {
        if(countTimeThread.isAlive()) countTimeThread.stopThread();
    }

    public void drawGameBoard(Canvas canvas){
        paint.setARGB(100,255,0,0);
        paint.setARGB(255,0,255,0);
        Rect rectSrcCard;
        Rect rectDstCard;
        Point positionSrcCard;
        for (int i = 0; i <NUM_COL; i++) {
            for (int j = 0; j < NUM_ROW; j++) {
                if(openedCard[i][j]==false&&flagCloseCard){
                    positionSrcCard= positionOpenCard[i][j];
                    rectSrcCard= new Rect(positionSrcCard.getX()*widthSrcCard, positionSrcCard.getY()*heightSrcCard
                            , positionSrcCard.getX()*widthSrcCard+widthSrcCard, positionSrcCard.getY()*heightSrcCard+heightSrcCard);
                    rectDstCard= new Rect(i*widthCard+leftGameBoard+BORDER_WIDTH, j*heightCard+topGameBoard+BORDER_WIDTH
                            , i*widthCard+widthCard+leftGameBoard, j*heightCard+heightCard+topGameBoard);
                    canvas.drawBitmap(spritePokemon,rectSrcCard,rectDstCard,paint);
                    canvas.drawRect(rectCloseCard[i][j],paint);
                }

               if (!flagCloseCard){
                   positionSrcCard= positionOpenCard[i][j];
                   rectSrcCard= new Rect(positionSrcCard.getX()*widthSrcCard, positionSrcCard.getY()*heightSrcCard
                           , positionSrcCard.getX()*widthSrcCard+widthSrcCard, positionSrcCard.getY()*heightSrcCard+heightSrcCard);
                   rectDstCard= new Rect(i*widthCard+leftGameBoard+BORDER_WIDTH, j*heightCard+topGameBoard+BORDER_WIDTH
                           , i*widthCard+widthCard+leftGameBoard, j*heightCard+heightCard+topGameBoard);
                   canvas.drawBitmap(spritePokemon,rectSrcCard,rectDstCard,paint);
               }
            }
        }

        if (count==0){
            positionSrcCard= positionOpenCard[pairOpenedCard[count].getX()][pairOpenedCard[count].getY()];
            rectSrcCard= new Rect(positionSrcCard.getX()*widthSrcCard, positionSrcCard.getY()*heightSrcCard
                    , positionSrcCard.getX()*widthSrcCard+widthSrcCard, positionSrcCard.getY()*heightSrcCard+heightSrcCard);
            rectDstCard= new Rect(pairOpenedCard[count].getX()*widthCard+leftGameBoard+BORDER_WIDTH, pairOpenedCard[count].getY()*heightCard+topGameBoard+BORDER_WIDTH
                    , pairOpenedCard[count].getX()*widthCard+widthCard+leftGameBoard, pairOpenedCard[count].getY()*heightCard+heightCard+topGameBoard);
            canvas.drawBitmap(spritePokemon,rectSrcCard,rectDstCard,paint);
        }
        if(count==1){
            for (int i = 0; i <=count; i++) {
                positionSrcCard= positionOpenCard[pairOpenedCard[i].getX()][pairOpenedCard[i].getY()];
                rectSrcCard= new Rect(positionSrcCard.getX()*widthSrcCard, positionSrcCard.getY()*heightSrcCard
                        , positionSrcCard.getX()*widthSrcCard+widthSrcCard, positionSrcCard.getY()*heightSrcCard+heightSrcCard);
                rectDstCard= new Rect(pairOpenedCard[i].getX()*widthCard+leftGameBoard+BORDER_WIDTH, pairOpenedCard[i].getY()*heightCard+topGameBoard+BORDER_WIDTH
                        , pairOpenedCard[i].getX()*widthCard+widthCard+leftGameBoard, pairOpenedCard[i].getY()*heightCard+heightCard+topGameBoard);
                canvas.drawBitmap(spritePokemon,rectSrcCard,rectDstCard,paint);
            }
        }
    }

    public boolean checkWin(){
        if (countWin==8) return true;
        else return false;
    }

    public void randomCard(){
        int[] rowArray= new int[4];
        for (int i = 0; i < 4; i++) {
            rowArray[i]=0;
        }
        Random random= new Random();
        for(int i=0;i<4; i++){
            for(int j=0; j<2; j++){
                for (int k = 0; k <2 ; k++) {
                    while(true){
                        int col= random.nextInt(4);
                        if (rowArray[col]<4){
                            rowArray[col]++;
                            while (true){
                                int row= random.nextInt(4);
                                if(positionOpenCard[row][col]==null){
                                    positionOpenCard[row][col]= new Point(i,j);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
