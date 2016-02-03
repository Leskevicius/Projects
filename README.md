#Projects that I've done

##C++

- **Note App**

  This terminal based note taking app was created as a group project of 5 teammates for second computer science course at UF. While working on this project I got experience to work with other students, and learn more about how to divide workload and try to split up code into modules which make sense.<br \>
  Note App supports:<br \>
  - Creation and deletion of accounts
  - Creation, deletion of notes as well as appending to an existing note
  - Search through existing notes
  - Should work on windows and linux (The app creates folders/files)
  - Notes and Logins are encrypted using Ceasar Cypher (terrible, I know, but it was all we had time/knowledge for)

 
- **Memory Management Simulator**

  This little project lets the user see how memory is managed in an Operating System. User is prompted to add/remove program and define how big the program should be. He/she can also prompt the program to tell how many fragments of memory there are. Once two programs that are adjacent are killed, the free memory will combine. A user can choose to either use best case fit algorithm or worst case fit algorithm. Best fit algorithm will try to place a program in the smallest fragment of free memory while worst case will place the program in the biggest fragment of free memory. 
  Compile this program like any other, but when running it you must provide it one argument that is a string: best or worst. Here is an example of usage of this program:
  ```
  g++ -Wall -o MemManagement pa2.cpp
  ./MemManagement best
  or
  ./MemManagement worst
  ```
  
##Java
  
- **Breakout**

  Breakout was the second project that I worked on, definitely the biggest of the time. After the initial implementation, I had tons of fun making perks and other additions to the game. 
  This game is just like any other Breakout games, with multiple levels. As you progress each level will have more bricks to break! Colored bricks give power-ups to the player, and these are:
  - Green Brick Power-up - gives double points for broken bricks for some time
  - Red Brick Power-up - extends the paddle for some time
  - Blue Brick Power-up - Gives an extra life to the player
  In order to run, simply compile all of the .java files and execute the following command:
  ```
  java Breakout
  ```

- **Text Adventure**

  Text adventure was the first project that I've worked on during the first computer course in University of Florida. Before this class I knew no programming at all, and I surprised myself at how much functionality I was able to pack into this little game. The game ended up being over 1000 lines of code using 11 classes.<br \>
  Explore and survive in an Island with 12 areas. The main goal of this text adventure is to survive and escape the island. As you move around, you get hungry so watch for opportunities to eat!<br \>
  This was my first project so it is not commented and kept up with. I'm in the process of adding comments and updating some code.<br \>
  In order to run, simply compile all of the .java files and execute the following command:
  ```
  java Survival
  ```
  Make sure to use help command when inside of the game to get the list of available commands!
  
