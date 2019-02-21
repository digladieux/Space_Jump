package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentDress;
import static com.example.dimitri.spacejump.Constants.ConstantsGame.mapAvailable;

public class DressActivity extends Activity {

    Button buttonPreviousActivity ;
    Button buttonNextDress;
    Button buttonPreviousDress;
    ImageView imageViewCurrentDress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_dress);

        imageViewCurrentDress = findViewById(R.id.imageCurrentDress) ;
        displayCurrentDress();

        buttonPreviousActivity = findViewById(R.id.buttonReturn) ;
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DressActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonNextDress = findViewById(R.id.buttonRightArrow);
        buttonNextDress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if ( (currentDress == 4) || (mapAvailable <= currentDress))
                {
                    currentDress = 0 ;
                }
                else
                {
                    currentDress ++ ;
                }
                displayCurrentDress();
            }
        });
        buttonPreviousDress = findViewById(R.id.buttonLeftArrow);
        buttonPreviousDress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (currentDress == 0)
                {
                    currentDress = mapAvailable;
                }
                else
                {
                    currentDress -- ;
                }
                displayCurrentDress();
            }
        });

    }

    private void displayCurrentDress()
    {
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
                    throw new IllegalArgumentException() ;
        }
    }

}


