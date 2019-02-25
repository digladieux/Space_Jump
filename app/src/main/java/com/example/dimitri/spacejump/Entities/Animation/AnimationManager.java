package com.example.dimitri.spacejump.Entities.Animation;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Conteneur pour une liste d'animation
 */
public class AnimationManager {

    /**
     * Liste des differentes animations
     */
    private final Animation[] animations;
    /**
     * Indice qui represente l'animation actuellement afficher a l'ecran
     */
    private int animationIndex = 0;

    /**
     * Constructeur de la classe AnimationManager
     * @param animations Liste des differentes animations
     */
    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    /**
     * Lancement d'une animation
     * @param index Index de l'animation qui se jouera
     */
    public void playAnim(int index) {
        for (int i = 0; i < animations.length; i++) {
            if (i == index) {
                if (!animations[animationIndex].isPLaying()) {
                    animations[i].play();
                }
            } else {
                animations[i].stop();
            }
        }
        animationIndex = index;
    }

    /**
     *
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     * @param rect Hitbox de l'entite
     */
    public void draw(Canvas canvas, Rect rect) {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].draw(canvas, rect);
        }
    }

    /**
     * Mise a jour de l'animation
     */
    public void update() {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].update();
        }
    }
}

