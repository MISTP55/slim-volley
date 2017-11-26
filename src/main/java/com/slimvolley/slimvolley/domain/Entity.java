package com.slimvolley.slimvolley.domain;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Entity
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected Body body;
    protected BodyDef bodyDefinition;
    protected FixtureDef fixtureDefinition;

    public static final int PIXEL_RATE = 1;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public float getXInMeters() {
        return x * (1.0f / PIXEL_RATE);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setXInMeters(float x) {
        this.x = (int) (x * PIXEL_RATE);
    }

    public int getY() {
        return y;
    }

    public float getYInMeters() {
        return y * (1.0f / PIXEL_RATE);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setYInMeters(float y) {
        this.y = (int) (y * PIXEL_RATE);
    }

    public int getWidth() {
        return width;
    }

    public float getWidthInMeters() {
        return this.width * (1.0f / PIXEL_RATE);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setWidthInMeters(float width) {
        this.width = (int) (width * PIXEL_RATE);
    }

    public int getHeight() {
        return height;
    }

    public float getHeightInMeters() {
        return this.height * (1.0f / PIXEL_RATE);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHeightInMeters(float height) {
        this.height = (int) (height * PIXEL_RATE);
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
