package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dimitri.spacejump.R;

public class WinActivity extends Activity {
    Button buttonRetry;
    Button buttonReturnMap;
    ImageView imageViewNewRewards;
    ImageView imageViewNewDress;
    ImageView imageViewNewBadge;
    TextView textViewNewRewards;
    TextView textViewVictory;
    int currentDress ;/* todo : var static dans alien apres */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        currentDress = 0 ;
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
        if (currentDress == 0) /* todo : map available = current map */
        {
            displayNewRewards();
        }

    }
    private void displayNewRewards()
    {
        imageViewNewRewards = findViewById(R.id.imageNewRewards) ;
        imageViewNewDress = findViewById(R.id.imageNewDress) ;
        imageViewNewBadge = findViewById(R.id.imageNewBadge) ;
        textViewNewRewards = findViewById(R.id.textNewRewards) ;
        textViewNewRewards .setVisibility(View.VISIBLE);
        imageViewNewRewards.setVisibility(View.VISIBLE);

        switch (currentDress)
        {
            case 0:
                imageViewNewDress.setImageResource(R.drawable.alienblue);
                imageViewNewBadge.setImageResource(R.drawable.alienblue_badge2);
                break ;

            case 1:
                imageViewNewDress.setImageResource(R.drawable.alienbeige);
                imageViewNewBadge.setImageResource(R.drawable.alienbeige_badge2);
                break ;

            case 2:
                imageViewNewBadge.setImageResource(R.drawable.alienpink_badge2);
                imageViewNewDress.setImageResource(R.drawable.alienpink);
                break ;

            case 3:
                imageViewNewBadge.setImageResource(R.drawable.alienyellow_badge2);
                imageViewNewDress.setImageResource(R.drawable.alienyellow);
                break ;

            case 4:
                imageViewNewBadge.setImageResource(R.drawable.aliengreen_badge2);
                imageViewNewDress.setImageResource(R.drawable.aliengreen);
                break ;
            default:
                throw new IllegalArgumentException() ;
        }
    }
}
