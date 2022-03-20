package bricker.brick_strategies;

import bricker.gameobjects.CreateObject;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;


public class AdditionalBalls extends RemoveBrickStrategy {
    private final ImageReader imageReader;
    private final SoundReader soundReader;
    private final BrickerGameManager gameManager;


    public AdditionalBalls(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                           SoundReader soundReader, BrickerGameManager gameManager) {
        super(gameObjectCollection, gameManager);
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.gameManager = gameManager;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        CreateObject createObject = new CreateObject(getGameObjectCollection(), gameManager);
        for (int i = 0; i < 3; i++) {
            getGameObjectCollection().addGameObject(
                    createObject.createBall(imageReader, soundReader, "assets/mockBall.png", otherObj));
        }
    }
}
