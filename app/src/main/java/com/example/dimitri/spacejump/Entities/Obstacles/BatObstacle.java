package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsBatObstacle;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

/**
 * Classe representant un obstace aerien de type chauve souris
 */
public class BatObstacle extends Obstacle {

    /**
     * Constructeur de la classe
     * @param movementRight 1ere image de l'animation de deplacement de l'obstacle
     * @param movementLeft 2ème image de l'animation de deplacement de l'obstacle
     * @param areaCenter Centre de la hitbox en coordonne y
     * @param areaTop Haut de la hitbox de l'obstacle en coordonne y
     */
    private BatObstacle(Bitmap movementRight, Bitmap movementLeft, int areaCenter, int areaTop) {
        super(movementRight, movementLeft, areaCenter - ConstantsBatObstacle.OBSTACLE_WIDTH/2, areaTop, areaCenter + ConstantsBatObstacle.OBSTACLE_WIDTH/2, areaTop + ConstantsBatObstacle.OBSTACLE_HEIGHT);
    }

    /**
     * Fonction qui intialise un obstacle suivant une coordonnee precise en y
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @param areaTop Haut de la hitbox de l'obstacle en coordonne y
     * @return Un obstacle de type chauve souris
     */
    public static Obstacle initialisationBatObstacle(Context context, int areaCenter, int areaTop) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, areaCenter, areaTop);
    }

    /**
     * Fonction qui intialise un obstacle
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @return Un obstacle de type chauve souris
     */
    public static Obstacle initialisationBatObstacle(Context context, double areaCenter) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsBatObstacle.OBSTACLE_WIDTH, ConstantsBatObstacle.OBSTACLE_HEIGHT);
        return new BatObstacle(scaledMovementLeft, scaledMovementRight, (int)(areaCenter * Constants.SCREEN_WIDTH), ConstantsBatObstacle.OBSTACLE_TOP);
    }
}