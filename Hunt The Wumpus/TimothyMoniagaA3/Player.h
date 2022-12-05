/**************************************************
 Project: Assignment 2, hunt the wumpus prototype
 Author: Timothy Moniaga
 Purpose: Player Header Class File
 declarations for all the player class code
**************************************************/

#pragma once



#ifndef PLAYER_H
#define PLAYER_H

#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <vector>
#include <fstream>
#include "Enums.h"
//#include "Goblin.h"

using namespace std;

class Player {
public:
	Player();
	Player(string name,difficulty level);
	//Player(string name, charType type);
	~Player();

	virtual string getPlayerName();
	virtual string CharacterWarning();
	int getLanternFuel();
	int getArrows();
	int getDifficulty();
	int getMaxLanternFuel();
	int getMaxArrows();
	charType getCharacterType();

	void setPlayerName(string name);
	void setDifficulty();
	void setLanternFuel(int num);
	void setArrows(int num);
	void changeLanternFuel(int num);
	void shootArrow();
	int getArrowsUsed();
	string getPlayerInfo();



protected:
	string playerName;
	int lanternFuel, maxLanternFuel;
	int arrows, maxArrows;
	int arrowsUsed = 0;
	difficulty difficultyLevel;
	charType characterType;
	
};


#endif