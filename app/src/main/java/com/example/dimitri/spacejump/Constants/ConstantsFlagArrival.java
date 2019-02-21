package com.example.dimitri.spacejump.Constants;

public class ConstantsFlagArrival {

    public static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 639;
    public static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 150;
    //public static final int OBSTACLE_RIGHT = OBSTACLE_WIDTH;
    public static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - OBSTACLE_HEIGHT;
    //public static final int OBSTACLE_LEFT = 0;
    public static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT;

}
