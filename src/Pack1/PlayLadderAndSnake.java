// -----------------------------------------------------
// Assignment (3)
// Question: (Play Ladder and Snack game /part 2)
// Written by: (Behnaz Khalili and Shadan Farahbakhshdarabi)
// -----------------------------------------------------
package Pack1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayLadderAndSnake {
    public static void main(String[] args) {
        System.out.println("Welcome to Shadan & Behnaz Ladder and Snake Game, Enjoy!");
        System.out.println("*********************************************************");
        System.out.println("            *******************************               ");
        System.out.println();

        // flag for determining correct number of players input
        boolean isCorrect = false;

        //Determine how many wrong attempt user done for entering number of players
        int count =0;

        int  numberOfPlayer = 0;
        Scanner kb = new Scanner( System.in);
        System.out.println("Enter the # of players for your game - Number must be between 2 and 4 inclusively : ");

        // asking user to enter number of players and verify the correct input otherwise ask to enter again, total chance
        // to try is 4 times, checking by count
        boolean validInput = false;
        while( !isCorrect ) {
            while (!validInput) {
                try {
                    numberOfPlayer = kb.nextInt();
                    if (numberOfPlayer < 2 || numberOfPlayer > 4) {
                        count++;
                        if (count < 3) {
                            System.out.println("Bad Attempt " + count + " - Invalid # of players.pleas enter a #   between 2 and 4 inclusively : ");
                        }
                        if (count == 3) {
                            System.out.println("Bad Attempt " + count + " - Invalid # of players.pleas enter a #   between 2 and 4 inclusively. This is your last attempt.");
                        }
                        if (count == 4) {
                            System.out.println("Bad Attempt " + count + "!" + "You have exhausted all your chances. Program will terminate!");
                            System.exit(0);
                        }

                    } else {
                        // if user enter the right amount for number of players isCorrect will be true
                        // and get of the while loop to start the game.
                        isCorrect = true;
                        validInput = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("MisMatch Input : Please try again:");
                    kb.nextLine();
                }
            }
        }

        // creat object from LadderAndSnake class.
        LadderAndSnake ladderAndSnake = new LadderAndSnake(numberOfPlayer);

        //Using Play engine to play the game.
        ladderAndSnake.play( );

    }
}
