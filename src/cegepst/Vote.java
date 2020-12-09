package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.UpdatableEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Vote extends UpdatableEntity {

    private static final String SPRITSHEET_PATH = "images/vote.png";
    private BufferedImage spriteSheet;

    public Vote() {
        loadSpriteSheet();
        setDimension(16, 13);
        teleport(getRandom(800), getRandom(600));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(spriteSheet, x, y);
    }

    private void loadSpriteSheet() {
        try {
            spriteSheet = ImageIO.read((this.getClass().getClassLoader().getResourceAsStream(SPRITSHEET_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
