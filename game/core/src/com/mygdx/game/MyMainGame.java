package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.ConnectServer.ConnectionToServer;
import com.mygdx.game.player.Player;


public class MyMainGame extends ApplicationAdapter {
    SpriteBatch batch;
    World world;
    Player player;

    Body wall;
    Box2DDebugRenderer debugRenderer;

    OrthographicCamera camera;
    public static final float PIXEL_TO_METER = 1/32F;
    @Override
    public void create() {
        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        float h = Gdx.graphics.getHeight();
        float w = Gdx.graphics.getWidth();

        camera = new OrthographicCamera();

        world = new World(new Vector2(0, -15), true);

        batch = new SpriteBatch();
        player = new Player(new Texture("Idle (1).png"), batch, 100,100);
        player.setBody(world);
        player.setPosition(100, 300);

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(0, 0);
        wall = world.createBody(def);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(w, 10, new Vector2(0, 0), 0);

        wall.createFixture(polygonShape, 1f);
        polygonShape.dispose();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(Gdx.graphics.getDeltaTime(), 6, 4);
        batch.begin();
        player.update();
        batch.end();
        debugRenderer.render(world, camera.combined);
        camera.update();
    }



    @Override
    public void dispose() {
        batch.dispose();
    }
}
