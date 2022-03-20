package bricker.gameobjects.effectobject.bot_effect;

import bricker.gameobjects.CreateObject;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


public class BotGoodEffect extends BotEffect {

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     * @param gameObjectCollection
     * @param gameManager
     */
    public BotGoodEffect(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                         GameObjectCollection gameObjectCollection, ImageReader imageReader,
                         BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection, imageReader, gameManager);
    }

    @Override
    protected GameObject createBot(CreateObject createObject, ImageReader imageReader) {
        return createObject.createBotGoodPaddle(imageReader);
    }


}
