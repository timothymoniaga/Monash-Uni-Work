#pragma once


#ifndef WUMPUS_H
#define WUMPUS_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Hazards.h"




using namespace std;

class Wumpus : public Hazards
{
protected:


public:
	Wumpus();
	Wumpus(string name);
	~Wumpus();

	virtual string getHazardName();
	virtual string hazardWarning();


};

#endif