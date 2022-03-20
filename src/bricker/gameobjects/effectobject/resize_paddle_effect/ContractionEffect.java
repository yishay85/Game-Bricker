package bricker.gameobjects.effectobject.resize_paddle_effect;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public class ContractionEffect extends ResizeEffect {
    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     * @param gameObjectCollection To add game object
     */
    public ContractionEffect(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                             GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection);
    }

    @Override
    protected float resizePaddle(GameObject other) {
        if (other.getDimensions().x() - NEW_SIZE_PADDLE <= 0)
            return other.getDimensions().x();
        else
            return other.getDimensions().x() - NEW_SIZE_PADDLE;
    }
}