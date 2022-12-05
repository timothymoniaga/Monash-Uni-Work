#include "Ghost.h"

Ghost::Ghost() {

}
Ghost::Ghost(string name) {
	playerName = name;
}
Ghost::~Ghost() {

}

string Ghost::getPlayerName() {
	return playerName;
}
string Ghost::CharacterWarning() {
	return "You feel someone is watching you. ";
}
