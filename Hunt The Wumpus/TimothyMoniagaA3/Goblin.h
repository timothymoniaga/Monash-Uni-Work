#pragma once

#ifndef GOBLIN_H
#define GOBLIN_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Player.h"




using namespace std;

class Goblin : public Player
{
protected:


public:

	Goblin();
	Goblin(string name);
	~Goblin();

	virtual string getPlayerName();
	virtual string CharacterWarning();



};
#endif
