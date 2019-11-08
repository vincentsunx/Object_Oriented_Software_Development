import bagel.Window;
import bagel.util.Point;
import bagel.util.Vector2;

/**
 * Program name: Bucket.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates bucket initial position and changes direction when it reaches border.
 * bucket class inherit GameObject class
 */
public class Bucket extends GameObject {
    private Vector2 velocity;
    private Vector2 direction;
    private Point dest;
    private static final double BUCKET_SPEED = 4.0;
    /* the left most point at the path of bucket (using for determine direction of bucket */
    private static final Point BUCKET_DEST = new Point(0, 744);


    /**
     * constructor for bucket
     * @param position position for bucket
     */
    public Bucket(Point position) {
        /* inherit from parent class */
        super(position, "res/bucket.png");
        this.dest = BUCKET_DEST;
        /* initial bucket position */
        initial();
    }

    /**
     * initial bucket direction and speed
     */
    public void initial(){
        direction = dest.asVector().sub(super.getBox().centre().asVector());
        velocity = direction.normalised().mul(BUCKET_SPEED);
    }

    /**
     * update position of bucket, change direction when it reaches border and render it.
     */
    public void update(){
        super.move(velocity);
        if (super.getBox().left() < 0 || super.getBox().right() > Window.getWidth()) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        super.render();
    }
}
