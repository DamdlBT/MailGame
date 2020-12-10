package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.entity.StaticEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class GameOver extends StaticEntity {

    private final static String GAME_OVER_PATH = "images/gameOver.png";
    private Image gameOver;

    public GameOver() {
        loadGameOver();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(gameOver, 160, 50);
    }

    private void loadGameOver() {
        try {
            gameOver = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(GAME_OVER_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
