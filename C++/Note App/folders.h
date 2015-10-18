#ifndef FOLDERS_H
#define FOLDERS_H

#include <sys/types.h>
#include <sys/stat.h>
#include <sys/unistd.h>
#include <dirent.h>
#include <iostream>
#include <string>


using namespace std;

class folders {
public:
	void makeFolder(string folderName);
	void removeFolder(string folderName);
};
#endif

//Makes a folder for a User
void folders::makeFolder(string folderName) {
	
	mkdir(folderName.c_str(), 0777);
}

//Deleted a folder when deleting a user
void folders::removeFolder(string folderName) {
	rmdir(folderName.c_str());
}