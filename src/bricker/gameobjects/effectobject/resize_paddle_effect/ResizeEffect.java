package bricker.gameobjects.effectobject.resize_paddle_effect;

import bricker.gameobjects.Paddle;
import bricker.gameobjects.effectobject.StatusEffects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

public abstract class ResizeEffect extends StatusEffects {
    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     * @param gameObjectCollection
     */
    public ResizeEffect(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                        GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, renderable, gameObjectCollection);
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        float resize = resizePaddle(other);
        other.setDimensions(new Vector2(resize, other.getDimensions().y()));
        ((Paddle) other).setSizePaddle((int) resize);
    }

    protected abstract float resizePaddle(GameObject other);
}
