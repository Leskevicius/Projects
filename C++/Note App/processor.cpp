
#include "interface.h"
#include "filesystem.h"
#include <algorithm>
#include <istream>
#include "Search.h"

using namespace std;

class processor 
{
public:
	processor();
	void run();
private:
	interface menuPrint;
	Users users;
	bool loggedIn;
	bool inEdit;
	bool inSearch;
	bool searchFormat;
	string checkDate(string oldDate);
	string dateOutputFix(string date);	
	void loggedMenu(string userName);
	void checkForUserList();
	void editTabs(Userfiles currentUser);
	void searchTabs(Userfiles currentUser);


};

//instantiating 
processor::processor() {
	menuPrint = interface();
	loggedIn = false;
	inEdit = false;
	inSearch = false;
	searchFormat = true;
	users = Users();
}


//this function is what starts the program
void processor::run() {
	//make sure that the userlist txt file exists.
	checkForUserList();
	int choice = 0;
	//while user doesnt want to exit
	while(choice != 4) {
		vector<string> userList = users.printusers();
		vector<string> pass = users.printpasswords();

		//print available users and the menu
		cout << "\nAvailable Users: ";
		for (unsigned int i = 0; i < userList.size(); i++) {
			cout << userList.at(i) << ' ';
		}
		cout << '\n' << endl;
		menuPrint.main1();
		
		//here we make sure that the input is always of type int
		cin >> choice;
		if (!cin) {
			cout << "\nIncorrect user input." << endl;
			cin.clear();
			cin.ignore(10000,'\n');
			continue;
		}

		//this int will be used to save the position in a vector
		int position = 0;
		switch(choice) {
			//case 1 is to make a new user.
			case 1 : 
			{
				menuPrint.newUser();
				string userName;
				cin >> userName;
				//check if username already exists
				if (find(userList.begin(), userList.end(), userName) != userList.end()) {
					menuPrint.userExists();
					break;
				}
				string password;
				menuPrint.password();
				cin >> password;
				//make a new user.
				users.newuser(userName, password);
				menuPrint.newUserSuccess();
				break;
			}
			//log in case
			case 2 : 
			{
				//check if there is no users for logging in
				if (userList.empty()) {
					cout << "\nNo users available." << endl;
					break;
				}

				menuPrint.logIn();
				string userName;
				cin >> userName;
				//check to make sure that the userName exists
				if (find(userList.begin(), userList.end(), userName) == userList.end()) {
					menuPrint.userDNE();
					break;
				}
				else {
					//if it exists, get password
					string password;
					menuPrint.logInPass();
					cin >> password;
					
					//save position of the user name to access its password
					for (unsigned int i = 0; i < userList.size(); i++) {
						if (userList.at(i) == userName) {
							position = i;
						}
					}
					//get true password and check if one entered matches
					string truePass = pass.at(position);
					if (password != truePass) {
						menuPrint.logInPassWrong();
						break;
					}
					//if matches, loggin
					else {
						loggedIn = true;
						menuPrint.logInSuccess();
						loggedMenu(userName);
					}
				}	
				break;
			}
			case 3 :
			{

				if (userList.empty()) {
					cout << "\nNo users available." << endl;
					break;
				}
				//delete usercase
				menuPrint.deleteUser();
				string userName;
				cin >> userName;
				
				//check if user exists
				if (find(userList.begin(), userList.end(), userName) == userList.end()) {
					menuPrint.userDNE();
					break;
				}
				else {
					
					string password;
					menuPrint.deleteUserPassword();
					cin >> password;
					
					for (unsigned int i = 0; i < userList.size(); i++) {
						if (userList.at(i) == userName) {
							position = i;
						}
					}

					string truePass = pass.at(position);
					if (password != truePass) {
						menuPrint.logInPassWrong();
						break;
					}
					
					else {
						//if password and username match, delete the file
						Userfiles userf = Userfiles(userName);
						userf.delfileall();
						users.deluser(userName);
						menuPrint.deleteUserSuccess();
					}
				}
				break;
			}		
			case 4 : 
			{
				//this is for exiting
				menuPrint.exit();
				break;
			}
			default : cout << "\nError: menu out of bounds.";
		}
	}
}

