package bricker.brick_strategies;

import bricker.gameobjects.effectobject.*;
import bricker.gameobjects.effectobject.bot_effect.BotBadEffect;
import bricker.gameobjects.effectobject.bot_effect.BotGoodEffect;
import bricker.gameobjects.effectobject.resize_paddle_effect.ContractionEffect;
import bricker.gameobjects.effectobject.resize_paddle_effect.ExpansionEffect;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;


public class RemoveBrickStrategy implements CollisionStrategy {
    private GameObjectCollection gameObjectCollection;
    private Counter counter;
    private BrickerGameManager gameManager;

    public RemoveBrickStrategy(GameObjectCollection gameObjectCollection, BrickerGameManager gameManager) {
        this.gameObjectCollection = gameObjectCollection;
        this.counter = gameManager.getCounter();
        this.gameManager = gameManager;
    }

    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (gameObjectCollection.removeGameObject(thisObj, Layer.STATIC_OBJECTS))
            counter.decrement();
    }


    public GameObjectCollection getGameObjectCollection() {
        return gameObjectCollection;
    }

    protected void CreateEffect(ImageReader imageReader, GameObject thisObj, String path, String nameFunc) {
        Renderable image = imageReader.readImage(path, false);
        GameObject gameEffect = null;
        switch (nameFunc) {
            case "slow":
                gameEffect = new SlowMotionEffect(Vector2.ZERO, new Vector2(100, 15), image,
                        getGameObjectCollection(), gameManager.getWindowController());
                break;
            case "quicken":
                gameEffect = new QuickenEffect(Vector2.ZERO, new Vector2(100, 15), image,
                        getGameObjectCollection(), gameManager.getWindowController());
                break;
            case "narrow":
                gameEffect = new ContractionEffect(Vector2.ZERO, new Vector2(100, 15), image,
                        getGameObjectCollection());
                break;
            case "widen":
                gameEffect = new ExpansionEffect(Vector2.ZERO, new Vector2(100, 15), image,
                        getGameObjectCollection());
                break;
            case "botGood":
                gameEffect = new BotGoodEffect(Vector2.ZERO, new Vector2(100, 15), image, getGameObjectCollection(), imageReader, gameManager);
                break;
            case "botBad":
                gameEffect = new BotBadEffect(Vector2.ZERO, new Vector2(100, 15), image, getGameObjectCollection(), imageReader, gameManager);
                break;
        }
        gameEffect.setVelocity(new Vector2(0, 90));
        gameEffect.setCenter(thisObj.getCenter());
        gameObjectCollection.addGameObject(gameEffect);
    }

}
