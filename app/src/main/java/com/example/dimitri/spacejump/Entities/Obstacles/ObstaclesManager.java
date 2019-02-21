package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Canvas;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.Entities.Map;

import java.util.ArrayList;
import java.util.Iterator;

class ObstaclesManager {
    private final ArrayList <Obstacle> obstacles;
    private long startTime ; /* debut frame */ /*todo : utile ? */
    private final long initTime; /* debut jeu */

    ObstaclesManager(Context context, int map)
    {
        startTime = initTime = System.currentTimeMillis() ;
        obstacles = Map.initialisationMap(context, map);
    }

    public int size() {
        return obstacles.size();
    }


    int playerCollide(AlienSprite player)
    {
        int collision = 0;
        int codeCollision ;
        for (Obstacle ob : obstacles) {
            codeCollision = ob.playerCollide(player) ;
            if (codeCollision == -1) {
                collision = -1;
            }
            if (codeCollision == 1)
            {
                collision = 1 ;
            }
        }
        return collision;
    }

    public void update()
    {
        if (startTime < Constants.INIT_TIME)
        {
            startTime = Constants.INIT_TIME ;
        }
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis() ;
        /* Combien de temps pour parcourir un ecran (10sec) et ca diminue apres */
        float speedBat = (float) (Math.sqrt(1 + (startTime - initTime) / 1500.0)) * Constants.SCREEN_HEIGHT / 1500.0f;
        float speedMonster = (float) (Math.sqrt(1 + (startTime - initTime) / 3000.0)) * Constants.SCREEN_HEIGHT / 3000.0f;

        Iterator <Obstacle> it = obstacles.iterator();
        while (it.hasNext())
        {
            Obstacle ob = it.next();
            if (ob instanceof BatObstacle) {
                ob.incrementX(Constants.SCREEN_WIDTH/120);
            } else {
                ob.incrementX(Constants.SCREEN_WIDTH/180);
            }
            ob.update();
            if (ob.getRectangle().right <= 0) {
                it.remove();
            }
        }
    }

    public void draw(Canvas canvas)
    {
        for (Obstacle ob : obstacles)
        {
            ob.draw(canvas);
        }
    }
}
