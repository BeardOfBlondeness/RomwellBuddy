package com.MichaelPearcey.RomwellBuddy;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Main {
    private final int FWIDTH = 1500, FHEIGHT=900; // Frame parameters
    private int WIDTH; // Screen width
    private int HEIGHT; // Screen height
    private int trueWidth; // Screen width full screen
    private int trueHeight; // Screen height full screen
    private int viewPortStartX; // X start coordinate of viewport
    private int viewPortStartY; // y start coordinate of viewport
    private Texture cursorTexture; // Texture for the cursor replacement
    private boolean fullScreen; // holding if the game is fullscreen or not

    /*
    Creates the window frame for Romwell
     */
    public Main() {
        try {
            createDisplay();
            update();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the display.
     */
    public void createDisplay() throws org.lwjgl.LWJGLException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int)screenSize.getWidth();
        HEIGHT = (int)screenSize.getHeight();
        trueWidth = WIDTH;
        trueHeight = HEIGHT;
        fullScreen = false;
        if(WIDTH > (HEIGHT/9)*16)
            trueWidth = (HEIGHT/9)*16;
        else
            trueHeight = (WIDTH/16)*9;
        viewPortStartX = (WIDTH - trueWidth)/2;
        viewPortStartY = (HEIGHT - trueHeight)/2;
        Display.setTitle("Romwell Buddy");
        Display.setDisplayMode(new DisplayMode(FWIDTH, FHEIGHT));
        Display.create();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, FWIDTH, FHEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Starts the game loop.
     */
    public void update() throws LWJGLException {
        Menu menu = new Menu();
        while(!Display.isCloseRequested())
        {
            glClear(GL_COLOR_BUFFER_BIT);
            if(fullScreen)
                holdFullScreenMouse();
            mainKeyListeners();
            menu.run();
            Display.update();
            Display.sync(60);
        }
        killAllGame();
    }

    /**
     * Destroys the game.
     */
    public static void killAllGame() {
        Display.destroy();
        System.exit(0);
    }

    /*
    Creates the constant key listeners (ones that will always apply to the game, regardless of level)
     */
    public void mainKeyListeners() {

    }


    public void holdFullScreenMouse() {
        if(Mouse.getX() > WIDTH) Mouse.setCursorPosition(900, Mouse.getY());
        if(Mouse.getY() > HEIGHT) Mouse.setCursorPosition(Mouse.getX(), 540);
    }

    public static void main(String[] args) {
        new Main();
    }
}