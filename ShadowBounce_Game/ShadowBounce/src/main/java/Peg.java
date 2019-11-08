import bagel.util.Point;

/**
 * Program name: Peg.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program updates and render peg.
 * Peg class inherit Gameobject
 * take reference by  Sample solution for SWEN20003 Object Oriented Software Development (author: Eleanor McMurtry)
 */
public class Peg extends GameObject{

    /**
     * constructor for peg
     * @param position position for peg.
     * @param imageSRC image source path as string.
     */
    public Peg(Point position, String imageSRC){
        /* inherit from GameObject */
        super(position, imageSRC);
    }

    /**
     * update and render the peg.
     */
    public void update() {
        super.render();
    }

    /**
     * get peg position
     * @return peg position
     */
    public Point getPosition(){
        return super.getPosition();
    }
}
