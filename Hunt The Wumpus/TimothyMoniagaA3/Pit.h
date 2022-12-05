#pragma once

#ifndef PIT_H
#define PIT_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Hazards.h"




using namespace std;

class Pit : public Hazards
{
protected:


public:

	Pit();
	Pit(string name);
	~Pit();

	virtual string getHazardName();
	virtual string hazardWarning();



};

#endif
