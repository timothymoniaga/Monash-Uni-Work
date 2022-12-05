#include "Fallen.h"


Fallen::Fallen() {

}
Fallen::Fallen(string name) {
	playerName = name;
}
Fallen::~Fallen() {

}

string Fallen::getPlayerName() {
	return playerName;
}
string Fallen::CharacterWarning() {
	return "You feel the presence of something holy. ";
}