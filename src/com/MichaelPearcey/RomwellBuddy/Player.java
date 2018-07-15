package com.MichaelPearcey.RomwellBuddy;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

public class Player {

    private final int runningSpriteCount = 6;
    private final int idleSpriteCount = 2;
    private final int acceleration = 1;
    private final int speedCap = 20;
    private int speed = 0;
    private int direction = 2;
    private int currentTextureIndex = 0;
    private int framesElapsed = 0;
    private Texture[] running;
    private Texture[] idle;
    private Sprite playerSprite;
    private boolean isRunning = false;
    private Point playerLocation;

    public Player() {
        running = new Texture[runningSpriteCount];
        idle = new Texture[idleSpriteCount];
        playerLocation = new Point();
        playerLocation.setLocation(500, 100);
        loadSpriteTextures();
        playerSprite = new Sprite(running[1], playerLocation.x, playerLocation.y);
    }

    private void loadSpriteTextures() {
        for(int i = 1; i <= runningSpriteCount; i++) {
            running[i-1] = Sprite.loadTexture("res/game/player/running/" + Integer.toString(i) + ".png");
        }
        for(int i = 1; i <= idleSpriteCount; i++) {
            idle[i-1] = Sprite.loadTexture("res/game/player/idle/" + Integer.toString(i) + ".png");
        }
    }

    public void run() {
        keyBindings();
        move();
        playerSprite.draw();
    }

    private void keyBindings() {
        direction = 4;
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            direction = 1;
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            direction = 3;
        if(Keyboard.isKeyDown(Keyboard.KEY_UP))
            direction = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
            direction = 2;
    }

    private void move() {
        switch(direction) {
            case 0:
                playerLocation.y = playerLocation.y - speed;
                break;
            case 1:
                playerLocation.x = playerLocation.x + speed;
                break;
            case 2:
                playerLocation.y = playerLocation.y + speed;
                break;
            case 3:
                playerLocation.x = playerLocation.x - speed;
                break;
            case 4:
                speed = 0;
                break;
        }
        accelerate();
        playerSprite.setxPos(playerLocation.x);
        playerSprite.setyPos(playerLocation.y);
    }

    private void accelerate() {
        if(speed < speedCap && direction != 4)
            speed+=acceleration;
        updateSpriteTexture();
    }

    private void updateSpriteTexture() {
        framesElapsed++;
        if(framesElapsed > (25-speed) && speed > 0) {
            if(currentTextureIndex < 5)
                currentTextureIndex++;
            else
                currentTextureIndex = 0;
            playerSprite.setTex(running[currentTextureIndex]);
            framesElapsed = 0;
        }
    }
}
