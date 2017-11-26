package com.slimvolley.slimvolley;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.slimvolley.slimvolley.domain.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;

public class SlimVolleyGame extends BasicGame
{
    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;

    public static final int PIXEL_RATE = 100;

    private World world;

    private float timeStep;
    private int velocityIterations;
    private int positionIterations;

    private Slim slim;
    private Ball ball;
    private Floor floor;

    public SlimVolleyGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.slim = new Slim(3.0f, 8.0f, 1.5f, 1.5f);
        this.ball = new Ball(3.0f, 4.0f, 0.4f, 0.4f);
        this.floor = new Floor(8.0f, 9.0f, 17.0f, 0.5f);

        this.slim.init(container);
        this.ball.init(container);
        this.floor.init(container);

        KeyListener slimControler = new SlimController(this.slim);
        container.getInput().addKeyListener(slimControler);

        Vec2 gravity = new Vec2(0, -9.8f);
        this.world = new World(gravity);

        this.timeStep = 1.0f / 60.0f;
        this.velocityIterations = 6;
        this.positionIterations = 2;

        this.slim.setBody(world.createBody(slim.getBodyDefinition()));
        this.ball.setBody(world.createBody(ball.getBodyDefinition()));
        this.floor.setBody(world.createBody(floor.getBodyDefinition()));
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        container.setMinimumLogicUpdateInterval(20);

        this.slim.update(container, delta);
        this.ball.update(container, delta);
        this.floor.update(container, delta);

        this.world.step(timeStep, velocityIterations, positionIterations);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        this.slim.render(container, graphics);
        this.ball.render(container, graphics);
        this.floor.render(container, graphics);

        int x = SlimVolleyGame.PIXEL_RATE;
        int y = SlimVolleyGame.PIXEL_RATE;
        for(int m = 1; m < SlimVolleyGame.WINDOW_WIDTH; m++) {
            graphics.drawString(m + "m", x, 0);
            x += SlimVolleyGame.PIXEL_RATE;
        }
        for(int m = 1; m < SlimVolleyGame.WINDOW_HEIGHT; m++) {
            graphics.drawString(m + "m", 0, y);
            y += SlimVolleyGame.PIXEL_RATE;
        }
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlimVolleyGame("Slim Volley"));
            appgc.setDisplayMode(SlimVolleyGame.WINDOW_WIDTH, SlimVolleyGame.WINDOW_HEIGHT, false);
            appgc.start();
        }
        catch (SlickException ex) {
            Logger.getLogger(SlimVolleyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}