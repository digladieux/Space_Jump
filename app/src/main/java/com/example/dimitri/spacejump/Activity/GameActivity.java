package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.Scene;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dimitri.spacejump.R;

public class GameActivity  extends Activity {

    private Button buttonPreviousActivity ;
    private ImageView imageAlienSprite ;
    private boolean running ;
    private double MAX_FPS = 60 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        running = true ;
        imageAlienSprite = findViewById(R.id.imageAlienSprite) ;
        buttonPreviousActivity = findViewById(R.id.buttonReturn) ;
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
/*        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame( getResources().getDrawable(R.drawable.alienbeige_walk1),100);
        animationDrawable.addFrame( getResources().getDrawable(R.drawable.alienbeige_walk2),100);
        imageAlienSprite.setImageDrawable(animationDrawable);
        animationDrawable.start();
*/

    }

}
