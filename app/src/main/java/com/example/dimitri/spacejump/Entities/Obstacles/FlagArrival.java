package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsFlagArrival;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.StaticMethod.createPicture;

public class FlagArrival extends Obstacle{

    public FlagArrival(Context context, double areaLeft) {
        super(createPicture(context, R.drawable.flag, ConstantsFlagArrival.OBSTACLE_WIDTH, ConstantsFlagArrival.OBSTACLE_HEIGHT),(int)(Constants.SCREEN_WIDTH * areaLeft), (int)( Constants.SCREEN_WIDTH * areaLeft + ConstantsFlagArrival.OBSTACLE_WIDTH), ConstantsFlagArrival.OBSTACLE_TOP, ConstantsFlagArrival.OBSTACLE_BOTTOM);
    }


}

