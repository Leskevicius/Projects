#include <iostream>
#include <string>

#ifndef INTERFACE_H
#define INTERFACE_H

using namespace std;

//Handles a lot of couts in order to clean up processor a bit
class interface 
{
public:
	void logIn();
	void logInSuccess();
	void logInPass();
	void logOut();
	void password();
	void userDNE();
	void logInPassWrong();
	void userExists();

	void main1();
	void main2();
	void newTab();
	void searchTabPhrase();
	void searchTabDate();
	void tabDNE();
	void tabSaved();
	void whatSearch();

	void exit();
	void deleteTab();

	
	void newUser();
	void newUserSuccess();

	void deleteUser();
	void deleteUserSuccess();
	void deleteUserPassword();

	void requestFileName();


	interface();

};
#endif

interface::interface() {}

void interface::requestFileName() {
	cout << "\nPlease enter desired file name: ";
}

void interface::deleteUser() {
	cout << "\nPlease enter the username to be deleted: ";
}

void interface::deleteUserPassword() {
	cout << "Please enter the password of user to be deleted: ";
}

void interface::deleteUserSuccess() {
	cout << "Accound has been successfully deleted." << endl;
}

void interface::main1() {
	cout << "Main Menu\n1. New User\n2. Log In\n3. Delete User\n4. Exit\n";
}

void interface::logIn() 
{
	cout << "\nPlease enter your username: ";
}

void interface::userDNE() 
{
	cout << "\nThe username does not exist.\n";
}

void interface::logInPass() 
{
	cout << "Please enter your password: ";
}

void interface::logInPassWrong() 
{
	cout << "\nThe password is wrong.\n";
}

void interface::logInSuccess()
{
	cout << "\nLogin Successful.\n" << endl;
}




void interface::newUser() 
{
	cout << "\nPlease enter your desired username: ";
}

void interface::newUserSuccess() 
{
	cout << "\nAccount created." << endl;
}

void interface::userExists() {
	cout << "\nThis user already exists. Please use a different username.22\n";
}

void interface::password() 
{
	cout << "Please enter your desired password: ";
}





void interface::main2() {
	cout << "Main Menu\n1. New tab\n2. Edit tabs\n3. Search tabs\n4. List tabs\n5. Log out\n";
}

void interface::logOut() {
	cout << "Logging out.\n";
}

void interface::newTab() {
	cout << "Entry: \n";
}

void interface::tabSaved() {
	cout << "Tab saved..\n";
}

void interface::whatSearch() {
	cout << "What search would you like to use?\n1. By date\n2. By phrase\n";
}

void interface::searchTabPhrase() {
	cout << "Please enter a phrase to be searched for: ";
}

void interface::searchTabDate() {
	cout << "Please enter a date to be searched for: ";
}

void interface::tabDNE() {
	cout << "No tabs could be found.\n";
}

void interface::exit() {
	cout << "\nExit...";
}

void interface::deleteTab() {
	cout << "";
}


