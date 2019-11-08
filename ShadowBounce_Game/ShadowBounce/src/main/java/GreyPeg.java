import bagel.util.Point;

/**
 * Program name: GreyPeg.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates and render grey pegs.
 */
public class GreyPeg extends Peg{

    /**
     * constructor for Greypeg class
     * @param position position for grey peg.
     * @param pic image source path as string.
     */
    public GreyPeg(Point position, String pic){
        /* inherit from Peg class */
        super(position, pic);
    }

    /**
     * update the grey peg.
     */
    public void update() {
        super.render();
    }
}
