package com.slimvolley.slimvolley;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.slimvolley.slimvolley.domain.*;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
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

    public SlimVolleyGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.slim = new Slim(3.0f, 8.0f, 1.5f, 1.5f);
        this.ball = new Ball(3.0f, 4.0f, 0.5f, 0.5f);

        this.slim.init(container);
        this.ball.init(container);

        Vec2 gravity = new Vec2(0, -20.0f);
        this.world = new World(gravity);

        this.timeStep = 1.0f / 60.0f;
        this.velocityIterations = 6;
        this.positionIterations = 2;

        this.slim.setBody(world.createBody(slim.getBodyDefinition()));
        this.ball.setBody(world.createBody(ball.getBodyDefinition()));


        // Bordures
        Vec2[] vs = new Vec2[4];
        vs[0] = new Vec2(0, 0);
        vs[1] = new Vec2(0, -(SlimVolleyGame.WINDOW_HEIGHT / SlimVolleyGame.PIXEL_RATE));
        vs[2] = new Vec2(SlimVolleyGame.WINDOW_WIDTH / SlimVolleyGame.PIXEL_RATE, -(SlimVolleyGame.WINDOW_HEIGHT / SlimVolleyGame.PIXEL_RATE));
        vs[3] = new Vec2(SlimVolleyGame.WINDOW_WIDTH / SlimVolleyGame.PIXEL_RATE, 0);

        ChainShape bShape = new ChainShape();
        bShape.createLoop(vs, 4);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = bShape;
        fDef.density = 100.f;

        BodyDef bDef = new BodyDef();
        bDef.position.set(0.0f, 0.0f);
        bDef.type = BodyType.STATIC;
        Body walls = world.createBody(bDef);
        walls.createFixture(fDef);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        container.setMinimumLogicUpdateInterval(20);
        container.setMaximumLogicUpdateInterval(20);

        this.slim.update(container, delta);
        this.ball.update(container, delta);

        this.world.step(timeStep, velocityIterations, positionIterations);
    }

    @Override
    public void render(GameContainer container, Graphics graphics) throws SlickException {
        this.slim.render(container, graphics);
        this.ball.render(container, graphics);

        Vec2[] line = this.slim.getArcVertices(3);
        //graphics.drawLine(0,0+150, line[0].x *100,(line[0].y*100)+150);
        for (int i = 0; i< line.length - 1;i++){
            graphics.drawLine(line[i].x*100,(line[i].y*100)+150,line[i+1].x*100,(line[i+1].y*100)+150);
        }
        //graphics.drawLine(0,0+150, line[0].x *100,(line[0].y*100)+150);
        graphics.drawLine(line[line.length-1].x*100,(line[line.length-1].y*100)+150,line[0].x*100,(line[0].y*100)+150);

        /*graphics.drawLine(line[0].x*100,(line[0].y*100)+150,line[1].x*100,(line[1].y*100)+150);
        graphics.drawLine(line[1].x*100,(line[1].y*100)+150, 150f,0+150);*/



        int x = SlimVolleyGame.PIXEL_RATE;
        int y = SlimVolleyGame.PIXEL_RATE;
        for(int m = 1; m < SlimVolleyGame.WINDOW_WIDTH / PIXEL_RATE; m++) {
            graphics.drawString("|" + m + "m", x, 0);
            graphics.drawString(" " + m * PIXEL_RATE + "px", x, 20);
            x += SlimVolleyGame.PIXEL_RATE;
        }
        for(int m = 1; m < SlimVolleyGame.WINDOW_HEIGHT / PIXEL_RATE; m++) {
            graphics.drawString("¯", 0, y);
            graphics.drawString(m + "m ", 0, y + 10);
            graphics.drawString(m * PIXEL_RATE + "px", 0, y + 30);
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