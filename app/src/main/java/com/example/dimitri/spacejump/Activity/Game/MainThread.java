package com.example.dimitri.spacejump.Activity.Game;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.dimitri.spacejump.Activity.Game.GamePanel;

class MainThread extends Thread {
    private final SurfaceHolder surfaceHolder; /* Zone de la fenetre */
    private final GamePanel gamePanel; /* Zone du jeu */
    private boolean running; /* Le thread tourne t'il */
    //   public static Canvas canvas ; /* ce qu'on va dessiner */

    void setRunning(boolean running) {
        this.running = running;
    }

    MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas(); /* Le canvas pourra etre modifier */
                synchronized (surfaceHolder) {
                    long time = System.currentTimeMillis();
                    while (true)
                    {
                        if (!(System.currentTimeMillis() - time < 16.6)) break;
                    }
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
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

