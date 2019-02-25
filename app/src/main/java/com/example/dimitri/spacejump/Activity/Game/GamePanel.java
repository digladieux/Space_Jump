package com.example.dimitri.spacejump.Activity.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Layout du jeu. Remplace les activity_xxx.xml des autres Activity
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    /**
     * Gaming Loop de notre jeu
     */
    private MainThread thread ;
    /**
     * Le layout du jeu
     */
    private final GameScene manager;

    /**
     * Constructeur de la classe GamePanel
     * @param context Activity actuellement en cours
     */
    public GamePanel(Context context) /*Context : L'etat a l'intant t du telephone. On peut allumer bluetooth, la camera */
    {
        super(context) ;


        getHolder().addCallback(this); /* On recupere sur la surface les evenements quand ils se declencheront */
        thread = new MainThread(getHolder(), this) ;
        manager = new GameScene(context) ;
        setFocusable(true);
    }

    /**
     * Cette methode est appelle lorsqu'un changement structurel en format ou taille est effectue. Ici cette methode ne nous sert pas.
     * @param holder Le SurfaceHolder ou la surface a change
     * @param format Le nouveau format de pixel de la surface
     * @param width Nouvelle Largeur de la surface
     * @param height Nouvelle hauteur de la surface
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) /* methode virtuel */
    {

    }

    /**
     * Cette methode est appelle avant la creation de la surface. On va lancer le thread qui permet de lancer le jeu
     * @param holder Le SurfaceHolder ou la surface a ete cree
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread = new MainThread(getHolder(), this) ;
        thread.setRunning(true) ;
        thread.start() ;
    }

    /**
     * Cette methode est appelle avant que la surface soit detruite. Il faut donc arreter le thread principale contenant notre jeu pour qu'il ne continue pas de tourner en arriere plan des autres activity
     * @param holder Le SurfaceHolder ou la surface a ete detruite
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true ; /* Essaie de fermer (peut ne pas marcher du premier coup, d'ou l'utilisation d'un while*/
        while(retry)
        {
            try {
                thread.setRunning(false) ; /* Arrete le jeu */
                thread.join() ; /* Arrete le thread et le detruit */

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
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

