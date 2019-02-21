package com.example.dimitri.spacejump.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.dimitri.spacejump.Animation.Animation;
import com.example.dimitri.spacejump.Animation.AnimationManager;
import com.example.dimitri.spacejump.Constants.PlayerConstants;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentDress;
import static com.example.dimitri.spacejump.StaticMethod.createPicture;

public class AlienSprite {

    /**
     * Attribut representant la hitbox du personnage (collision) sur lequel on applique les animations
     */
    private final Rect rectangle;
    /**
     * Gestionnaire d'animation de l'alien
     */
    private AnimationManager animationManager;
    /**
     * Attribut determinant la vitesse du personnage
     */
    private final double velocity;
    /**
     * Vitesse initiale du personnage
     */
    private final double initSpeed;
    /**
     * Vitesse actuelle du personnage
     */
    private double currentSpeed;

    /**
     * Contructeur du personnage, on initialise tous les attributs a partir des constantes du fichier Constants.java, et on ajoute les animations
     * @param context Contexte actuel du programme
     * @param rectangle Zone occupee par le personnage
     */
    public AlienSprite(Context context, Rect rectangle)
    {
        this.rectangle = rectangle ;
        this.velocity = PlayerConstants.VELOCITY;
        this.initSpeed = PlayerConstants.SPEED;
        this.currentSpeed = initSpeed;

        initialisationAnimationSprite(context);
    }

    /**
     * Fonction initialisant toutes les animations du personnage, pour chacunes des tenues. Chaque tenue possede 2 animations de marche, une de saut et une de collision.
     * Une fois que toutes les animations sont bien dimensionnees, elles sont creees et integrees a animationManager.
     * @param context Context actuel du programme
     */
    private void initialisationAnimationSprite(Context context)
    {
        Bitmap scaledWalk1, scaledWalk2, scaledJump, scaledGravity ;
        switch(currentDress)
        {
            case 0 :
                scaledWalk1 = createPicture(context, R.drawable.alienblue_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledWalk2 = createPicture(context, R.drawable.alienblue_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledJump= createPicture(context, R.drawable.alienblue_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledGravity = createPicture(context, R.drawable.alienblue_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                break ;
            case 1 :
                scaledWalk1 = createPicture(context, R.drawable.alienbeige_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledWalk2 = createPicture(context, R.drawable.alienbeige_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledJump= createPicture(context, R.drawable.alienbeige_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledGravity= createPicture(context, R.drawable.alienbeige_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                break ;
            case 2 :
                scaledWalk1 = createPicture(context, R.drawable.alienpink_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledWalk2 = createPicture(context, R.drawable.alienpink_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledJump= createPicture(context, R.drawable.alienpink_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledGravity= createPicture(context, R.drawable.alienpink_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                break ;
            case 3 :
                scaledWalk1 = createPicture(context, R.drawable.alienyellow_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledWalk2 = createPicture(context, R.drawable.alienyellow_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledJump= createPicture(context, R.drawable.alienyellow_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledGravity= createPicture(context, R.drawable.alienyellow_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                break ;
            case 4 :
                scaledWalk1 = createPicture(context, R.drawable.aliengreen_walk1, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledWalk2 = createPicture(context, R.drawable.aliengreen_walk2, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledJump= createPicture(context, R.drawable.aliengreen_jump, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                scaledGravity= createPicture(context, R.drawable.aliengreen_hurt, PlayerConstants.PLAYER_WIDTH, PlayerConstants.PLAYER_HEIGHT);
                break ;
                default:
                    throw new IllegalArgumentException() ;
        }

        Animation animJump = new Animation(new Bitmap[]{scaledJump}, 2);
        Animation animGravity = new Animation(new Bitmap[]{scaledGravity}, 2);
        Animation animWalkRight = new Animation(new Bitmap[]{scaledWalk1, scaledWalk2}, 0.25f);
        this.animationManager = new AnimationManager(new Animation[]{animWalkRight, animJump, animGravity});
    }

    /**
     *
     * @return
     */
    public Rect getRectangle() {
        return rectangle;
    }


    /**
     * Fonction permettant de recuperer les coordonnees du rectangle du personnage
     * @return Rectangle representant l'objet
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }


    /**
     *
     */
    public void resetCurrentSpeed()
    {
        this.currentSpeed = this.initSpeed;
    }

    /**
     *
     */
    public void incrementCurrentSpeed()
    {
        this.currentSpeed += velocity;
    }

    /**
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rectangle);
    }

    /**
     *
     * @param point
     */
    public void update(Point point)
    {
        float oldTop = rectangle.top; /* est ton aller a gauche ou pas */
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        animationManager.playAnim(giveStateSprite(oldTop));
        animationManager.update();
    }

    /**
     *
     * @param oldTop
     * @return
     */
    private int giveStateSprite(float oldTop)
    {
        int state = 0 ;
        if (rectangle.top - oldTop > 5) /* On aurait pu mettre 0, mais sinon il y aurait eu trop d'anim, on prefere laisser un peu d'espace entre une anim donc 5 pixels*/ {
            state = 2; /* allez a gauche */
        } else if (rectangle.top - oldTop < -5) {
            state = 1; /* allez a droite */
        }
        return state ;
    }
}


