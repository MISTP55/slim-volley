package com.slimvolley.slimvolley;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.slimvolley.slimvolley.domain.Slim;
import org.newdawn.slick.*;
import org.newdawn.slick.command.*;

public class SimpleSlickGame extends BasicGame
{
    private Input input;
    private InputProvider provider;
    private Command jump = new BasicCommand("jump");

    private Slim slim;

    public SimpleSlickGame(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        input = container.getInput();
        slim = new Slim(100, 100);

        provider = new InputProvider(container.getInput());
        provider.addListener(slim);

        provider.bindCommand(new KeyControl(Input.KEY_W), jump);

    }

    @Override
    public void update(GameContainer container, int i) throws SlickException {
        if(container.getInput().isKeyDown(Input.KEY_A)) {
            slim.setX(slim.getX() - 1);
        }
        if(container.getInput().isKeyDown(Input.KEY_S)) {
            slim.setY(slim.getY() + 1);
        }
        if(container.getInput().isKeyDown(Input.KEY_D)) {
            slim.setX(slim.getX() + 1);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawOval(slim.getX(), slim.getY(), 100, 100);
        g.drawString(message,100,150);
    }

    private String message = "";

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new SimpleSlickGame("Slim Volley"));
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}