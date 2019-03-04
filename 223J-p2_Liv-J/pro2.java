//Jeffrey Liv
//Email: jeffro001@csu.fullerton.edu
//Description: The Games Master program is a program that contains two games
//				called The Number Guess Game and The Nim Game. The objective of the number guess game is to guess the correct number
//				within the range of 1 to 1000. The user will be prompted whether their 
//				guess is warmer or colder. The objective of the Nim game is to beat the AI
//				by taking the last pebbles from the given rows.
import java.util.Scanner;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;

//Games Master class
class GM
{
	//Number guess class
	public static class Num_guess
	{
		Scanner input = new Scanner(System.in);
		//Number guess game method
		public void guessGame()
		{
			//Prompts user to start the game
			System.out.println("Welcome to the Number Guess Game!");
			Random randomGen = new Random();
			int randInt = randomGen.nextInt(1000);
			System.out.println(randInt);
			//Variable declaration
			int num = 0;
			int count = 0;
			int correct = 0;
			int prevNum;
			
			System.out.println("Choose a number from 1 to 1000 ( 0 to quit)");
			//User will be prompted to guess a number from 1 to 1000
			do{
				prevNum = num;
				System.out.print("Number: ");
				num = input.nextInt();
				
				//Checking the number
				//If 0 is selected then game will exit
				if(num == 0)
				{
					System.out.println("Thanks for playing!");
					break;
				}
				
				//Guess is too high
				if(randInt < num)
				{
					System.out.println("Guess is too high!");
					
					if(num > prevNum && count >= 1)
					{
						System.out.println("Colder!");
					}
					else if(num < prevNum && count >= 1)
					{
						System.out.println("Warmer!");
					}
					count++;
				}
				//Guess is too low
				if(randInt > num)
				{
					System.out.println("Guess is too low!");
					if(num < prevNum && count >= 1)
					{
						System.out.println("Colder!");
					}
					else if(num > randInt && count >= 1)
					{
						System.out.println("Warmer!");
					}
					count++;
				}
				//Correct guess
				if(num == randInt)
				{
					System.out.println("That's correct! Congratulations!");
					correct = 1;
					break;
				}
				
			}while(correct != 1 || num != 0);
		}
	}
	
	//Nim class
	public static class Nim
	{
		Scanner input = new Scanner(System.in);
		//Nim method
		public void nimGame()
		{
			//Prompt user
			System.out.println("Welcome to the Nim Game!");
			Random randomGen = new Random();
			
			//This assigns the number of rows randomly between 3 to 6
			int rows = randomGen.nextInt(4) + 3;
			
			//Create an ArrayList of integers for pebbles
			ArrayList<Integer> pebbles = new ArrayList<>();
			
			//Fill the ArrayList
			for(int i = 0; i < rows; i++)
			{
				//Assigns number of pebbles into the rows between 3 to 8
				int pebNum = randomGen.nextInt(6) + 3;
				//Add pebbles to the rows
				pebbles.add(pebNum);
			}
			
			//Setting isWinner to false since there are no winners yet
			boolean isWinner = false;
			outerloop:
			do{
				//Initial variable declaration 
				int rowCount = 1;
				int userRow = 0;
				int userPebbles = 0;
				
				//Display the current board
				displayBoard(pebbles);
				
				//Getting the move from the user
				boolean validInput = true;
				do{
					System.out.println("Choose a row to remove pebbles from: ");
					userRow = input.nextInt();
					if(userRow < 0 || userRow > rows)
					{
						validInput = false;
						System.out.println("	PLEASE ENTER A VALID ROW");
					}
					if(userRow == 0)
					{
						System.out.println("Thanks for playing!" );
						break outerloop;
					}
					else
						validInput = true;
				}while(validInput == false);
				
				validInput = true;
				do{
					System.out.println("How many pebbles would you like to remove from row " + userRow + "?");
					userPebbles = input.nextInt();
					if(userPebbles < 0 || userPebbles > pebbles.get(userRow - 1))
					{
						validInput = false;
						System.out.println("	PLEASE ENTER A VALID NUMBER OF PEBBLES");
					}
					if(userPebbles == 0)
					{
						System.out.println("Thanks for playing!" );
						break outerloop;
					}
					else
						validInput = true;
				}while(validInput == false);
				
				//Modifying the board
				pebbles.set(userRow - 1, pebbles.get(userRow - 1) - userPebbles);
				
				//Reinitialzing
				rowCount = 0;
				int sum = 0;
				//For loop checks pebbles ArrayList if rows have pebbles left
				//If there are none left then in this case the player is the winner
				for(int element : pebbles)
				{
					sum = sum + pebbles.get(rowCount);
					if(sum == 0 && rowCount == (rows - 1))
					{
						isWinner = true;
						System.out.println("Player wins!");
					}
					rowCount++;
				}
				
				//Display the board		
				displayBoard(pebbles);
				
				//If the player has not yet won, it is the AI's turn
				if(isWinner == false)
				{
					//The AI brain method is called to play
					pebbles = brain(pebbles, rowCount);
					
					//Reinitialzing 
					rowCount = 0;
					sum = 0;
					//For loop checks pebbles ArrayList if rows have pebbles left
					//If there are none left then in this case the AI is the winner
					for(int element : pebbles)
					{
						sum = sum + pebbles.get(rowCount);
						if(sum == 0 && rowCount == (rows - 1))
						{
							isWinner = true;
							displayBoard(pebbles);
							System.out.println("AI wins!");
						}
						rowCount++;
					}	
				}
			}while(isWinner == false);
			
		}
		
