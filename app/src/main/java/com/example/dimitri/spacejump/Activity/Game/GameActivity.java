package com.example.dimitri.spacejump.Activity.Game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.dimitri.spacejump.Constants.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Activity pour le jeu
 */
public class GameActivity  extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("DressActivity") ;

    /**
     * Methode appelle a la creation de l'activité
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ; /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */
        logger.log(Level.INFO, "CreationGameActivity");

        DisplayMetrics dm = new DisplayMetrics() ;

        getWindowManager().getDefaultDisplay().getMetrics(dm); /* Recuperation de toutes les metriques (ici la taille de l'ecran nous interesse) */

        Constants.SCREEN_WIDTH = dm.widthPixels ;
        Constants.SCREEN_HEIGHT = dm.heightPixels ;
        logger.log(Level.INFO, "Width = " + Constants.SCREEN_WIDTH  );
        logger.log(Level.INFO, "Height = " + Constants.SCREEN_HEIGHT  );

        setContentView(new GamePanel(this)); /* Setter sur le choix de la disposition des objets a l'ecran. Ici on disposera nous meme les elements */

    }

}
