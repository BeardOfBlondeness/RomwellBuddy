package com.MichaelPearcey.RomwellBuddy;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Button extends Sprite {

    private final int offset = 20;
    private float sizeOffsetX;
    private float sizeOffsetY;
    private float originXRes;
    private float originYRes;
    private int posOffsetX;
    private int posOffsetY;
    private int originX;
    private int originY;
    private boolean hovered = false;

    public Button(String path, int x, int y) {
        super(path, x, y);
        originXRes = xRes;
        originYRes = yRes;
        originX = xPos;
        originY = yPos;
        sizeOffsetX = xRes+(xRes/4);
        sizeOffsetY = yRes+(yRes/4);
        posOffsetX = (int)(xPos-(sizeOffsetX/8));
        posOffsetY = (int)(yPos-(sizeOffsetY/8));
    }

    public void drawButton() {
        this.draw();
        int x = Mouse.getX(), y = Display.getHeight()-Mouse.getY();
        if(x > xPos-offset && y > yPos-offset && x < xPos+xRes+offset && y < yPos+yRes+offset) {
            if(!hovered) {
                setxRes(sizeOffsetX);
                setyRes(sizeOffsetY);
                setxPos(posOffsetX);
                setyPos(posOffsetY);
                hovered = true;
            }
        }else if(hovered) {
            setxRes(originXRes);
            setyRes(originYRes);
            setxPos(originX);
            setyPos(originY);
            hovered = false;
        }
    }
}
