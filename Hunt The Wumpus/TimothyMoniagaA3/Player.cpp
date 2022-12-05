/**************************************************
 Project: Assignment 3, hunt the wumpus
 Author: Timothy Moniaga
 Purpose: Player class to create new players
**************************************************/


#include "Player.h"


Player::Player() {
}

Player::Player(string name, difficulty level) {
	playerName = name;
	difficultyLevel = level;
	setDifficulty();
	maxArrows = arrows;
	maxLanternFuel = lanternFuel;
}

//Player::Player(string name, charType type) {
//	playerName = name;
//	characterType = type;
//}

Player::~Player() {
}

//makes sure that the name entered is not empty
string Player::getPlayerName() {
	string retVal = "";

	if (playerName.length() > 0) {
		retVal = playerName;
	}
	else {
		retVal = "Please enter a name";
	}

	return retVal;
}

int Player::getLanternFuel() {
	return lanternFuel;
}

int Player::getArrows() {
	return arrows;
}

void Player::setPlayerName(string name) {
	playerName = name;
}

void Player::changeLanternFuel(int num) {
	lanternFuel += num;
}

void Player::shootArrow() {
	arrows--;
	arrowsUsed++;
}

string Player::getPlayerInfo() {

	stringstream playerDetails;
	playerDetails << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	playerDetails << "\nPlayer Name: " << playerName;
	playerDetails << "\nLantern Fuel: " << lanternFuel;
	playerDetails << "\nArrows: " << arrows;
	playerDetails << "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	return playerDetails.str();
}

int Player::getDifficulty() {
	switch (difficultyLevel) {
	case EASY:
		return 1;

	case MEDIUM:
		return 2;

	case HARD:
		return 3;

	default:
		break;
	}
}

void Player::setDifficulty() {
	switch (difficultyLevel) {
	case EASY:
		lanternFuel = 60;
		arrows = 7;
		break;

	case MEDIUM:
		lanternFuel = 50;
		arrows = 5;
		break;

	case HARD:
		lanternFuel = 40;
		arrows = 3;
		break;

	default:
		break;
	}
}

int Player::getArrowsUsed() {
	return arrowsUsed;
}

string Player::CharacterWarning() {
	return "default warning";
}

void Player::setLanternFuel(int num) {
	lanternFuel = num;
}

void Player::setArrows(int num) {
	arrows = num;
}

int Player::getMaxArrows() {
	return maxArrows;
}

int Player::getMaxLanternFuel() {
	return maxLanternFuel;
}