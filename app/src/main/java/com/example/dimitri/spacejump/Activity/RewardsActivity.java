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

import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

public class RewardsActivity extends Activity {

    TextView textViewRewards ;
    Button buttonPreviousActivity;
    Button[] buttonsBadges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_rewards);

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

        if (mapAvailable >= 1)
        {
            buttonsBadges[0] = findViewById(R.id.buttonBadgeBlue);
            buttonsBadges[0].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    displayRewards(R.string.reward0);
                }
            });
            buttonsBadges[0].setVisibility(View.VISIBLE);
        }
        if (mapAvailable >= 2)
        {
            buttonsBadges[1] = findViewById(R.id.buttonBadgeBeige);
            buttonsBadges[1].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    displayRewards(R.string.reward1);
                }
            });
            textViewRewards.setVisibility(View.VISIBLE);
        }
        if (mapAvailable >= 3)
        {
            buttonsBadges[2] = findViewById(R.id.buttonBadgePink);
            buttonsBadges[2].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    displayRewards(R.string.reward2);
                }
            });

            buttonsBadges[2].setVisibility(View.VISIBLE);
        }
        if (mapAvailable >= 4)
        {
            buttonsBadges[3] = findViewById(R.id.buttonBadgeYellow);
            buttonsBadges[3].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    displayRewards(R.string.reward3);
                }
            });
            buttonsBadges[3].setVisibility(View.VISIBLE);
        }
        if (mapAvailable >= 5)
        {
            buttonsBadges[4] = findViewById(R.id.buttonBadgeGreen);
            buttonsBadges[4].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    displayRewards(R.string.reward4);
                }
            });
            buttonsBadges[4].setVisibility(View.VISIBLE);
        }


    }

    private void displayRewards(int text)
    {
        textViewRewards.setText(text);
        textViewRewards.setVisibility(View.VISIBLE);

    }

}
