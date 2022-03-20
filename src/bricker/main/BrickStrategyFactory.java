package bricker.main;

import bricker.brick_strategies.*;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;

import java.util.Random;

public class BrickStrategyFactory {
    private final GameObjectCollection gameObjectCollection;
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final UserInputListener inputListener;
    private final BrickerGameManager gameManager;

    public BrickStrategyFactory(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                                SoundReader soundReader, UserInputListener inputListener, BrickerGameManager gameManager) {
        this.gameObjectCollection = gameObjectCollection;
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.gameManager = gameManager;
    }

    public CollisionStrategy getStrategy() {
        CollisionStrategy collisionStrategy = null;
        Random random = new Random();
        int num = random.nextInt(100);
        if (num < 40)
            collisionStrategy = new RemoveBrickStrategy(gameObjectCollection, gameManager);
        else if (num < 50) {
            CollisionStrategy collisionStrategy1 = getStrategy();
            CollisionStrategy collisionStrategy2 = getStrategy();
            collisionStrategy = new DoubleBehavior(collisionStrategy1, collisionStrategy2);
        } else if (num < 60)
            collisionStrategy = new AdditionalBalls(gameObjectCollection, imageReader, soundReader, gameManager);
        else if (num < 70)
            collisionStrategy = new GamePace(gameObjectCollection, imageReader, gameManager);
        else if (num < 80)
            collisionStrategy = new ExpansionOrContraction(gameObjectCollection, imageReader, gameManager);
        else if (num < 90)
            collisionStrategy = new CameraChange(gameObjectCollection, gameManager);
        else if (num < 95)
            collisionStrategy = new AnotherPaddle(gameObjectCollection, imageReader, inputListener, gameManager);
        else
            collisionStrategy = new GoodBot(gameObjectCollection, gameManager, imageReader);

        return collisionStrategy;
    }
}
