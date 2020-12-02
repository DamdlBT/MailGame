package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.entity.MovableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Republican extends MovableEntity {

    private final static String SPRITSHEET_PATH = "images/player.png";
    private final static int ANIMATION_SPEED = 8;
    private final int INITIAL_STRENGTH = 10;
    private BufferedImage spriteSheet;
    private Image[] upFrames;
    private Image[] downFrames;
    private Image[] leftFrames;
    private Image[] rightFrames;
    private int currentAnimationFrame = 1;
    private int nextFrame = ANIMATION_SPEED;
    private int strength;

    public Republican() {
        setDimension(32, 32);
        setSpeed(1);
        strength = INITIAL_STRENGTH;
        teleport(getRandom(800), getRandom(600));
        CollidableRepository.getInstance().registerEntity(this);
        loadSpriteSheet();
        loadFrames();
        //setMoveTrue();
    }

    public void update(int xObjectif, int yObjextif) {
        super.update();
        move(xObjectif, yObjextif);
        if (!hasMoved() && (getDirection() == Direction.LEFT || getDirection() == Direction.RIGHT)) {
            y += getSpeed();
            return;
        } else if (!hasMoved() && (getDirection() == Direction.DOWN || getDirection() == Direction.UP)) {
            x -= getSpeed();
            return;
        }
        if (super.hasMoved()) {
            --nextFrame;
            if (nextFrame == 0) {
                ++currentAnimationFrame;
                if (currentAnimationFrame >= leftFrames.length) {
                    currentAnimationFrame = 0;
                }
                nextFrame = ANIMATION_SPEED;
            }
        } else {
            currentAnimationFrame = 1;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (getDirection() == Direction.UP) {
            buffer.drawImage(upFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.DOWN) {
            buffer.drawImage(downFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.LEFT) {
            buffer.drawImage(leftFrames[currentAnimationFrame], x, y);
        } else if (getDirection() == Direction.RIGHT) {
            buffer.drawImage(rightFrames[currentAnimationFrame], x, y);
        }
        drawHitBox(buffer);
    }

    public void dealDamage(MovableEntity entity) {
        entity.receiveDamage(strength);
    }

    private void move(int xObjectif, int yObjextif) {
        if (xObjectif < x) {
            moveLeft();
        }
        if (xObjectif > x) {
            moveRight();
        }
        if (yObjextif < y) {
            moveUp();
        }
        if (yObjextif > y) {
            moveDown();
        }
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read((this.getClass().getClassLoader().getResourceAsStream(SPRITSHEET_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFrames() {
        downFrames = new Image[3];
        downFrames[0] = spriteSheet.getSubimage(96, 128, width, height);
        downFrames[1] = spriteSheet.getSubimage(128, 128, width, height);
        downFrames[2] = spriteSheet.getSubimage(160, 128, width, height);

        upFrames = new Image[3];
        upFrames[0] = spriteSheet.getSubimage(96, 224, width, height);
        upFrames[1] = spriteSheet.getSubimage(128, 224, width, height);
        upFrames[2] = spriteSheet.getSubimage(160, 224, width, height);

        leftFrames = new Image[3];
        leftFrames[0] = spriteSheet.getSubimage(96, 160, width, height);
        leftFrames[1] = spriteSheet.getSubimage(128, 160, width, height);
        leftFrames[2] = spriteSheet.getSubimage(160, 160, width, height);

        rightFrames = new Image[3];
        rightFrames[0] = spriteSheet.getSubimage(96, 192, width, height);
        rightFrames[1] = spriteSheet.getSubimage(128, 192, width, height);
        rightFrames[2] = spriteSheet.getSubimage(160, 192, width, height);
    }

    private int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
