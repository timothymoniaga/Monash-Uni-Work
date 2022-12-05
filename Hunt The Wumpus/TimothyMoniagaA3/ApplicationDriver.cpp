/**************************************************
 Project: Assignment 2, hunt the wumpus prototype
 Author: Timothy Moniaga
 Purpose: ApplicationDriver
 Class to run the entire program calling location and player class
**************************************************/

#include "ApplicationDriver.h"


int main() {


	srand(time(NULL));
	currentLocation = addLocation();
	assignDirections();


	readTextFile("WumpusAbout.txt");
	system("pause");
	system("CLS");
	readTextFile("HelpMenu.txt");
	system("pause");
	system("CLS");
	newPlayer();
	makeGenerator();
	makeHazards();
	generateRoamingCharacters();
	system("pause");
	system("CLS");


	playTheGame();
	deleteLocations(locations);


	return 0;
}

//creates new player (attributes are not set up yet as this is just a prototype,
//atributes like oil and arrows will be implimented in next assignment)
Player newPlayer() {


	char difficulty = 0;

	//difficulty level;
	stringstream newPlayer;
	newPlayer << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	newPlayer << "\n                              Hunt the Wumpus ";
	newPlayer << "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	cout << newPlayer.str();

	string name;
	cout << "\nEnter player name: ";
	cin >> name;

	//checks what difficulty is selected
	cout << "Select a difficulty\nDifficulties: Rookie[1], Hunter[2], Master Hunter[3]: ";
	bool flag = false;
	while (!flag) {
		cin >> difficulty;
		switch (difficulty) {
		case '1':
			player = Player(name, EASY);
			flag = true;
			break;

		case '2':
			player = Player(name, MEDIUM);
			flag = true;
			break;

		case '3':
			player = Player(name, HARD);
			flag = true;
			break;

		default:
			cout << "\nEnter one of the 3 numbers above: ";
		}
		
	}
	//player = Player(name);
	cout << "Welcome " << player.getPlayerName() << "\n" << endl;

	return player;

}

//to read and print a traditional text file
void readTextFile(string fileName) {
	ifstream fileToRead;
	fileToRead.open(fileName);

	if (fileToRead.is_open()) {
		string myData = "";
		while (!fileToRead.eof())
		{
			getline(fileToRead, myData);
			cout << myData << endl;
		}
		fileToRead.close();
	}
	else
	{
		cout << "File not found\n";
	}

}

//adds all locations into the Location pointer vector 
//Each line in text file looks like this: 1,Cave Entrance,E(2) S(3) W(4),
//where the first number is the location number spereated by a comma
//is the location name and finally the exit directions and what number they lead to
//3 variables are created one for each of the attributes of the location and placed
//into the constructor and added to the vector also checks each line to ensure that the line
//is not empty if it is then it will end as it will have reached the end of the file and all 
//locations have been added
Location* addLocation() {

	ifstream fileToRead("Cave Layout ver3.txt");
	if (fileToRead.is_open())
	{
		string locationNumber = "";
		string locationName = "";
		string locationExits = "";
		while (!fileToRead.eof())
		{

			getline(fileToRead, locationNumber, ',');
			getline(fileToRead, locationName, ',');
			getline(fileToRead, locationExits, ',');
			if (locationNumber.length() > 0 && locationName.length() > 0 && locationExits.length() > 0) {
				locations.push_back(new Location(locationNumber, locationName, locationExits));
				locationNumber = "";
				locationName = "";
				locationExits = "";
			}

		}
	}
	else {
		cout << "\n File not found!\n";
	}
	fileToRead.close();

	return locations[0];

}


void displayLocation(Location* current) {

	system("CLS");
	displayInterface();
	cout << current->getLocationDetails(player, checkAdjacentLocation(currentLocation)) << endl;

}

