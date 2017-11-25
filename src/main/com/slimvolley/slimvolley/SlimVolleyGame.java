package com.slimvolley.slimvolley;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.slimvolley.slimvolley.domain.Ball;
import com.slimvolley.slimvolley.domain.Slim;
import com.slimvolley.slimvolley.domain.SlimController;
import org.newdawn.slick.*;

public class SlimVolleyGame extends BasicGame
{
    private Slim slim;
    private Ball ball;

    public SlimVolleyGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        this.slim = new Slim(200, 300, 100, 100);
        this.ball = new Ball(250, 50, 40, 40);

        this.slim.init(container);
        this.ball.init(container);

        KeyListener slimControler = new SlimController(this.slim);
        container.getInput().addKeyListener(slimControler);
    }

    @Override
    public void update(GameContainer container, int i) throws SlickException {
        this.slim.update(container, i);
        this.ball.update(container, i);
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
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex) {
            Logger.getLogger(SlimVolleyGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}