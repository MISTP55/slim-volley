package com.slimvolley.slimvolley.domain;

import com.slimvolley.slimvolley.SlimVolleyGame;
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

    public Slim(float x, float y, float width, float height) {
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
        this.fixtureDefinition.restitution = 0.0f;
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

    public void jump() {
        this.body.applyLinearImpulse(new Vec2(0, 15.0f), this.body.getWorldCenter());
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.isMoving = false;
        this.direction = 0;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if(isMoving()) {
            this.body.setLinearVelocity(new Vec2(12.0f * this.direction, this.body.getLinearVelocity().y));
        } else {
            this.body.setLinearVelocity(new Vec2(0, this.body.getLinearVelocity().y));
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillArc(
                (this.body.getPosition().x - this.fixtureDefinition.shape.m_radius) * SlimVolleyGame.PIXEL_RATE,
                -(this.body.getPosition().y + this.fixtureDefinition.shape.m_radius) * SlimVolleyGame.PIXEL_RATE,
                this.fixtureDefinition.shape.m_radius * 2 * SlimVolleyGame.PIXEL_RATE,
                this.fixtureDefinition.shape.m_radius * 2 * SlimVolleyGame.PIXEL_RATE,
                180,
                0);

    }
}