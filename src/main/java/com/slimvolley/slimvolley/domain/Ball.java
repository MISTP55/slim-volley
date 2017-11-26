package com.slimvolley.slimvolley.domain;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Ball extends Entity
{
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.bodyDefinition = new BodyDef();
        this.bodyDefinition.position.set(getXInMeters() + (getWidthInMeters() / 2), -getYInMeters() + (getHeightInMeters()  / 2));
        this.bodyDefinition.type = BodyType.DYNAMIC;

        CircleShape cs = new CircleShape();
        cs.m_radius = (getWidthInMeters() / 2);

        this.fixtureDefinition = new FixtureDef();
        this.fixtureDefinition.shape = cs;
        this.fixtureDefinition.density = 0.5f;
        this.fixtureDefinition.friction = 0.5f;
        this.fixtureDefinition.restitution = 0.2f;
    }

    @Override
    public void init(GameContainer container) throws SlickException { }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        setXInMeters(this.getBody().getPosition().x);
        setYInMeters(-this.getBody().getPosition().y);

        System.out.println(this.getBody().getPosition().y);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillOval(this.x, this.y, this.width, this.height);
    }
}