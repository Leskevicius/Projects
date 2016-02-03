Breakout!

this version of Breakout includes statistics, game menu, win menu, restart menu, continue menu, three power ups for three different colored bricks.

Once I was done with the base version of the game, I started to think of what additions I might add.
First I decided I want some sort of statistics in the game, So i made a new class called Stats. Here you will find all statistics such as lives and score. Also in this class I implemented 3 Paint verions, which version executed depends on the state of the game. if dead, gameover, or regular.
Then I implemented key listeners to when you die or when the game is over. If you die, you may continue, if game over you may restart.
Also at the end I implemented what happens when you win with class Win, as well as Game menu when the game first starts, with GameMenu class.

The code may seem to be a little unorganized but I did my best keeping it readable and usefull. This is my first big project ever, and I enjoyed it so much...shouldve spent more time on that linear algebra exam but this was much worth it.

a couple problems that I ran in to were how to stop key listeners but It's all fixed now.