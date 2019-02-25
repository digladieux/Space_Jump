package com.example.dimitri.spacejump.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.dimitri.spacejump.Entities.Animation.Animation;
import com.example.dimitri.spacejump.Entities.Animation.AnimationManager;
import com.example.dimitri.spacejump.Constants.PlayerConstants;
import com.example.dimitri.spacejump.Exception.InvalidCurrentDress;
import com.example.dimitri.spacejump.R;

import static com.example.dimitri.spacejump.Constants.ConstantsGame.currentDress;
import static com.example.dimitri.spacejump.StaticMethod.createPicture;

/**
 * Classe representant le joueur
 */
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
     * Contructeur de la classe AlienSprite
     * @param context Activity actuellement en cours
     * @param rectangle Hitbox du personnage
     */
    public AlienSprite(Context context, Rect rectangle) throws InvalidCurrentDress {
        this.rectangle = rectangle ;
        this.velocity = PlayerConstants.VELOCITY;
        this.initSpeed = PlayerConstants.SPEED;
        this.currentSpeed = initSpeed;

        initialisationAnimationSprite(context);
    }

    /**
     * Fonction initialisant toutes les images du personnage suivant sa tenue. Une tenue possede 1 animations de marche, 1 image de saut et une de gravite.
     * Une fois que toutes les animations sont bien dimensionnees, elles sont creees et integrees a animationManager.
     * @param context Activity actuellement en cours
     */
    private void initialisationAnimationSprite(Context context) throws InvalidCurrentDress {
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
                    throw new InvalidCurrentDress() ;
        }

        Animation animJump = new Animation(new Bitmap[]{scaledJump}, 2);
        Animation animGravity = new Animation(new Bitmap[]{scaledGravity}, 2);
        Animation animWalkRight = new Animation(new Bitmap[]{scaledWalk1, scaledWalk2}, 0.25f);
        this.animationManager = new AnimationManager(new Animation[]{animWalkRight, animJump, animGravity});
    }

    /**
     * Getteur sur la zone occupe par le persionnage
     * @return Hitbox du personnage
     */
    public Rect getRectangle() {
        return rectangle;
    }


    /**
     * Getteur sur la vitesse actuelle du personnage
     * @return Vitesse actuelle du personnage
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }


    /**
     * Remet a la vitesse actuelle a la vitesse intiale
     */
    public void resetCurrentSpeed()
    {
        this.currentSpeed = this.initSpeed;
    }

    /**
     * Increment la vitesse actuelle de la valeur de l'accelation du personnage
     */
    public void incrementCurrentSpeed()
    {
        this.currentSpeed += velocity;
    }

    /**
     * Dessine le personnage a l'ecran
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     */
    public void draw(Canvas canvas) {
        animationManager.draw(canvas, rectangle);
    }

    /**
     * Mise a jour de la position du personnage, ainsi que son animation
     * @param point Coordonnee du centre de la hitbox du personnage
     */
    public void update(Point point)
    {
        float oldTop = rectangle.top;
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,point.x + rectangle.width()/2, point.y + rectangle.height()/2);
        animationManager.playAnim(giveStateSprite(oldTop));
        animationManager.update();
    }

    /**
     * Donne l'etat actuel du personnage (en saut, marche, retombe au sol)
     * @param oldTop Coordonnee y du centre de la hitbox du personnage avant le update
     * @return L'animation a faire pour le personnage
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


