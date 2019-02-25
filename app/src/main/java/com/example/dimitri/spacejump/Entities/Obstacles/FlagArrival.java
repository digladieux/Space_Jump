package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsFlagArrival;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

public class FlagArrival extends Obstacle {

    /**
     * Constructeur de la classe
     * @param context Activity actuellement en cours
     * @param areaCenter Centre de la hitbox en coordonne y
     */
    public FlagArrival(Context context, double areaCenter) {
        super(createPicture(context, R.drawable.flag, ConstantsFlagArrival.OBSTACLE_WIDTH, ConstantsFlagArrival.OBSTACLE_HEIGHT), (int) (Constants.SCREEN_WIDTH * areaCenter - ConstantsFlagArrival.OBSTACLE_WIDTH / 2), (int) (Constants.SCREEN_WIDTH * areaCenter + ConstantsFlagArrival.OBSTACLE_WIDTH / 2), ConstantsFlagArrival.OBSTACLE_TOP, ConstantsFlagArrival.OBSTACLE_BOTTOM);
    }

    /**
     * Methode qui indique si la hitbox d'un personnage est entree en collision avec la hitbox de l'obstacle
     * @param player Le personnage qu'on veut savoir si il est en collision
     * @return 0 si la collision n'a pas eu lieu, -1 si la collision a eu lieu
     */
    @Override
    public int playerCollide(AlienSprite player) {
        int collision = super.playerCollide(player) ;
        if (collision == 1) {
            collision = -1;
        }
        return collision;
    }
}

