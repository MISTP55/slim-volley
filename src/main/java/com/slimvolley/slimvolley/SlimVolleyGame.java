package com.slimvolley.slimvolley;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.slimvolley.slimvolley.domain.Ball;
import com.slimvolley.slimvolley.domain.Entity;
import com.slimvolley.slimvolley.domain.Slim;
import com.slimvolley.slimvolley.domain.SlimController;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.*;

public class SlimVolleyGame extends BasicGame
{
    private World world;

    private Slim slim;
    private Ball ball;

    public SlimVolleyGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.slim = new Slim(500, 700, 100, 100);
        this.ball = new Ball(530, 400, 40, 40);

        this.slim.init(container);
        this.ball.init(container);

        KeyListener slimControler = new SlimController(this.slim);
        container.getInput().addKeyListener(slimControler);

        Vec2 gravity = new Vec2(0, -1.0f);
        this.world = new World(gravity);

        slim.setBody(world.createBody(slim.getBodyDefinition()));
        ball.setBody(world.createBody(ball.getBodyDefinition()));
    }

    @Override
    public void update(GameContainer container, int i) throws SlickException {
        this.slim.update(container, i);
        this.ball.update(container, i);

        float timeStep = 1.0f / 60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;

        this.world.step(timeStep, velocityIterations, positionIterations);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        this.slim.render(container, graphics);
        this.ball.render(container, graphics);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SlimVolleyGame("Slim Volley"));
            appgc.setDisplayMode(1366, 768, false);
            appgc.start();
        }
        catch (SlickException ex) {
            Logger.getLogger(SlimVolleyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}