#pragma once

#ifndef GHOST_H
#define GHOST_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Player.h"




using namespace std;

class Ghost : public Player
{
protected:


public:

	Ghost();
	Ghost(string name);
	~Ghost();

	virtual string CharacterWarning();
	virtual string getPlayerName();




};
#endif
