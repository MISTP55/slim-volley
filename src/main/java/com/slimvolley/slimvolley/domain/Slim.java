package com.slimvolley.slimvolley.domain;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.*;

public class Slim extends Entity
{
    private boolean isMoving;
    private int direction;

    public Slim(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.bodyDefinition = new BodyDef();
        this.bodyDefinition.position.set(getXInMeters() + (getWidthInMeters() / 2), -getYInMeters() - (getHeightInMeters()  / 2));
        this.bodyDefinition.type = BodyType.DYNAMIC;

        CircleShape cs = new CircleShape();
        cs.m_radius = (getWidthInMeters() / 2);

        this.fixtureDefinition = new FixtureDef();
        this.fixtureDefinition.shape = cs;
        this.fixtureDefinition.density = 0.5f;
        this.fixtureDefinition.friction = 0.5f;
        this.fixtureDefinition.restitution = 0.5f;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.isMoving = false;
        this.direction = 0;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(this.direction == 1) {
            if(this.x + (this.direction * delta) + this.width > 1366) {
                setMoving(false);
            }
        }

        if(this.direction == -1) {
            if(this.x - (this.direction * delta) < 0) {
                setMoving(false);
            }
        }

        if(isMoving()) {
            this.body.setLinearVelocity(new Vec2(40 * this.direction * delta, 0));
        } else {
            this.body.setLinearVelocity(new Vec2(0, 0));
        }

        setXInMeters(this.getBody().getPosition().x);
        setYInMeters(-this.getBody().getPosition().y);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillArc(this.x, this.y, this.width, this.height, 180, 0);
    }
}