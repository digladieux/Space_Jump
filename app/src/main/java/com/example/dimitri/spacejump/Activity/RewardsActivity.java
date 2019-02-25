package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

/**
 * Activity pour l'affichage des récompenses
 */
public class RewardsActivity extends Activity {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("RewardsActivity") ;

    /**
     * Texte qui indique comment la recompense a ete obtenue
     */
    TextView textViewRewards ;
    /**
     * Boutton pour le retour a l'activite precedente (MainActivity)
     */
    Button buttonPreviousActivity;
    /**
     * Liste de toutes les recompenses
     */
    Button[] buttonsBadges;

    /**
     * Methode appelle a la creation de l'activit
     * @param savedInstanceState null si c'est la premiere fois que l'application se cree, sinon contient la nouvelle orientation du jeu (paysage ou portrait)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); /* On veut afficher l'application sur tout l'ecran */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ; /* On ne souhaite pas afficher le nom de l'application en haut de l'ecran */
        setContentView(R.layout.activity_rewards); /* Setter sur le choix de la disposition des objets a l'ecran */
        logger.log(Level.INFO, "CreationRewardsActivty");

        textViewRewards = findViewById(R.id.textRewards) ;

        buttonPreviousActivity = findViewById(R.id.buttonReturn);
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RewardsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonsBadges = new Button[5];

        displayRewardsIfAvailable(0, R.string.reward0 );
        displayRewardsIfAvailable(1, R.string.reward1 );
        displayRewardsIfAvailable(2, R.string.reward2 );
        displayRewardsIfAvailable(3, R.string.reward3 );
        displayRewardsIfAvailable(4, R.string.reward4 );
    }


    private void displayRewardsIfAvailable(final int i, final int text)
    {
        if (mapAvailable >= i+1)
        {
            buttonsBadges[i] = findViewById(R.id.buttonBadgeBlue);
            buttonsBadges[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    logger.log(Level.INFO, "ClickOnReward");
                    textViewRewards.setText(text);
                    textViewRewards.setVisibility(View.VISIBLE);
                }
            });
            buttonsBadges[i].setVisibility(View.VISIBLE);
        }

    }

}