		//This method displays the current board
		public void displayBoard(ArrayList<Integer> pebbles)
		{
			int rowCount = 1;
			int spaceItr = 0;
			System.out.println("	Here is the current game board: ");
			for(int element : pebbles)
			{
				System.out.print("Row " + rowCount + ": ");
				for(int i = 0; i < element; i++)
				{
					System.out.print("o");
					spaceItr++;
					if(spaceItr == 3 || spaceItr == 6)
					{
						System.out.print(" ");
					}
				}
				System.out.println();
				spaceItr = 0;
				rowCount++;
			}
		}
		
		//AI BRAIN
		//Method is of type ArrayList of ints
		//This method takes in an ArrayList and rowCount as parameters
		//The AI will make a move and then return the newly modified ArrayList
		public ArrayList<Integer> brain(ArrayList<Integer> aList, int rowCount)
		{
			int AI_pebbles = 0;
			int AI_row = 0;
			boolean validMove = true;
			
			//Prompt user that the AI is making a move
			System.out.println("The AI is making a move . . .");
			
			//The AI will randomly make a legal move
			//If the AI does not make a legal move, then the loop will continue until a legal move is made
			do{
				Random randomGen = new Random();
				AI_row = randomGen.nextInt(rowCount);
				AI_pebbles = randomGen.nextInt(8) + 1;
				
				if(AI_pebbles <= aList.get(AI_row))
				{
					System.out.println("AI removes " + AI_pebbles + " pebbles from row " + (AI_row + 1));
					aList.set(AI_row, aList.get(AI_row) - AI_pebbles);
					validMove = true;
				}
				else
					validMove = false;
				
			}while(validMove == false);
			
			//return newly modified ArrayList
			return aList;
		}		
	}
	
	//MAIN
	public static void main(String[] args)
	{
		int gameChoice;
		int repeatVal;
		do{
			hello();
			gameChoice = listen();
			repeatVal = respond(gameChoice);
		}while(repeatVal == 1);
	}
	//Hello method says hello to user
	public static void hello()
	{
		System.out.println("Welcome to Games Master!");
	}
	//Shows a menu to the user and takes in choice
	public static int listen()
	{
		System.out.println("Please choose one of the following games: ");
		System.out.println("(1) The Number-Guess Game ");
		System.out.println("(2) The Nim Game ");
		System.out.println("(0) QUIT ");
		
		int choice;
		Scanner input = new Scanner(System.in);
		
		choice = input.nextInt();
		
		return choice;
	}
	//Respond method takes in user choice as an argument and based on the choice, the method
	//will act accordingly
	public static int respond(int choice)
	{
		int repeat = 0;
		switch(choice)
		{
			//Number guess game
			case 1:
			{
				repeat = 1;
				Num_guess NG = new Num_guess();
				NG.guessGame();
				break;
			}
			//Nim Game
			case 2:
			{
				repeat = 1;
				Nim nimG = new Nim();
				nimG.nimGame();
				break;
			}
			//QUIT
			case 0:
			{
				repeat = 0;
			}
		}
		return repeat;
	}
	//Clean up method
	public static void cleanUp()
	{
		//No cleaning up needed
	}
}