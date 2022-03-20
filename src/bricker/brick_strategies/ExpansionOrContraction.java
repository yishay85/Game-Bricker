package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;

import java.util.Random;

public class ExpansionOrContraction extends RemoveBrickStrategy{
    private ImageReader imageReader;

    public ExpansionOrContraction(GameObjectCollection gameObjectCollection, ImageReader imageReader,
                                  BrickerGameManager gameManager) {
        super(gameObjectCollection, gameManager);
        this.imageReader = imageReader;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        Random random = new Random();
        String path = "assets/buffWiden.png";
        String nameFunc = "widen";
        if (random.nextBoolean()) {
            path = "assets/buffNarrow.png";
            nameFunc = "narrow";
        }
        CreateEffect(imageReader, thisObj, path, nameFunc);
    }


}
