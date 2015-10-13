#ifndef SEARCH_H
#define SEARCH_H

#include <vector>
#include <string>
#include <iostream>

using namespace std;

class Search
{
public:

	//Searches through the filelist by date and returns all the names of files that match date
	static vector<string> searchByDate(vector<string> tabs, string search)
	{
		vector<string> found;

		//iterates over every date (every other element of filelist) to find matching dates
		//saves them to a variable named found
		for(unsigned int i = 1; i < tabs.size(); i += 2)
		{
			if(search == tabs.at(i))
			{
				found.push_back(tabs.at(i-1));
				found.push_back(tabs.at(i));
			}
		}
		//if(found.empty() == true)
		//{
		//	cout << "File not found" << endl;
		//}
		return found;
	}

	//searches through the filenames to see if any match the keyword
	//If so, returns the name of that file
	static string searchByName(vector<string> tabs, string search)
	{
		string found = "";

		for(unsigned int i = 0; i < tabs.size(); i ++)
		{
			if(search == tabs.at(i))
			{
				found = tabs.at(i);
			}
		}	
		return found;
	}

	//takes in the content of each file, the list of filenames and dates, and a phrase
	//if the phrase is present in a file, it adds that file and date to a vector which
	//it then returns at the end. Can have multiple files match
	static vector<string> searchByEntry(vector<string> fileContents, vector<string> fileList, string keyword) {
		vector<string> found;
		int k = 0;
		for (unsigned int i = 0; i < fileList.size(); i+= 2) {
			if (fileContents.at(k).find(keyword) != string::npos) {
				found.push_back(fileList.at(i));
				found.push_back(fileList.at(i+1));
			}
			k++;			
		}

		return found;
	}
};

#endif