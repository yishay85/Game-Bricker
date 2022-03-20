package bricker.gameobjects.movement_strategies;

import bricker.gameobjects.Ball;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;

import static bricker.main.BrickerGameManager.PADDLE_WIDTH;
import static bricker.main.BrickerGameManager.SCREEN_WIDTH;

public class UserMovementStrategy implements MovementStrategies {
    private UserInputListener inputListener;
    private int sizePaddle;
    private boolean original;
    private int count;

    public UserMovementStrategy(UserInputListener inputListener, boolean original) {

        this.inputListener = inputListener;
        this.original = original;
        sizePaddle = PADDLE_WIDTH;
        count = 3;
    }

    @Override
    public Vector2 calcMovementDir(GameObject owner) {
        Vector2 movementDir = Vector2.ZERO;
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT))
            movementDir = movementDir.add(Vector2.LEFT);
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT))
            movementDir = movementDir.add(Vector2.RIGHT);

        if (owner.getTopLeftCorner().x()  < MIN_DISTANCE_FROM_SCREEN_EDGE)
            owner.transform().setTopLeftCornerX((float) MIN_DISTANCE_FROM_SCREEN_EDGE);
        if (owner.getTopLeftCorner().x()  > SCREEN_WIDTH)
            owner.transform().setTopLeftCornerX(SCREEN_WIDTH - sizePaddle);
        return movementDir;
    }

    @Override
    public boolean onCollisionStrategy(GameObject other) {
        if (!original && other instanceof Ball) {
            count--;
            if (count == 0)
                return true;
        }
        return false;
    }
}
