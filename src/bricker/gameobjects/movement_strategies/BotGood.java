package bricker.gameobjects.movement_strategies;

import danogl.GameObject;

public class BotGood extends AIMovementStrategy {

    public BotGood(GameObject objectToFollow) {
        super(objectToFollow);
    }

    @Override
    protected boolean whereToGo(GameObject owner, GameObject objectToFollow) {
        return objectToFollow.getCenter().y() < owner.getCenter().y();
    }
}
