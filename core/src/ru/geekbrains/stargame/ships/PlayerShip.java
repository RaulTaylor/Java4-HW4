package ru.geekbrains.stargame.ships;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

/**
 * Created by raultaylor on 04.02.18.
 */

public class PlayerShip extends Sprite {
    private Rect worldBounds;
    private float speed;
    private final int KEY_LEFT =21;
    private final int KEY_RIGHT =22;
    private final boolean MOVE_SHIP = true;
    private final boolean STOP_SHIP = false;
    private boolean isMoveToRight;
    private boolean isMoveToLeft;

    public PlayerShip(TextureAtlas atlas, float height, float speed){
        super(atlas.findRegion("main_ship").split(195,287)[0][0]);
        setHeightProportion(height);
        pos.set(0,0);
        this.speed = speed;
    }

    public void update(float delta){
        if(isMoveToLeft){
            pos.x-=speed*delta;
        }
        if(isMoveToRight){
            pos.x+=speed*delta;
        }
        checkBounds();
    }

    private void checkBounds(){
        if(getRight()>worldBounds.getRight()){
            setRight(worldBounds.getRight());
        }
        if(getLeft()<worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
        }
    }

    public void keyUp(int keyCode){

        if(KEY_LEFT == keyCode){
            isMoveToLeft = STOP_SHIP;
        }
        if(KEY_RIGHT == keyCode){
            isMoveToRight = STOP_SHIP;
        }
    }

    public void keyDown(int keyCode){

        if(KEY_LEFT == keyCode){
            isMoveToLeft = MOVE_SHIP;
        }
        if(KEY_RIGHT == keyCode){
            isMoveToRight = MOVE_SHIP;
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom()+0.02f);
        super.resize(worldBounds);
    }
}
