package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;
import static com.example.dimitri.spacejump.StaticMethod.readFile;
import static com.example.dimitri.spacejump.StaticMethod.writeFile;

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

        mapAvailable = readFile(this, "map_available") ;
        writeFile(this, "dress0", 1);

        logger.log(Level.INFO, "map available = " + mapAvailable);

   /*     writeFile(this, "dress0", 1);
        writeFile(this, "dress1", 0);
        writeFile(this, "dress2", 0);
        writeFile(this, "dress3", 0);
        writeFile(this, "dress4", 0);
        writeFile(this, "dress5", 0);
        writeFile(this, "dress6", 0);
        writeFile(this, "dress7", 0);
        writeFile(this, "dress8", 0);
        writeFile(this, "dress9", 0);
        writeFile(this, "map_available", 0);
        writeFile(this, "coin", 0);
*/
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
