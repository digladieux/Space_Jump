package com.example.dimitri.spacejump.Activity.Game;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

class MainThread extends Thread {

    /**
     * Zone total de la fenetre
     */
    private final SurfaceHolder surfaceHolder;

    /**
     * Zone du jeu
     */
    private final GamePanel gamePanel;

    /**
     * Boolean pour savoir si le jeu tourne ou pas
     */
    private boolean running;

    private int MAX_FPS = 60 ;
    private long MS_PER_UPDATE = 1000 / MAX_FPS ;


    MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    /**
     * Setteur sur la valeur de running
     * @param running Valeur boolean de running
     */
    void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Reecriture de la fonction run d'un thread. C'est ici que l'on gere la gaming loop de notre jeu
     */
    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas(); /* Le canvas pourra etre modifier */
                synchronized (surfaceHolder) {   /* On effectue les instructions suivantes meme si le canvas n'est pas disponible */
                    long time = System.currentTimeMillis();
                    boolean wait = true ;
                    while (wait)
                    {
                        if (!(System.currentTimeMillis() - time < MS_PER_UPDATE))
                        {
                            wait = false;
                        }
                    }
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally { /* Quand la boucle de jeu et le canvas sont disponible */
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}

