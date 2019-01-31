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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dimitri.spacejump.R;

public class DressActivity extends Activity {

    Button buttonPreviousActivity ;
    Button buttonNextDress;
    Button buttonPreviousDress;
    ImageView imageViewCurrentDress;

    int currentDress ; /* todo : var static dans alien apres */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress);

        currentDress = 0 ;

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
                if (currentDress == 4)
                {
                    currentDress = 0 ;
                }
                else
                {
                    currentDress ++ ; /* todo : si = map available => 0 */
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
                    currentDress = 4 ;
                }
                else
                {
                    currentDress -- ; /* todo : si = 0 => map available  */
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