//assigns all the correct directions to each room pointing to the correct room
void assignDirections() {
	string direction;

	//repeats for all the locations and gets all room numbers in a known format N,E,S,W (for E(2) S(3) W(4) = "-1,2,3,4"
	for (int i = 0; i < locations.size(); i++) {
		direction = locations[i]->extractDirection();
		int counter = 0;
		//program knows that the first number will be for north and will use the correct switch case based on the counter
		while (counter < 8) {
			string temp = direction.substr(0, direction.find(","));
			direction.erase(0, direction.find(",") + 1);
			int entry = atoi(temp.c_str());
			//checks if exit direction exists first by making sure that the vale is not -1
			if (entry >= 0) {

				switch (counter)
				{
				case 0:
					locations[i]->setNorth(locations[entry - 1]);
					break;

				case 1:
					locations[i]->setNorthEast(locations[entry - 1]);
					break;

				case 2:
					locations[i]->setEast(locations[entry - 1]);
					break;

				case 3:
					locations[i]->setSouthEast(locations[entry - 1]);
					break;

				case 4:
					locations[i]->setSouth(locations[entry - 1]);
					break;

				case 5:
					locations[i]->setSouthWest(locations[entry - 1]);
					break;

				case 6:
					locations[i]->setWest(locations[entry - 1]);
					break;

				case 7:
					locations[i]->setNorthWest(locations[entry - 1]);
					break;

				default:
					break;
				}
			}
			counter++;
		}
	}
}

//maps directions so that inputs are easy to read
int mappingInputs(string input) {

	//0 = invalid
	//1 = N,
	//2 = E, 
	//3 = S,
	//4 = W
	//5 = NE
	//6 = NW
	//7 = SE
	//8 = SW
	//9 = quit
	//10 = help
	//11 = map
	//12 = shoot
	int retVal = 0;

	string map = " N E S W NE NW SE SW QUIT Q HELP H MAP M SHOOT C ";
	input = strToUpper(input);
	input = " " + input + " ";

	switch (map.find(input)) {
	case 0:
		retVal = 1;
		break;

	case 2:
		retVal = 2;
		break;

	case 4:
		retVal = 3;
		break;

	case 6:
		retVal = 4;
		break;

	case 8:
		retVal = 5;
		break;

	case 11:
		retVal = 6;
		break;

	case 14:
		retVal = 7;
		break;

	case 17:
		retVal = 8;
		break;

	case 20: case 25:
		retVal = 9;
		break;

	case 27: case 32:
		retVal = 10;
		break;

	case 34: case 38:
		retVal = 11;
		break;

	case 40:
		retVal = 12;
		break;

	case 46:
		retVal = 13;
		break;
	}

	return retVal;

}

//converts string to upercase
string strToUpper(string input) {

	transform(input.begin(), input.end(), input.begin(), ::toupper);
	return input;
}

