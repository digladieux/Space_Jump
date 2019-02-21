package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Canvas;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.Entities.Map;

import java.util.ArrayList;
import java.util.Iterator;

public class ObstacleManager {
    private final ArrayList <Obstacle> obstacles;
    private long startTime ; /* debut frame */ /*todo : utile ? */
    private final long initTime; /* debut jeu */

    public ObstacleManager(Context context, int map)
    {
        startTime = initTime = System.currentTimeMillis() ;
        obstacles = Map.initialisationMap(context, map);
    }

    public int size() {
        return obstacles.size();
    }


    public int playerCollide(AlienSprite player)
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
        Iterator <Obstacle> it = obstacles.iterator();
        while (it.hasNext())
        {
            Obstacle ob = it.next();
            if (ob instanceof BatObstacle) {
                ob.incrementX(Constants.SCREEN_WIDTH/60);
            } else {
                ob.incrementX(Constants.SCREEN_WIDTH/80);
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
