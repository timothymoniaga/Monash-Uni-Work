#include "Wumpus.h"

Wumpus::Wumpus() {

}
Wumpus::Wumpus(string name) {
	hazardName = name;
}
Wumpus::~Wumpus() {
}

string Wumpus::getHazardName() {
	return hazardName;
}

string Wumpus::hazardWarning() {
	return "You smell the Wumpus! ";
}