//main function to run the entire game 
void playTheGame() {
	
	string directionArrow = "";
	bool exitFlag = false;
	string input = "";
	int output;
	Location* previous = nullptr;
	int counter = 0;
	int randWumpusMove = (rand() % 5) + 1;;

	//will coninuously run the program until the player decides to quit with 'Q'
	while (!exitFlag) {

		

		if (!exitFlag) {
			displayLocation(currentLocation);
			if (currentLocation->hazardExists() != nullptr || currentLocation->getCharacter() != nullptr) {
				exitFlag = hazardEffects();
			}
			
			cout << "Enter your command: ";
			cin >> input;
			//to ensure that a correct input is entered
			output = mappingInputs(input);

			//checks if the player wants to move or if they want to do another thing

			switch (output) {
			case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8:
				currentLocation = changeLocation(currentLocation, output);
				movement();
				//check if player has died
				exitFlag = hazardEffects();

				//moves bandit after character has moved
				wumpusRun("Bandit", "Character");

				//random wumpus movement
				if (randWumpusMove < 1) {
					randWumpusMove = (rand() % 5) + 1;
					wumpusRun("Wumpus", "Hazard");
				}
				else  {
					randWumpusMove--;
				}

				break;

			case 9:
				cout << "\nThank you for playing the game" << endl;
				system("pause");
				exitFlag = true;
				break;

			case 10:
				system("CLS");
				readTextFile("HelpMenu.txt");
				system("pause");
				break;

			case 11:
				system("CLS");
				readTextFile("WumpusMapM.txt");
				system("pause");
				break;

			case 12:
				
				randWumpusMove--;
				cout << "\nWhich direction would you like to shoot your arrow? ";
				cin >> directionArrow;
				//check if wumpus has been slain
				exitFlag = shootDirection( mappingInputs(directionArrow));

				//check if bandit has been slain
				if (banditFlag) {
					wumpusRun("Bandit", "Character");
				}
				
				break;

			case 13:

				printObjectLocations();
				system("pause");
				break;

			default:
				cout << "\nInvalid input try again, for help enter ('H'). \n" << endl;
				system("pause");
			}
			if (player.getLanternFuel() < 1) {
				cout << "\nYou have ran out of lantern oil." << endl;
				cout << "You wander around the caverns aimlessly until your death." << endl;
				cout << "You have died." << endl;
				exitFlag = true;
			}
			else if (player.getArrows() < 1) {
				cout << "\nYou have no more magical arrows!" << endl;
				cout << "The Wumpus finds you." << endl;
				cout << "You have died." << endl;
				exitFlag = true;
			}
			//system("CLS");
		}
	}
	score();
}

//error messege to reducdce repeats
void errorMessege() {
	cout << "Invalid direction, Enter direction from one of the above" << endl;
	system("pause");
}

//used for changing the players current position by using pointers 
Location* changeLocation(Location* location, int direction) {
	Location* previous = currentLocation;

	switch (toupper(direction))
	{
	case 1:
		if (location->getNorth() != nullptr) {
			return location->getNorth();
		}
		else {
			errorMessege();
			return previous;
		}
	case 2:
		if (location->getEast() != nullptr) {
			return location->getEast();
		}
		else {
			errorMessege();
			return previous;
		}

	case 3:
		if (location->getSouth() != nullptr) {
			return location->getSouth();
		}
		else {
			errorMessege();
			return previous;
		}

	case 4:
		if (location->getWest() != nullptr) {
			return location->getWest();
		}
		else {
			errorMessege();
			return previous;
		}

	case 5:
		if (location->getNorthEast() != nullptr) {
			return location->getNorthEast();
		}
		else {
			errorMessege();
			return previous;
		}

	case 6:
		if (location->getNorthWest() != nullptr) {
			return location->getNorthWest();
		}
		else {
			errorMessege();
			return previous;
		}

	case 7:
		if (location->getSouthEast() != nullptr) {
			return location->getSouthEast();
		}
		else {
			errorMessege();
			return previous;
		}

	case 8:
		if (location->getSouthWest() != nullptr) {
			return location->getSouthWest();
		}
		else {
			errorMessege();
			return previous;
		}

	default:
		return nullptr;
	}

}

//deletes all locations to prevent memory leak
void deleteLocations(vector <Location*> loc) {
	for (int whichOne = loc.size() - 1; whichOne >= 0; --whichOne) {
		delete loc[whichOne];
		loc[whichOne] = nullptr;
	}
}

void displayInterface() {

	stringstream userInterface;
	userInterface << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	userInterface << "\n                             Hunt the Wumpus ";
	userInterface << "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
	userInterface << "\nControls:      [Q] Quit        [M] Map        [H] Help        [Shoot] Shoot";
	userInterface << "\nInventory:     Arrows: " << player.getArrows() << "       Lantern Oil: " << player.getLanternFuel();
	userInterface << "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

	cout << userInterface.str();
}

//creates generator for hazards and players/characters
void makeGenerator() {
	generator->getInstance();

	generator->createHazard();
}

