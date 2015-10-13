#ifndef FILEIO_w_H
#define FILEIO_w_H

#include <iostream>
#include <fstream>
#include <string>
#include <cstring>
#include <stdio.h>
#include <vector>
#include "Caesar.h"

using namespace std;

class FileIO_w {

public:

	//returns a vector<string> from a file, used for userlist
	static vector<string> readfile_vect(string Name) {
		string line;
	  	vector <string> str;
	  	ifstream myfile (Name.c_str());
	  	if (myfile.is_open()) {
	    		while ( getline (myfile,line) ) {
	    			//decrypts file
	      			CaesarCipher::decryptFile(line);	
	      			str.push_back(line);
	    		}
	    		myfile.close();
	  	}
		//else cout << "Unable to open file READ" << endl; 
		return str;
	}
	
	//returns string from a file of Name.txt
	static string readfile(string Name) {
		string line;
	  	string str = "";	
	  	ifstream myfile (Name.c_str());
	  	if (myfile.is_open()) {
		    	while ( getline (myfile,line) ) {
		    		//decrypts file
	      			CaesarCipher::decryptFile(line);	
	      			str += line;
	    		}
	    		myfile.close();
	  	}
		//else cout << "Unable to open file READ" << endl; 
		return str;
	}

	//writes a file "name" from vector<string> input
	static void writefile(vector<string> entry, string name){	
		ofstream myfile (name.c_str());
		if (myfile.is_open()) {
			for(unsigned int i = 0; i < entry.size(); i++) {
				//encrypts file
				CaesarCipher::encryptFile(entry.at(i));
			}
			for(unsigned int i = 0; i < entry.size(); i++){
				myfile << entry.at(i) << endl;
			}
			myfile.close();
		}
		else cout << "Unable to open file WRITE" << endl;
	}

	//writes a file "name" from string input
	static void writefile(string entry,string name) {
		//encrypts file
		CaesarCipher::encryptFile(entry);		  	
	  	ofstream myfile (name.c_str());
	   	if (myfile.is_open()) {
	    	myfile << entry;
	    	myfile.close();
	  	}
		else cout << "Unable to open file WRITE" << endl;
	}

	//creates empty file
	static void write_emptyfile(string filename){
		ofstream myfile (filename.c_str());
	   	if (myfile.is_open()) {
	    	myfile.close();
	  	}
		else cout << "Unable to open file WRITE" << endl;
	}

	//deletes file
	static void delfile(string filename){
		if( remove( filename.c_str()) != 0) {
   			perror( "Error deleting file" );
		}
 		else{
    			puts("");
		}
	}

	//adds on to file
	static void appendfile(string entry, string name) {
  		CaesarCipher::encryptFile(entry);	
  		ofstream myfile (name.c_str(), ofstream::app);
   		if (myfile.is_open()) {
    		myfile << entry;
    		myfile.close();
  	}
		else cout << "Unable to open file APPEND" << endl;		
	}
};

//same as FileIO but shifts which folder is being looked at
class FileIO_userfolder{
private:
	string username;
	FileIO_w rwfile;
public:
	//MUST IMPLEMENT BEFORE USING OTHER METHODS
	void setuser(string name){
		username=name;
	}
	//path setup might depend on setup (linux vs windows vs cygwin)!!!!
	vector<string> readfile_vect(string filename){
		filename = username + "/" + filename;
		return rwfile.readfile_vect(filename);
	}
	string readfile(string filename){
		filename = username + "/" + filename;
		return rwfile.readfile(filename);	
	}
	void writefile(vector<string> tabs, string filename){
		filename = username + "/" + filename;
		rwfile.writefile(tabs, filename);
	}
	void writefile(string text, string filename){
		filename = username + "/" + filename;
		rwfile.writefile(text, filename);
	}

	void delfile(string filename){
		filename = username + "/" + filename;
		rwfile.delfile(filename);
	}
	void appendfile(string entry, string filename) {
		filename = username + "/" + filename;
		rwfile.appendfile(entry, filename);
	}

	//append file???
};



#endif

