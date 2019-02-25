package com.example.dimitri.spacejump.Entities;

import android.content.Context;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsBatObstacle;
import com.example.dimitri.spacejump.Constants.ConstantsGroundObstacle;
import com.example.dimitri.spacejump.Entities.Obstacles.FlagArrival;
import com.example.dimitri.spacejump.Entities.Obstacles.Obstacle;
import com.example.dimitri.spacejump.Exception.InvalidNumberMap;

import java.util.ArrayList;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.Entities.Obstacles.BatObstacle.initialisationBatObstacle;
import static com.example.dimitri.spacejump.Entities.Obstacles.GroundObstacle.initialisationGroundObstacle;
import static com.example.dimitri.spacejump.Entities.Obstacles.SnakeObstacle.initialisationSnakeObstacle;

public final class Map {

    /**
     * Creation de la carte
     * @param context Activity actuellement en cours
     * @return Une liste de tous les obstacles presents sur le terrain
     */
    public static ArrayList <Obstacle> initialisationMap(Context context) throws InvalidNumberMap {
        switch (currentMap) {
            case 0:
                return initialisationMap0(context);
            case 1:
                return initialisationMap1(context);
            case 2:
                return initialisationMap2(context);
            case 3:
                return initialisationMap3(context);
            default:
                throw new InvalidNumberMap();
        }
    }


    /**
     * Creation de la carte Earth
     * @param context Activity actuellement en cours
     * @return Une liste de tous les obstacles presents sur le terrain
     */
    static private ArrayList <Obstacle> initialisationMap0(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,0.5));
        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,4 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH));

        level.add(initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,0.875));
        level.add(initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,7 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));


        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,1.25));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - ConstantsBatObstacle.OBSTACLE_HEIGHT - 5));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + 2 * ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));

        level.add(initialisationBatObstacle(context,10 * Constants.SCREEN_WIDTH / 8 + ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_TOP - 2 * ConstantsBatObstacle.OBSTACLE_HEIGHT));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < 3 * Constants.SCREEN_WIDTH/2 ) {
            level.add(initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 1.5)) ;
        return level;
    }

    /**
     * Creation de la carte Moon
     * @param context Activity actuellement en cours
     * @return Une liste de tous les obstacles presents sur le terrain
     */
    static private ArrayList <Obstacle> initialisationMap1(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        level.add(initialisationSnakeObstacle(context,0.416));
        level.add(initialisationSnakeObstacle(context,0.666));
        level.add(initialisationSnakeObstacle(context,0.916));

        level.add(initialisationBatObstacle(context,(3+3*12) * Constants.SCREEN_WIDTH / 12, ConstantsBatObstacle.OBSTACLE_TOP + ConstantsBatObstacle.OBSTACLE_HEIGHT));
        level.add(initialisationBatObstacle(context,3.416));
        level.add(initialisationSnakeObstacle(context,3.666));
        level.add(initialisationSnakeObstacle(context,3.750));
        level.add(initialisationBatObstacle(context,4));

        level.add(initialisationSnakeObstacle(context,5.25));
        level.add(initialisationSnakeObstacle(context,5.33));
        level.add(initialisationSnakeObstacle(context,5.41));
        level.add(initialisationSnakeObstacle(context,5.75));
        level.add(initialisationSnakeObstacle(context,6));

        level.add(initialisationSnakeObstacle(context,6.41));
        level.add(initialisationSnakeObstacle(context,6.5));
        level.add(initialisationSnakeObstacle(context,6.58));

        level.add(initialisationBatObstacle(context,7.58));
        level.add(initialisationBatObstacle(context,7.66));
        level.add(initialisationBatObstacle(context,7.75));


        level.add(initialisationSnakeObstacle(context,8.41));
        level.add(initialisationSnakeObstacle(context,8.5));
        level.add(initialisationSnakeObstacle(context,8.58));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 11) {
            level.add(initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 10)) ;
        return level;
    }

    /**
     * Creation de la carte Mars
     * @param context Activity actuellement en cours
     * @return Une liste de tous les obstacles presents sur le terrain
     */
    static private ArrayList <Obstacle> initialisationMap2(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();

        level.add(initialisationSnakeObstacle(context, 0.5));
        level.add(initialisationSnakeObstacle(context, 1));
        level.add(initialisationSnakeObstacle(context, 1.5));

        level.add(initialisationBatObstacle(context,4));

        level.add(initialisationSnakeObstacle(context, 2.5));
        level.add(initialisationSnakeObstacle(context, 3));

        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH * 4) {
            level.add(initialisationGroundObstacle(context, i));
            i++;
        }
        level.add(new FlagArrival(context, 4 * Constants.SCREEN_WIDTH)) ;
        return level;
    }

    /**
     * Creation de la carte Sun
     * @param context Activity actuellement en cours
     * @return Une liste de tous les obstacles presents sur le terrain
     */
    static private ArrayList <Obstacle> initialisationMap3(Context context) {
        ArrayList <Obstacle> level = new ArrayList <>();
        int i = 0;
        while (i * ConstantsGroundObstacle.OBSTACLE_WIDTH < Constants.SCREEN_WIDTH) {
            level.add(initialisationGroundObstacle(context,i));
            i++;
        }
        level.add(new FlagArrival(context, 1)) ;

        return level;
    }
}
