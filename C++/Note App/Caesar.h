#ifndef CAESAR_H
#define CAESAR_H

#include <string>
#include <iostream>

using namespace std;
class CaesarCipher {
private:

public:

	//Our implementation of a Caesar Cipher, used by FileIO_w write files
	// to encrypt files before they are written to a file. This ensures 
	//the contents of every file are scrambled and hard to read
	static void encryptFile(string &file) {
		unsigned int size = file.length();
		int num[size];
		int k = 2;

		//Shifts only uppercase and lowercase letters, ignoring numbers and special punctuation
		//Also preserves case, i.e. Z wraps around to A.
		for (unsigned int i = 0; i < size; i++) {
			if (((int)file[i] > 64 && (int)file[i] < 91 )||( (int)file[i] > 96 && (int)file[i] < 123)) {
				num[i] = ((int)file[i] + (k%26));
				if (num[i] > 122) {
					num[i] = 96 + ((num[i])%122);
				}
				if ((int)file[i] > 64 && (int)file[i] < 91 && num[i] > 90) {
					num[i] = 64 + ((num[i])%90);
				}
			} else {
				num[i] = ((int)file[i]);
			}
		}

		//converts the chars back to letters, creating the encrypted string
		for (unsigned int i = 0; i < size; i++) {
			file[i] = (char)num[i];
					
		}

		//cout << message << endl;

	}

	//Operates in the same way as the encryption algorithm, however it shifts in the
	//opposite direction. Is used by the read file functions in FileIO_w
	static void decryptFile(string &file) {
		unsigned int size = file.length();
		int num[size];
		int k = 2;

		//the reciprocal of the above algorithm, maintains case, numbers and special 
		//punctuation, but instead shifts in the opposite direction to make the string
		//readable again.
		for (unsigned int i = 0; i < size; i++) {
			if (((int)file[i] > 64 && (int)file[i] < 91 )||( (int)file[i] > 96 && (int)file[i] < 123)) {
				num[i] = ((int)file[i] - (k%26));
				if (num[i] < 97 && (int)file[i] > 96 && (int)file[i] < 123) {
					num[i] = 123 - (97%(num[i]));
				}
				if ((int)file[i] > 64 && (int)file[i] < 91 && num[i] < 65) {
					num[i] = 91 + (65%(num[i]));
				}
			} else {
				num[i] = ((int)file[i]);
			}
		}
		for (unsigned int i = 0; i < size; i++) {
			file[i] = (char)num[i];
			
		}

		//cout << message << endl;
	}

};

#endif