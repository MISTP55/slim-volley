package com.slimvolley.slimvolley.domain;

import com.slimvolley.slimvolley.SlimVolleyGame;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Floor extends Entity
{
    public Floor(float x, float y, float width, float height) {
        super(x, y, width, height);

        this.bodyDefinition = new BodyDef();
        this.bodyDefinition.position.set(x, -y);
        this.bodyDefinition.type = BodyType.STATIC;

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width / 2, height / 2);

        this.fixtureDefinition = new FixtureDef();
        this.fixtureDefinition.shape = ps;
        this.fixtureDefinition.density = 0.5f;
        this.fixtureDefinition.friction = 0.0f;
        this.fixtureDefinition.restitution = 1.0f;
    }

    @Override
    public void init(GameContainer container) throws SlickException { }

    @Override
    public void update(GameContainer container, int delta) throws SlickException { }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillRect(
                (this.body.getPosition().x - (this.width / 2)) * SlimVolleyGame.PIXEL_RATE,
                -(this.body.getPosition().y + (this.height / 2)) * SlimVolleyGame.PIXEL_RATE,
                this.width * SlimVolleyGame.PIXEL_RATE,
                this.height * SlimVolleyGame.PIXEL_RATE);
    }
}