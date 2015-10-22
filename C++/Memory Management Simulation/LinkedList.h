#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include <iostream>

using namespace std;

/********************************************************************************
*	Class Node          														*
*********************************************************************************/

class Node {
public:
	int position;
	string name;
	int value;
	Node *next;
	void insert(int value);
	void insert(int value, string n);
	void add(Node n);
	Node(int value);
	Node();
};

/********************************************************************************
*	Class Node LinkedList														*
*********************************************************************************/

class LinkedList {
public:
	Node *head;
	int size();
	void insert(int value);
	void insert(int value, string n);
	void add(Node n);
	//Node get(string name);
	Node * get(string n);
	Node * getAt(int n);
	int getMaxPos();
	int getMaxVal();
	Node getCopy(string n);
	void transfer(LinkedList *b, string n);
	void remove(int value);
	void remove(string n);
	void removeAt(int position);
	bool contains(string n);
	bool contains(int n);
	void printList();
	void splitHead(int x, string n);
	void splitAt(int position, int amount, string name);
	int getDataAtPosition(int x);
	string getNameAtPosition(int x);
	void incrementPositionsFrom(int x);
	void decrementPositionsFrom(int x);

};

#endif
/********************************************************************************
*	Class Node Functions below													*
*********************************************************************************/

Node::Node() {}

Node::Node(int value) {
	this->value = value;
	next = NULL;
}

void Node::insert(int value) {
	if (next == NULL) {		//at the end of the list
		next = new Node(value);
	//s	cout << value << endl;
		return;
	}
	next->insert(value);	//not at the end of the list
}

void Node::insert(int value, string n) {
	if (next == NULL) {		//at the end of the list
		next = new Node(value);
		name = n;
	//s	cout << value << endl;
		return;
	}
	next->insert(value, n);	//not at the end of the list
}

void Node::add(Node n) {
	if (next == NULL) {
		next = new Node;
		*next = n;
		next->next = NULL;
		return;
	}
	next->add(n);
}

/********************************************************************************
*	Class LinkedList Functions below											*
*********************************************************************************/
int LinkedList::size() {
	int size = 0;
	Node *position;
	position = head;
	while (position != NULL) {
		size++;
		position = position->next;
	}
	return size;
}

int LinkedList::getMaxPos() {
	int max = 0;
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->position > max) {
			max = position->position;
		}
		position = position->next;
	}
	return max;
}

int LinkedList::getMaxVal() {
	int val = 0;
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->value > val) {
			val = position->value;
		}
 		position = position->next;
	}
	return val;
}


void LinkedList::incrementPositionsFrom(int x) {
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->position >= x) {
			position->position++;
		}
		position = position->next;
	}
}
	


void LinkedList::decrementPositionsFrom(int x) {
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->position >= x) {
			position->position--;
		}
		position = position->next;
	}
}



void LinkedList::insert(int value) {
	if (head == NULL) {		//empty list
		head = new Node(value);
	//	cout << value << endl;
		return;
	}
	head->insert(value);
}

void LinkedList::insert(int value, string n) {
	if (head == NULL) {		//empty list
		head = new Node(value);
		head->name = n;
	//	cout << value << endl;
		return;
	}
	head->insert(value, n);
}

void LinkedList::remove(int value) {
	Node *position = new Node;
	position = head;

	if (position->value == value) {			//if the value to be removed is the first value, 
		Node *temp = position->next;	//this loop will take care of it
		delete head;
		head = temp;
		return;
	}
	while (position->next != NULL) {			//this loop will remove a value if its anywhere else besides
		if (position->next->value == value) {	//beginning and end
			Node *temp = position->next->next;
			delete position->next;
			position->next = temp;
			return;
		}
		position = position->next;
	}
	if (position->value == value) {				//this loop will remove the last value if it is to be removed
		delete position;
		position = NULL;
	}
}

void LinkedList::removeAt(int pos) {
	Node *transverse = new Node;
	transverse = head;

	if (transverse->position == pos) {			//if the value to be removed is the first value, 
		Node *temp = transverse->next;	//this loop will take care of it
		delete head;
		head = temp;
		return;
	}
	while (transverse->next != NULL) {			//this loop will remove a value if its anywhere else besides
		if (transverse->next->position == pos) {	//beginning and end
			Node *temp = transverse->next->next;
			delete transverse->next;
			transverse->next = temp;
			return;
		}
		transverse = transverse->next;
	}
	if (transverse->position == pos) {				//this loop will remove the last value if it is to be removed
		delete transverse;
		transverse = NULL;
	}
}

void LinkedList::remove(string n) {
	Node *position = new Node;
	position = head;

	if (position->name == n) {			//if the value to be removed is the first value, 
		Node *temp = position->next;	//this loop will take care of it
		delete head;
		head = temp;
		return;
	}
	while (position->next != NULL) {			//this loop will remove a value if its anywhere else besides
		if (position->next->name == n) {		//beginning and end
			Node *temp = position->next->next;
			delete position->next;
			position->next = temp;
			return;
		}
		position = position->next;
	}
	if (position->name == n) {				//this loop will remove the last value if it is to be removed
		delete position;
		position = NULL;
	}
}

bool LinkedList::contains(string n) {
	bool contains = false;
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->name == n) {
			contains = true;
			return contains;
		}
		position = position->next;
	}
	return contains;
}

bool LinkedList::contains(int n) {
	bool contains = false;
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->position == n) {
			contains = true;
			return contains;
		}
		position = position->next;
	}
	return contains;
}

void LinkedList::printList() {
	Node *position = head;
	int test = 1;
	while (position != NULL) {
		cout << test << ": ";
		int temp = position->value;
		string temp2 = position->name;
		int temp3 = position->position;
		cout << temp << ' ' << temp2 << ' ' << temp3 << endl;
		position = position->next;
		test++;
	}
}

void LinkedList::splitHead(int x, string n) {
	if (head->value >= x) {
		head->value-=x;
		Node * temp = head->next;
		head->next = 0;
		head->next = new Node(x);
		head->next->name = n;
		head->next->next = temp;
	}
}

void LinkedList::splitAt(int pos, int amount, string n) {
	if (contains(pos)) {
		Node *node = getAt(pos);
		node->value-=amount;
		Node *temp = node->next;
		node->next = 0;
		node->next = new Node(amount);
		node->next->name = n;
		//node->next->position = pos+1;
		node->next->next = temp;
	}
}

void LinkedList::transfer(LinkedList *b, string n) {

	if (this->contains(n)) {
		Node temp = this->getCopy(n);
		b->add(temp);
		this->remove(n);		
	}
	
}

void LinkedList::add(Node n) {
	if (head == NULL) {
		head = new Node;
		*head = n;
		head->next = NULL;
		return;
	}
	head->add(n);
}


Node LinkedList::getCopy(string n) {
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->name == n) {
			return *position;
		}
		position = position->next;
	}
	return 0;
}






Node * LinkedList::get(string n) {
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->name == n) {
			return position;

		}
		position = position->next;
	}
	return 0;
}

Node * LinkedList::getAt(int n) {
	Node *position;
	position = head;
	while (position != NULL) {
		if (position->position == n) {
			return position;

		}
		position = position->next;
	}
	return 0;
}

int LinkedList::getDataAtPosition(int x) {
	Node * position = head;
	while (position != NULL) {
		if (position->position == x) {
			return position->value;
		}
		position = position->next;
	}
	return -1;
}

string LinkedList::getNameAtPosition(int x) {
	Node * position = head;
	while (position != NULL) {
		if (position->position == x) {
			return position->name;
		}
		position = position->next;
	}
	return 0;
}