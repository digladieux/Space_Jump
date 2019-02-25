package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsCoin;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

/**
 * Classe representant un bonus
 */
public class Coin extends Obstacle {
    private Coin(Bitmap movement_right, Bitmap movement_left, int areaCenter, int area_top) {
        super(movement_right, movement_left, areaCenter - ConstantsCoin.OBSTACLE_WIDTH/2, area_top, areaCenter + ConstantsCoin.OBSTACLE_WIDTH/2, area_top + ConstantsCoin.OBSTACLE_HEIGHT);
    }

    /**
     * Fonction qui intialise une piece
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @return Un obstacle de piece
     */
    public static Obstacle initialisationCoin(Context context, double areaCenter) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsCoin.OBSTACLE_WIDTH, ConstantsCoin.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsCoin.OBSTACLE_WIDTH, ConstantsCoin.OBSTACLE_HEIGHT);
        return new Coin(scaledMovementLeft, scaledMovementRight, (int)(areaCenter * Constants.SCREEN_WIDTH), ConstantsCoin.OBSTACLE_TOP);
    }

    /**
     * Fonction qui intialise un obstalce suivant une coordonnee precise en y
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     * @param areaTop Haut de la hitbox de l'obstacle en coordonne y
     * @return Un obstacle de type chauve souris
     */
    public static Obstacle initialisationCoin(Context context, int areaCenter, int areaTop) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.bat, ConstantsCoin.OBSTACLE_WIDTH, ConstantsCoin.OBSTACLE_HEIGHT);
        Bitmap scaledMovementRight = createPicture(context, R.drawable.bat_fly, ConstantsCoin.OBSTACLE_WIDTH, ConstantsCoin.OBSTACLE_HEIGHT);
        return new Coin(scaledMovementLeft, scaledMovementRight, areaCenter, areaTop);
    }

    /**
     * Methode qui indique si la hitbox d'un personnage est entree en collision avec la hitbox de l'obstacle
     * @param player Le personnage qu'on veut savoir si il est en collision
     * @return 0 si la collision n'a pas eu lieu, -2 si la collision a eu lieu
     */
    @Override
    public int playerCollide(AlienSprite player) {
        int collision = super.playerCollide(player) ;
        if (collision == 1) {
            collision = -2;
        }
        return collision;
    }
}