//creates and places hazards in random locations, hazards cannot be in the same location
//hazard number varies based on difficulty selected
void makeHazards() {
	int difficultyModifier = 2;
	if (player.getDifficulty() == 1) {
		difficultyModifier = 1;
	}
	else if (player.getDifficulty() == 2) {
		difficultyModifier = 2;
	}
	else if (player.getDifficulty() == 3) {
		difficultyModifier = 3;
	}

	int randomLocation = 0;
	int numOfBats = 0, numOfPits = 0;

	bool flag1 = false;

	while (!flag1 ) {
		randomLocation = rand() % (locations.size() - 1) + 1;

		if (locations[randomLocation]->hazardExists() == nullptr) {
			if (numOfBats < difficultyModifier) {
			
				locations[randomLocation]->addHazardToLoc(generator->createHazard("Super Bats", 1));
				numOfBats++;
			}
			else if (numOfPits < difficultyModifier) {
				locations[randomLocation]->addHazardToLoc(generator->createHazard("Bottomless Pit", 2));
				numOfPits++;
			}
			else if (!flag1) {
				locations[randomLocation]->addHazardToLoc(generator->createHazard("Wumpus", 0));
				flag1 = true;
			}
		}
	}
}

//reduces lanturn oil
void movement() {
	player.changeLanternFuel(-1);
}

//shoots direction in specified direction
bool shootDirection( int direction) {
	bool retVal = false;
	Location* arrowLoc = currentLocation;
	if (direction > 0 && direction < 9) {
		player.shootArrow();
		arrowLoc = changeLocation(arrowLoc, direction);
		//checks if there is a Wumpus in there and if there is kills wumpus and returns true so that the player wins
		if (arrowLoc->hazardExists() != nullptr && arrowLoc->hazardExists()->getHazardName() == "Wumpus"  ) {
			
			cout << "\n" << player.getPlayerName() << " has slain the Wumpus! Congratulations!!" << endl;
			locations[(arrowLoc->getLocationNumber()) - 1]->removeWumpus();
			retVal = true;
			
		}
		//checks if there is a Bandit in there and if there is kills the Bandit and gives rewards
		else if (arrowLoc->getCharacter() != nullptr && arrowLoc->getCharacter()->getPlayerName() == "Bandit") {

			cout << "You have taken the Bandit by suprise and killed him! " << endl;
			cout << "They had 5 arrows and 20 lantern oil on them" << endl;
			player.changeLanternFuel(20);
			player.setArrows(player.getArrows() + 5);
			locations[(arrowLoc->getLocationNumber()) - 1]->removeCharacter();
			banditFlag = false;

		}

		//if the arrow misses and there is a wumpus near he has a 75% chance to run to a random room
		else if (checkAdjacentLocation(currentLocation) == "You smell the Wumpus! ") {
			int v2 = rand() % 4;
			if (v2 < 3 ) {
				cout << "\nThe Wumpus heard your arrow and has run to a different room" << endl;
				wumpusRun("Wumpus","Hazard");
				
			}
			else {
				cout << "\n" << player.getPlayerName() << " has missed, but the Wumpus is still asleep" << endl;
			}
		}
		system("pause");
	}
	else {
		cout << "\nEnter a valid direction\n" << endl;
		system("pause");
	}
	return retVal;
}

//collects the warnings messeges from both hazards and characters
string warnings(Location* nxtLocation) {
	string str = "";
	if (nxtLocation->hazardExists() != nullptr) {
		str += nxtLocation->hazardExists()->hazardWarning();
	}
	if (nxtLocation->getCharacter() != nullptr) {
		str += nxtLocation->getCharacter()->CharacterWarning();
	}
	return str;
}

