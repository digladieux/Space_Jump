package com.example.dimitri.spacejump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Constants.ConstantsGame;
import com.example.dimitri.spacejump.Constants.PlayerConstants;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.Entities.Obstacles.BatObstacle;
import com.example.dimitri.spacejump.Entities.Obstacles.FlagArrival;
import com.example.dimitri.spacejump.Entities.Obstacles.Obstacle;
import com.example.dimitri.spacejump.Entities.Obstacles.ObstacleManager;
import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void AlienSprite() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Constants.SCREEN_WIDTH = 100;
        Constants.SCREEN_HEIGHT = 10;

        Rect rect = new Rect(5, 10, 15, 20);
        AlienSprite alienSprite = null;
        try {
            alienSprite = new AlienSprite(appContext, rect);
        } catch (InvalidCurrentDress invalidCurrentDress) {
            assertEquals(0, 1);
        }
        assertEquals(alienSprite.getRectangle(), rect);
        assertEquals(alienSprite.getCurrentSpeed(), PlayerConstants.SPEED, 0.1);

        alienSprite.incrementCurrentSpeed();

        assertEquals(alienSprite.getCurrentSpeed(), PlayerConstants.SPEED + PlayerConstants.VELOCITY, 0.1);

        alienSprite.resetCurrentSpeed();
        assertEquals(alienSprite.getCurrentSpeed(), PlayerConstants.SPEED, 0.1);

        alienSprite.update(new Point(100, 100));
        alienSprite.draw(new Canvas());
    }

    @Test
    public void Obstacle() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Constants.SCREEN_WIDTH = 100;
        Constants.SCREEN_HEIGHT = 100;

        Obstacle obstacle1 = BatObstacle.initialisationBatObstacle(appContext, 1) ;
        Obstacle obstacle2 = BatObstacle.initialisationBatObstacle(appContext, 3) ;

        Rect rect = new Rect(0, 0, 100, 100);
        AlienSprite alienSprite = null;
        try {
            alienSprite = new AlienSprite(appContext, rect);
        } catch (InvalidCurrentDress invalidCurrentDress) {
            assertEquals(0,1);
        }

        assertEquals(1 , obstacle1.playerCollide(alienSprite));
        assertEquals(0 , obstacle2.playerCollide(alienSprite));

        Obstacle flag = new FlagArrival(appContext, 0.5) ;
        assertEquals(-1 , flag.playerCollide(alienSprite));

    }

    @Test
    public void ObstacleManager() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Constants.SCREEN_WIDTH = 100;
        Constants.SCREEN_HEIGHT = 100;
        ConstantsGame.currentMap = 0;
        ObstacleManager obstacleManager = new ObstacleManager(appContext);

        assertEquals(obstacleManager.size(), 46);

        for (int i = 0; i < 30; i++) {
            obstacleManager.update();
        }
        assertEquals(obstacleManager.size(), 42);

        Rect rect = new Rect(0, 0, 100, 100);
        AlienSprite alienSprite = null;
        try {
            alienSprite = new AlienSprite(appContext, rect);
        } catch (InvalidCurrentDress invalidCurrentDress) {
            assertEquals(0,1);
        }

        assertEquals(obstacleManager.playerCollide(alienSprite), 1);
    }
/*
    @Test
    public void Animation() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        Constants.SCREEN_WIDTH = 100;
        Constants.SCREEN_HEIGHT = 100;

        Bitmap scaledWalk1 = createPicture(appContext, R.drawable.aliengreen_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
        Bitmap scaledWalk2 = createPicture(appContext, R.drawable.aliengreen_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);

        Animation animation = new Animation(new Bitmap[]{scaledWalk1, scaledWalk2}, 2);
        AnimationManager animationManager = new AnimationManager(new Animation[]{animation}) ;
        animationManager.playAnim(0);
    }*/

}
