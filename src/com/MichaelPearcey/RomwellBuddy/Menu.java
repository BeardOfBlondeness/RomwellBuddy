package com.MichaelPearcey.RomwellBuddy;

public class Menu {

    private Sprite bg;
    private Sprite title;
    private Button play;
    private Button exit;

    public Menu() {
        bg = new Sprite("res/menu/Background.bmp", 0, 0);
        title = new Sprite("res/menu/Title.png", 100, 50);
        play = new Button("res/menu/Play.png", 300, 300, new Runnable() {
            @Override
            public void run() {
                //TODO Add play function
            }
        });
        exit = new Button("res/menu/Exit.png", 300, 480, new Runnable() {
            @Override
            public void run() {
                Main.killAllGame();
            }
        });
    }

    public void run() {
        bg.draw();
        title.draw();
        play.drawButton();
        exit.drawButton();
    }
}
