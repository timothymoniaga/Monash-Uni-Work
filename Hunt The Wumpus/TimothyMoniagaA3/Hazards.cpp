

#include "Hazards.h"


Hazards::Hazards(){

}
Hazards::Hazards(string name) {
	hazardName = name;
}
Hazards::~Hazards() {
}

string Hazards::getHazardName() {
	return hazardName;
}

//string Hazards::getHazardName() {
//	return "default hazard";
//}
string Hazards::hazardWarning() {
	return "default hazard warning";
}