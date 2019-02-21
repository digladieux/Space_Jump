package com.example.dimitri.spacejump.Animation;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationManager {

    /**
     *
     */
    private final Animation[] animations;
    /**
     *
     */
    private int animationIndex = 0;

    /**
     *
     * @param animations Liste de toutes les animations differentes pour un meme objet
     */
    public AnimationManager(Animation[] animations) {
        this.animations = animations;
    }

    /**
     *
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
     * @param canvas Zone de dessin
     * @param rect Rectangle ou se joue l'animation
     */
    public void draw(Canvas canvas, Rect rect) {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].draw(canvas, rect);
        }
    }

    /**
     *
     */
    public void update() {
        if (animations[animationIndex].isPLaying()) {
            animations[animationIndex].update();
        }
    }
}

