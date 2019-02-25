package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dimitri.spacejump.Activity.Game.GameActivity;
import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;
import static com.example.dimitri.spacejump.StaticMethod.resetMusic;

/**
 * Activity pour le choix de la carte par l'utilisateur
 */
public class MapActivity extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("MapActivity") ;
    /**
     * Boutton pour le retour a l'activite precedente (MainActivity)
     */

    Button buttonPreviousActivity;

    /**
     * Musique du menu
     */
    MediaPlayer musicMap ;
    /**
     * Liste des bouttons qui represente la liste des differentes cartes de notre application
     */
    Button[] buttonsMaps;

    /**
     * Methode appelle a la creation de l'activite
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */

        SharedPreferences fileMapAvailable = getSharedPreferences("MapAvailable", Context.MODE_PRIVATE); /* On ouvre la lecture d'un fichier qui sera ouvrable que par notre application */
        mapAvailable = fileMapAvailable.getInt("map", 0); /* On recupere la valeur de cette cle. Ce sera 0 si la cle n'existe pas ( a la premiere installation de l'application a debloque 0 carte */

        setContentView(R.layout.activity_map); /* Setter sur le choix de la disposition des objets a l'ecran */
        logger.log(Level.INFO, "CreationMapActivty");

        musicMap = MediaPlayer.create(getApplicationContext(), R.raw.menusong);
        musicMap.start();
        buttonPreviousActivity = findViewById(R.id.buttonReturn);
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener() {
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite MapActivity a la MainActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                resetMusic(musicMap) ;
                startActivity(intent);
            }
        });


        buttonsMaps = new Button[4];

        displayButtonIfMapAvailable(0, R.id.buttonEarth);
        displayButtonIfMapAvailable(1, R.id.buttonMoon);
        displayButtonIfMapAvailable(2, R.id.buttonMars);
        displayButtonIfMapAvailable(3, R.id.buttonSun);
    }

        private void displayButtonIfMapAvailable( final int i, int idImage)
        {
            if (mapAvailable >= i) {
                buttonsMaps[i] = findViewById(idImage);
                buttonsMaps[i].setOnClickListener(new View.OnClickListener() {
                    /**
                     * Methode qui se declenche lors d'un clique. Permet de passer de l'activite MapActivity a la GameActivity
                     * @param view Interface utilisateur pour la manipulation du composant boutton
                     */
                    @Override
                    public void onClick(View view) {
                        logger.log(Level.INFO, "ChangeActivity");
                        Intent intent = new Intent(MapActivity.this, GameActivity.class);
                        currentMap = i ;
                        resetMusic(musicMap);
                        startActivity(intent);
                    }
                });
                buttonsMaps[i].setVisibility(View.VISIBLE);

            }
        }
    }
