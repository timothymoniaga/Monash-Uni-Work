#include "Bats.h"


Bats::Bats() {

}
Bats::Bats(string name) {
	hazardName = name;
}
Bats::~Bats() {
}

string Bats::getHazardName() {
	return hazardName;
}

string Bats::hazardWarning() {
	return "Sounds like Super Bats nearby. ";
}