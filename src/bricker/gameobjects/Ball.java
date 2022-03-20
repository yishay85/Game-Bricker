package bricker.gameobjects;

import bricker.main.BrickerGameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;


public class Ball extends GameObject {

    private Sound collisionSound;
    private BrickerGameManager gameManager;

    /**
     * Construct a new GameObject instance.
     *
     * @param topLeftCorner Position of the object, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height in window coordinates.
     * @param renderable    The renderable representing the object. Can be null, in which case
     * @param gameManager
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound, BrickerGameManager gameManager) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
        this.gameManager = gameManager;
    }

    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        setVelocity(getVelocity().flipped(collision.getNormal()));
        collisionSound.play();
        if (getTag().equals("start count") && other instanceof Brick) {
            gameManager.setCountCollision(gameManager.getCountCollision() - 1);
            if (gameManager.getCountCollision() == 0) {
                gameManager.setCamera(null);
                setTag("");
            }
        }
    }

    @Override
    public boolean shouldCollideWith(GameObject other) {
        return !(other instanceof Ball);
    }

}
