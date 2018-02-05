package ru.geekbrains.stargame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.org.apache.regexp.internal.RE;

import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.star.Star;

/**
 * Created by raultaylor on 04.02.18.
 */

public class StarsController {
    private Star[][] stars;
    private Rect worldBounds;
    private boolean firstStart;

    public StarsController(TextureAtlas atlas, float maxSpeed, int countLayer, int countStars, float height){
        stars = new Star[countLayer][countStars];
        for(int i=0;i<countLayer;i++){
            for(int j=0;j<countStars;j++){
                stars[i][j] = new Star(atlas,0, -(maxSpeed/(countLayer-i)),height/(countLayer-i));
            }
        }
        firstStart = true;
        worldBounds = new Rect();
    }

    public void update(float delta){
        for(Star[] arr: stars){
            for(Star star: arr){
                star.update(delta);
            }
        }
    }

    public void reset(){
        for(Star[] arr: stars){
            for(Star star: arr){
                star.reset();
            }
        }
    }



    public void draw(SpriteBatch spriteBatch){
        for(Star[] arr: stars){
            for(Star star: arr){
                star.draw(spriteBatch);
            }
        }
    }

    public void resize(Rect worldBounds){
        for(Star[] arr: stars){
            for(Star star: arr){
                star.resize(worldBounds);
            }
        }
    }

    public void setWorldBounds(Rect worldBounds){
        this.worldBounds.set(worldBounds);
        if(firstStart){
            resize(worldBounds);
            reset();
            firstStart = false;
        }else{
            resize(worldBounds);
        }
    }
}
