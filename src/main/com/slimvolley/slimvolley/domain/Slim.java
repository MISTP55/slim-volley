package com.slimvolley.slimvolley.domain;

import org.newdawn.slick.*;

public class Slim extends Entity
{
    private boolean isMoving;
    private int direction;

    public Slim(int x, int y, int width, int height) {
        super(x, y, width, height);
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
            if(this.x + (this.direction * delta) + this.width > 640) {
                setMoving(false);
            }
        }

        if(this.direction == -1) {
            if(this.x - (this.direction * delta) < 0) {
                setMoving(false);
            }
        }

        if(isMoving()) {
            this.x += this.direction * delta;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillArc(this.x, this.y, this.width, this.height, 180, 0);
    }
}