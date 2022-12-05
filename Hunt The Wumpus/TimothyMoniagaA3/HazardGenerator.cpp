#include "HazardGenerator.h"

HazardGenerator* HazardGenerator::instance = nullptr;

HazardGenerator::HazardGenerator() {
}

HazardGenerator* HazardGenerator::getInstance() {
	if (instance == nullptr) {
		instance = new HazardGenerator();
	}
	else {
		cout << "A Hazard generator already exists. So we're using that.";
	}
	return instance;
}

void HazardGenerator::createHazard() {
	//cout << "creating a Hazard";
}

Hazards* HazardGenerator::createHazard(string name, int num) {
	Hazards* newHazard = nullptr;

	switch (num) {
	case 0: 
		newHazard = new Wumpus(name);
		break;

	case 1:
		newHazard = new Bats(name);
		break;

	case 2:
		newHazard = new Pit(name);
		break;

	default:
		cout << "Invalid selection.";
	}

	return newHazard;

}

Player* HazardGenerator::createCharacter(string name, int num) {
	Player* newCharacter = nullptr;

	switch (num) {
	case 0:
		newCharacter = new Goblin(name);
		break;

	case 1:
		newCharacter = new Fallen(name);
		break;

	case 2:
		newCharacter = new Ghost(name);
		break;

	case 3:
		newCharacter = new Bandit(name);
		break;

	default:
		cout << "Invalid selection.";
	}

	return newCharacter;
}
