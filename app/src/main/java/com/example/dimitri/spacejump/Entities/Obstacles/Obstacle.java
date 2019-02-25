package com.example.dimitri.spacejump.Entities.Obstacles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.dimitri.spacejump.Entities.Animation.Animation;
import com.example.dimitri.spacejump.Entities.Animation.AnimationManager;
import com.example.dimitri.spacejump.Entities.AlienSprite;

public abstract class Obstacle {

    /**
     * Hitbox de l'obstacle
     */
    private final Rect areaObstacle;

    /**
     * Liste des animations d'un obstacle
     */
    private final AnimationManager animationManager;

    /**
     * Constructeur de la classe Obstacle
     * @param movementRight 1ere image de l'animation de deplacement de l'obstacle
     * @param movementLeft 2ème image de l'animation de deplacement de l'obstacle
     * @param areaLeft Gauche de la hitbox de l'obstacle en coordonne x
     * @param areaTop Haut de la hitbox de l'obstacle en coordonne y
     * @param areaRight Droite de la hitbox de l'obstacle en coordonne x
     * @param areaBottom Bas de la hitbox de l'obstacle en coordonne y*
     */

    Obstacle(Bitmap movementRight, Bitmap movementLeft, int areaLeft, int areaTop, int areaRight, int areaBottom) {
        areaObstacle = new Rect(areaLeft, areaTop, areaRight, areaBottom);
        Animation animation = new Animation(new Bitmap[]{movementLeft, movementRight}, 0.25f);
        animationManager = new AnimationManager(new Animation[]{animation});
        animationManager.playAnim(0);
    }

    /**
     * Constructeur de la classe Obstacle
     * @param idle Image de l'obstacle au repos
     * @param areaLeft Gauche de la hitbox de l'obstacle en coordonne x
     * @param areaTop Haut de la hitbox de l'obstacle en coordonne y
     * @param areaRight Droite de la hitbox de l'obstacle en coordonne x
     * @param areaBottom Bas de la hitbox de l'obstacle en coordonne y*
     */
    Obstacle(Bitmap idle, int areaLeft, int areaRight, int areaTop, int areaBottom) {
        areaObstacle = new Rect(areaLeft, areaTop, areaRight, areaBottom);
        Animation idleGround = new Animation(new Bitmap[]{idle}, 2);
        animationManager = new AnimationManager(new Animation[]{idleGround});
        animationManager.playAnim(0);
    }

    /**
     * Methode qui indique si la hitbox d'un personnage est entree en collision avec la hitbox de l'obstacle
     * @param player Le personnage qu'on veut savoir si il est en collision
     * @return 0 si la collision n'a pas eu lieu, 1 si la collision a eu lieu
     */
    public int playerCollide(AlienSprite player) {
        boolean collide = areaObstacle.intersect(player.getRectangle());
        int codeCollision = 0 ;
        if (collide)
        {
            codeCollision = 1 ;
        }

        return codeCollision ;

    }

    /**
     * Deplacement un obstacle sur la gauche de l'ecran
     * @param numberPixelDecrement Nombre de pixel que l'on veut deplacer a gauche
     */
    void decrementX(float numberPixelDecrement) {
        areaObstacle.right -= numberPixelDecrement;
        areaObstacle.left -= numberPixelDecrement;
    }

    /**
     * Getteur sur la hitbox de l'obstacle
     * @return La hitbox de l'obstacle
     */
    Rect getRectangle() {
        return areaObstacle;
    }


    /**
     * Dessine l'obstacle en jeu
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     */
    void draw(Canvas canvas) {
        animationManager.draw(canvas, areaObstacle);
    }

    /**
     * Met a jour l'animation de l'obstacle
     */
    void update() {
        animationManager.update();
    }
}
