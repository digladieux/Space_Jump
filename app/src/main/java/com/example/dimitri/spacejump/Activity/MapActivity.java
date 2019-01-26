package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dimitri.spacejump.R;

public class MapActivity extends Activity {

    Button buttonPreviousActivity;
    Button[] buttonsMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                startActivity(intent);
            }
        });

        buttonsMaps[1] = findViewById(R.id.buttonMoon);
        buttonsMaps[1].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        buttonsMaps[2] = findViewById(R.id.buttonMars);
        buttonsMaps[2].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        buttonsMaps[3] = findViewById(R.id.buttonSun);
        buttonsMaps[3].setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