void processor::loggedMenu(string userName) {
	//this is logged in menu. while the loggedIn is true you will be in this menu
	while (loggedIn) {
		Userfiles currentUser = Userfiles(userName);
		//print the logged in menu and get a choice while making sure its an int
		menuPrint.main2();
		int choice;
		cin >> choice;
		if (!cin) {
			cout << "\nIncorrect user input." << endl;
			cin.clear();
			cin.ignore(10000,'\n');
			continue;
		}

		switch(choice) {
			case 1 : 
			{
				//case 1 is for making a new tab!
				//this case will make sure that file name isnt taken
				//then request for entry and save it in allocated folder
				string entry;
				string fileName;
				menuPrint.requestFileName();
				cin >> fileName;

				bool nameTaken = false;
				vector<string> filelist = currentUser.printfile_list_name();
				for (unsigned int i =0; i < filelist.size(); i++) {
					if (fileName == filelist.at(i)) {
						cout << "\nFile name taken.\n" << endl;
						nameTaken = true;
					}
				}
				if (nameTaken) break;
				
				menuPrint.newTab();
				cin.ignore();
				getline(cin,entry);
				currentUser.newfile(fileName ,entry);
				cout << "\nFile created successfully.\n" << endl;
				break;
			}
			case 2 : 
			{
				//case 2 is for editting the tab
				inEdit = true;
				editTabs(currentUser);
				break;
			}
			case 3 : 
			{
				//case 3 is for searching for tabs
				inSearch = true;
				searchTabs(currentUser);
				break;
			}
			case 4 :
			{
				//case 4 will list all the tabs available for current user
				vector<string> tabs = currentUser.printfile_list_name();
				if (tabs.empty()) {
					cout << "\nCurrently there are no tabs.\n" << endl;
					break;
				} else {
					cout << "\nAvailable tabs: " << endl;
				}
				for (unsigned int i = 0; i < tabs.size(); i++) {
					cout << '-' << tabs.at(i) << endl;
				}
				cout << '\n';
				break;
			}
			case 5 : 
			{	
				//case 5 is for logging out
				cout << "\nLogged out." << endl;
				loggedIn = false;
				break;
			}
			default : {
				cout << "\nError: menu out of bounds." << endl;
			}


		}
	}
}

