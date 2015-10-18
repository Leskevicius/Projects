//MUST HAVE FileIO_w.h 

#ifndef FILESYSTEM_H
#define FILESYSTEM_H

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <stdio.h>
#include <fstream>

#include "FileIO_w.h"
#include "folders.h"
#include "time.h"


using namespace std;

//meant to be used in the main menu
class Users{
private:	
	folders folder;
	vector<string> userlist; //user1 endl pw1 endl user2 endl pw2 ....
	FileIO_w users;
	void loadusers();
public:
	Users();

	//add a newuser
	void newuser(string username,string password);

	//when calling deluser, must also delete all files [Userfiles.delfileall]
	void deluser(string username);

	//returns a <vector> string of all users
	vector<string> printusers();

	//returns a <vector> string of all passwords
	vector<string> printpasswords();
};

//meant to be used once logged in
//is now in the user's folder
class Userfiles{
private:
	vector<string> filelist;
	FileIO_userfolder userfiles;
	void loadfile_list();
	//void remove();
public:
	//constructor, implements loadfile_list()
	Userfiles(string user);
	
	//creates a new file
	void newfile(string filename, string text);

	//adds on to existing file
	void appendfile(string filename, string text);

	//deletes a file
	void delfile(string filename);

	//deletes all files in users folder
	//meant to be used with deluser
	void delfileall();

	//returns vector<string> of all tab data
	vector<string> printfile_list();

	//returns a vector <string> of all tab creation dates
	vector<string> printfile_list_date();

	//returns a vector <string> of all tab vector
	vector<string> printfile_list_name();

	// returns a string of file.txt entry
	string printfile(string filename);
};

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////


Users::Users(){
	loadusers();
}

void Users::loadusers(){
	userlist = users.readfile_vect("userlist.txt");
}

void Users::newuser(string username,string password){
	//check that name not already used???
	userlist.push_back(username);
	userlist.push_back(password);
	users.writefile(userlist, "userlist.txt");

	//make userfolder
	folder.makeFolder(username);
	//must create tablist.txt in that folder
	string name = username + "/" + "tablist.txt";
	users.write_emptyfile(name);
	
	
}

//MUST CALL USERFILES::DELFILEALL BEFORE CALLING THIS
void Users::deluser(string username){
	//delete user's folder
	bool match = false;
	for (unsigned int i=0; i<userlist.size(); i++){
		if (username == userlist.at(i)){
			userlist.erase(userlist.begin()+i);
			userlist.erase(userlist.begin()+i);
			users.writefile(userlist, "userlist.txt");
			match = true;
			break;
		}
	}
	if (match == false) {
		cout << "User not found" << endl;
		return;
	}
	folder.removeFolder(username);
}	


vector<string> Users::printusers(){
	vector<string> list;
	for (unsigned int i=0; i<userlist.size(); i++) { 
		if (i == 0 || i%2 == 0) {
			list.push_back(userlist.at(i));
			//cout << userlist.at(i) << endl;
		}
	}
	return list;
}

vector<string> Users::printpasswords(){
	vector<string> list;
	for (unsigned int i=0; i<userlist.size(); i++) { 
		if (i%2 == 1) {
			list.push_back(userlist.at(i));
			//cout << userlist.at(i) << endl;
		}
	}
	return list;
}

////////////////////////////////////////////////////////////////////////////

Userfiles::Userfiles(string user){
	userfiles.setuser(user);
	loadfile_list();
}

void Userfiles::loadfile_list(){
	filelist = userfiles.readfile_vect("tablist.txt");
}

void Userfiles::newfile(string filename, string text){
	//change tablist and filelist
	filelist.push_back(filename);
	filelist.push_back(Time::getTime());
	userfiles.writefile(filelist,"tablist.txt");

	filename = filename + ".txt";
	//make new tab
	userfiles.writefile(text, filename);
}

void Userfiles::appendfile(string filename, string text){

	filename = filename + ".txt";
	//make new tab
	userfiles.appendfile(text, filename);
}

//must delete also date from filelist
void Userfiles::delfile(string filename){
	bool match = false;

	//cout << "\n\n" << filename << "\n" <<  endl;
	for (unsigned int i=0; i<filelist.size(); i++){
		if (filename == filelist.at(i)){
			filelist.erase(filelist.begin()+i);
			filelist.erase((filelist.begin()+i));
			//update tablist.txt
			userfiles.writefile(filelist, "tablist.txt");
			match = true;
			break;
		}
	}
	if (match == false) {
		cout << "File not found" << endl;
		return;
	}

	userfiles.delfile(filename + ".txt");
}

void Userfiles::delfileall(){
	for (unsigned int i=0; i<filelist.size(); i++){
		//cout << filelist.at(i) << endl;
		if (i == 0 || i%2 == 0) {
			userfiles.delfile(filelist.at(i) + ".txt");	
		}
		

	}
	filelist.clear();
	userfiles.writefile(filelist, "tablist.txt");
	//delete tablist.txt so folder can be deleted. 
	userfiles.delfile("tablist.txt");
}


vector<string> Userfiles::printfile_list(){
	vector<string> list;
	for (unsigned int i=0; i<filelist.size(); i++) {
		//cout << filelist.at(i) << endl;
		list.push_back(filelist.at(i));
	}
	return list;
}

vector<string> Userfiles::printfile_list_name(){
	vector<string> list;
	for (unsigned int i=0; i<filelist.size(); i++) {
		//cout << filelist.at(i) << endl;
		if ( i == 0 || i%2 ==0) {
			list.push_back(filelist.at(i));
		}
	}
	return list;
}

vector<string> Userfiles::printfile_list_date(){
	vector<string> list;
	for (unsigned int i=0; i<filelist.size(); i++) {
		//cout << filelist.at(i) << endl;
		if (i%2 == 1) {
			list.push_back(filelist.at(i));
		}
	}
	return list;
}
string Userfiles::printfile(string filename){
	for (unsigned int i =0; i < filelist.size(); i++) {
		if (filename == filelist.at(i)) {
			filename = filename + ".txt";
		//	cout << "\n\n" << filename << endl;
			return userfiles.readfile(filename);
		}
	}
	cout << userfiles.readfile(filename) << endl;	
	return "File not found";	
	
}


#endif















