package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dimitri.spacejump.R;

public class GameActivity  extends Activity {

    Button buttonPreviousActivity ;
    TextView textAttempt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        buttonPreviousActivity = findViewById(R.id.buttonReturn) ;
        buttonPreviousActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


    }
}
