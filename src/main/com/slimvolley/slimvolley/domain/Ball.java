package com.slimvolley.slimvolley.domain;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Ball extends Entity
{
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void init(GameContainer container) throws SlickException { }

    @Override
    public void update(GameContainer container, int delta) throws SlickException { }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.fillOval(this.x, this.y, this.width, this.height);
    }
}