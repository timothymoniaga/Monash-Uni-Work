#pragma once

#ifndef FALLEN_H
#define FALLEN_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Player.h"




using namespace std;

class Fallen : public Player
{
protected:


public:

	Fallen();
	Fallen(string name);
	~Fallen();

	virtual string CharacterWarning();
	virtual string getPlayerName();




};
#endif
