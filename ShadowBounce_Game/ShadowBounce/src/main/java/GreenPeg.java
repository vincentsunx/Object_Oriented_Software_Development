import bagel.util.Point;

/**
 * Program name: GreenPeg.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates and render green pegs.
 */
public class GreenPeg extends Peg{
    /* image source as String type */
    public  String pic;

    /**
     * constructor for GreenPeg class
     * @param position position for green peg.
     * @param pic image source path as string.
     */
    public GreenPeg(Point position, String pic){
        /* inherit from Peg class */
        super(position, pic);
        this.pic = pic;
    }

    /**
     * get image source path as string.
     * @return image source as string
     */
    public String getPic() {
        return pic;
    }

    /**
     * update the green peg.
     */
    public void update() {
        super.render();
    }
}
