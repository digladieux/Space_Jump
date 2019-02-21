package com.example.dimitri.spacejump.Activity.Game;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.dimitri.spacejump.Activity.MapActivity;
import com.example.dimitri.spacejump.Activity.WinActivity;
import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.PlayerConstants;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.Entities.Obstacles.ObstacleManager;
import com.example.dimitri.spacejump.R;

import java.io.IOException;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.StaticMethod.createPicture;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmap;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmapBackground;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmapReturn;
import static com.example.dimitri.spacejump.StaticMethod.isButtonClick;

class GameScene{

    private final int mapNumber ;
    private final Bitmap scaledReturnMenu;
    private final Bitmap scaledBackgroundAttempt ;
    private final Bitmap scaledBackground;
    private final Bitmap scaledGameOver;
    private final AlienSprite player;
    private Point playerPoint ;
    private ObstacleManager obstacleManager ;

    private boolean movingPlayer = false ;
    private boolean gameOver = false ;
    private boolean win = false;
    private boolean actionDown = false;
    private boolean flagGameOverTime ;
    private boolean gameNotStarted = true;
    private boolean changingMap = false;

    private int attempt = 0;
    private final Context context ;
    private long gameOverTime ;

    private final MediaPlayer gamingMusic ;
    private final MediaPlayer gameOverMusic;


    /**
     *
     * @param context
     */
    GameScene(Context context)
    {
        this.mapNumber = currentMap;
        player = new AlienSprite(context, new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER));
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        obstacleManager = new ObstacleManager(context, mapNumber);

        this.context = context ;
        this.scaledBackground = createPicture(context, R.drawable.background_game, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBackgroundAttempt = createPicture(context, R.drawable.attempt, Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT/6);
        this.scaledGameOver = createPicture(context, R.drawable.gameover, Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT/6);

        gameOverMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gameoversong);
        gamingMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gamingsong);
    }

    /**
     *
     */
    private void reset() {
        gamingMusic.start();
        gameOver = false ;
        win = false ;
        actionDown = false ;
        changingMap = false ;
        movingPlayer = false ;

        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.resetCurrentSpeed();
        obstacleManager = new ObstacleManager(context,mapNumber);

    }

    /*TODO : possible alleger cette fonction ? */

    /**
     *
     */
    void update() {
        if (gameNotStarted)
        {
            gameNotStarted = false ;
            reset();
        }
        else if (win)
        {
            this.terminate();
        } else if ((gameOver))
        {
            if (flagGameOverTime)
            {
                if (System.currentTimeMillis() - gameOverTime > 1000)
                {
                    reset();
                    flagGameOverTime = false;
                }
            } else {
                flagGameOverTime = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
        else {
            if (!movingPlayer && actionDown) {
                movingPlayer = true;
            }
            if (movingPlayer) {
                player.incrementCurrentSpeed();
                playerPoint.y += player.getCurrentSpeed();
                if (playerPoint.y > PlayerConstants.INIT_POSITION_Y) {
                    movingPlayer = false;
                    player.resetCurrentSpeed();
                    playerPoint.y = PlayerConstants.INIT_POSITION_Y;
                }
            }

            player.update(playerPoint);
            obstacleManager.update();
            int codeCollision =  obstacleManager.playerCollide(player) ;

            if (codeCollision == 1) {
                this.attempt++;
                gameOver = true;
                gamingMusic.stop();

                try {
                    gamingMusic.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                gamingMusic.seekTo(0);
                gameOverMusic.start();
            }

            else if (codeCollision == -1) {
                this.attempt = 0;
                win = true;
            }
        }
    }


    /**
     *
     * @param canvas
     */
    void draw(Canvas canvas) {

        drawBitmapBackground(canvas, scaledBackground);
        drawBitmapReturn(canvas, scaledReturnMenu);
        drawBitmap(canvas, scaledBackgroundAttempt,(float)1/5, (float)1/7);

        player.draw(canvas);
        obstacleManager.draw(canvas);
        Paint paintAttempt = new Paint();
        paintAttempt.setTextSize(Constants.SCREEN_WIDTH/14);
        paintAttempt.setColor(Color.WHITE);

        canvas.drawText(""+this.attempt, Constants.SCREEN_WIDTH/5 + scaledBackgroundAttempt.getWidth()/2 - (int) paintAttempt.measureText(" "+ attempt), Constants.SCREEN_HEIGHT/5, paintAttempt);
        if (gameOver) {
            drawBitmap(canvas, scaledGameOver,(float)1/2,(float)1/2 );
        }
    }


    /*TODO : fonction musique peut pas tester sans tel si c'est un pointeur */

    /**
     *
     */
    private void terminate() {


        gamingMusic.stop();
        gameOverMusic.stop();

        try {
            gamingMusic.prepare();
            gameOverMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gamingMusic.seekTo(0);
        gameOverMusic.seekTo(0);
        gameNotStarted = true ;

        if (changingMap)
        {
            Intent intent = new Intent(context,MapActivity.class);
            context.startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(context,WinActivity.class);
            context.startActivity(intent);
        }
    }

    /**
     *
     * @param event
     */
    void recieveTouch(MotionEvent event) {
        if (isButtonClick(event))
        {
            this.actionDown = false ;
            changingMap = true ;
            this.terminate();
        }
        else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.actionDown = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.actionDown = false;
        }
    }
}
