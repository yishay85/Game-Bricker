package bricker.brick_strategies;

import danogl.GameObject;


public class DoubleBehavior implements CollisionStrategy {
    private CollisionStrategy collisionStrategy1;
    private CollisionStrategy collisionStrategy2;

    public DoubleBehavior(CollisionStrategy collisionStrategy1, CollisionStrategy collisionStrategy2) {
        this.collisionStrategy1 = collisionStrategy1;
        this.collisionStrategy2 = collisionStrategy2;
    }

    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        collisionStrategy1.onCollision(thisObj, otherObj);
        collisionStrategy2.onCollision(thisObj, otherObj);
    }
}
