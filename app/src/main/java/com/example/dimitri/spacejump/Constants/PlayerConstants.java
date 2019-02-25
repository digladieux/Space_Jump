package com.example.dimitri.spacejump.Constants;

/**
 * Classe contenant les constantes autour du personnage. On effectue une regle de 3 pour adapter la taille a tous les ecrans
 */
public class PlayerConstants {
    public static final int PLAYER_WIDTH = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 132); /* Largeur du personnage */
    public static final int PLAYER_HEIGHT = (int)((Constants.SCREEN_HEIGHT * 1.0 /1080) * 186); /* Hauteur du personnage */
    public static final double VELOCITY = (int)((Constants.SCREEN_HEIGHT * 1.0 /720) * 2); /* Acceleration du personnage */
    public static final double SPEED = (int)((Constants.SCREEN_HEIGHT * 1.0 /720) * -50); /* Vitesse du personnage */
    public static final int INIT_POSITION_X = (int)((Constants.SCREEN_WIDTH * 1.0 /1794) * 200); /* Position x du personnage */
    public static final int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - PLAYER_HEIGHT / 2; /* Position y du personnage (sur le sol) */
    public static final int LEFT_PLAYER = (int)((Constants.SCREEN_WIDTH * 1.0 / 1794) * 100); /* Gauche de la hitbox du personnage en coordonne x */
    public static final int TOP_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT; /* Haut de la hitbox de l'obstacle en coordonne y */
    public static final int RIGHT_PLAYER = LEFT_PLAYER + PLAYER_WIDTH; /* Droite de la hitbox de l'obstacle en coordonne x */
    public static final int BOTTOM_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT + PLAYER_HEIGHT; /* Bas de la hitbox de l'obstacle en coordonne y */
}


