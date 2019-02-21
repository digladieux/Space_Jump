package com.example.dimitri.spacejump.Constants;

public class ConstantsBatObstacle {

    public static final int OBSTACLE_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 117;
    public static final int OBSTACLE_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 175;
    //public static final int OBSTACLE_RIGHT = Constants.SCREEN_WIDTH + OBSTACLE_WIDTH;
    //public static final int OBSTACLE_LEFT = Constants.SCREEN_WIDTH;
    private static final int OBSTACLE_GAP_TOP = 400;
    public static final int OBSTACLE_TOP = OBSTACLE_GAP_TOP;
    public static final int OBSTACLE_BOTTOM = OBSTACLE_HEIGHT + OBSTACLE_GAP_TOP;

}
