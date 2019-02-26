package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;
import com.example.dimitri.spacejump.R;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentDress;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.maxDress;
import static com.example.dimitri.spacejump.StaticMethod.readFile;
import static com.example.dimitri.spacejump.StaticMethod.writeFile;

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
     * Texte du prix de la tenue
     */
    TextView textViewPriceDress ;

    /**
     * Ce message s'affiche si la tenue n'est pas deverouille
     */
    TextView textViewLockedDress ;
    /**
     * Image de la tenue si non debloque
     */
    ImageView imagePadlock ;

    /**
     * Activity actuellement en cours
     */
    Context context ;

    /**
     * Nombre de piece de l'utilisateur
     */
    int[] coin;

    TextView textCoinNumber ;
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
        logger.log(Level.INFO, "CreationDressActivty");

        coin = new int[]{readFile(this, "coin")};
        context = this ;
        textViewLockedDress = findViewById(R.id.textLockedDress) ;
        textViewPriceDress = findViewById(R.id.textPriceDress) ;
        imagePadlock = findViewById(R.id.imagePadlock) ;
        imageViewCurrentDress = findViewById(R.id.imageCurrentDress) ;
        textCoinNumber = findViewById(R.id.textCoinNumber) ;
        textCoinNumber.setText(String.valueOf(coin[0]));

        imageViewCurrentDress.setOnClickListener(new View.OnClickListener(){
            /**
             * Methode qui se declenche lors d'un clique. Permet de passer de l'activite DressActivity a la MainActivity
             * @param view Interface utilisateur pour la manipulation du composant boutton
             */
            @Override
            public void onClick(View view) {
                try {
                    if ( (readFile(context, "dress" + currentDress) == 0) && (currentDress >= mapAvailable) && (coin[0] >= getTextPrice()))
                    {
                        logger.log(Level.INFO, "Dress Buy");

                        imagePadlock.setVisibility(View.INVISIBLE);
                        textViewPriceDress.setVisibility(View.INVISIBLE);
                        writeFile(context, "dress" + currentDress, 1);
                        coin[0] -= getTextPrice() ;
                        writeFile(context, "coin", coin[0]);
                        textCoinNumber.setText(String.valueOf(coin[0]));
                        displayCurrentDress();
                    }
                    else
                    {
                        logger.log(Level.INFO, "Not enough coin or already unlocked");

                    }
                } catch (InvalidCurrentDress invalidCurrentDress) {
                    logger.log(Level.SEVERE, "InvalidCurrentDress");
                }

            }
        });


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

                if (readFile(context, "dress" + currentDress) == 0)
                {
                    textViewLockedDress.setVisibility(View.VISIBLE);
                    Thread t = new Thread() {
                        public void run() {
                            long time = System.currentTimeMillis();
                            while (System.currentTimeMillis() - time < 2000)
                            {

                            }
                        }
                    };
                    t.start();
                    textViewLockedDress.setVisibility(View.INVISIBLE);
                }
                else
                {
                    logger.log(Level.INFO, "ChangeActivity");
                    Intent intent = new Intent(DressActivity.this, MainActivity.class);
                    startActivity(intent);
                }

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
                if (currentDress == maxDress)
                {
                    currentDress = 0 ;
                }
                else
                {
                    currentDress ++ ;
                }
                logger.log(Level.INFO, "Current Dress : "+String.valueOf(currentDress)) ;
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
                    currentDress = maxDress;
                }
                else
                {
                    currentDress -- ;
                }
                logger.log(Level.INFO, "Current Dress : "+String.valueOf(currentDress) + ", Map Available : " + mapAvailable) ;
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

            case 5:
                imageViewCurrentDress.setImageResource(R.drawable.shipblue);
                break ;

            case 6:
                imageViewCurrentDress.setImageResource(R.drawable.shipbeige);
                break ;

            case 7:
                imageViewCurrentDress.setImageResource(R.drawable.shippink);
                break ;

            case 8:
                imageViewCurrentDress.setImageResource(R.drawable.shipyellow);
                break ;

            case 9:
                imageViewCurrentDress.setImageResource(R.drawable.shipgreen);
                break ;
                default:
                    throw new InvalidCurrentDress() ;
        }
        setVisibilityDress();
    }

    /**
     * Affichage ou non du cadenas et du prix de la robe
     */
    private void setVisibilityDress()
    {
        int visible = readFile(context, "dress"+String.valueOf(currentDress)) ;
        if (visible == 0)
        {
            setVisibility(View.VISIBLE);
            try {
                if (  ((currentDress < 5) && (currentDress < mapAvailable)) || (currentDress > 4 && coin[0] >= getTextPrice()))
                {
                    textViewPriceDress.setTextColor(Color.GREEN);
                }
                else
                {
                    textViewPriceDress.setTextColor(Color.RED);
                }
            } catch (InvalidCurrentDress invalidCurrentDress) {
                logger.log(Level.SEVERE, "InvalidCurrentDress");
            }
        }

        else
        {
            setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Affichage du cadenas et du prix
     * @param visibility Boolean pour l'affichage ou non
     */
    private void setVisibility(int visibility)
    {

        try {
            setTextViewPriceDress();
        } catch (InvalidCurrentDress invalidCurrentDress) {
            logger.log(Level.SEVERE, "InvalidCurrentDress");
        }
        textViewPriceDress.setVisibility(visibility);
    }

    /**
     * Setter sur le prix de la tenue
     */
    private int setTextViewPriceDress() throws InvalidCurrentDress {
        int price ;
        switch (currentDress)
        {
            case 0 :
            case 1 :
                price = setPrice(R.string.price1) ;
                break;
            case 2 :
                price = setPrice(R.string.price2) ;
                break;
            case 3 :
                price = setPrice(R.string.price3) ;
                break;
            case 4 :
                price = setPrice(R.string.price4) ;
                break;
            case 5 :
                price = setPrice(R.string.price5) ;
                break ;
            case 6 :
                price = setPrice(R.string.price6) ;
                break ;
            case 7 :
                price = setPrice(R.string.price7) ;
                break ;

            case 8 :
                price = setPrice(R.string.price8) ;
                break ;
            case 9 :
                price = setPrice(R.string.price9) ;
                break ;
            default:
                throw new InvalidCurrentDress() ;
        }
        textViewPriceDress.setText(price);
        return price ;

    }

    /**
     * Setter pour savoir si le prix d'une robe achetable doit etre affichager
     * @param string Le prix a afficher si non dispo
     * @return Le texte a afficher
     */
    private int setPrice(int string)
    {
        int price ;
        if (mapAvailable > currentDress)
        {
            price = R.string.price ;
        }
        else
        {
            price = string ;
        }
        return price ;
    }

    /**
     * Getter sur le prix de la tenue
     */
    private int getTextPrice() throws InvalidCurrentDress {
        int price ;
        switch (currentDress)
        {
            case 5 :
                price = 1 ;
                break ;
            case 6 :
                price = 2 ;
                break ;
            case 7 :
                price = 3 ;
                break ;

            case 8 :
                price = 4 ;
                break ;
            case 9 :
                price = 5 ;
                break ;
            default:
                throw new InvalidCurrentDress() ;
        }
        return price ;

    }



}