void processor::searchTabs(Userfiles currentUser) {
	//this is the search menu. there are three
	//cases for search. to search for filename, date, or phrase within tabs.
	while (inSearch) {
		cout << "\nWhich search would you like to use?" << endl;
		cout << "1. Search by file name\n2. Search by date\n3. Search by phrase" << endl;
		int choice;
		cin >> choice;
		
		if (!cin) {
			cout << "\nIncorrect user input." << endl;
			cin.clear();
			cin.ignore(10000,'\n');
			continue;
		}
		switch (choice) {
			//this case is for searching by filename;
			case 1 :
			{
				cout << "\nPlease enter the name of the tab: " << endl;
				string search;
				cin >> search;
				string found = Search::searchByName(currentUser.printfile_list_name(),search);
				if (found == "") {
					cout << "\nFile not found.\n" << endl;
				}
				else {
					vector<string> tabs = currentUser.printfile_list_name();
					int choice = 0;
					///this is the menu for editing the file
					cout << "\nWould you like to\n1. View\n2. Edit\n3. Delete\n";
					cin >> choice;

					if (!cin) {
						cout << "\nIncorrect user input." << endl;
						cin.clear();
						cin.ignore(10000,'\n');
						continue;
					}

					switch (choice) {
						//case 1 is to read file
						case 1: 
						{
							string output = currentUser.printfile(found);
							cout << '\n' << output << '\n' << endl;
							inEdit = false;
							break;
						}
						case 2: 
						//case 2 is to append to the file
						{
							string preview = currentUser.printfile(found);
							cout << '\n' << preview << '\n' << endl;
							cout << "What would you like to add?" << endl;
							string append;
							cin.ignore();
							getline(cin, append);
							currentUser.appendfile(found,append);

							cout << '\n';
							inEdit = false;
							break;
						}
						case 3 : 
						//case 3 is to delete the file
						{
							cout << "\nTab has been deleted" << endl;
							currentUser.delfile(found);
							inEdit = false;
							break;
						}
						default : 
						{
							cout << "\nError: menu out of bounds." << endl;
							inEdit = false;
						}
					}

				}
				inSearch = false;
				break;
			}
			case 2 :
			{
				//case 2 here is to search by date
				cout << "\nPlease enter the date of the tab (in format MM/DD/YYYY): ";
				string date;
				cin >> date;
				date = checkDate(date);
				if (date == "") {
					cout << "\nWrong date format.\n" << endl;
				}
				else {
					vector<string> tabs = currentUser.printfile_list();
					vector<string> results = Search::searchByDate(tabs,date);
					//if search yielded no tabs, then results vector is empty
					if (results.empty()) {
						cout << "\nYour date did not match any tabs.\n" << endl;
						inEdit = false;
						inSearch = false;
						break;
					}
					//print out the tabs found
					cout << "\nTabs found: " << endl;
					unsigned int k = 1;
					for (unsigned int i = 0; i < results.size(); i += 2, k++) {
						cout << k << ". " << results.at(i) << ' ' << dateOutputFix(results.at(i+1)) << endl;
						
					}
					//here you choose which tab to edit
					cout << "\nChoose a tab please: ";
					unsigned int choice = 0;
					cin >> choice;
					if (choice > (tabs.size()+1)) {
						cout << "\nError: menu out of bounds." << endl;
						break;
					}
					int choice2 = 0;
					//from here until the end of case 2 is basically the same as case 1
					cout << "\nWould you like to\n1. View\n2. Edit\n3. Delete\n";
					cin >> choice2;

					if (!cin) {
						cout << "\nIncorrect user input." << endl;
						cin.clear();
						cin.ignore(10000,'\n');
						continue;
					}

					switch (choice2) {
						case 1: {

							string output = currentUser.printfile(results.at((choice-1)*2));
							cout << '\n' << output << '\n' << endl;
							inEdit = false;
							break;
						}
						case 2: 
						{
							string preview = currentUser.printfile(results.at((choice-1)*2));
							cout << '\n' << preview << endl;
							cout << "\nWhat would you like to add?" << endl;
							string append;
							cin.ignore();
							getline(cin, append);
							currentUser.appendfile(results.at((choice-1)*2),append);

							inEdit = false;
							break;
						}
						case 3 : 
						{
							cout << "\nTab has been deleted" << endl;
							currentUser.delfile(results.at((choice-1)*2));
							inEdit = false;
							break;
						}
						default : 
						{
							cout << "\nError: menu out of bounds." << endl;
							inEdit = false;
						}
					}

				}
				inSearch = false;
				break;
			}

			case 3 :
			{
				//this case is for search by phrase
				cout << "\nPlease enter a phrase to be searched: ";
				string phrase;
				cin >> phrase;
				
				vector<string> tabs = currentUser.printfile_list();
				vector<string> content;
				int q = 0;
				for (unsigned int i = 0; i < tabs.size(); i +=2) {
					content.push_back(currentUser.printfile(tabs.at(i)));
					cout << content.at(q) << endl;
					q++;
				}
				vector<string> results = Search::searchByEntry(content,tabs,phrase);
				
				if (results.empty()) {
					cout << "\nYour phrase did not match any tabs.\n" << endl;
					inEdit = false;
					inSearch = false;
					break;
				}
				//from here again this is basically the same thing as case 2 or 1
				cout << "\nTabs found: " << endl;
				unsigned int k = 1;
				for (unsigned int i = 0; i < results.size(); i += 2, k++) {
					cout << k << ". " << results.at(i) << ' ' << dateOutputFix(results.at(i+1)) << endl;
					
				}
				cout << "\nChoose a tab please: ";
				unsigned int choice = 0;
				cin >> choice;
				if (choice > (tabs.size()+1)) {
					cout << "\nError: menu out of bounds." << endl;
					break;
				}
				int choice2 = 0;
				cout << "\nWould you like to\n1. View\n2. Edit\n3. Delete\n";
				cin >> choice2;
					if (!cin) {
					cout << "\nIncorrect user input." << endl;
					cin.clear();
					cin.ignore(10000,'\n');
					continue;
					}
					switch (choice2) {
					case 1: {

							string output = currentUser.printfile(results.at((choice-1)*2));
							cout << '\n' << output << '\n' << endl;
							inEdit = false;
							break;
						}
						case 2: 
						{
							string preview = currentUser.printfile(results.at((choice-1)*2));
							cout << '\n' << preview << endl;
							cout << "\nWhat would you like to add?" << endl;
							string append;
							cin.ignore();
							getline(cin, append);
							currentUser.appendfile(results.at((choice-1)*2),append);
							cout << '\n';

							inEdit = false;
							break;
						}
						case 3 : 
						{
							cout << "\nTab has been deleted" << endl;
							currentUser.delfile(results.at((choice-1)*2));
							inEdit = false;
							break;
						}
						default : 
						{
							cout << "\nError: menu out of bounds." << endl;
							inEdit = false;
						}
					
				}
				inSearch = false;
				break;
			}
			default : {
				cout << "\nError: menu out of bounds." << endl;
				inSearch = false;
			}
		}
	
	}
}

