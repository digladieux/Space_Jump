package com.example.dimitri.spacejump.Entities.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Classe pour l'utilisation d'animation
 */
public class Animation {

    /**
     * Liste des differentes images de l'animation
     */
    private final Bitmap[] pictures;
    /**
     * Indice qui represente l'image actuellement afficher a l'ecran
     */
    private int pictureIndex = 0;

    /**
     * Boolean pour connaitre si une animation est actuellement en train d'etre lance a l'ecran
     */
    private boolean isPLaying = false;
    /**
     * Temps maximal entre 2 images
     */
    private final float pictureTime;
    /**
     * Temps actuel que l'image est affiche a l'écran
     */
    private long currentTimePicture;

    /**
     * Constructeur de la classe Animation
     * @param pictures Liste des differentes images de l'animation
     * @param animTime Temps maximal entre 2 images
     */
    public Animation(Bitmap[] pictures, float animTime) {
        this.pictures = pictures;
        pictureTime = animTime/ pictures.length ;
        currentTimePicture = System.currentTimeMillis();
    }

    /**
     * Getteurpour connaitre si une animation est actuellement en train d'etre lance a l'ecran
     * @return Boolean pour connaitre si une animation est actuellement en train d'etre lance a l'ecran
     */
    boolean isPLaying() {
        return isPLaying;
    }

    /**
     * Lancement d'une animation
     */
    void play() {
        pictureIndex = 0;
        isPLaying = true;
        currentTimePicture = System.currentTimeMillis();
    }

    /**
     * Arret d'une animation
     */
    void stop() {
        isPLaying = false;
    }

    /**
     *
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     * @param destination Hitbox de l'entite
     */
    void draw(Canvas canvas, Rect destination)
    {
        if (isPLaying) {
            canvas.drawBitmap(pictures[pictureIndex], null, destination, new Paint());
        }
    }

    /**
     * Mise a jour de l'animation
     */
    void update()
    {
        if ((isPLaying) && (System.currentTimeMillis() - currentTimePicture > pictureTime *1000))
        {
            pictureIndex++ ;
            pictureIndex = pictureIndex >= pictures.length ? 0 : pictureIndex;
            currentTimePicture = System.currentTimeMillis() ;
        }
    }
}
