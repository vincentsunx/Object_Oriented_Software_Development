import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Program name: FireBall.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates fire ball position, direction and render it.
 */
public class FireBall extends Ball{

    /**
     * FireBall class inherit Ball
     * @param position position for fire ball
     * @param direction moving direction for fire ball.
     */
    public FireBall(Point position, Vector2 direction){
        /* inherit from parent class */
        super(position, direction, "res/fireball.png");
    }

    /**
     * update method inherit parent's (Ball class) update method
     */
    public void update(){
        super.update();
    }
}