//this function will check to make sure the date is in right format
string processor::checkDate(string oldDate) {
	if (oldDate.size() != 10) {
		return "";
	}
	if (oldDate[2] != '/') {
		return "";
	}
	if (oldDate[5] != '/') {
		return "";
	}
	if (oldDate[0] == '0') {
		oldDate[0] = ' ';
	}
	if (oldDate[3] == '0') {
		oldDate[3] = ' ';
	}
	return oldDate;
}

//this function is the edit tab
void processor::editTabs(Userfiles currentUser) {
	//while inEdit is true, you will be in edit menu
	while (inEdit) {
		vector<string> tabs = currentUser.printfile_list_name();
		if (tabs.empty()) {
			cout << "\nThere are no tabs to edit.\n" << endl;
			inEdit = false;
			break;
		}
		cout << "\nAvailable files:" << endl;
		//cout all tabs available for the current user
		for (unsigned int i = 0; i < tabs.size(); i++) {
			cout << i+1 << ". " << tabs.at(i) << endl;
		}
		cout << "\nChoose a tab please: ";
		unsigned int choice = 0;
		cin >> choice;
		if (choice > (tabs.size()+1)) {
			cout << "\nTab does not exist.\n" << endl;
			break;
		}
		int choice2 = 0;
		cout << "\nWould you like to:\n1. View\n2. Edit\n3. Delete\n";
		cin >> choice2;

		if (!cin) {
			cout << "\nIncorrect user input." << endl;
			cin.clear();
			cin.ignore(10000,'\n');
			continue;
		}

		switch (choice2) {
			case 1: {
				//case 1 is to view chosen file
				string output = currentUser.printfile(tabs.at(choice-1));
				cout << '\n' << output <<'\n' << endl;
				inEdit = false;
				break;
			}
			case 2: 
			{
				//this case is for appending a chosen file
				string preview = currentUser.printfile(tabs.at(choice-1));
				cout << '\n' << preview << '\n' << endl;
				cout << "What would you like to add?" << endl;
				string append;
				cin.ignore();
				getline(cin, append);
				currentUser.appendfile(tabs.at(choice-1),append);
				cout << "\nTab has been edited successfully.\n" << endl;
				inEdit = false;
				break;
			}
			case 3 : 
			{
				//this case is for deleting a file
				currentUser.delfile(tabs.at(choice-1));
				cout << "Tab has been deleted successfully.\n" << endl;
				inEdit = false;
				break;
			}
			default : 
			{
				cout << "crazy?" << endl;
				inEdit = false;
			}
		}



	}
}
//fixes date format
string processor::dateOutputFix(string date) {
	for (unsigned int i = 0; i < date.size(); i++) {
		if (date[i] == ' ') {
			date[i] = '0';
		}
	}
	return date;
}

//makes sure that userlist.txt exists
void processor::checkForUserList() {
	ifstream file("userlist.txt");
	if (!file) {
		FileIO_w temp;
		temp.write_emptyfile("userlist.txt");
	}
}
int main() {
	processor prc = processor();
	prc.run();
}

