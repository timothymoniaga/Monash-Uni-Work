#include "Goblin.h"


Goblin::Goblin() {

}
Goblin::Goblin(string name) {
	playerName = name;
}
Goblin::~Goblin() {

}

string Goblin::getPlayerName() {
	return playerName;
 }
string Goblin::CharacterWarning() {
	return "";
}