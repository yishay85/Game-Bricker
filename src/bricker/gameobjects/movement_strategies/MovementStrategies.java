package bricker.gameobjects.movement_strategies;

import danogl.GameObject;
import danogl.util.Vector2;

public interface MovementStrategies {
    public static final int MIN_DISTANCE_FROM_SCREEN_EDGE = 0;

    Vector2 calcMovementDir(GameObject owner);

    boolean onCollisionStrategy(GameObject other);

}
