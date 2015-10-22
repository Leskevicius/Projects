#include <iostream>
#include <iomanip>
#include "LinkedList.h"

using namespace std;

void initialize();
void addProgram(string, int);
void removeProgram(string);
void printMemory();
void combineFreeSpace(int);
void showMenu();
void requestAction();
int freeSpace();

const int ADD_PROGRAM = 1,
		  KILL_PROGRAM = 2,
		  FRAGMENTATION = 3,
		  PRINT_MEMORY = 4,
		  EXIT = 5,
		  PAGES = 32,
          SIZE_OF_PAGE = 4;

int choice = 0;
bool worstFit = false;
bool bestFit = false;

LinkedList * freeMemory;
LinkedList * usedMemory;
Node * freeChunk;



int main(int argc, char ** argv) {

	initialize();
	string fit = argv[1];
	//cout << fit;
	if (fit == "best") {
		bestFit = true;
	} 
	else {
		worstFit = true;
	}
	do {
		showMenu();	
		requestAction();
	} while (choice != EXIT);
	
	return 0;
}

void showMenu() {
	string fit;
	if (bestFit) {
		fit = "best";
	}
	else {
		fit = "worst";
	}

	cout << "Using " << fit << " fit algorithm\n\n"
		 << "\t1. Add Program\n"
		 << "\t2. Kill Program\n"
		 << "\t3. Fragmentation\n"
		 << "\t4. Print memory\n"
		 << "\t5. Exit\n" << endl;
}

void requestAction() {
	
	
	cout << "choice - ";

	cin >> choice;
	if (choice > 5) {
		cout << "There is only 5 options. Choose 1-5." << endl;
	}
	else {
		switch(choice) {
			case ADD_PROGRAM:
			{
				cout << "Program name - ";
				string programName;
				cin >> programName;
				cout << "Program size (KB) - ";
				int size;
				cin >> size;
				int maxSize = freeMemory->getMaxVal();
				if (size > maxSize*SIZE_OF_PAGE) {
					cout << "\nError, not enough memory for Program " << programName << "\n\n";
				} else {
					addProgram(programName, size);
					cout << "\nProgram " << programName << " added successfully.\n\n";
				}
				break;
			}
			case KILL_PROGRAM:
			{
				cout << "Program name - ";
				string programName;
				cin >> programName;
				if (usedMemory->contains(programName)) {
					removeProgram(programName);
					cout << "\nProgram " << programName << " successfully killed.\n\n";
				}
				else {
					cout << "\nThere is no program with that name.\n\n";
				}
				break;
			}
			case FRAGMENTATION:
			{
				int fragments = freeMemory->size();
				cout << "\nThere are " << fragments << " fragment(s).\n\n";
				break;
			}
			case PRINT_MEMORY:
			{
				cout << '\n';
				printMemory();
				cout << '\n';
				break;
			}
			case EXIT:
			{

			}
		}	
	}
}


void removeProgram(string n) {
	if (usedMemory->contains(n)) {
		
		usedMemory->transfer(freeMemory, n);
		
		Node *temp = freeMemory->get(n);
		temp->name = "FREE";

		int pos = temp->position;
		combineFreeSpace(pos);
	}
}

void combineFreeSpace(int x) {

	int position = x-1;
	while (freeMemory->contains(position) && position != 0) {
		Node * ptr = freeMemory->getAt(position);
		
		int data1 = freeMemory->getDataAtPosition(position+1);
		int data2 = freeMemory->getDataAtPosition(position);
		int data = data1 + data2;
		
		ptr->value = data;
		
		freeMemory->removeAt(position+1);
		freeMemory->decrementPositionsFrom(position+2);
		usedMemory->decrementPositionsFrom(position+2);
		
		position--;
	}
	position+=2;
	int maxPos = freeChunk->position + 1;
	while (freeMemory->contains(position) && position != maxPos) {
		Node * ptr = freeMemory->getAt(position-1);
		
		int data1 = freeMemory->getDataAtPosition(position-1);
		int data2 = freeMemory->getDataAtPosition(position);
		int data = data1 + data2;
		
		ptr->value = data;
		
		freeMemory->removeAt(position);
		freeMemory->decrementPositionsFrom(position+1);
		usedMemory->decrementPositionsFrom(position+1);
	}	
}


void initialize() {
	
	freeMemory = new LinkedList;
	usedMemory = new LinkedList;

	freeMemory->insert(PAGES,"FREE");
	freeChunk = freeMemory->get("FREE");
	freeChunk->position = 1;

}

void addProgram(string n, int size) {
	//see how many pages we should use
	int pages;
	if (size%SIZE_OF_PAGE == 0) {
		pages = size/SIZE_OF_PAGE;
	} else {
		pages = size/SIZE_OF_PAGE + 1;
	}

	if (worstFit) {

		int memorySize = 0;
		int memoryAt = 0;
		Node * position = freeMemory->head;
		
		if (memorySize < position->value) {
			memorySize = position->value;
			memoryAt = position->position;
		}
		while (position->next != NULL) {
			if (memorySize < position->next->value) {
				memorySize = position->next->value;
				memoryAt = position->next->position;
			}
			position = position->next;
		}

		freeMemory->splitAt(memoryAt, pages, n);

		Node * temp = freeMemory->get(n);
		freeMemory->incrementPositionsFrom(memoryAt);
		usedMemory->incrementPositionsFrom(memoryAt);
		temp->position = memoryAt;


		freeMemory->transfer(usedMemory, n);


	} else if (bestFit) {

		int memorySize = 0;
		int memoryAt = 0;
		Node * position = freeMemory->head;
		
		if (position->value >= pages) {
			memorySize = position->value;
			memoryAt = position->position;
		}
		while (position->next != NULL) {
			int tempValue = position->next->value;
			if (tempValue >= pages && tempValue < memorySize) {
				memorySize = position->next->value;
				memoryAt = position->next->position;
			}
			position = position->next;
		}

		freeMemory->splitAt(memoryAt, pages, n);

		Node * temp = freeMemory->get(n);
		freeMemory->incrementPositionsFrom(memoryAt);
		usedMemory->incrementPositionsFrom(memoryAt);
		temp->position = memoryAt;


		freeMemory->transfer(usedMemory, n);
	}
}


void printMemory() { 
	int count = 0;
	int max1 = usedMemory->getMaxPos();
	int max2 = freeMemory->getMaxPos();
	int maxPosition = 0;
	if (max1 < max2) {
		maxPosition = max2;
	} else {
		maxPosition = max1;
	}
	for (int i = 1; i <= maxPosition; i++) {
		if (usedMemory->getDataAtPosition(i) != -1) {
			//cout << usedMemory->getDataAtPosition(i) << "  ";
			int temp = usedMemory->getDataAtPosition(i);
			string name = usedMemory->getNameAtPosition(i);
			while (temp >= 1) {
				cout << name << " ";
				temp--;
				count++;
				if (count >= 8) {
					count = 0;
					cout << endl;
				}
			}



		}
		else {
			int temp = freeMemory->getDataAtPosition(i);
			string name = freeMemory->getNameAtPosition(i);
			while (temp >= 1) {
				cout << name << " ";
				temp--;
				count++;
				if (count >= 8) {
					count = 0;
					cout << endl;
				}
			}
		}
		
	}
}


