package com.MichaelPearcey.RomwellBuddy;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public class Sprite {

    protected String path;
    protected int xPos;
    protected int yPos;
    protected float xRes;
    protected float yRes;
    protected  float RATIO = 1;
    protected float alpha = 0;
    protected boolean fadeIn = true;
    protected static boolean fadeFinish = false;
    protected Texture tex;

    public Sprite(String path, int xPos, int yPos) {
        this.path = path;
        this.xPos = xPos;
        this.yPos = yPos;
        tex = loadTexture();
        xRes = tex.getImageWidth();
        yRes = tex.getImageHeight();
    }
    public static void setFadeFinish(boolean a) {
        fadeFinish = a;
    }
    public float getRatio() {
        return RATIO;
    }

    public void setRatio(float ratio) {
        RATIO = ratio;
    }

    public String getPath() {
        return path;
    }

    public void setxRes(float xRes) {
        this.xRes = xRes;
    }

    public void setyRes(float yRes) {
        this.yRes = yRes;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public float getxRes() {
        return xRes;
    }

    public void setxRes(int xRes) {
        this.xRes = xRes;
    }

    public float getyRes() {
        return yRes;
    }

    public void setyRes(int yRes) {
        this.yRes = yRes;
    }

    public void draw() {
        glPushMatrix();
        tex.bind();
        glTranslatef(xPos, yPos, 0);
        glColor3f(1,1,1);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);                 			 glVertex2f(0, 0);
            glTexCoord2f(0, tex.getHeight());   			 glVertex2f(0, yRes);
            glTexCoord2f(tex.getWidth(), tex.getHeight()); 	 glVertex2f(xRes,yRes);
            glTexCoord2f(tex.getWidth(), 0);				 glVertex2f(xRes,0);
        }
        glEnd();

        glPopMatrix();
    }


    public void drawFade() {
        glPushMatrix();
        tex.bind();
        glTranslatef(xPos, yPos, 1);
        glColor4f(1,2,3,alpha);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2f(0, 0);
            glTexCoord2f(0, tex.getHeight());
            glVertex2f(0, yRes);
            glTexCoord2f(tex.getWidth(), tex.getHeight());
            glVertex2f(xRes,yRes);
            glTexCoord2f(tex.getWidth(), 0);
            glVertex2f(xRes,0);
        }
        glEnd();
        glPopMatrix();
        doubleFadeAlpha();
    }

    public void doubleFadeAlpha() {
        if(!fadeFinish) {
            if(alpha <= 1.2 && fadeIn) {
                alpha+=0.005;
            }else if(alpha >= -0.05){
                fadeIn = false;
                alpha-=0.005;
            } else {
                fadeFinish = true;
            }
        }
    }

    protected boolean checkFadeStop() {
        return fadeFinish;
    }

    protected Texture loadTexture() {
        String fileType = path.substring(path.length() - 3);
        if(fileType.equals("peg"))
            fileType = "jpeg";
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(fileType, in);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return tex;
    }

}
