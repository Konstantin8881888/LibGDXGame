package xyz.belochka.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;

    public Bullet() {
        this.texture = new Texture("bullet.png");
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.active = false;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x-32, position.y-32);
    }
    public void activate(float x, float y, float vx, float vy)
    {
        this.active = true;
        this.position.set(x, y);
        this.velocity.set(vx, vy);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate()
    {
        active = false;
    }

    public void update(float dt){
        position.mulAdd(velocity, dt);
        if (position.x < 0.0f || position.x > Gdx.graphics.getWidth() || position.y < 0.0f || position.y > Gdx.graphics.getHeight()){
            deactivate();
        }
    }
}
