package bricker.gameobjects;

import bricker.gameobjects.movement_strategies.BotBad;
import bricker.gameobjects.movement_strategies.BotGood;
import bricker.gameobjects.movement_strategies.UserMovementStrategy;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

import static bricker.main.BrickerGameManager.*;


public class CreateObject {
    private GameObjectCollection gameObjectCollection;
    private BrickerGameManager gameManager;

    public CreateObject(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager) {
        this.gameObjectCollection = gameObjectCollection;
        this.gameManager = gameManager;
    }

    public GameObject createBall(ImageReader imageReader, SoundReader soundReader, String path, GameObject otherObj) {
        Renderable ballImage = imageReader.readImage(path, true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav");
        GameObject ball = new Ball(Vector2.ZERO, new Vector2(BALL_RADIUS, BALL_RADIUS), ballImage, collisionSound, gameManager);
        resetBall(ball, otherObj);
        return ball;
    }

    public void resetBall(GameObject ball, GameObject otherObj) {
        if (otherObj instanceof Paddle)
            ball.setCenter(new Vector2(otherObj.getCenter().x(), otherObj.getCenter().y() - 21));
        else
            ball.setCenter(new Vector2(otherObj.getCenter().x(), otherObj.getCenter().y()));
        float y = BALL_SPEED;
        float x = BALL_SPEED;

        Random rand = new Random();
        if (rand.nextBoolean())
            x *= -1;
        if (rand.nextBoolean())
            y *= -1;
        ball.setVelocity(new Vector2(x, y));
    }

    public GameObject createUserPaddle(ImageReader imageReader, UserInputListener inputListener, boolean original) {
        int heightPaddle = 100;
        if (original)
            heightPaddle = 30;
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject userPaddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT),
                paddleImage, new UserMovementStrategy(inputListener, original), gameObjectCollection);
        userPaddle.setCenter(new Vector2(gameManager.getWindowController().getWindowDimensions().x() / 2,
                gameManager.getWindowController().getWindowDimensions().y() - heightPaddle));
        return userPaddle;
    }

    public GameObject createBotGoodPaddle(ImageReader imageReader) {
        Renderable botPaddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject botPaddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), botPaddleImage,
                new BotGood(gameManager.getBall()), gameObjectCollection);
        botPaddle.setCenter(new Vector2(gameManager.getWindowController().getWindowDimensions().x() / 2,
                gameManager.getWindowController().getWindowDimensions().y() - 150));
        return botPaddle;
    }

    public GameObject createBotBadPaddle(ImageReader imageReader) {
        Renderable botPaddleImage = imageReader.readImage("assets/paddle.png", true);
        GameObject botPaddle = new Paddle(Vector2.ZERO, new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT), botPaddleImage,
                new BotBad(gameManager.getBall()), gameObjectCollection);
        botPaddle.setCenter(new Vector2(gameManager.getWindowController().getWindowDimensions().x() / 2,
                gameManager.getWindowController().getWindowDimensions().y() - 150));
        return botPaddle;
    }

}
