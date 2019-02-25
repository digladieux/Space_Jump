package com.example.dimitri.spacejump;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.MotionEvent;

import com.example.dimitri.spacejump.Constants.Constants;

import java.io.IOException;

/**
 * Classe contenant toutes les methodes statiques necessaires au fonctionnement de l'application
 */
public class StaticMethod {

    /**
     * Cree une image et la redimensionne
     * @param context Activity actuellement en cours
     * @param id Nom de l'image
     * @param width Largeur souhaitee pour l'image
     * @param height Hauteur souhaitee pour l'image
     * @return L'image redimensionnee
     */
    public static Bitmap createPicture(Context context, int id, int width, int height)
    {
        Bitmap picture = BitmapFactory.decodeResource(context.getResources(),id) ;
        return Bitmap.createScaledBitmap(picture, width, height, true);
    }


    /**
     * Dessine l'image de fond de l'application de jeux
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     * @param scaledBackground L'image a afficher
     */
    public static void drawBitmapBackground(Canvas canvas, Bitmap scaledBackground)
    {
        Rect srcBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        Rect destBackground = new Rect(0, 0, scaledBackground.getWidth() - 1, scaledBackground.getHeight() - 1);
        canvas.drawBitmap(scaledBackground, srcBackground, destBackground, null);
    }

    /**
     * Desinne une image sur l'application de jeu a une position precise
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     * @param scaledPicture L'image a afficher
     * @param x Coordonnee x du centre de l'image
     * @param y Coordonnee y du centre de l'image
     */
    public static void drawBitmap(Canvas canvas, Bitmap scaledPicture, float x , float y )
    {
        int left = (int) (x * Constants.SCREEN_WIDTH)  - scaledPicture.getWidth()/2 ;
        int top = (int) (y * Constants.SCREEN_HEIGHT) - scaledPicture.getHeight()/2 ;
        int right =  (int) (x * Constants.SCREEN_WIDTH) + scaledPicture.getWidth()/2 ;
        int bottom = (int) (y * Constants.SCREEN_HEIGHT)  + scaledPicture.getHeight()/2;
        Rect srcBitmap = new Rect(0, 0, scaledPicture.getWidth() - 1, scaledPicture.getHeight() - 1);
        Rect destBitmap= new Rect(left, top,right, bottom);
        canvas.drawBitmap(scaledPicture, srcBitmap, destBitmap, null);
    }

    /**
     * Desinne l'image de retour arriere de l'application de jeu
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image lors du jeu
     * @param scaledReturnMenu Image du boutton pour le retour arriere
     */
    public static void drawBitmapReturn(Canvas canvas, Bitmap scaledReturnMenu)
    {
        int left = (13 * Constants.SCREEN_WIDTH/14);
        int top = 0 ;
        int right =  Constants.SCREEN_WIDTH ;
        int bottom = Constants.SCREEN_WIDTH/14 ;
        Rect srcBitmap = new Rect(0, 0, scaledReturnMenu.getWidth() - 1, scaledReturnMenu.getHeight() - 1);
        Rect destBitmap= new Rect(left, top,right, bottom);
        canvas.drawBitmap(scaledReturnMenu, srcBitmap, destBitmap, null);
    }

    /**
     * Verifie si un boutton a ete appuiye lors d'un evenement
     * @param event L'evenement declenche
     * @param bitmap La zone de l'ecran ou se trouve le boutton, represente par une image
     * @param x Coordonnee x du centre de l'image
     * @param y Coordonnee y du centre de l'image
     * @return True si le boutton a ete clique, faux sinon
     */
    public static boolean isButtonClick(MotionEvent event, Bitmap bitmap, float x, float y)
    {
        return (event.getAction() == MotionEvent.ACTION_UP)
                && (event.getRawX() >= x * Constants.SCREEN_WIDTH - bitmap.getWidth()/2)
                && (event.getRawX() <= x * Constants.SCREEN_WIDTH + bitmap.getWidth()/2)
                && (event.getRawY() >= y * Constants.SCREEN_HEIGHT - bitmap.getHeight()/2)
                && (event.getRawY() <= y * Constants.SCREEN_HEIGHT + bitmap.getHeight()/2);
    }

    /**
     * Verifie si le boutton de retour arriere a ete appuiye lors d'un evenement
     * @param event L'evenement declenche
     * @return True si le boutton de retour arriere a ete appuye, false sinon
     */
    public static boolean isButtonClick(MotionEvent event)
    {
        return (event.getAction() == MotionEvent.ACTION_UP) && (event.getRawX() > 13 * Constants.SCREEN_WIDTH/14) && (event.getRawY() < Constants.SCREEN_WIDTH/14);
    }

    public static void resetMusic(MediaPlayer music)
    {
        music.stop();

        try {
            music.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        music.seekTo(0);
    }




}
