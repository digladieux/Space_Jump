package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

/**
 * Activity pour le menu principal de l'application
 */
public class MainActivity extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("MainActivity") ;
    /**
     * Boutton pour le lancement du choix des cartes (MapActivity)
     */
    Button buttonStart;

    /**
     * Boutton pour l'affichage des recompenses (RewardsActivity)
     */
    Button buttonRewards;

    /**
     * Boutton pour le choix de la tenue (DressActivity)
     */
    Button buttonDress;

    /**
     * Methode appelle a la creation de l'activite
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ; /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */
        setContentView(R.layout.activity_main); /* Setter sur le choix de la disposition des objets a l'ecran */

        logger.log(Level.INFO, "CreationMainActivty");
        SharedPreferences fileMapAvailable = getSharedPreferences("MapAvailable", Context.MODE_PRIVATE); /* On ouvre la lecture d'un fichier qui sera ouvrable que par notre application */
        mapAvailable = fileMapAvailable.getInt("map", 0); /* On recupere la valeur de cette cle. Ce sera 0 si la cle n'existe pas ( a la premiere installation de l'application a debloque 0 carte */
        logger.log(Level.INFO, "map available = " + mapAvailable);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite MainActivity a la MapActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        buttonRewards = findViewById(R.id.buttonRewards);
        buttonRewards.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite MainActivity a la RewardsActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(MainActivity.this, RewardsActivity.class);
                startActivity(intent);
            }
        });

        buttonDress = findViewById(R.id.buttonDress);
        buttonDress.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite MainActivity a la DressActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(MainActivity.this, DressActivity.class);
                startActivity(intent);
            }
        });
    }
}
