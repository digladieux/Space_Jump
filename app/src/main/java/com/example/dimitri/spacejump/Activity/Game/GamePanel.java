package com.example.dimitri.spacejump.Activity.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 *
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     *
     */
    private MainThread thread ;
    /**
     *
     */
    private final GameScene manager;

    /**
     *
     * @param context Activite en cours (Game ici)
     */
    public GamePanel(Context context) /*Context : L'etat a l'intant t du telephone. On peut allumer bluetooth, la camera */
    {
        super(context) ;


        /* getHolder : recupere la surface */
        /* addCallBack : On regarde si il y a eu des nouveaux events */
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this) ;
        manager = new GameScene(context) ;
        /* Focus du "curseur" sur la fenetre de GamePanel */
        setFocusable(true);
    }

    /**
     *
     * @param holder Surface ou se deroule le jeu
     * @param format Format
     * @param width Largeur de l'ecran
     * @param height Hauteur de l'ecran
     */
    @Override /* Permet de dire que cette fonction va heriter */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) /* methode virtuel */
    {

    }

    /**
     *
     * @param holder Surface ou se deroule le jeu
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this) ;
        thread.setRunning(true) ;
        thread.start() ;
    }

    /**
     *
     * @param holder Surface ou se deroule le jeu
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true ; /* Essaie de fermer le jeu plusieurs fois */
        while(retry) /* On tente de le fermer plusieurs fois car cela peut planter defois */
        {
            try {
                thread.setRunning(false) ; /* Arrete le jeu */
                thread.join() ; /* Arrete le thread et le detruit */

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }  /* Retourne sur la sortie d'erreur standard, la trace (log) des exception */
            retry = false ;
        }

    }

    /**
     *
     * @param event Event declenche par l'utilisateur
     * @return vrai car l'event s'est declenche
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.recieveTouch(event);
        return true;
    }

    /**
     *
     */
    public void update()
    {
        manager.update();
    }

    @Override

    public void draw(Canvas canvas)
    {
        super.draw(canvas) ;
        manager.draw(canvas);
    }


}

