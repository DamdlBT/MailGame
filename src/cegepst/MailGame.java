package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class MailGame extends Game {

    private GamePad gamePad;
    private MailTruck mailTruck;
    private ArrayList<Republican> republicans;
    private ArrayList<Vote> votes;
    private ArrayList<Vote> deleteVotes;
    private World world;
    private GameOver gameOver;
    private boolean gameOverExist;

    public MailGame() {
        gamePad = new GamePad();
        republicans = new ArrayList<>();
        votes = new ArrayList<>();
        deleteVotes = new ArrayList<>();
        gameOverExist = false;
        for (int i = 0; i < 4; i++) {
            republicans.add(new Republican());
        }
        for (int i = 0; i < 8; i++) {
            votes.add(new Vote());
        }
        mailTruck = new MailTruck(gamePad);
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
        if (mailTruck.getHealth() <= 0) {
            gameOver = new GameOver();
            gameOverExist = true;
        } else {
            for (Vote vote : deleteVotes) {
                votes.remove(vote);
            }
            for (Republican republican : republicans) {
                republican.update(mailTruck.getCenterX(), mailTruck.getCenterY());
                if (republican.hitBoxIntersectWith(mailTruck)) {
                    republican.dealDamage(mailTruck);
                }
            }
            for (Vote vote : votes) {
                if (mailTruck.intersectWith(vote)) {
                    mailTruck.addVote(vote.getValue());
                    deleteVotes.add(vote);
                }
            }
            mailTruck.update();
            if (GameTime.getElapsedTime() % 15000 == 0) {
                votes.add(new Vote());
                //republicans.add(new Republican());
            }
        }
        if (gamePad.isQuitPressed()) {
            this.stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        world.draw(buffer);
        mailTruck.draw(buffer);
        for (Republican republican : republicans) {
            republican.draw(buffer);
        }
        for (Vote vote : votes) {
            vote.draw(buffer);
        }
        if (gameOverExist) {
            gameOver.draw(buffer);
        }
    }
}
