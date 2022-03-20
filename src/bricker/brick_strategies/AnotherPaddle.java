package bricker.brick_strategies;

import bricker.gameobjects.CreateObject;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.UserInputListener;

public class AnotherPaddle extends RemoveBrickStrategy {

    private final ImageReader imageReader;
    private final UserInputListener inputListener;
    private final BrickerGameManager gameManager;

    public AnotherPaddle(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                         UserInputListener inputListener, BrickerGameManager gameManager) {
        super(gameObjectCollection, gameManager);
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.gameManager = gameManager;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        CreateObject createObject = new CreateObject(getGameObjectCollection(), gameManager);
        getGameObjectCollection().addGameObject(createObject.createUserPaddle(imageReader, inputListener, false));
    }
}
