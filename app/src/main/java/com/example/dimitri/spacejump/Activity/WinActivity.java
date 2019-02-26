package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dimitri.spacejump.Activity.Game.GameActivity;
import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;
import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;
import static com.example.dimitri.spacejump.StaticMethod.resetMusic;
import static com.example.dimitri.spacejump.StaticMethod.writeFile;

/**
 * Activity pour l'ecran de victoire d'un niveau
 */
public class WinActivity extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("DressActivity") ;

    /**
     * Boutton pour le retour a l'activite precedente (GameActivity)
     */
    Button buttonPreviousActivity;

    /**
     * Musique de victoire
     */
    MediaPlayer musicWin ;

    /**
     * Boutton pour le retour a l'activite du choix des cartes (MapActivity)
     */
    Button buttonReturnMap;

    /**
     * Image qui s'affiche si le joueur a gagne de nouvelles recompenses
     */
    ImageView imageViewNewRewards;

    /**
     * Image qui s'affiche si le joueur a gagne une nouvelle tenue
     */
    ImageView imageViewNewDress;

    /**
     * Image qui s'affiche si le joueur a gagne un nouveau badge
     */
    ImageView imageViewNewBadge;

    /**
     * Texte qui s'affiche si le joueur a gagne de nouvelles recompenses
     */
    TextView textViewNewRewards;

    /**
     * Texte pour indiquer la victoire du joueur
     */
    TextView textViewVictory;

    /**
     * Methode appelle a la creation de l'activite
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ; /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */
        setContentView(R.layout.activity_win); /* Setter sur le choix de la disposition des objets a l'ecran */
        logger.log(Level.INFO, "CreationWinActivty");
        logger.log(Level.INFO, "Current Map : "+String.valueOf(currentMap) + ", Map Available : " + mapAvailable) ;

        musicWin = MediaPlayer.create(getApplicationContext(), R.raw.winningsong);
        musicWin.start();


        if (mapAvailable == currentMap)
        {
            mapAvailable ++ ;
            writeFile(this, "dress" + mapAvailable, 1);
            logger.log(Level.INFO, "NewRewards");
            try {
                displayNewRewards();
                writeFile(this, "map_available", mapAvailable);
            } catch (InvalidCurrentDress invalidCurrentDress) {
                logger.log(Level.SEVERE, "InvalidCurrentDress");
            }
        }

        buttonPreviousActivity = findViewById(R.id.buttonRetry);
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite WinActivity a la GameActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
                resetMusic(musicWin);
                startActivity(intent);
            }
        });

        buttonReturnMap = findViewById(R.id.buttonReturn);
        buttonReturnMap.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite WinActivity a la MapActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(WinActivity.this, MapActivity.class);
                resetMusic(musicWin);
                startActivity (intent);
            }
        });
        textViewVictory= findViewById(R.id.textVictory) ;
    }

    /**
     * Cette methode se declenche si on a debloque un nouveau niveau
     */
    private void displayNewRewards() throws InvalidCurrentDress {
        imageViewNewDress = findViewById(R.id.imageNewDress) ;
        imageViewNewBadge = findViewById(R.id.imageNewBadge) ;

        textViewNewRewards = findViewById(R.id.textNewRewards) ;
        imageViewNewRewards = findViewById(R.id.imageNewRewards) ;

        imageViewNewRewards.setImageResource(R.drawable.new_rewards);
        textViewNewRewards .setVisibility(View.VISIBLE);
        imageViewNewRewards.setVisibility(View.VISIBLE);

        switch (currentMap)
        {
            case 0:
                imageViewNewDress.setImageResource(R.drawable.alienbeige);
                imageViewNewBadge.setImageResource(R.drawable.alienbeige_badge2);
                break ;

            case 1:
                imageViewNewBadge.setImageResource(R.drawable.alienpink_badge2);
                imageViewNewDress.setImageResource(R.drawable.alienpink);
                break ;

            case 2:
                imageViewNewBadge.setImageResource(R.drawable.alienyellow_badge2);
                imageViewNewDress.setImageResource(R.drawable.alienyellow);
                break ;

            case 3:
                imageViewNewBadge.setImageResource(R.drawable.aliengreen_badge2);
                imageViewNewDress.setImageResource(R.drawable.aliengreen);
                break ;

            case 4:
                break ;
            default:
                throw new InvalidCurrentDress() ;
        }
        imageViewNewBadge .setVisibility(View.VISIBLE);
        imageViewNewDress.setVisibility(View.VISIBLE);

    }
}
