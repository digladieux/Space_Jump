package com.example.dimitri.spacejump.Activity.Game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.dimitri.spacejump.Constants.Constants;

public class GameActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;

        DisplayMetrics dm = new DisplayMetrics() ;

        /* Recupère la liste de toutes les fenetre puis Sur l'écran par default, on recuperer toutes les metrics (taille etc) */
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        /* Affecte les coordoonée de l'écran a notre classe des var statique */
        Constants.SCREEN_WIDTH = dm.widthPixels ;
        Constants.SCREEN_HEIGHT = dm.heightPixels ;
        setContentView(new GamePanel(this));

    }

}
