package com.example.dimitri.spacejump.Constants;

/**
 * Classe contenant les constantes autour de l'obstacle serpent. On effectue une regle de 3 pour adapter la taille a tous les ecrans
 */
public class ConstantsSnakeObstacle {

    public static final int OBSTACLE_WIDTH = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 106); /* Largeur de l'obstacle */
    public static final int OBSTACLE_HEIGHT = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 294); /* Hauteur de l'obstacle */
    public static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2; /* Haut de la hitbox de l'obstacle en coordonne y */
    public static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2;

}
