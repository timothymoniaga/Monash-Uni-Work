#pragma once

#ifndef BANDIT_H
#define BANDIT_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Player.h"




using namespace std;

class Bandit : public Player
{
protected:


public:

	Bandit();
	Bandit(string name);
	~Bandit();

	virtual string CharacterWarning();
	virtual string getPlayerName();




};
#endif
