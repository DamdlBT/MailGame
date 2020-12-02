package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class MailGame extends Game {

    private GamePad gamePad;
    private MailTruck mailTruck;
    private Republican[] republicans;
    private Wall wall;
    private World world;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        republicans = new Republican[4];
        for (int i = 0; i < 4; i++) {
            republicans[i] = new Republican();
        }
        mailTruck = new MailTruck(gamePad);
        wall = new Wall();
        world = new World();
    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        mailTruck.update();
        for (Republican republican : republicans) {
            republican.update(mailTruck.getCenterX(), mailTruck.getCenterY());
            if (republican.intersectWith(mailTruck)) {
                republican.dealDamage(mailTruck);
            }
        }
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
        wall.draw(buffer);
    }
}
