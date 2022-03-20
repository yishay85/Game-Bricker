package bricker.gameobjects.movement_strategies;

import danogl.GameObject;

public class BotBad extends AIMovementStrategy {
    public BotBad(GameObject objectToFollow) {
        super(objectToFollow);
    }

    @Override
    protected boolean whereToGo(GameObject owner, GameObject objectToFollow) {
        return objectToFollow.getCenter().y() > owner.getCenter().y();
    }


}
