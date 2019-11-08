import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Program name: NormalBall.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates normal ball position, direction and render it.
 * NormalBall class inherit Ball
 */
public class NormalBall extends Ball{

    /**
     * constructor for NormalBall class
     * @param position position for normal ball
     * @param direction moving direction for normal ball.
     */
    public NormalBall(Point position, Vector2 direction){
        /* inherit from parent class */
        super(position, direction, "res/ball.png");
    }

    /**
     *  update method inherit parent's(Ball class) update method
     */
    public void update(){
        super.update();
    }
}
