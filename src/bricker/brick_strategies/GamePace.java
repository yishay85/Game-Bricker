package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.WindowController;

public class GamePace extends RemoveBrickStrategy {
    private final WindowController windowController;
    private final ImageReader imageReader;

    public GamePace(GameObjectCollection gameObjectCollection,
                    ImageReader imageReader, BrickerGameManager gameManager) {
        super(gameObjectCollection, gameManager);
        this.windowController = gameManager.getWindowController();
        this.imageReader = imageReader;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        String path = "assets/slow.png";
        String nameFunc = "slow";
        if (windowController.getTimeScale() == 0.9F) {
            path = "assets/quicken.png";
            nameFunc = "quicken";
        }
        CreateEffect(imageReader, thisObj, path, nameFunc);
    }
}
