package com.example.dimitri.spacejump.Constants;

public class PlayerConstants {
    //public static final double VELOCITY = (Constants.SCREEN_HEIGHT / 720) * 0.0002;
    //public static final double SPEED = (Constants.SCREEN_HEIGHT / 720) * -0.08;
    public static final double VELOCITY = (Constants.SCREEN_HEIGHT / 720) * 2;
    public static final double SPEED = (Constants.SCREEN_HEIGHT / 720) * -50;
    public static final int PLAYER_HEIGHT = (Constants.SCREEN_HEIGHT / 1080) * 186;
    public static final int PLAYER_WIDTH = (Constants.SCREEN_WIDTH / 1794) * 132;
    public static final int INIT_POSITION_X = (Constants.SCREEN_WIDTH / 1794) * 200;
    public static final int INIT_POSITION_Y = Constants.SCREEN_HEIGHT - ConstantsGroundObstacle.OBSTACLE_HEIGHT - PLAYER_HEIGHT / 2;
    public static final int LEFT_PLAYER = (Constants.SCREEN_WIDTH / 1794) * 100;
    public static final int TOP_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT;
    public static final int RIGHT_PLAYER = LEFT_PLAYER + PLAYER_WIDTH;
    public static final int BOTTOM_PLAYER = ConstantsGroundObstacle.OBSTACLE_HEIGHT + PLAYER_HEIGHT;
}


