package com.slimvolley.slimvolley.domain;

import com.slimvolley.slimvolley.SlimVolleyGame;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Entity
{
    protected float width;
    protected float height;

    protected Body body;
    protected BodyDef bodyDefinition;
    protected FixtureDef fixtureDefinition;

    public Entity(float x, float y, float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        this.body.createFixture(this.fixtureDefinition);
    }

    public BodyDef getBodyDefinition() {
        return bodyDefinition;
    }

    public void setBodyDefinition(BodyDef bodyDefinition) {
        this.bodyDefinition = bodyDefinition;
    }

    public FixtureDef getFixtureDefinition() {
        return fixtureDefinition;
    }

    public void setFixtureDefinition(FixtureDef fixtureDefinition) {
        this.fixtureDefinition = fixtureDefinition;
    }

    public abstract void init(GameContainer container) throws SlickException;

    public abstract void update(GameContainer container, int delta) throws SlickException;

    public abstract void render(GameContainer gc, Graphics g) throws SlickException;
}
