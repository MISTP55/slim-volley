package com.slimvolley.slimvolley.domain;

import com.slimvolley.slimvolley.SlimVolleyGame;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Ball extends Entity
{
    public Ball(float x, float y, float width, float height) {
        super(x, y, width, height);

        this.bodyDefinition = new BodyDef();
        this.bodyDefinition.position.set(x, -y);
        this.bodyDefinition.type = BodyType.DYNAMIC;

        CircleShape cs = new CircleShape();
        cs.m_radius = width / 2;

        this.fixtureDefinition = new FixtureDef();
        this.fixtureDefinition.shape = cs;
        this.fixtureDefinition.density = 1.0f;
        this.fixtureDefinition.friction = 0.0f;
        this.fixtureDefinition.restitution = 1.0f;
    }

    @Override
    public void init(GameContainer container) throws SlickException { }

    @Override
    public void update(GameContainer container, int delta) throws SlickException { }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillOval(
                (this.body.getPosition().x - this.fixtureDefinition.shape.m_radius) * SlimVolleyGame.PIXEL_RATE,
                -(this.body.getPosition().y + this.fixtureDefinition.shape.m_radius) * SlimVolleyGame.PIXEL_RATE,
                this.fixtureDefinition.shape.m_radius * 2 * SlimVolleyGame.PIXEL_RATE,
                this.fixtureDefinition.shape.m_radius * 2 * SlimVolleyGame.PIXEL_RATE);
    }
}