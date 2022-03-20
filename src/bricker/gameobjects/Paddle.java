package bricker.gameobjects;

import bricker.gameobjects.movement_strategies.MovementStrategies;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.main.BrickerGameManager.PADDLE_WIDTH;
import static bricker.main.BrickerGameManager.SCREEN_WIDTH;

public class Paddle extends GameObject {

    public static final int MIN_DISTANCE_FROM_SCREEN_EDGE = 0;
    public static final float MOVEMENT_SPEED = 300;

    private MovementStrategies movementStrategies;
    private int sizePaddle;
    private GameObjectCollection gameObjectCollection;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param renderable           The renderable representing the object. Can be null, in which case
     * @param movementStrategies
     * @param gameObjectCollection
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, MovementStrategies movementStrategies,
                  GameObjectCollection gameObjectCollection) {
        super(topLeftCorner, dimensions, renderable);
        this.movementStrategies = movementStrategies;
        this.gameObjectCollection = gameObjectCollection;
        sizePaddle = PADDLE_WIDTH;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = movementStrategies.calcMovementDir(this);
        setVelocity(movementDir.mult(MOVEMENT_SPEED));
        if (getTopLeftCorner().x() < MIN_DISTANCE_FROM_SCREEN_EDGE)
            transform().setTopLeftCornerX((float) MIN_DISTANCE_FROM_SCREEN_EDGE);
        if (getTopLeftCorner().x() + sizePaddle > SCREEN_WIDTH)
            transform().setTopLeftCornerX(SCREEN_WIDTH - sizePaddle);
    }

    public void setSizePaddle(int sizePaddle) {
        this.sizePaddle = sizePaddle;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (movementStrategies.onCollisionStrategy(other))
            gameObjectCollection.removeGameObject(this);

    }
}
