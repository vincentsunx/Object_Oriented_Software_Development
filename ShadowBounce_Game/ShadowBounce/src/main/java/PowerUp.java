import bagel.Window;
import bagel.util.Point;
import bagel.util.Vector2;
import java.lang.Math;
import java.util.Random;

/**
 * Program name: PowerUp.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program sets random direction for powerup and render and update it.
 * PowerUp class inherit GameObject class
 */
public class PowerUp extends GameObject{
    Random rand = new Random();
    private Vector2 velocity;
    private Vector2 direction;
    private Point tempDest;
    public static final double POWERUP_SPEED = 3.0;
    public static final double DEST_DISTANCE = 5.0;

    /**
     * constructor for powerup.
     * @param position position for powerup.
     */
    public PowerUp(Point position){
        /* inherit from parent class */
        super(position,"res/powerup.png");
        /* initial poweup position */
        setDestination();
        initial();
    }

    /**
     * initial powerup direction and speed
     */
    public void initial(){
        direction = tempDest.asVector().sub(super.getBox().centre().asVector());
        velocity = direction.normalised().mul(POWERUP_SPEED);
    }

    /**
     * set a random initial position for powerup
     */
    public void setDestination(){
        tempDest = new Point(Window.getWidth() * rand.nextDouble(), Window.getHeight() * rand.nextDouble());
    }

    /**
     * update position of powerup and render it.
     */
    public void update(){
        super.move(velocity);
        if((Math.sqrt(((tempDest.x - super.getBox().centre().x) * (tempDest.x - super.getBox().centre().x)) +
                ((tempDest.y - super.getBox().centre().y) * (tempDest.y - super.getBox().centre().y))))
                <= DEST_DISTANCE){
            setDestination();
            initial();
        }
        super.render();
    }
}



