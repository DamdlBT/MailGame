package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.Direction;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entity.ControllableEntity;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MailTruck extends ControllableEntity {

    private final int INITIAL_VOTE = 100;
    private String spritePath = "images/truckUp.png";
    private BufferedImage spriteSheet;
    private Image image;

    public MailTruck(MovementController controller) {
        super(controller);
        setSpeed(3);
        teleport(200, 200);
        super.setHealth(INITIAL_VOTE);
        loadSpriteSheet();
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToHandler();
        imageDirection();
        setDimension(spriteSheet.getWidth(), spriteSheet.getHeight());
        loadSpriteSheet();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(spriteSheet, x, y);
        buffer.drawText(Integer.toString(health), 10, 10, Color.white);
    }

    public void addVote(int vote) {
        this.addHealth(vote);
    }

    public int getCenterX() {
        return x + width/2;
    }

    public int getCenterY() {
        return y + height/2;
    }

    private void imageDirection() {
        if (getDirection() == Direction.UP) {
            spritePath = "images/truckUp.png";
        } else if (getDirection() == Direction.DOWN) {
            spritePath = "images/truckDown.png";
        } else if (getDirection() == Direction.LEFT) {
            spritePath = "images/truckLeft.png";
        } else if (getDirection() == Direction.RIGHT) {
            spritePath = "images/truckRight.png";
        }
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read((this.getClass().getClassLoader().getResourceAsStream(spritePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
