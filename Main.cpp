#pragma GCC diagnostic push

// save the current state for diagnostics
#pragma GCC diagnostic ignored "-Wunused-parameter"
#define SFML_STATIC
#include <stdlib.h>
#include "Global.h"
using namespace std;

//Initiates the window and window events
sf::RenderWindow window(sf::VideoMode(1920, 1080), "Romwell Buddy");
sf::Event event;

/*
* contains the game loop
*/
int main()
{
    window.setFramerateLimit(60);
    while (window.isOpen())
    {
        while (window.pollEvent(event))
        {
            if (event.type == sf::Event::Closed)
                window.close();
        }
        window.clear();
        //Do work here
        window.display();
    }
    return 0;
}
