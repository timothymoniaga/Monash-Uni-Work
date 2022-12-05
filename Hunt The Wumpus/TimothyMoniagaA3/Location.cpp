/**************************************************
 Project: Assignment 2, hunt the wumpus prototype
 Author: Timothy Moniaga
 Purpose: Sets and gets the location details and direction destinations
**************************************************/


#include "Location.h"

Location::Location() {
}
Location::Location(string location, string name, string direction) {
	locationName = name;
	exitDirection = direction;
	locationNumbers = location;
}
Location::~Location() {
}

string Location::getLocation() {
	return locationName;
}

int Location::getLocationNumber() {
	return stoi(locationNumbers);
}

string Location::getExits() {
	return exitDirection;
}

//for printing all details
string Location::getLocationDetails(Player player,string clues) {
	//string clues = "";
	//if (hazards != nullptr) {
	//	clues = hazards->hazardWarning();
	//}
	stringstream locationDetails;
	locationDetails << "\nLocation Name: " << locationName;
	locationDetails << "\nExits: " << exitDirection;
	locationDetails << "\nCharacters: " << player.getPlayerName();
	locationDetails << "\nHazard Clues: "<< clues; 
	locationDetails << "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	return locationDetails.str();
}

//extracts directions by removing 
// brackets from the exitDirection variable and 
//returns only the direction letter
//e.g E(2) S(3) W(4) is turned into E(2) 
//then further removed to "2" and returning just the number 
//as the function that uses it will input direction and be able to 
//assign the direction to the location number extracted from here
string Location::splitDirections(string direction, string text) {
	text = " " + text;
	direction = " " + direction + "(";
	string token = "";
	string number = "";

	//makes sure that there is something there if it is skipped 
	//it will return a blank and the next function will know that the exit 
	//does not exist in the location other wise it will send a string of the 
	//location number that the direction input is going to lead to 
	if (text.find(direction) != string::npos && direction.length() == 3) {
		if ((text.substr(text.find(direction), 5)).find(")") != string::npos) {
			token = text.substr(text.find(direction), 4);
		}
		else {
			token = text.substr(text.find(direction), 5);
		}
		number = token.substr(token.find("(") + 1, token.find(")") - 2);
	}
	if (text.find(direction) != string::npos && direction.length() == 4) {
		if ((text.substr(text.find(direction), 6)).find(")") != string::npos) {
			token = text.substr(text.find(direction), 5);
		}
		else {
			token = text.substr(text.find(direction), 6);
		}

		number = token.substr(token.find("(") + 1, token.find(")") - 3);
	}

	return number;
}

//this function compiles and sends the location data to the main function
//while they are all numbers it is returned as string to place the commas 
//for the main program to be able to decifer which direction goes to which location
//e.g E(2) S(3) W(4) will turn into "-1,2,3,4"
//It will also change the exit direction to be able to return just "E S W" for user readability
string Location::extractDirection() {

	string tempDirection = "";
	string retVal = "";
	string temp;

	//goes through each direction checking if there is the direction desired and if not will add a -1
	//which will be read in the main program as not having an exit in that direction
	temp = splitDirections("N", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "N ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("NE", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "NE ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("E", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "E ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("SE", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "SE ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}


	/***********************************************************/
	temp = splitDirections("S", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "S ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("SW", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "SW ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("W", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "W ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}

	temp = splitDirections("NW", exitDirection);
	if (temp.length() > 0) {
		tempDirection += "NW ";
		retVal += temp + ",";
	}
	else {
		retVal += "-1,";
	}
	exitDirection = tempDirection;

	return retVal;

}

void Location::setNorth(Location* destination) {
	north = destination;
}

Location* Location::getNorth() {
	return north;
}

void Location::setNorthEast(Location* destination) {
	northEast = destination;
}

Location* Location::getNorthEast() {
	return northEast;
}

void Location::setEast(Location* destination) {
	east = destination;
}

Location* Location::getEast() {
	return east;
}

void Location::setSouthEast(Location* destination) {
	southEast = destination;
}

Location* Location::getSouthEast() {
	return southEast;
}

void Location::setSouth(Location* destination) {
	south = destination;
}

Location* Location::getSouth() {
	return south;
}

void Location::setSouthWest(Location* destination) {
	southWest = destination;
}

Location* Location::getSouthWest() {
	return southWest;
}

void Location::setWest(Location* destination) {
	west = destination;
}

Location* Location::getWest() {
	return west;
}

void Location::setNorthWest(Location* destination) {
	northWest = destination;
}

Location* Location::getNorthWest() {
	return northWest;
}

void Location::addHazardToLoc(Hazards* hazard) {
	hazards = hazard;
}

//string Location::getHazardsContent() {
//	string content = "";
//	if (hazards.empty()) {
//		content = "No hazards present";
//	}
//	else {
//		for (int i = 0; i < hazards.size(); i++) {
//			content += hazards[i]->getHazardName();
//		}
//	}
//	return content;
//}

//int Location::getNumOfHazards() {
//	return hazards.size();
//}

Hazards* Location::hazardExists() {
	return hazards;
}

void Location::removeWumpus() {
	hazards = nullptr;
}

Player* Location::getCharacter() {
	return roamingCharacter;
}

void Location::removeCharacter() {
	roamingCharacter = nullptr;
}

void Location::addCharacterToLoc(Player* character) {
	roamingCharacter = character;
}