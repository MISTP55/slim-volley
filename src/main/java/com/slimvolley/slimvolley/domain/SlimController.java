package com.slimvolley.slimvolley.domain;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class SlimController implements KeyListener {
    private Slim slim;

    public SlimController(Slim slim) {
        this.slim = slim;
    }

    @Override
    public void keyPressed(int key, char c) {
        
        switch (key) {
            case Input.KEY_W:
                break;
            case Input.KEY_A:
                this.slim.setMoving(true);
                this.slim.setDirection(-1);
                break;
            case Input.KEY_D:
                this.slim.setMoving(true);
                this.slim.setDirection(1);
                break;
        }

    }

    @Override
    public void keyReleased(int key, char c) {
        this.slim.setMoving(false);
    }

    @Override
    public void setInput(Input input) { }

    @Override
    public void inputEnded() { }

    @Override
    public void inputStarted() { }

    @Override
    public boolean isAcceptingInput() { return true; }
}