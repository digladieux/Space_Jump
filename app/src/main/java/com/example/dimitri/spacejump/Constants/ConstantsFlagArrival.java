package com.example.dimitri.spacejump.Constants;

public class ConstantsFlagArrival {

    public static final int OBSTACLE_WIDTH = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 150); /* Largeur de l'obstacle */
    public static final int OBSTACLE_HEIGHT = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 639); /* Hauteur de l'obstacle */
    public static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - OBSTACLE_HEIGHT; /* Haut de la hitbox de l'obstacle en coordonne y */
    public static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT; /* Bas de la hitbox de l'obstacle en coordonne y */

}
