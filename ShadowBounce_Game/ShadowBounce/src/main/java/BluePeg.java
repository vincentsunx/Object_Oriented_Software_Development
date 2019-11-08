import bagel.util.Point;

/**
 * Program name: BluePeg.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates and render blue pegs.
 */
public class BluePeg extends Peg{
    /* image source as String type */
    public  String pic;

    /**
     * constructor for BluePeg class
     * @param position position for blue peg.
     * @param pic image source path as string.
     */
    public BluePeg(Point position, String pic){
        /* inherit from Peg class */
        super(position, pic);
        this.pic = pic;
    }

    /**
     * update the blue peg.
     */
    public void update() {
        super.render();
    }

    /**
     * get image source path as string.
     * @return image source as string
     */
    public String getPic() {
        return pic;
    }

    /**
     * get position of blue peg
     * @return position for blue peg
     */
    public Point getPosition(){
        return super.getPosition();
    }
}
