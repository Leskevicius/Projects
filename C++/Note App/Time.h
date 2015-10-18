#ifndef TIME_H
#define TIME_H

#include <fstream>
#include <iostream>
#include <string>
using namespace std;

//Overwrites the Standard Date format in C++ to a format easier for us to manipulate
//in the program. This is a standard class I am overwriting, not one I implemeneted
//from scratch
class Time {
private:

  //Sets how the format the date will be when MM/DD/YYYY
  static char* asctime(const struct tm *timeptr) {
    static char result[26];
    sprintf(result, "%.2d/%2d/%d",
      1+ timeptr->tm_mon,
      timeptr->tm_mday,
      1900 + timeptr->tm_year);
    return result;
  }	

public:
  //Grabs the current system time, and returns the string in the format MM/DD/YYYY 
  static string getTime() {
    time_t ltime;
    ltime = time(NULL);
    string currentTime;
    currentTime = asctime(localtime(&ltime));
    return currentTime;
  }
};

#endif