package com.slimvolley.slimvolley.domain;

import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

public class Slim extends Entity implements InputProviderListener {
    private Command jump = new BasicCommand("jump");

    public Slim(int x, int y) {
        super(x, y);
    }

    public void controlPressed(Command command) {
        if(command.equals(jump)) {
            this.y -= 1;
        }
    }

    public void controlReleased(Command command) {
    }
}
