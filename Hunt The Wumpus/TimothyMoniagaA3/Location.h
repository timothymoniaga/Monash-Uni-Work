/**************************************************
 Project: Assignment 2, hunt the wumpus prototype
 Author: Timothy Moniaga
 Purpose: Location class header file
 declarations for all the location class code
**************************************************/

#pragma once

#ifndef LOCATION_H
#define LOCATION_H

#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <vector>
#include <fstream>
#include "Hazards.h"
#include "Player.h"

using namespace std;

class Location {

public:

	Location();
	Location(string locationNumbers, string name, string direction);
	~Location();

	string getLocation();
	string getExits();
	string getLocationDetails(Player player, string clues);
	int getLocationNumber();
	Location* getNorth();
	Location* getNorthEast();
	Location* getEast();
	Location* getSouthEast();
	Location* getSouth();
	Location* getSouthWest();
	Location* getWest();
	Location* getNorthWest();
	string extractDirection();
	string splitDirections(string direction, string text);
	Hazards* hazardExists();
	Player* getCharacter();

	void setNorth(Location* destination);
	void setNorthEast(Location* destination);
	void setEast(Location* destination);
	void setSouthEast(Location* destination);
	void setSouth(Location* destination);
	void setSouthWest(Location* destination);
	void setWest(Location* destination);
	void setNorthWest(Location* destination);
	void addHazardToLoc(Hazards* hazard);
	void removeWumpus();
	void removeCharacter();
	void addCharacterToLoc(Player* character);



private:

	string locationName, exitDirection, locationNumbers;
	Location* north = nullptr;
	Location* northEast = nullptr;
	Location* east = nullptr;
	Location* southEast = nullptr;
	Location* south = nullptr;
	Location* southWest = nullptr;
	Location* west = nullptr;
	Location* northWest = nullptr;
	Hazards* hazards = nullptr;

	Player* roamingCharacter = nullptr;
};


#endif