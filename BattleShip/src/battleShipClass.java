import java.util.Random; //used to generate random numbers

import java.util.Scanner;

public class battleShipClass {

public static void main(String[] args) {
    Scanner input = new Scanner(System.in); //This line creates a Scanner for you to use
 // ...
    Integer[][] gameArray = new Integer[10][10];
    
    System.out.println("Welcome the the battle ship game! \n"); //print welcome statement once
    fillArray(gameArray);
    deployComputerShip(gameArray);
    deployUserShip(gameArray, input);
    System.out.println("SHIPS successfully deployed, let's start the game...\n"); 
    oceanMap(gameArray);
    int gameResult = gamePlay(gameArray, input);
    	if (gameResult == 1)
    		System.out.println("Congratulations, you win");
    	else if (gameResult == 2)
    		System.out.println("Sorry, you lose");
    	else
    		System.out.println("Something went wrong");
    System.out.println("END OF THE GAME! \n"); 
    oceanMap(gameArray);
 } //main ends here

public static void oceanMap(Integer [][] array) {
	
	System.out.print("\t  ");
	for ( int i = 0; i < array.length; i ++) {
        System.out.print(" " + i + " ");
	}
	for ( int i = 0; i < array.length; i ++) {
		System.out.print("\n\t"+i+"|");
		for (int j = 0; j < array[i].length; j ++) {
			if(array[i][j]==0) 
				System.out.print("   ");
			else if (array[i][j]==1)
				System.out.print(" @ ");
			else if (array[i][j]==6)
				System.out.print("   ");
				//System.out.print(" C ");
			else if (array[i][j]==3 || array[i][j]==4)
				System.out.print(" - ");//computer or player fire
			else if (array[i][j]==2)
				System.out.print(" ! ");//computer or player fire
			else if (array[i][j]==5)
				System.out.print(" x ");//computer or player fire
			else System.out.print("ERR");
		}
		System.out.print("|"+i);
	}
	System.out.print("\n\t  ");
	for ( int i = 0; i < array.length; i ++) {
		System.out.print(" " + i + " ");
	}
}

public static void fillArray(Integer [][] array) {
    for(int i = 0; i < array.length; i++) {
        for(int j=0; j< array[i].length; j++){
            array[i][j] = 0;
        }
    } //for loop ends here
}

public static void deployUserShip(Integer[] [] Array, Scanner in) { 
    //Here is some sample code where you ask the user to enter in the coordinates for where to place a ship
	int x;
	int y;
	for (int i=0; i < 5; i++) {
    	System.out.print("\nEnter X coordinate for your ship" + (i+1) + ": ");
    	x = in.nextInt();
    	System.out.print("\nEnter Y coordinate for your ship: ");
    	y = in.nextInt();
        //Error treatment
        if((x < 0 || x > 9) || (y < 0 || y > 9)) {
            System.err.println("Can't place a ship outside battleZone");
            i--; //to decrease the s value for the loop to run
        	}
        else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 1) {
            System.err.println("You can't place two or more ships on the same location");
            i--;
        	}
        else if(Array[x][y] == 6) {
            System.out.println("A ship already exists there!  TRY AGAIN! ");
            i--;
        	}
        else{
            Array[x][y]= 1; //user ships saved as a 1 in array
        	}
    }
	
}
public static void fillArrayTESTE(Integer [][] array) {
    for(int i = 0; i < 7; i++) {
        for(int j=0; j< 7; j++){
            array[i][j] = 1;
        }
    } //for loop ends here
}

public static void deployComputerShip(Integer[] [] Array) {
	int x, y;
	Random xGen = new Random();
	for (int i=0; i < 5; i++) {
    	x = xGen.nextInt(10);
    	y = xGen.nextInt(10);
    	//System.out.print("\nNumero gerado randomicamente: " + x +" " + y);
        //Error treatment
        if((x < 0 || x > 9) || (y < 0 || y > 9)) {
            System.err.println("Can't place a ship outside battleZone");
            i--; //to decrease the s value for the loop to run
        	}
        else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 6) {
            System.err.println("You can't place two or more ships on the same location");
            i--;
        	}
        else if(Array[x][y] == 1) {
            System.err.println("There is already one ship on this position");
            i--;
        	}
        else{
            Array[x][y]= 6; //user ships saved as a 1 in array
            System.out.print("\nComputer Ship " + (i+1) +" deployed");
        	}
        
	}
	System.out.println("");
}

public static int playerTurn(Integer[][] Array, Scanner in) {
	int x;
	int y;
	System.out.println("\nYour turn");
	System.out.print("\nEnter X coordinate for your FIRE: ");
	x = in.nextInt();
	System.out.print("\nEnter Y coordinate for your FIRE: ");
	y = in.nextInt();
	//Error treatment
	if((x < 0 || x > 9) || (y < 0 || y > 9)) {
		System.err.println("Can't shoof a FIRE outside battleZone");
	}
	else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 1) {
		System.err.println("Oh no, you sunk your own ship :(");
		Array[x][y]=5;
		return 2;
	}
	else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 6) {
		System.out.println("GOOD Job you sunk an opponent ship.");
		Array[x][y]=2;
		return 1;
	}
	else{
		System.out.println("You missed your fire, WATER!!!");
		Array[x][y]= 3; //Player missed fire
	}
	return 0;
}

public static int compTurn(Integer[][] Array) {
	int x, y;
	System.out.println("\nComputer turn");
	Random xGen = new Random();
	x = xGen.nextInt(10);
	y = xGen.nextInt(10);
	//Error treatment
	if((x < 0 || x > 9) || (y < 0 || y > 9)) {
		System.err.println("Computer can't shoot a FIRE outside battleZone");
	}
	else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 6) {
		System.err.println("Oh no, computer sunk your own ship :(");
		Array[x][y]=2;
		return 2;
	}
	else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 1) {
		System.out.println("GOOD Job Computer sunk one of yours ship.");
		Array[x][y]=5;
		return 1;
	}
	else{
		Array[x][y]= 4; //Computer missed fire
	}
	return 0;
	//System.out.println("");
}

public static int gamePlay(Integer[][] Array, Scanner in) {
	int userShips = 5;
	int compShips = 5;
	
	while (userShips > 0 && compShips > 0) {
		int pT = playerTurn(Array, in);
		if (pT == 1)
			compShips--;
		else if (pT == 2)
			userShips--;
		else
			System.out.println("\nYou missed!");
		
		int cT = compTurn(Array);
		if (cT == 1)
			userShips--;
		else if (cT == 2)
			compShips--;
		else
			System.out.println("\nComputer missed!");
				
		oceanMap(Array);
		System.out.println("\nYour Ships: " + userShips + " |  Computer Ships: " + compShips);
	}
	if (compShips == 0)
		return 1;
	else if (userShips == 0)
		return 2;
	else 
		return 0;

}

}//end class