//checks adjacent location for hazards/characters
string checkAdjacentLocation(Location* curLoc ) {
	string str = "";
	for (int i = 1; i < 9; i++) {
		curLoc = currentLocation;
		switch (i) {
		case 1:
			if (curLoc->getNorth() != nullptr) {
				curLoc = curLoc->getNorth();
				str += warnings(curLoc);
			}
			break;

		case 2:
			if (curLoc->getNorthEast() != nullptr) {
				curLoc = curLoc->getNorthEast();

				
				str += warnings(curLoc);
				break;

		case 3:
			if (curLoc->getEast() != nullptr) {
				curLoc = curLoc->getEast();

			
				str += warnings(curLoc);
			}
			break;

		case 4:
			if (curLoc->getSouthEast() != nullptr) {
				curLoc = curLoc->getSouthEast();

				
				str += warnings(curLoc);
			}
			break;

		case 5:
			if (curLoc->getSouth() != nullptr) {
				curLoc = curLoc->getSouth();

				
				str += warnings(curLoc);
			}
			break;

		case 6:
			if (curLoc->getSouthWest() != nullptr) {
				curLoc = curLoc->getSouthWest();

				
				str += warnings(curLoc);
			}
			break;

		case 7:
			if (curLoc->getWest() != nullptr) {
				curLoc = curLoc->getWest();

			
				str += warnings(curLoc);
			}
			break;

		case 8:
			if (curLoc->getNorthWest() != nullptr) {
				curLoc = curLoc->getNorthWest();

				
				str += warnings(curLoc);
			}
			break;
			}
		}
	}


	return str;
}


//hazard effects
bool hazardEffects() {
	bool flag = false;
	if (currentLocation->hazardExists() != nullptr) {
		if (currentLocation->hazardExists()->getHazardName() == "Super Bats") {
			readTextFile("SuperBatArt.txt");
			cout << "\n\nSuper Bats have taken you away to a random room\n" << endl;
			system("pause");
			currentLocation = locations[rand() % locations.size()];

		}
		else if (currentLocation->hazardExists()->getHazardName() == "Bottomless Pit") {
			readTextFile("PitsArt.txt");
			cout << "\n\n" << player.getPlayerName() << " Has fallen into a Bottomless Pit!" << endl;
			cout << "You have died." << endl;
			flag = true;
		}
		else if (currentLocation->hazardExists()->getHazardName() == "Wumpus") {
			readTextFile("WumpusArt.txt");
			cout << "\n\n" << player.getPlayerName() << " Has run into the Wumpus!" << endl;
			cout << "You have died." << endl;
			flag = true;
		}
	}
	else if(currentLocation->getCharacter()!=nullptr) {
		if (currentLocation->getCharacter()->getPlayerName() == "Goblin") {
			readTextFile("GoblinArt.txt");
			cout << "\n\nSneaky goblins have snuck up on you and knocked you out!" << endl;
			cout << "They have taken half of your arrows and oil and dragged you to a random room\n" << endl;
			player.setLanternFuel((player.getLanternFuel()) / 2);
			player.setArrows((player.getArrows()) / 2);
			currentLocation = locations[rand() % locations.size()];
	

		}
		else if (currentLocation->getCharacter()->getPlayerName() == "Fallen") {
			readTextFile("FallenArt.txt");
			cout << "\n\n" << player.getPlayerName() << " Has stumbled into a fallen angel" << endl;
			cout << "They wish you the best and take you back to the start and give you some resources." << endl;
			player.changeLanternFuel(5);
			player.setArrows(player.getArrows() + 1);
			currentLocation = locations[0];
			

		}
		else if (currentLocation->getCharacter()->getPlayerName() == "Ghost") {
			readTextFile("GhostArt.txt");
			cout << "\n\nThe ghost of an old man aproaches you and wishes to talk to tell you his story" << endl;
			cout << "Do you wish to talk? [Y/N] ";
			ghostStory();
			locations[currentLocation->getLocationNumber() - 1]->removeCharacter();
	
		}
		else if (currentLocation->getCharacter()->getPlayerName() == "Bandit") {
			readTextFile("BanditArt.txt");
			cout << "\n\nA strong bandit that lives in the caves attacks you!" << endl;
			cout << "You fight back but are helpless against him. He steals everything you own except 1 arrow and 2 lantern oil" << endl;
			cout << "\"Better luck next time kid.\" He laughs as he leaves you" << endl;
			player.setArrows(1);
			player.setLanternFuel(2);
			
		}
		system("pause");
		displayLocation(currentLocation);
	}
	return flag;
}

