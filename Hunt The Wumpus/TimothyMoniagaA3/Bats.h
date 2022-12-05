#pragma once
#ifndef BATS_H
#define BATS_H


#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include "Hazards.h"




using namespace std;

class Bats : public Hazards
{
protected:
	
	

public:
	Bats();
	Bats(string name);
	~Bats();

	virtual string getHazardName();
	virtual string hazardWarning();


};

#endif