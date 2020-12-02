package cegepst;

import cegepst.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH = "images/map.png";
    private Image background;

    public World() {
        loadBackground();
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(background, 0, 0);
    }

    private void loadBackground() {
        try {
            background = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
