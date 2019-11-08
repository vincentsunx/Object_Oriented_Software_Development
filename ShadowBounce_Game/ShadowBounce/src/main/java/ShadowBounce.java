import bagel.*;
import bagel.util.Point;
import bagel.util.Vector2;
import bagel.util.Side;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Program name: ShadowBounce.java
 * This program is part of project solution.
 * Write by Dechao Sun (student id: 980546).
 * This program processes all information and run the game.
 * the world of the game.
 */
public class ShadowBounce extends AbstractGame {
    /* arraylist of balls and pegs */
    private ArrayList<Ball> balls;
    private ArrayList<Peg> pegs;
    /* initial position of ball and bucket */
    private static final Point BALL_POSITION = new Point(512, 32);
    private static final Point BUCKET_POSITION = new Point(512, 744);
    private static final double BURST_DISTANCE = 70.0;
    private PowerUp powerup;
    private Bucket bucket;
    private Point tempStart;
    /* array of string to store game board for each level */
    private String[] filename = {"res/0.csv","res/1.csv","res/2.csv","res/3.csv","res/4.csv"};
    private int level;
    private int numBall;
    private int numBluePeg;
    private int numRedPeg;
    /* image source name */
    private static String imageBlue = "res/peg.png";
    private static String imageBlueH = "res/horizontal-peg.png";
    private static String imageBlueV = "res/vertical-peg.png";

    /**
     * constructor for ShadowBounce class.
     */
    public ShadowBounce() {
        Random random = new Random();
        this.balls = new ArrayList<>();
        this.pegs = new ArrayList<>();
        this.tempStart = new Point(Window.getWidth() * random.nextDouble(),
                Window.getHeight() * random.nextDouble());
        /* set the level becomes 1 and number of ball as 20 */
        this.level = 0;
        this.numBall = 20;
        loadPeg(filename);
        initialPeg();
        initialBucket();
    }

