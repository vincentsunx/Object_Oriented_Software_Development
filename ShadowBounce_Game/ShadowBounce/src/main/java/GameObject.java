import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import bagel.util.Vector2;

/**
 * Program name: GameObject.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program is parent class for 4 child class which have common attributes and methods.
 * take reference by Sample solution for SWEN20003 Object Oriented Software Development (author: Eleanor McMurtry)
 */
public abstract class GameObject {
    private Image image;
    private Rectangle box;
    private Point position;

    /**
     * constructor for GameObject.
     * @param position position for game object (sub class)
     * @param imageSrc image source path for game object (sub class)
     */
    public GameObject(Point position, String imageSrc){
        this.position = position;
        this.image = new Image(imageSrc);
        this.box = image.getBoundingBoxAt(position);
    }

    /**
     * get bounding box for a game object
     * @return a bounding box
     */
    public Rectangle getBox() {
        return box;
    }

    /**
     * get position for a game object
     * @return a position for a game object
     */
    public Point getPosition(){
        return position;
    }

    /**
     * check collision between two objects
     * @param other game object(sub class)
     * @return boolean true for intersected and false for not.
     */
    public boolean intersects(GameObject other) {
        return box.intersects(other.box);
    }

    /**
     * move the object to target direction
     * @param target destination
     */
    public void move(Vector2 target) {
        box.moveTo(box.topLeft().asVector().add(target).asPoint());
    }

    /**
     * render the game object.
     */
    public void render() {
        image.draw(box.centre().x, box.centre().y);
    }

    /**
     * abstract class update
     */
    public abstract void update();
}
