package cegepst.engine.entity;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MovementController;

import java.awt.*;

public abstract class ControllableEntity extends MovableEntity {

    private MovementController controller;

    public ControllableEntity(MovementController controller) {

        this.controller = controller;
    }

    public void moveAccordingToHandler() {
        if (!controller.isMoving()) {
            return;
        }
        if (controller.isUpPressed()) {
            moveUp();
        } else if (controller.isDownPressed()) {
            moveDown();
        } else if (controller.isLeftPressed()) {
            moveLeft();
        } else if (controller.isRightPressed()) {
            moveRight();
        }
    }

    public abstract void draw(Buffer buffer);
}
