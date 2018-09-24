#pragma once
#include <cassert>
#include "Global.h"
using namespace std;

class Sprite {
public:
    Sprite();
    Sprite(string path);
    Sprite(string path, int x, int y);
    Sprite(string path, int x, int y, int width, int height);
    void init(string path, int x, int y, int width, int height);
    void init(string path, int x, int y);
    void init(string path);
    ~Sprite();
    void setRect(int x, int y, int width, int height);
    void InitiateSprite();
    void Draw();
    void setSize(float x, float y);
    void setPos(int x, int y);
    int getXRect();
    int getYPos();
    int getXPos();
protected:
    sf::Texture tex;
    sf::Sprite renderSprite;
    string path;
    int x, y, width, height, xRect, yRect;
};
