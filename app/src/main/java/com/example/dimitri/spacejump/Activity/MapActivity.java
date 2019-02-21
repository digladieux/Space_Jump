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

import com.example.dimitri.spacejump.Activity.Game.GameActivity;
import com.example.dimitri.spacejump.Constants.ConstantsGame;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

public class MapActivity extends Activity {

    Button buttonPreviousActivity;
    Button[] buttonsMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;

        SharedPreferences fileMapAvailable = getSharedPreferences("MapAvailable", Context.MODE_PRIVATE);
        mapAvailable = fileMapAvailable.getInt("map", 0);

        setContentView(R.layout.activity_map);

        buttonPreviousActivity = findViewById(R.id.buttonReturn);
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        buttonsMaps = new Button[4];

        buttonsMaps[0] = findViewById(R.id.buttonEarth);
        buttonsMaps[0].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                currentMap = 0 ;
                startActivity(intent);
            }
        });

        if (mapAvailable >= 1)
        {
            buttonsMaps[1] = findViewById(R.id.buttonMoon);
            buttonsMaps[1].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    currentMap = 1 ;
                    startActivity(intent);
                }
            });
            buttonsMaps[1].setVisibility(View.VISIBLE);

        }

        if (mapAvailable >= 2) {

            buttonsMaps[2] = findViewById(R.id.buttonMars);
            buttonsMaps[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    currentMap = 2 ;
                    startActivity(intent);
                }
            });
            buttonsMaps[2].setVisibility(View.VISIBLE);
        }

        if (mapAvailable >= 3) {
            buttonsMaps[3] = findViewById(R.id.buttonSun);
            buttonsMaps[3].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapActivity.this, GameActivity.class);
                    currentMap = 3 ;
                    startActivity(intent);
                }
            });
            buttonsMaps[3].setVisibility(View.VISIBLE);

        }
    }
}
