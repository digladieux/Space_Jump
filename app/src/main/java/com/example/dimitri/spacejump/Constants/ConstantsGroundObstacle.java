package com.example.dimitri.spacejump.Constants;

/**
 * Classe contenant les constantes autour du sol. On effectue une regle de 3 pour adapter la taille a tous les ecrans
 */
public class ConstantsGroundObstacle {

    public static final int OBSTACLE_HEIGHT = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 140); /* Largeur de l'obstacle */
    public static final int OBSTACLE_WIDTH = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 140); /* Hauteur de l'obstacle */
    public static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT; /* Haut de la hitbox de l'obstacle en coordonne y */
    public static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT; /* Bas de la hitbox de l'obstacle en coordonne y */

}
