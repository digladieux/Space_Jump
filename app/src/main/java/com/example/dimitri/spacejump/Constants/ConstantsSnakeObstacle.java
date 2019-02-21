package com.example.dimitri.spacejump.Constants;

public class ConstantsSnakeObstacle {

    public static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 294;
    public static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 106;
    //public static final int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    public static final int OBSTACLE_TOP = Constants.SCREEN_HEIGHT - OBSTACLE_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2;
    //public static int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    public static final int OBSTACLE_BOTTOM = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT + 2;

}
