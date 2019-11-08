import bagel.util.Point;

/**
 * Program name: RedPeg.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates and render red pegs.
 */

public class RedPeg extends Peg{
    /* image source as String type */
    private String pic;

    /**
     * constructor for RedPeg class
     * @param position position for red peg.
     * @param pic image source path as string.
     */
    public RedPeg(Point position, String pic){
        /* inherit from Peg class */
        super(position, pic);
        this.pic = pic;
    }

    /**
     * update the red peg.
     */
    public void update() {
        super.render();
    }
}
