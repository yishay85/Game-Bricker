package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;

import java.util.Random;

public class GoodBot extends RemoveBrickStrategy {
    private ImageReader imageReader;

    public GoodBot(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager, ImageReader imageReader) {
        super(gameObjectCollection, gameManager);
        this.imageReader = imageReader;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        Random random = new Random();
        String path = "assets/botGood.png";
        String nameFunc = "botGood";
        if (random.nextBoolean()) {
            path = "assets/botBad.png";
            nameFunc = "botBad";
        }
        CreateEffect(imageReader, thisObj, path, nameFunc);
    }
}
