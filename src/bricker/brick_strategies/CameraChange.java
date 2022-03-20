package bricker.brick_strategies;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;

public class CameraChange extends RemoveBrickStrategy {
    private static final int COUNT_COLLISION = 4;
    private final BrickerGameManager gameManager;

    public CameraChange(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager) {
        super(gameObjectCollection, gameManager);
        this.gameManager = gameManager;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        super.onCollision(thisObj, otherObj);
        if (gameManager.getCamera() == null) {
            gameManager.setCamera(
                    new Camera(
                            gameManager.getBall(),            //object to follow
                            Vector2.ZERO,    //follow the center of the object
                            gameManager.getWindowController().getWindowDimensions().mult(1.2f),  //widen the frame a bit
                            gameManager.getWindowController().getWindowDimensions()   //share the window dimensions
                    )
            );
            gameManager.getBall().setTag("start count");
            gameManager.setCountCollision(COUNT_COLLISION);
        }
    }


}