//for wumpus/bandit movement
//checks all 8 directions for either wumpus or bandit then places adjacent locations in
//vector to be randomly selected later
void wumpusRun(string name, string objType) {

	Location* wumpus1 = nullptr;
	Location* wumpusOrigin = nullptr;
	vector <Location*> options;


	string cardianlDirections = "1 2 3 4 5 6 7 8 ";
	string temp1 = cardianlDirections.substr(0, cardianlDirections.find(" "));
	cardianlDirections.erase(0, cardianlDirections.find(" ") + 1);

	string locationNumber = cheatMode(name, objType);
	string temp = locationNumber.substr(0, locationNumber.find(" "));
	locationNumber.erase(0, locationNumber.find(" ") + 1);
	int x = 0;


	while (temp != "") {
		int locationInt = -1;
		locationInt= atoi(temp.c_str());
		if (locationInt > 0) {
			wumpusOrigin = locations[locationInt-1];
			wumpus1 = locations[locationInt-1];
			while (cardianlDirections != "") {
				x = atoi(temp1.c_str());
				switch (x)
				{
				case 1:
					if (wumpus1->getNorth() != nullptr) {
						options.push_back(wumpus1->getNorth());

					}

					break;
				case 2:
					if (wumpus1->getEast() != nullptr) {
						options.push_back(wumpus1->getEast());
					}

					break;
				case 3:
					if (wumpus1->getSouth() != nullptr) {
						options.push_back(wumpus1->getSouth());
					}
	
					break;
				case 4:
					if (wumpus1->getWest() != nullptr) {
						options.push_back(wumpus1->getWest());
					}

					break;
				case 5:
					if (wumpus1->getNorthEast() != nullptr) {
						options.push_back(wumpus1->getNorthEast());
					}

					break;
				case 6:
					if (wumpus1->getNorthWest() != nullptr) {
						options.push_back(wumpus1->getNorthWest());
					}

					break;
				case 7:
					if (wumpus1->getSouthEast() != nullptr) {
						options.push_back(wumpus1->getSouthEast());
					}

					break;
				case 8:
					if (wumpus1->getSouthWest() != nullptr) {
						options.push_back(wumpus1->getSouthWest());
					}
					break;


				}
				cardianlDirections.erase(0, cardianlDirections.find(" ") + 1);
				temp1 = cardianlDirections.substr(0, cardianlDirections.find(" "));

			}
		}

		locationNumber.erase(0, locationNumber.find(" ") + 1);
		temp = locationNumber.substr(0, locationNumber.find(" "));
	}

	int random = rand() % options.size() +1;
	int optionSelected = options[random-1]->getLocationNumber();
	int wumnpusRoom = wumpusOrigin->getLocationNumber();
	if (name == "Wumpus") {
		//int wumnpusRoom = wumpusOrigin->getLocationNumber();
		locations[wumnpusRoom - 1]->removeWumpus();
		locations[optionSelected - 1]->addHazardToLoc(generator->createHazard("Wumpus", 0));
	}
	else {
		//int BanditRoom = wumpusOrigin->getLocationNumber();
		locations[wumnpusRoom - 1]->removeCharacter();
		locations[optionSelected - 1]->addCharacterToLoc(generator->createCharacter("Bandit", 3));
	}
	
	

}


