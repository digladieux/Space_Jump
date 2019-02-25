package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsSnakeObstacle;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

/**
 * Classe representant un obstacle terrien de type serpent
 */
public class SnakeObstacle extends Obstacle {

    /**
     * Constructeur de la classe
     * @param movementRight 1ere image de l'animation de deplacement de l'obstacle
     * @param movementLeft 2ème image de l'animation de deplacement de l'obstacle
     * @param areaCenter Centre de la hitbox en coordonne y
     */
    private SnakeObstacle(Bitmap movementRight, Bitmap movementLeft, int areaCenter) {
        super(movementRight, movementLeft, areaCenter - ConstantsSnakeObstacle.OBSTACLE_WIDTH/2, ConstantsSnakeObstacle.OBSTACLE_TOP, areaCenter + ConstantsSnakeObstacle.OBSTACLE_WIDTH/2, ConstantsSnakeObstacle.OBSTACLE_BOTTOM);
    }

    /**
     * Fonction qui intialise un obstacle terrien
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @return Un obstacle de piece
     */
    public static Obstacle initialisationSnakeObstacle(Context context, double areaCenter) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.snake_slime, ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_HEIGHT);
        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la futur image en mirroir verticale */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new SnakeObstacle(scaledMovementLeft, scaledMovementRight, (int)(areaCenter * Constants.SCREEN_WIDTH));
    }
}


