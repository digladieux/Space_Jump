package com.example.dimitri.spacejump.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dimitri.spacejump.R;

public class MainActivity extends Activity {

    Button buttonStart;
    Button buttonRewards;
    Button buttonDress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
        buttonRewards = findViewById(R.id.buttonRewards);
        buttonRewards.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RewardsActivity.class);
                startActivity(intent);
            }
        });

        buttonDress = findViewById(R.id.buttonDress);
        buttonDress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DressActivity.class);
                startActivity(intent);
            }
        });
    }
}
