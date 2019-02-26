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
import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;
import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmap;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmapBackground;
import static com.example.dimitri.spacejump.StaticMethod.drawBitmapReturn;
import static com.example.dimitri.spacejump.StaticMethod.isButtonClick;
import static com.example.dimitri.spacejump.StaticMethod.readFile;
import static com.example.dimitri.spacejump.StaticMethod.resetMusic;
import static com.example.dimitri.spacejump.StaticMethod.writeFile;

class GameScene{
    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("DressActivity") ;

    /**
     * Boutton pour le retour a l'activite precedente (MapActivity)
     */
    private final Bitmap scaledReturnMenu;

    /**
     * Image pour l'affichage du nombre de tentative de ne niveau
     */
    private final Bitmap scaledBackgroundAttempt ;

    /**
     * Fond d'ecran du jeu
     */
    private final Bitmap scaledBackground;

    /**
     * Image qui s'affiche lorsque l'on perd le niveau
     */
    private final Bitmap scaledGameOver;

    /**
     * Le joueur
     */
    private AlienSprite player;

    /**
     * Le centre de la hitbox du personnage
     */
    private Point playerPoint ;

    /**
     * La liste des obstacles de la partie
     */
    private ObstacleManager obstacleManager ;

    /**
     * Booleen si le personnage est actuellement en train de sauter
     */
    private boolean jumping = false ;

    /**
     * Booleen pour savoir si l'utilisateur a perdu
     */
    private boolean gameOver = false ;
    /**
     * Booleen pour savoir si l'utilisateur a gagne
     */
    private boolean win = false;

    /**
     * Booleen pour savoir si l'utilisateur appuie sur l'ecran
     */
    private boolean actionDown = false;

    /**
     * Booleen pour savoir si il faut activer le chronometre pour l'affichage de la defaite pendant x secs
     */
    private boolean flagGameOverTime = false ;
    private boolean changingMap = false;

    private int attempt = 0;
    private final Context context ;
    private long gameOverTime ;

    private final MediaPlayer gamingMusic ;
    private final MediaPlayer gameOverMusic;


    /**
     * Constructeur de la classe GameScene
     * @param context Activity actuellement en cours
     */
    GameScene(Context context)
    {
        Rect rect = new Rect(PlayerConstants.LEFT_PLAYER, PlayerConstants.TOP_PLAYER, PlayerConstants.RIGHT_PLAYER, PlayerConstants.BOTTOM_PLAYER) ;
        try {
            player = new AlienSprite(context, rect);
        } catch (InvalidCurrentDress invalidCurrentDress) {
            logger.log(Level.SEVERE, "InvalidCurrentDress");
        }
        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        obstacleManager = new ObstacleManager(context);

        this.context = context ;
        this.scaledBackground = createPicture(context, R.drawable.background_game, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.scaledReturnMenu  = createPicture(context, R.drawable.return_arrow, Constants.SCREEN_WIDTH/14,Constants.SCREEN_WIDTH/14);
        this.scaledBackgroundAttempt = createPicture(context, R.drawable.attempt, Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT/6);
        this.scaledGameOver = createPicture(context, R.drawable.gameover, Constants.SCREEN_WIDTH/3, Constants.SCREEN_HEIGHT/6);

        gameOverMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gameoversong);
        gamingMusic = MediaPlayer.create(context.getApplicationContext(), R.raw.gamingsong);
        gamingMusic.start();
    }

    /**
     * Remise a 0 du niveau apres la defaite de l'utilisateur
     */
    private void reset() {
        resetMusic(gameOverMusic);
        resetMusic(gamingMusic);
        gamingMusic.start();
        gameOver = false ;
        win = false ;
        actionDown = false ;
        changingMap = false ;
        jumping = false ;

        playerPoint = new Point(PlayerConstants.INIT_POSITION_X, PlayerConstants.INIT_POSITION_Y);
        player.update(playerPoint) ;
        player.resetCurrentSpeed();
        obstacleManager = new ObstacleManager(context);

    }

    /**
     * Mise a jour de notre jeu
     */
    void update() {
        if (win)
        {
            this.terminate();
        } else if ((gameOver))
        {
            logger.log(Level.INFO, "GameOver");
            Loosing();
        }
        else {
            if (!jumping && actionDown) {
                jumping = true;
            }
            ifJumping();

            player.update(playerPoint);
            obstacleManager.update();
            managerCollision();

        }
    }

    /**
     * Gestion des actions suivant la collision du joueur
     */
    private void managerCollision()
    {
        int codeCollision =  obstacleManager.playerCollide(player) ;

        switch (codeCollision) {
            case 1: /* Collision avec un obstacle */
                this.attempt++;
                gameOver = true;
                break;

            case 0: /* Aucune collision */
                break ;
            case -1: /* Victoire */
                win = true;
                break;

            case -2: /* Piece */
                int coin = readFile(context, "coin") ;
                writeFile(context, "coin", coin + 1);
                break;
        }
    }

    /**
     * Si l'utilisateur a perdu alors on affiche un message
     */
    private void Loosing()
    {
        if (flagGameOverTime)
        {
            if (System.currentTimeMillis() - gameOverTime > 1000)
            {
                resetMusic(gameOverMusic);
                reset();
                flagGameOverTime = false;
            }
        } else {
            flagGameOverTime = true;
            gameOverTime = System.currentTimeMillis();
            resetMusic(gamingMusic);
            gameOverMusic.start();
        }
    }

    /**
     * Si l'utilisateur est en train de sauter, alors on deplace le personnage
     */
    private void ifJumping()
    {
        if (jumping) {
            player.incrementCurrentSpeed();
            playerPoint.y += player.getCurrentSpeed();
            if (playerPoint.y > PlayerConstants.INIT_POSITION_Y) {
                jumping = false;
                player.resetCurrentSpeed();
                playerPoint.y = PlayerConstants.INIT_POSITION_Y;
            }
        }
    }


    /**
     * Methode qui dessigne le jeu sur la surface
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
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


    /**
     * Si changement d'activity alors on arrete la musique et on change
     */
    private void terminate() {

        resetMusic(gameOverMusic) ;
        resetMusic(gamingMusic) ;

        if (changingMap)
        {
            logger.log(Level.INFO, "ChangeActivity");
            Intent intent = new Intent(context,MapActivity.class);
            context.startActivity(intent);
        }
        else
        {
            logger.log(Level.INFO, "Victoire");
            logger.log(Level.INFO, "ChangeActivity");
            Intent intent = new Intent(context,WinActivity.class);
            context.startActivity(intent);
        }
    }

    /**
     * Methode declenche lors d'un event utilisateur
     * @param event L'evenement declenche
     */
    void recieveTouch(MotionEvent event) {
        if (isButtonClick(event))
        {
            this.actionDown = false ;
            changingMap = true ;
            this.terminate();
        }
        else if (event.getAction() == MotionEvent.ACTION_DOWN) { /* Si l'utilisateur veut sauter */
            logger.log(Level.INFO, "Jump");
            this.actionDown = true;
        }
        else if (event.getAction() == MotionEvent.ACTION_UP) { /* Si l'utilisateur ne veut plus sauter */
            logger.log(Level.INFO, "StopJump");
            this.actionDown = false;
        }
    }
}
