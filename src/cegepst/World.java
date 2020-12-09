package cegepst;

import cegepst.engine.Buffer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    private static final String MAP_PATH_BL = "images/mapBL.png";
    private static final String MAP_PATH_BR = "images/mapBR.png";
    private Image backgroundBL;
    private Image backgroundBR;

    public World() {
        loadBackgroundBL();
        loadBackgroundBR();
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(backgroundBL, 0, 0);
        buffer.drawImage(backgroundBR, 689, 0);
    }

    private void loadBackgroundBL() {
        try {
            backgroundBL = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH_BL));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBackgroundBR() {
        try {
            backgroundBR = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(MAP_PATH_BR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
