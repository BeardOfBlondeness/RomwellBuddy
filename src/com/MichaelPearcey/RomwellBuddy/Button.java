package com.MichaelPearcey.RomwellBuddy;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Button extends Sprite {

    private final int offset = 20;
    private final int LEFT_MOUSE_BUTTON = 0;
    private float sizeOffsetX;
    private float sizeOffsetY;
    private int posOffsetX;
    private int posOffsetY;
    private float clickSizeOffsetX;
    private float clickSizeOffsetY;
    private int clickPosOffsetX;
    private int clickPosOffsetY;
    private float originXRes;
    private float originYRes;
    private int originX;
    private int originY;
    private boolean hovered = false;
    private boolean isMouseDown = false;
    private Runnable clickFunction;

    public Button(String path, int x, int y, Runnable clickFunction) {
        super(path, x, y);
        originXRes = xRes;
        originYRes = yRes;
        originX = xPos;
        originY = yPos;
        sizeOffsetX = xRes+(xRes/4);
        sizeOffsetY = yRes+(yRes/4);
        posOffsetX = (int)(xPos-(sizeOffsetX/8));
        posOffsetY = (int)(yPos-(sizeOffsetY/8));
        clickSizeOffsetX = xRes+(xRes/8);
        clickSizeOffsetY = yRes+(yRes/8);
        clickPosOffsetX = (int)(xPos-(clickSizeOffsetX/16));
        clickPosOffsetY = (int)(yPos-(clickSizeOffsetY/16));
        this.clickFunction = clickFunction;
    }

    public void drawButton() {
        this.draw();
        checkClick();
        hoverAnimation();
    }

    private void checkClick() {
        if(hovered && isMouseDown && !Mouse.isButtonDown(LEFT_MOUSE_BUTTON)) {
            clickAction();
        }
    }

    private void clickAction() {
        clickFunction.run();
    }

    private void hoverAnimation() {
        int x = Mouse.getX(), y = Display.getHeight()-Mouse.getY();
        if(x > xPos-offset && y > yPos-offset && x < xPos+xRes+offset && y < yPos+yRes+offset) {
            if(!hovered) {
                setHoveredSizeAndLocation();
                hovered = true;
            }
            if(Mouse.isButtonDown(LEFT_MOUSE_BUTTON) && !isMouseDown) {
                toggleMouseDown();
                isMouseDown = true;
            } else if(!Mouse.isButtonDown(LEFT_MOUSE_BUTTON) && isMouseDown) {
                toggleMouseDown();
                isMouseDown = false;
            }
        }else if(hovered) {
            if(isMouseDown) {
                toggleMouseDown();
                isMouseDown = false;
            }
            setOriginalSizeAndLocation();
            hovered = false;
        }
    }

    private void toggleMouseDown() {
        if(!isMouseDown) {
            setxRes(clickSizeOffsetX);
            setyRes(clickSizeOffsetY);
            setxPos(clickPosOffsetX);
            setyPos(clickPosOffsetY);
        } else if(!hovered) {
            setOriginalSizeAndLocation();
        } else {
            setHoveredSizeAndLocation();
        }
    }

    private void setHoveredSizeAndLocation() {
        setxRes(sizeOffsetX);
        setyRes(sizeOffsetY);
        setxPos(posOffsetX);
        setyPos(posOffsetY);
    }

    private void setOriginalSizeAndLocation() {
        setxRes(originXRes);
        setyRes(originYRes);
        setxPos(originX);
        setyPos(originY);
    }
}
