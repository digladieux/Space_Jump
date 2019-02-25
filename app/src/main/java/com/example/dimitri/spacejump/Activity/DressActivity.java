package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;
import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentDress;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

/**
 * Activity pour le choix de la tenue du joueur
 */
public class DressActivity extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("DressActivity") ;
    /**
     * Boutton pour le retour a l'activite precedente (MainActivity)
     */
    Button buttonPreviousActivity ;

    /**
     * Boutton pour l'affichage de la prochaine tenue disponible
     */
    Button buttonNextDress;
    /**
     * Boutton pour l'affichage de la tenue precedente disponible
     */
    Button buttonPreviousDress;

    /**
     * Image de la tenue
     */
    ImageView imageViewCurrentDress;

    /**
     * Methode appelle a la creation de l'activite
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ; /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */
        setContentView(R.layout.activity_dress); /* Setter sur le choix de la disposition des objets a l'ecran */

        imageViewCurrentDress = findViewById(R.id.imageCurrentDress) ;
        logger.log(Level.INFO, "CreationDressActivty");

        try {
            displayCurrentDress();
        } catch (InvalidCurrentDress invalidCurrentDress) {
            logger.log(Level.SEVERE, "InvalidCurrentDress");
        }

        buttonPreviousActivity = findViewById(R.id.buttonReturn) ;
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite DressActivity a la MainActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ChangeActivity");
                Intent intent = new Intent(DressActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonNextDress = findViewById(R.id.buttonRightArrow);
        buttonNextDress.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer a la tenue suivante disponible
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ClickOnNextDress");
                if ( (currentDress == 4) || (mapAvailable <= currentDress))
                {
                    currentDress = 0 ;
                }
                else
                {
                    currentDress ++ ;
                }
                try {
                    displayCurrentDress();
                } catch (InvalidCurrentDress invalidCurrentDress) {
                    logger.log(Level.SEVERE, "InvalidCurrentDress");
                }
            }
        });
        buttonPreviousDress = findViewById(R.id.buttonLeftArrow);
        buttonPreviousDress.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer a la tenue precedente
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                logger.log(Level.INFO, "ClickOnPreviousDress");
                if (currentDress == 0)
                {
                    currentDress = mapAvailable;
                }
                else
                {
                    currentDress -- ;
                }
                try {
                    displayCurrentDress();
                } catch (InvalidCurrentDress invalidCurrentDress) {
                    logger.log(Level.SEVERE, "InvalidCurrentDress");
                }
            }
        });

    }

    /**
     * Affichage de la tenue de l'utilisateur. Genere une erreur si la tenue n'existe pas
     */
    private void displayCurrentDress() throws InvalidCurrentDress {
        if (currentDress > mapAvailable)
        {
            logger.log(Level.SEVERE, "InvalidCurrentDress");
        }
        switch (currentDress)
        {
            case 0:
                imageViewCurrentDress.setImageResource(R.drawable.alienblue);
            break ;

            case 1:
                imageViewCurrentDress.setImageResource(R.drawable.alienbeige);
                break ;

            case 2:
                imageViewCurrentDress.setImageResource(R.drawable.alienpink);
                break ;

            case 3:
                imageViewCurrentDress.setImageResource(R.drawable.alienyellow);
                break ;

            case 4:
                imageViewCurrentDress.setImageResource(R.drawable.aliengreen);
                break ;
                default:
                    throw new InvalidCurrentDress() ;
        }
    }

}


