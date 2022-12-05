#pragma once

#ifndef HAZARDGENERATOR_H
#define HAZARDGENERATOR_H

#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Wumpus.h"
#include "Pit.h"
#include "Bats.h"
#include "Hazards.h"
#include "Player.h"
#include "Goblin.h"
#include "Fallen.h"
#include "Bandit.h"
#include "Ghost.h"

using namespace std;

class HazardGenerator
{
private:
	HazardGenerator();
	static HazardGenerator* instance;

public:
	static HazardGenerator* getInstance();
	void createHazard();
	Hazards* createHazard(string name, int num);
	Player* createCharacter(string name, int num);


};
#endif

