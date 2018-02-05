package ru.geekbrains.stargame.star;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class Star extends Sprite {

    private final Vector2 v = new Vector2();
    private Rect worldBounds;
    private float enterHeight;

    public Star(TextureAtlas atlas, float vx, float vy, float height) {
        super(atlas.findRegion("star"));
        v.set(vx, vy);
        enterHeight = height;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (checkAndHandleBounds()) {
            pos.set(Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight()), pos.y);
            setHeightProportion(Rnd.nextFloat( enterHeight/ 2, enterHeight * 2));
        }
    }

    protected boolean checkAndHandleBounds() {
//        if (getRight() < worldBounds.getLeft()) {
//            setLeft(worldBounds.getRight());
//            return true;
//        }
//        if (getLeft() > worldBounds.getRight()) {
//            setRight(worldBounds.getLeft());
//            return true;
//        }
        if (getTop() < worldBounds.getBottom()) {
            setBottom(worldBounds.getTop()+Rnd.nextFloat(0,1f));
            return true;
        }
//        if (getBottom() > worldBounds.getTop()) {
//            setTop(worldBounds.getBottom());
//            return true;
//        }
        return false;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;

    }

    public void reset(){
        setHeightProportion(Rnd.nextFloat(enterHeight / 2, enterHeight * 2));
        float posX = Rnd.nextFloat(worldBounds.getLeft(), worldBounds.getRight());
//        float posX = worldBounds.getLeft() + 0.2f;
        float posY = Rnd.nextFloat(worldBounds.getBottom(), worldBounds.getTop());
//        float posY = worldBounds.getTop();
        pos.set(posX, posY);
    }
}
