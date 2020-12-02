package cegepst;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.entity.StaticEntity;

import java.awt.*;

public class Wall extends StaticEntity {

    public Wall() {
        teleport(400,200);
        setDimension(50, 200);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, Color.WHITE);
    }
}
