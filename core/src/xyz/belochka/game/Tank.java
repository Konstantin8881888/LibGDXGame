package xyz.belochka.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

public class Tank {
    private MyGdxGame game;
    private Texture texture;
    private Texture textureTurret;
    private Vector2 position;
    private float speed;
    private float angle;
    private float turretAngle;

    public Tank(MyGdxGame game){
        this.game = game;
        this.texture = new Texture("tank3.png");
        this.textureTurret = new Texture("tank3Turret.png");
        this.position = new Vector2(40.0f, 30.0f);
        this.speed = 100.0f;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x-32, position.y-32, 32, 32, 64, 64, 1, 1, angle, 0, 0, 64, 64, false, false);
        batch.draw(textureTurret, position.x-32, position.y-32, 32, 32, 64, 64, 1, 1, turretAngle, 0, 0, 64, 64, false, false);
    }
    public void update(float dt)
    {
        checkMovements(dt);
        float mx = Gdx.input.getX();
        float my = Gdx.graphics.getHeight() - Gdx.input.getY();
        float angleTo = Utils.getAngle(position.x, position.y, mx, my);
        turretAngle = Utils.makeRotation(turretAngle, angleTo, 180.0f, dt);
        turretAngle = Utils.angleToFromNegPiToPosPi(turretAngle);
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            fire();
        }
    }
    public void checkMovements(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            position.x += speed * dt;
            angle = 0.0f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            position.x -= speed * dt;
            angle = 180.0f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
        {
            position.y -= speed * dt;
            angle = 270.0f;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            position.y += speed * dt;
            angle = 90.0f;
        }
    }

    public void fire(){
        if (!game.getBullet().isActive()){
            float angleRad = (float) Math.toRadians(angle);
            game.getBullet().activate(position.x, position.y, 200.0f * (float) Math.cos(angleRad), 200.0f * (float) Math.sin(angleRad));
        }
    }
}
