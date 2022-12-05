/**************************************************
 Project: Assignment 2, hunt the wumpus prototype
 Author: Timothy Moniaga
 Purpose: ApplicationDriver Header Class File
 Class to run the entire program calling location and player class
**************************************************/
#pragma once

#ifndef APPLICATIONDRIVER_H
#define APPLICATIONDRIVER_H

#include <iostream>
#include <sstream>
#include <string>
#include <time.h>
#include <cstring>
#include <vector>
#include <fstream>
#include <algorithm>
#include "Location.h"
#include "Player.h"
#include "Hazards.h"
#include "Pit.h"
#include "Bats.h"
#include "Wumpus.h"
#include "HazardGenerator.h"
#include "Enums.h"

using namespace std;

HazardGenerator* generator = nullptr;
Player player;
Location* currentLocation;
vector <Location*> locations;
bool banditFlag = true;
void readTextFile(string fileName);
Player newPlayer();
void playTheGame();
Location* addLocation();
void displayLocation(Location* current);
Location* changeLocation(Location* currentLocation, int direction);
void assignDirections();
void deleteLocations(vector <Location*> loc);
bool shootDirection( int direction);
void movement();
int mappingInputs(string input);
void displayInterface();
void makeGenerator();
void makeHazards();
string strToUpper(string input);
void errorMessege();
string checkAdjacentLocation(Location* curLoc);
bool hazardEffects();
void wumpusRun(string name, string objType);
string cheatMode(string searchObject, string characterOrHazard);
void score();
void generateRoamingCharacters();
string warnings(Location* nxtLocation);
void ghostStory();
void printObjectLocations();

#endif