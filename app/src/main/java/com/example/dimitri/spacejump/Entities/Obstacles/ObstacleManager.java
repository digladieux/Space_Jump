package com.example.dimitri.spacejump.Entities.Obstacles;

import android.content.Context;
import android.graphics.Canvas;

import com.example.dimitri.spacejump.Constants.Constants;
import com.example.dimitri.spacejump.Entities.AlienSprite;
import com.example.dimitri.spacejump.Exception.InvalidNumberMap;
import com.example.dimitri.spacejump.Entities.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conteneur de la liste de tous les obstacles
 */
public class ObstacleManager {

    /**
     * Logger pour afficher les differents evenements systeme lors de l'execution de l'application
     */
    private static final Logger logger = Logger.getLogger("GameActivityObstacle") ;

    /**
     * Liste de tous les obstacles
     */
    private ArrayList <Obstacle> obstacles;

    /**
     * Constructeur de la liste des obstacles
     * @param context Activity actuellement en cours
     */
    public ObstacleManager(Context context)
    {
        try {
            obstacles = Map.initialisationMap(context);
        } catch (InvalidNumberMap invalidNumberMap) {
            logger.log(Level.SEVERE, "MapNotFound");
        }

    }

    /**
     * Getteur sur la taille du vecteur d'obstacles
     * @return La taille du vecteur d'obstacles
     */
    public int size() {
        return obstacles.size();
    }


    /**
     * Methode qui indique si la hitbox d'un personnage est entree en collision avec la hitbox de l'obstacle
     * @param player Le personnage qu'on veut savoir si il est en collision
     * @return 0 si la collision n'a pas eu lieu, - 1 si le personnage est arrive a la fin du niveau, -2 si le personnage a rencontre une piece, 1 si le personnage a perdu
     */
    public int playerCollide(AlienSprite player)
    {
        int collision = 0;
        int codeCollision ;
        for (Obstacle ob : obstacles) {
            if ( !(ob instanceof GroundObstacle)) {
                codeCollision = ob.playerCollide(player);

                if (codeCollision == -1) {
                    collision = -1;
                    break;
                } else if (codeCollision == 1) {
                    collision = 1;
                    break;
                } else if (codeCollision == -2) {
                    obstacles.remove(ob) ;
                    collision = -2;
                    break;
                }
            }
        }
        return collision;
    }

    /**
     * Met a jour la position de tous les obstacles, et les supprime si le personnage les a passe
     */
    public void update()
    {
        Iterator <Obstacle> it = obstacles.iterator();
        while (it.hasNext())
        {
            Obstacle ob = it.next();
            if (ob instanceof BatObstacle) { /* La vitesse des obstacles est differentes suivant si les personnages sont aerien ou terrien  */
                ob.decrementX(Constants.SCREEN_WIDTH/40);
            } else {
                ob.decrementX(Constants.SCREEN_WIDTH/60);
            }
            ob.update();
            if (ob.getRectangle().right <= 0) {
                it.remove();
            }
        }
    }

    /**
     * Dessine tous les personnages a l'ecran
     * @param canvas Zone de dessin ou on souhaite faire apparaitre l'image
     */
    public void draw(Canvas canvas)
    {
        for (Obstacle ob : obstacles)
        {
            ob.draw(canvas);
        }
    }
}
