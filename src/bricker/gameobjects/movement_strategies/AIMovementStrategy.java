package bricker.gameobjects.movement_strategies;

import danogl.GameObject;
import danogl.util.Vector2;


public abstract class AIMovementStrategy implements MovementStrategies {
    private GameObject objectToFollow;

    public AIMovementStrategy(GameObject objectToFollow) {
        this.objectToFollow = objectToFollow;
    }

    @Override
    public Vector2 calcMovementDir(GameObject owner) {
        Vector2 movementDir = Vector2.ZERO;

        if (whereToGo(owner, objectToFollow)) {
            if (objectToFollow.getCenter().x() < owner.getCenter().x())
                movementDir = Vector2.LEFT;
            if (objectToFollow.getCenter().x() > owner.getCenter().x())
                movementDir = Vector2.RIGHT;
        } else {
            if (objectToFollow.getCenter().x() > owner.getCenter().x())
                movementDir = Vector2.LEFT;
            if (objectToFollow.getCenter().x() < owner.getCenter().x())
                movementDir = Vector2.RIGHT;
        }
        return movementDir;
    }

    @Override
    public boolean onCollisionStrategy(GameObject other) {
        return false;
    }

    protected abstract boolean whereToGo(GameObject owner, GameObject objectToFollow);
}
