package bricker.main;

import bricker.gameobjects.*;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;


public class BrickerGameManager extends GameManager {
    public static final float SCREEN_WIDTH = 900;
    public static final float SCREEN_HEIGHT = 500;
    public static final int BALL_RADIUS = 20;
    public static final float BALL_SPEED = 250;


    private static final int SPACE_BETWEEN_BRICK = 1;
    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = 20;


    private static final float SIZE_HEARTS = 20;
    private static final int LINE_BRICK = 8;
    private static final int COL_BRICK = 7;

    private BrickStrategyFactory brickStrategyFactory;
    private GameObject ball;
    private Vector2 windowDimension;
    private WindowController windowController;
    private int livesLeft;
    private GameObject[] life;
    private Counter counter;
    private CreateObject createObject;
    private GameObject paddle;
    private int countCollision;

    public BrickerGameManager(String windowTitle, Vector2 vector2) {
        super(windowTitle, vector2);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        counter = new Counter();
        this.windowController = windowController;
        windowDimension = windowController.getWindowDimensions();
        livesLeft = 3;
        brickStrategyFactory = new BrickStrategyFactory(gameObjects(), imageReader, soundReader, inputListener, this);
        createObject = new CreateObject(gameObjects(), this);
        life = new GameObject[3];


        paddle = createObject.createUserPaddle(imageReader, inputListener, true);
        gameObjects().addGameObject(paddle);
//        windowController.setTimeScale(0.2F);
        ball = createObject.createBall(imageReader, soundReader, "assets/ball.png", paddle);
        gameObjects().addGameObject(ball);


        createBackground(imageReader, windowController);

        createBrickWall(imageReader);

        createLife(imageReader);

        createWall(imageReader);

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkForGameEnd();

    }

    public GameObject getBall() {
        return ball;
    }

    public WindowController getWindowController() {
        return windowController;
    }

    public void setCountCollision(int countCollision) {
        this.countCollision = countCollision;
    }

    public int getCountCollision() {
        return countCollision;
    }

    public Counter getCounter() {
        return counter;
    }

    private void createWall(ImageReader imageReader) {
        Renderable wallImage = imageReader.readImage("assets/brick.png", true);
        GameObject wall = new Wall(Vector2.ZERO, new Vector2(windowDimension.x(), 5), wallImage);
        gameObjects().addGameObject(wall, Layer.STATIC_OBJECTS);
        wall = new Wall(new Vector2(windowDimension.x() - 5, 0), new Vector2(5, windowDimension.y()), wallImage);
        gameObjects().addGameObject(wall, Layer.STATIC_OBJECTS);
        wall = new Wall(Vector2.LEFT, new Vector2(5, windowDimension.y()), wallImage);
        gameObjects().addGameObject(wall, Layer.STATIC_OBJECTS);
    }


    private void checkForGameEnd() {
        float ballHeights = ball.getCenter().y();
        String prompt = "";
        if (ballHeights >= windowDimension.y()) {
            livesLeft--;
            gameObjects().removeGameObject(life[livesLeft], Layer.BACKGROUND);
            if (livesLeft == 0)
                prompt = "Yoe Lose! ";
            else
                createObject.resetBall(ball, paddle);
        }
        if (counter.value() == 0) {
            prompt = "Yoe Win! ";
        }
        if (!prompt.isEmpty()) {
            prompt += "Play again?";

            if (windowController.openYesNoDialog(prompt))
                windowController.resetGame();
            else
                windowController.closeWindow();
        }
    }


    private void createBrickWall(ImageReader imageReader) {
        Renderable brickImage = imageReader.readImage("assets/brick.png", true);
        System.out.println(windowDimension.x() / 8);
        for (int i = 1; i < LINE_BRICK; i++) {
            for (int j = 1; j < COL_BRICK; j++) {
                GameObject brick = new Brick(Vector2.ZERO, new Vector2(windowDimension.x() / 8, 15), brickImage, brickStrategyFactory.getStrategy());
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
                Vector2 vector2 = new Vector2(((windowDimension.x() / 7) + SPACE_BETWEEN_BRICK) * j, 30 * i);
                brick.setCenter(vector2);
                counter.increment();
            }
        }
    }

    private void createBackground(ImageReader imageReader, WindowController windowController) {
        //background
        GameObject background = new GameObject(Vector2.ZERO,
                windowController.getWindowDimensions(),
                imageReader.readImage("assets/DARK_BG2_small.png", false));
        gameObjects().addGameObject(background, Layer.BACKGROUND);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
    }

    private void createLife(ImageReader imageReader) {
        Renderable heartImage = imageReader.readImage("assets/heart.png", true);

        for (int i = 0; i < livesLeft; i++) {
            GameObject heart = new GameObject(Vector2.ZERO, new Vector2(SIZE_HEARTS, SIZE_HEARTS), heartImage);
            heart.setCenter(new Vector2((SIZE_HEARTS * (i + 1)), windowDimension.y() - SIZE_HEARTS));
            gameObjects().addGameObject(heart, Layer.BACKGROUND);
            life[i] = heart;
        }
    }

    public static void main(String[] args) {
        new BrickerGameManager("Bouncing Ball",
                new Vector2(SCREEN_WIDTH, SCREEN_HEIGHT)).run();
    }
}
