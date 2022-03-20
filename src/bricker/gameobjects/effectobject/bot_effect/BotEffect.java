package bricker.gameobjects.effectobject.bot_effect;

import bricker.gameobjects.CreateObject;
import bricker.gameobjects.effectobject.StatusEffects;
import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.ImageReader;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public abstract class BotEffect extends StatusEffects {
    private ImageReader imageReader;
    private BrickerGameManager gameManager;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     * @param gameObjectCollection
     * @param imageReader
     * @param gameManager
     */
    public BotEffect(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                     GameObjectCollection gameObjectCollection,
                     ImageReader imageReader, BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection);
        this.imageReader = imageReader;
        this.gameManager = gameManager;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        CreateObject createObject = new CreateObject(getGameObjectCollection(), gameManager);
        getGameObjectCollection().addGameObject(createBot(createObject, imageReader));
    }

    protected abstract GameObject createBot(CreateObject createObject, ImageReader imageReader);
}
