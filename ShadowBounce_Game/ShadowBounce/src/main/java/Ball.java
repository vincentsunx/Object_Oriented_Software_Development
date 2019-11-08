import bagel.Window;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Program name: Ball.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program processes information about ball(box, direction, check whether out of screen etc.)
 * Ball class inherit Gameobject
 * take reference by  Sample solution for SWEN20003 Object Oriented Software Development (author: Eleanor McMurtry)
 */
public class Ball extends GameObject{
    private Vector2 velocity;
    /* constant variable GRAVITY and BALL_SPEED for ball */
    private static final double GRAVITY = 0.15;
    private static final double BALL_SPEED = 10.0;

    /**
     * constructor for Ball class
     * @param position position for ball
     * @param direction moving direction for ball
     * @param imageSRC image source path as string.
     */
    public Ball(Point position, Vector2 direction, String imageSRC) {
        /* inherit from parent class */
        super(position, imageSRC);
        velocity = direction.mul(BALL_SPEED);
    }

    /**
     * set a velocity (Vector2)
     * @param velocity a new velocity which is a vector
     */
    public void setVelocity(Vector2 velocity){
        this.velocity = velocity;
    }

    /**
     * get a velocity of ball
     * @return a velocity of ball
     */
    public Vector2 getVelocity(){
        return this.velocity;
    }

    /**
     * check whether the ball out the screen
     * @return true ball out of screen, false not.
     */
    public boolean outOfScreen() {
        return super.getBox().top() > Window.getHeight();
    }

    /**
     * update box as ball's position and change direction when ball reach to border
     * render ball
     */
    public void update() {
        velocity = velocity.add(Vector2.down.mul(GRAVITY));
        super.move(velocity);
         if (super.getBox().left() < 0 || super.getBox().right() > Window.getWidth()) {
             velocity = new Vector2(-velocity.x, velocity.y);
         }
         super.render();
        }
}
