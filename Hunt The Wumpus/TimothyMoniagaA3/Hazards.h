#pragma once


#ifndef HAZARDS_H
#define HAZARDS_H

#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <vector>
#include <fstream>

using namespace std;

class Hazards {
public:

	Hazards();
	Hazards(string name);
	~Hazards();

	//string getHazardName();

	//void setHazardName(string name);
	virtual string getHazardName();
	virtual string hazardWarning();
	/*friend class Location;
	friend ostream& operator<<(ostream& os, const Hazards& aHazards);*/


protected:
	string hazardName;
};

#endif