    /**
     *  load pegs from csv file
     * @param filename  an array of string which contain 5 source paths for boards.
     */
    public void loadPeg(String[] filename) {
        /* clean the rest balls and pegs when loads the next level board */
        balls.clear();
        pegs.clear();
        try {
            numBluePeg = 0;
            FileReader fileReader = new FileReader(filename[level]);
            BufferedReader br = new BufferedReader(fileReader);
            String text;
            Point tempPosition;
            while ((text = br.readLine()) != null) {
                /* read file and split by comma and create relative pegs adding into arraylist of peg */
                String[] item = text.split(",");
                tempPosition = new Point(Double.parseDouble(item[1]), Double.parseDouble(item[2]));
                String imageGreyV = "res/grey-vertical-peg.png";
                String imageGreyH = "res/grey-horizontal-peg.png";
                String imageGrey = "res/grey-peg.png";
                switch (item[0]) {
                    case "blue_peg":
                        pegs.add(new BluePeg(tempPosition, imageBlue));
                        numBluePeg++;
                        break;
                    case "grey_peg":
                        pegs.add(new GreyPeg(tempPosition, imageGrey));
                        break;
                    case "grey_peg_horizontal":
                        pegs.add(new GreyPeg(tempPosition, imageGreyH));
                        break;
                    case "grey_peg_vertical":
                        pegs.add(new GreyPeg(tempPosition, imageGreyV));
                        break;
                    case "blue_peg_horizontal":
                        numBluePeg++;
                        pegs.add(new BluePeg(tempPosition, imageBlueH));
                        break;
                    case "blue_peg_vertical":
                        numBluePeg++;
                        pegs.add(new BluePeg(tempPosition, imageBlueV));
                        break;
                }

            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  turn the 1/5 blue pegs to red pegs.
     */
    public void initialPeg() {
        numRedPeg = numBluePeg / 5;
        Random random = new Random();
        int i = 0;
        /* turn blue peg to red peg in different shape */
        while (i < numRedPeg) {
            int index = random.nextInt(pegs.size());
            if (pegs.get(index) instanceof BluePeg) {
                if(((BluePeg) pegs.get(index)).getPic().equals(imageBlue)){
                    String imageRed = "res/red-peg.png";
                    pegs.set(index, new RedPeg(pegs.get(index).getPosition(), imageRed));
                    i++;
                }
                else if(((BluePeg) pegs.get(index)).getPic().equals(imageBlueH)){
                    String imageRedH = "res/red-horizontal-peg.png";
                    pegs.set(index, new RedPeg(pegs.get(index).getPosition(), imageRedH));
                    i++;
                }
                else if(((BluePeg) pegs.get(index)).getPic().equals(imageBlueV)){
                    String imageRedV = "res/red-vertical-peg.png";
                    pegs.set(index, new RedPeg(pegs.get(index).getPosition(), imageRedV));
                    i++;
                }


            }
        }
        /* generate a new green peg */
        initialTurn();
    }

    /**
     * generate a new green peg.
     */
    public void initialTurn() {
        numBluePeg = numBluePeg - numRedPeg;
        int indexPeg = 0;
        String imageGreenV = "res/green-vertical-peg.png";
        String imageGreenH = "res/green-horizontal-peg.png";
        String imageGreen = "res/green-peg.png";
        /* if there is a green peg on board convert it back to blue */
        for (Peg peg : pegs) {
            if (peg instanceof GreenPeg) {
                if(((GreenPeg) pegs.get(indexPeg)).getPic().equals(imageGreen)){
                    pegs.set(indexPeg, new BluePeg(pegs.get(indexPeg).getPosition(),imageBlue));
                }
                else if(((GreenPeg) pegs.get(indexPeg)).getPic().equals(imageGreenH)){
                    pegs.set(indexPeg, new RedPeg(pegs.get(indexPeg).getPosition(),imageBlueH));
                }
                else if(((GreenPeg) pegs.get(indexPeg)).getPic().equals(imageGreenV)){
                    pegs.set(indexPeg, new RedPeg(pegs.get(indexPeg).getPosition(),imageBlueV));
                }
            }
            indexPeg++;
        }
        /* if there is no green peg on the board, choose a random blue peg to be green peg */
        if (numBluePeg > 0) {
            int j = 0;
            Random rand = new Random();
            while (j < 1) {
                int index = rand.nextInt(pegs.size());
                if (pegs.get(index) instanceof BluePeg) {
                    if(((BluePeg) pegs.get(index)).getPic().equals(imageBlue)){
                        pegs.set(index, new GreenPeg(pegs.get(index).getPosition(), imageGreen));
                        j++;
                    }
                    else if(((BluePeg) pegs.get(index)).getPic().equals(imageBlueH)){
                        pegs.set(index, new GreenPeg(pegs.get(index).getPosition(), imageGreenH));
                        j++;
                    }
                    else if(((BluePeg) pegs.get(index)).getPic().equals(imageBlueV)){
                        pegs.set(index, new GreenPeg(pegs.get(index).getPosition(), imageGreenV));
                        j++;
                    }
                }
            }
        }
        /* generate a powerup */
        initialPowerUp();
    }


    /**
     * generate a powerup and within 1/10 chance.
     */
    public void initialPowerUp() {
        Random rand = new Random();
        int num = rand.nextInt(10);
            if (num == 6) {
            powerup = new PowerUp(tempStart);
            powerup.initial();
            powerup.update();
        }
    }

    /**
     * generate a bucket at bottom of the board
     */
    public void initialBucket() {
        bucket = new Bucket(BUCKET_POSITION);
    }
    /* using turnStarted to check whether the turn finished */
    boolean turnStarted = true;
    /* using for collision between bucket and ball */
    int check = 0;

    /**
     * update method for Shadowbounce
     * @param input read information from mouse or keyboard (Input class).
     */
    public void update(Input input) {
        /* update and render peg */
        for (Peg peg : pegs) {
            peg.update();
        }
        /* check and update powerup */
        if (powerup != null) {
//            int index = 0;
            powerup.update();
            for(Ball ball: balls){
                if(ball.intersects(powerup)){
                    powerup = null;
                    balls.set(balls.indexOf(ball),new FireBall(ball.getBox().centre(),
                            ball.getVelocity().normalised()));
                }
//                index++;
            }
        }

        /* update bucket */
        bucket.update();

        /* create a ball when left button pressed and no ball on the board */
        if (input.wasPressed(MouseButtons.LEFT) && balls.isEmpty()) {
            balls.add(new NormalBall(BALL_POSITION, input.directionToMouse(BALL_POSITION)));
            turnStarted = true;
        }


        /* check collision between ball and bucket. If intersects adding one more shot */
        for (Ball ball : new ArrayList<>(balls)) {
            ball.update();
            if (ball.intersects(bucket)) {
                check += 1;
            }
            if(check == 3){
                numBall++;
                check = 0;
            }
            if (ball.outOfScreen()) {
                balls.remove(ball);
                numBall--;
            }
        }

        /**
         *  ball bounce to different direction and if collision, removing pegs.
         * count number of red pegs as well
         */
        for (Ball ball : new ArrayList<>(balls)) {
            for (Peg peg : new ArrayList<>(pegs)) {
                if (ball.intersects(peg)) {
                    /* four points for ball box to check collision and bounce direction */
                    if(ball.getBox().intersectedAt(peg.getBox().topLeft(), ball.getVelocity()).equals(Side.TOP) ||
                            ball.getBox().intersectedAt(peg.getBox().topLeft(), ball.getVelocity()).equals(Side.BOTTOM)||
                            ball.getBox().intersectedAt(peg.getBox().topRight(), ball.getVelocity()).equals(Side.TOP) ||
                            ball.getBox().intersectedAt(peg.getBox().topRight(), ball.getVelocity()).equals(Side.BOTTOM)||
                            ball.getBox().intersectedAt(peg.getBox().bottomLeft(), ball.getVelocity()).equals(Side.TOP) ||
                            ball.getBox().intersectedAt(peg.getBox().bottomLeft(), ball.getVelocity()).equals(Side.BOTTOM)||
                            ball.getBox().intersectedAt(peg.getBox().bottomRight(), ball.getVelocity()).equals(Side.TOP) ||
                            ball.getBox().intersectedAt(peg.getBox().bottomRight(), ball.getVelocity()).equals(Side.BOTTOM)){
                        double newX;
                        double newY;
                        newX = ball.getVelocity().x;
                        newY = ball.getVelocity().y;
                        Vector2 newVelocity = new Vector2(-newX, newY);
                        ball.setVelocity(newVelocity);
                    }
                    else if(ball.getBox().intersectedAt(peg.getBox().topLeft(), ball.getVelocity()).equals(Side.LEFT)||
                            ball.getBox().intersectedAt(peg.getBox().topLeft(), ball.getVelocity()).equals(Side.RIGHT) ||
                            ball.getBox().intersectedAt(peg.getBox().topRight(), ball.getVelocity()).equals(Side.LEFT)||
                            ball.getBox().intersectedAt(peg.getBox().topRight(), ball.getVelocity()).equals(Side.RIGHT)||
                            ball.getBox().intersectedAt(peg.getBox().bottomRight(), ball.getVelocity()).equals(Side.LEFT)||
                            ball.getBox().intersectedAt(peg.getBox().bottomRight(), ball.getVelocity()).equals(Side.RIGHT)||
                            ball.getBox().intersectedAt(peg.getBox().bottomLeft(), ball.getVelocity()).equals(Side.LEFT)||
                            ball.getBox().intersectedAt(peg.getBox().bottomLeft(), ball.getVelocity()).equals(Side.RIGHT)){
                        double newX;
                        double newY;
                        newX = ball.getVelocity().x;
                        newY = ball.getVelocity().y;
                        Vector2 newVelocity = new Vector2(newX, -newY);
                        ball.setVelocity(newVelocity);
                    }

                    if (peg instanceof BluePeg || peg instanceof RedPeg) {
                        if(peg instanceof RedPeg){
                            numRedPeg--;
                        }
                        if(ball instanceof NormalBall) {
                            pegs.remove(peg);
                        }
                        else if(ball instanceof FireBall){
                            for (int k = 0; k < pegs.size();k++) {
                                 if(Math.sqrt(((pegs.get(k).getPosition().x - peg.getPosition().x) *
                                         (pegs.get(k).getPosition().x - peg.getPosition().x)) +
                                         ((pegs.get(k).getPosition().y - peg.getPosition().y) *
                                                 (pegs.get(k).getPosition().x - peg.getPosition().x)))
                                         < BURST_DISTANCE){
                                    if(pegs.get(k) instanceof GreyPeg){
                                    }
                                    else {
                                        if(pegs.get(k) instanceof RedPeg){
                                            numRedPeg--;
                                        }
                                        pegs.remove(k);
                                    }
                                }
                            }
                            pegs.remove(peg);
                        }
                    }
                    /* if collision with green peg add create two balls(ball type the same as ball intersects with peg)
                     and in two different direction */
                    else if(peg instanceof GreenPeg) {
                        if (ball instanceof NormalBall) {
                            Vector2 direct1 = peg.getBox().topLeft().asVector().sub
                                    (peg.getBox().centre().asVector()).normalised();
                            Vector2 direct2 = peg.getBox().topRight().asVector().sub
                                    (peg.getBox().centre().asVector()).normalised();
                            balls.add(new NormalBall(peg.getBox().centre(), direct1));
                            balls.add(new NormalBall(peg.getBox().centre(), direct2));
                            pegs.remove(peg);
                        } else if (ball instanceof FireBall) {
                            Vector2 direct1 = peg.getBox().topLeft().asVector().sub
                                    (peg.getBox().centre().asVector()).normalised();
                            Vector2 direct2 = peg.getBox().topRight().asVector().sub
                                    (peg.getBox().centre().asVector()).normalised();
                            balls.add(new FireBall(peg.getBox().centre(), direct1));
                            balls.add(new FireBall(peg.getBox().centre(), direct2));
                            /* achieve ball burst functon */
                            for (int h = 0; h< pegs.size(); h++) {
                                if(Math.sqrt(((pegs.get(h).getPosition().x - peg.getPosition().x) *
                                        (pegs.get(h).getPosition().x - peg.getPosition().x)) +
                                        ((pegs.get(h).getPosition().y - peg.getPosition().y) *
                                                (pegs.get(h).getPosition().x - peg.getPosition().x)))
                                        < BURST_DISTANCE){
                                    if(pegs.get(h) instanceof GreyPeg){
                                    }
                                    else {
                                        if(pegs.get(h) instanceof RedPeg){
                                            numRedPeg--;
                                        }
                                        pegs.remove(h);
                                    }
                                }
                            }
                            pegs.remove(peg);
                        }
                    }

                }

            }
        }
        /* check whether the turn finished. if so initialise a new turn */
        if(turnStarted && (balls.size() == 0)){
            initialTurn();
            turnStarted = false;
        }

        /*if number of red peg is less than 0 and ball size equal to 0, if there is a new board, loading next
        board. if not game finish(win the game) */
        if(numRedPeg <= 0){
            if(level >= 4 && (balls.size() == 0)){
               Window.close();
            }
            else if((balls.size() == 0) && level < 4){
                level++;
                loadPeg(filename);
                initialPeg();
            }
        }
        /* if run out of number of shots game finished (lose) */
        if(numBall == 0){
            Window.close();
        }
    }
    /**
     * main function of ShadowBounce, run the game
     * @param args std input for the program.
     */
    public static void main(String[] args) {
        ShadowBounce game = new ShadowBounce();
        game.run();

    }
}
