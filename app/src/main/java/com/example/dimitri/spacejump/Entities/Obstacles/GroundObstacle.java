package com.example.dimitri.spacejump.Entities.Obstacles;
import android.content.Context;
import android.graphics.Bitmap;

import com.example.dimitri.spacejump.Constants.ConstantsGroundObstacle;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

/**
 * Classe representant un obstacle terrien de type sol
 */
public class GroundObstacle extends Obstacle {
    /**
     * Constructeur de la classe
     * @param idle Image de repos du personnage
     * @param areaCenter Centre de la hitbox en coordonne y
     */
    private GroundObstacle(Bitmap idle, int areaCenter)
    {
        super(idle, areaCenter * ConstantsGroundObstacle.OBSTACLE_WIDTH - ConstantsGroundObstacle.OBSTACLE_WIDTH/2,areaCenter * ConstantsGroundObstacle.OBSTACLE_WIDTH + ConstantsGroundObstacle.OBSTACLE_WIDTH/2, ConstantsGroundObstacle.OBSTACLE_TOP, ConstantsGroundObstacle.OBSTACLE_BOTTOM);
    }

    /**
     * Fonction qui intialise un obstacle suivant une coordonnee precise en y
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @return Un obstacle de type sol
     */
    public static Obstacle initialisationGroundObstacle(Context context, int areaCenter) {
        Bitmap scaledIdle = createPicture(context, R.drawable.grass_block, ConstantsGroundObstacle.OBSTACLE_WIDTH, ConstantsGroundObstacle.OBSTACLE_HEIGHT);
        return new GroundObstacle(scaledIdle, areaCenter);
    }
}