//returns location of hazard/character by iterating through entire locaions vector
string cheatMode(string searchObject, string characterOrHazard) {
	string retVal = "";
	string temp = "";
	int x;

	for (int i = 0; i < locations.size(); i++) {
		if (characterOrHazard == "Hazard") {
			if (locations[i]->hazardExists() != nullptr && locations[i]->hazardExists()->getHazardName() == searchObject) {
				x = locations[i]->getLocationNumber();
				temp = to_string(x);
				retVal = retVal + temp + " ";
			}
		}
		else {
			if (locations[i]->getCharacter() != nullptr && locations[i]->getCharacter()->getPlayerName() == searchObject) {
				x = locations[i]->getLocationNumber();
				temp = to_string(x);
				retVal = retVal + temp + " ";
			}
		}
	}
	return retVal;
}

//calculation for end score based on resources used and difficulty selected.
void score() {
	int score = 0;

	if (player.getArrowsUsed() == 1) {
		score += 100;
	}
	else if (player.getArrowsUsed() > 1 && player.getArrowsUsed() < 4) {
		score += 50;
	}
	else {
		score += 10;
	}

	score += 110 - player.getLanternFuel();
	score *= (player.getDifficulty())*2;
	score -= 20;
	if (cheatMode("Wumpus", "Hazard") != "") {
		score = 0;
	}
	if (score < 0) {
		score = 0;
	}

	cout << "\n" << player.getPlayerName() << " has achieved a score of: " << score << "/1000" << endl;
}

//creates random characters in random locations that cannot be in the same 
//location as eachother and cannot be in the same location as a hazard
//there will always be 1 bandit reguardless of difficulty
void generateRoamingCharacters() {
	int randomCharacter = 0, randomLocation = 0;
	int counter = 0;
	bool flag = false;
	while (counter < player.getDifficulty() + 1) {
		randomCharacter = rand() % 3;
		randomLocation = rand() % (locations.size() - 1) + 1;
		if (locations[randomCharacter]->getCharacter() == nullptr && locations[randomLocation]->hazardExists() == nullptr) {
			switch (randomCharacter) {
				case 0:
					locations[randomLocation]->addCharacterToLoc(generator->createCharacter("Goblin", 0));
					counter++;
					break;

				case 1:
					locations[randomLocation]->addCharacterToLoc(generator->createCharacter("Fallen", 1));
					counter++;
					break;

				case 2:
					locations[randomLocation]->addCharacterToLoc(generator->createCharacter("Ghost", 2));
					counter++;
					break;

			}
			randomLocation = rand() % (locations.size() - 1) + 1;
			if (!flag) {
				locations[randomLocation]->addCharacterToLoc(generator->createCharacter("Bandit", 3));

				flag = true;
			}
		}
	}
}

//ghost effects
void ghostStory() {
	char answer;
	
	bool flag = false;
	

	while (!flag) {
		cin >> answer;
		answer = toupper(answer);
		if (answer == 'Y') {
			system("CLS");
			readTextFile("GhostStory.txt");
			player.setArrows(player.getMaxArrows());
			player.setLanternFuel(player.getMaxLanternFuel());
			flag = true;
		}
		else if (answer == 'N') {
			cout << "\nShame, I'll leave you alone then." << endl;
			flag = true;
		}
		else{
			cout << "Invalid input [Y/N]";
		}
	}
}

void printObjectLocations() {
	cout << "\nSuper bats in room(s) [ " << cheatMode("Super Bats", "Hazard") << "]";
	cout << "\nBottomless Pits in room(s) [ " << cheatMode("Bottomless Pit", "Hazard") << "]";
	cout << "\nWumpus in room(s) [ " << cheatMode("Wumpus", "Hazard") << "]";
	cout << "\nGoblins in room(s) [ " << cheatMode("Goblin", "Players") << "]";
	cout << "\nFallen in room(s) [ " << cheatMode("Fallen", "Players") << "]";
	cout << "\nGhost in room(s) [ " << cheatMode("Ghost", "Players") << "]";
	cout << "\nBandit in room(s) [ " << cheatMode("Bandit", "Players") << "]\n" << endl;
}