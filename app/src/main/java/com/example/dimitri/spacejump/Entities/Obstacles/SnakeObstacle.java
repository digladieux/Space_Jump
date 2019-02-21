package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsSnakeObstacle;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

public class SnakeObstacle extends Obstacle {

    private SnakeObstacle(Bitmap movement_right, Bitmap movement_left, int area_left) {
        super(movement_right, movement_left, area_left, ConstantsSnakeObstacle.OBSTACLE_TOP, area_left + ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_BOTTOM);
    }

    public static Obstacle initialisationSnakeObstacle(Context context, double area_left) {
        Bitmap scaledMovementLeft = createPicture(context, R.drawable.snake_slime, ConstantsSnakeObstacle.OBSTACLE_WIDTH, ConstantsSnakeObstacle.OBSTACLE_HEIGHT);
        Matrix m = new Matrix();
        m.preScale(-1, 1); /* Permet de definir la "taille de l'image" [-1 ; 1 ] */
        Bitmap scaledMovementRight = Bitmap.createBitmap(scaledMovementLeft, 0, 0, scaledMovementLeft.getWidth(), scaledMovementLeft.getHeight(), m, false);
        return new SnakeObstacle(scaledMovementLeft, scaledMovementRight, (int)(area_left * Constants.SCREEN_WIDTH));
    }
}


