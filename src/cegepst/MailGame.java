package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class MailGame extends Game {

    private GamePad gamePad;
    private MailTruck mailTruck;
    private Republican[] republicans;
    private Wall wall;
    private ArrayList<Vote> votes;
    private World world;

    public MailGame() {
        gamePad = new GamePad();
        republicans = new Republican[4];
        votes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            republicans[i] = new Republican();
        }
        for (int i = 0; i < 8; i++) {
            votes.add(new Vote());
        }
        mailTruck = new MailTruck(gamePad);
        wall = new Wall();
        world = new World();

    }

    @Override
    public void initialize() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    this.getClass().getClassLoader().getResourceAsStream("music/broForceTheme.wav"));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        for (Republican republican : republicans) {
            republican.update(mailTruck.getCenterX(), mailTruck.getCenterY());
            if (republican.hitBoxIntersectWith(mailTruck)) {
                republican.dealDamage(mailTruck);
            }
        }
        mailTruck.update();
        if (gamePad.isQuitPressed()) {
            this.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        //world.draw(buffer);
        mailTruck.draw(buffer);
        for (Republican republican : republicans) {
            republican.draw(buffer);
        }
        for (Vote vote : votes) {
            vote.draw(buffer);
        }
        wall.draw(buffer);
    }
}
