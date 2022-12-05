#include "Pit.h"


Pit::Pit() {

}
Pit::Pit(string name) {
	hazardName = name;
}
Pit::~Pit() {
}

string Pit::getHazardName() {
	return hazardName;
}

string Pit::hazardWarning() {
	return "You feel a draft. ";
}