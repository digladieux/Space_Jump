package com.example.dimitri.spacejump.Constants;

public class ConstantsBatObstacle {

    public static final int OBSTACLE_WIDTH = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 175); /* Largeur de l'obstacle */
    public static final int OBSTACLE_HEIGHT = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 117); /* Hauteur de l'obstacle */
    private static final int OBSTACLE_GAP_TOP = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 400); /* Distance entre le haut de l'ecran et un obstacle */
    public static final int OBSTACLE_TOP = OBSTACLE_GAP_TOP; /* Haut de la hitbox de l'obstacle en coordonne y */

}
