package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.MyMainGame;
import org.graalvm.compiler.word.Word;
import com.badlogic.gdx.utils.Array;


public class Player {

    Sprite sprite;
    Body body;
    World world;

    SpriteBatch batch;
    //transform
    float x, y;
    Fixture fixture;

    //property
    float speed;
    
    public Player(Texture image, SpriteBatch batch, float w, float h) {
        sprite = new Sprite(image);
        sprite.setSize(w, h);
        this.batch = batch;
    }

    public void setBody(World world) {
        this.world = world;

        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(100, 300);
        def.bullet = true;

        body = world.createBody(def);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(sprite.getWidth()/ 2, sprite.getHeight() / 2);

        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;

        fixture = body.createFixture(fixtureDef);
        polygonShape.dispose();

        body.setLinearDamping(0.2f);
    }

    public void setPosition(float x, float y) {
        body.getPosition().set(x, y);
    }
    public Vector2 getPosition(){
        return  new Vector2(x,y);
    }
    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x = 70;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x = -70;
        } else {
            x = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && isPlayerGrounded()) {
            y += 20f;
        }else{
            y= body.getLinearVelocity().y;
        }

        body.setLinearVelocity(x, y);

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.draw(batch);
    }
    private boolean isPlayerGrounded() {
        Array<Contact> contactList = world.getContactList();
        for(int i = 0; i < contactList.size; i++) {
            Contact contact = contactList.get(i);

            if(contact.isTouching() && (contact.getFixtureA() == fixture ||
                    contact.getFixtureB() == fixture)) {

//                Vector2 pos = body.getPosition();
//                WorldManifold manifold = contact.getWorldManifold();
//                boolean below = true;
//                for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
//                    below &= (manifold.getPoints()[j].y < pos.y - 1.5f);
//                }

                return true;
            }
        }
        return false;
    }
}
