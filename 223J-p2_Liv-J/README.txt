Author: Jeffrey Liv
Email: jeffro001@csu.fullerton.edu
Description: The Games Master program is a program that contains two games
called The Number Guess Game and The Nim Game. The objective of the number guess game is to guess the correct number
within the range of 1 to 1000. The user will be prompted whether their 
guess is warmer or colder. The objective of the Nim game is to beat the AI
by taking the last pebbles from the given rows.

The number guess game chooses
a random number from 1 to 1000 and prompts the user to guess the number. The program
tells the user whether their guess is too high or too low and will tell the user if
their guess is getting warmer or colder. The user has the option to quit the program and
the game by entering 0. If the guess is correct then the game will end. The user will then
be prompted to choose a game or quit the program. 

There was difficulty trying to figure out the if and else conditions to accurately
display whether the user was warmer or colder. However the program does accurately display
if the guess was too high or too low.

The nim game first creates a game board for the player and AI to play on.
The rows are chosen randomly between 3 and 6 rows. The amount of pebbles 
for each row contain between 3 and 8 pebbles. The user gets the first turn and chooses
the row and amount of pebbles they want to remove from the row. If the user
inputs 0, then the game will exit. If the user takes the
last pebbles then the user wins. If the player does not win, then it is the AI's turn.
During the AI's turn the AI chooses rows and amount of pebbles randomly. However the AI
must make a legal move; otherwise, the AI will choose randomly again until a legal move
is made. After every turn the newly modified board is displayed for the user. Afer every
turn, if all pebbles are gone then the person (user or AI) who took the last pebbles is
the winner. 

For the nim game, I had a hard time figuring out the win conditions. At first, I
checked to see if the ArrayList was null; however the ArrayList will not be able to equal
null since ArrayList contained integers. 