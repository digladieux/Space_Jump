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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dimitri.spacejump.Activity.Game.GameActivity;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentMap;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

public class WinActivity extends Activity {
    Button buttonRetry;
    Button buttonReturnMap;
    ImageView imageViewNewRewards;
    ImageView imageViewNewDress;
    ImageView imageViewNewBadge;
    TextView textViewNewRewards;
    TextView textViewVictory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_win);

        buttonRetry = findViewById(R.id.buttonRetry);
        buttonRetry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        buttonReturnMap = findViewById(R.id.buttonReturn);
        buttonReturnMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        textViewVictory= findViewById(R.id.textVictory) ;
        if (mapAvailable == currentMap)
        {
            displayNewRewards();
        }

    }
    private void displayNewRewards()
    {
        mapAvailable ++ ;
        SharedPreferences.Editor edit = getSharedPreferences("MapAvailable", Context.MODE_PRIVATE).edit();
        edit.putInt("map", mapAvailable) ;
        edit.apply();

        imageViewNewDress = findViewById(R.id.imageNewDress) ;
        imageViewNewBadge = findViewById(R.id.imageNewBadge) ;

        textViewNewRewards = findViewById(R.id.textNewRewards) ;
        imageViewNewRewards = findViewById(R.id.imageNewRewards) ;

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
                throw new IllegalArgumentException() ;
        }
        imageViewNewBadge .setVisibility(View.VISIBLE);
        imageViewNewDress.setVisibility(View.VISIBLE);

    }
}
