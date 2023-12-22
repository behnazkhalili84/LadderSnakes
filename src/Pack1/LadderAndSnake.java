// -----------------------------------------------------
// Assignment (3)
// Question: (Ladder and Snack board,player order and play engine/part 1)
// Written by: (Behnaz Khalili and Shadan Farahbakhshdarabi)
// -----------------------------------------------------

package Pack1;

import java.util.Random;


public class LadderAndSnake {
    private  int numOfPlayers;


    public  int getNumOfPlayers() {
        return numOfPlayers;
    }

    public  void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public LadderAndSnake() {
    }

    public LadderAndSnake (int numOfPlayers){
        this.numOfPlayers = numOfPlayers;
    }

    //A method for creating a random number between 1 and 6
    private static int flipDice() {
        Random random = new Random();
        int dice = random.nextInt( 6 ) + 1;

        return dice;
    }

    // This method ask all players to flip dice until getting different number and petting them in descending order
    public  Integer[] detemindPlayerOrder() {
        boolean flag = true;
        int numOfPlayers = 0;
        numOfPlayers = this.numOfPlayers;
        Integer[] playerOrder = new Integer[ numOfPlayers ];
        Integer[] player = new Integer[ numOfPlayers ];

        //Flip dice for all players
        for (int i = 0; i < numOfPlayers; i++) {
            playerOrder[ i ] = flipDice();
            System.out.println( "Player" + (i +1) + "  got a dice value of " + playerOrder[ i ] );

        }

        while (flag) {

            //if two players have same dice number hasTie become true
            boolean hasTie = false;
            for (int i = 0; i < numOfPlayers; i++) {
                for (int j = i + 1; j < numOfPlayers; j++) {
                    if (playerOrder[ i ] == playerOrder[ j ]) {
                        System.out.println( " A tie was achieved between Player " + ( i + 1 ) + " and Player " + ( j + 1 ) + " Attempting to break the tie" );
                        playerOrder[ i ] = flipDice();
                        System.out.println( "Player" + ( i + 1 ) + "  got a dice value of " + playerOrder[ i ] );
                        playerOrder[ j ] = flipDice();
                        System.out.println( "Player" + ( j + 1 ) + "  got a dice value of " + playerOrder[ j ] );
                        hasTie = true;
                    }
                }
            }
            if (!hasTie) {

                // if all players get different dice number, flag become false to get out of while loop
                flag = false;
            }
        }


        // this switch loop received numbers of players as input and return the right order of player
        switch(numOfPlayers){
            case 2 :
                player[0] = findMaxIndex( playerOrder ,-1,-1,-1);
                player[1] = findMaxIndex( playerOrder ,player[0],-1,-1);
                break;

            case 3:
                player[0] = findMaxIndex( playerOrder ,-1,-1,-1);
                player[1] = findMaxIndex( playerOrder ,player[0],-1,-1);
                player[2] = findMaxIndex( playerOrder ,player[0],player[1],-1);
                break;

            case 4:
                player[0] = findMaxIndex( playerOrder ,-1,-1,-1);
                player[1] = findMaxIndex( playerOrder ,player[0],-1,-1);
                player[2] = findMaxIndex( playerOrder ,player[0],player[1],-1);
                player[3] = findMaxIndex( playerOrder ,player[0],player[1],player[2]);
        }

        System.out.println("Reached final decision on order of playing: ");
        for(int i=0 ;i< numOfPlayers; i++ ){
            System.out.println( " Player" + (player[i]+1));
        }

        return player;
    }
    //this method find all max index af the array for getting players order, at first index1,index2 and index3 are equal -1, in second call
    // we replaced index1 by index max and continue this approach to find all maximums.
    public static int findMaxIndex (Integer[] input, int index1, int index2, int index3){

        int max = Integer.MIN_VALUE;
        int i, maxIndex = -1;
        for (i = 0; i < input.length ; i++) {
            if (i!=index1 && i!=index2 && i!=index3 ) {
                if (input[ i ] > max) {
                    max = input[ i ];
                    maxIndex = i;

                }
            }
        }

        return maxIndex;
    }

    // this method will receive current position and dice # of the players and the output is their final position
    // base on the position of ladders and snakes of the board , if a player's position exceed 100, the method
    //will bing the position back by (100-exceeding number).
    public int board(int position, int dice){
        int newposition  = position + dice;
        boolean flag = false;

        do {
            switch (newposition) {
                case 1:
                    newposition = 38;
                    break;

                case 4:
                    newposition = 14;
                    break;

                case 9:
                    newposition = 31;
                    break;

                case 16:
                    newposition = 6;
                    break;

                case 21:
                    newposition = 42;
                    break;

                case 28:
                    newposition = 84;
                    break;

                case 36:
                    newposition = 44;
                    break;

                case 48:
                    newposition = 30;
                    break;

                case 51:
                    newposition = 67;
                    break;

                case 62:
                    newposition = 19;
                    break;

                case 64:
                    newposition = 60;
                    break;

                case 71:
                    newposition = 91;
                    break;

                case 80:
                    newposition = 100;
                    break;

                case 86:
                    newposition = 24;
                    break;

                case 93:
                    newposition = 68;
                    break;

                case 95:
                    newposition = 24;
                    break;

                case 97:
                    newposition = 76;
                    break;

                case 98:
                    newposition = 78;
                    break;

            }

            //if a player's position exceed 100, they will being the position back by (100-exceeding number)
            // and flag is still false to continue the game
            if (newposition > 100) {
                int extra = newposition - 100;
                newposition = 100 - extra;
                flag = true;

            }

            else {
                flag = false;
            }

        }  while(flag);



        return newposition;
    }

    // The method play is the game engine which it's attribute is number of player as a class property
    // and isWin is false until a player arrive to #100 and win the game then it become true and the game ends.
    public void play(){
        int numOfPlayers = 0;
        numOfPlayers = this.numOfPlayers;
        boolean isWin = false;
        int dice;
        int[] currentPlayerPosition = new int[ numOfPlayers ];
        Integer [] player;
        player =  detemindPlayerOrder();

        do { for (int i = 0; i<numOfPlayers; i++){
            dice =flipDice();
            currentPlayerPosition[i] = board(currentPlayerPosition[i],dice);
            System.out.println( "Player " + (player[i]+1) + " got a dice value of " + dice + " ; now in square " + currentPlayerPosition[i]  );
            if (currentPlayerPosition[i] == 100) {
                isWin = true ;
                System.out.println(" Congratulation you win the game ");
                System.exit(0);
            }
        }
            if(!isWin)   System.out.println(" Game not over; flipping again : ");
        } while (!isWin);

    }
}