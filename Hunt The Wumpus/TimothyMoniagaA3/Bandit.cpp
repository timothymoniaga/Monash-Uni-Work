#include "Bandit.h"

Bandit::Bandit() {

}
Bandit::Bandit(string name) {
	playerName = name;
}
Bandit::~Bandit() {

}

string Bandit::getPlayerName() {
	return playerName;
}
string Bandit::CharacterWarning() {
	return "You hear heavy footsteps. ";
}
