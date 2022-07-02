package tictactoe;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    static void gridprint(char[][] grid) { // Method to print grid state by grid input.
        System.out.println("---------");
        System.out.println("| " + grid[0][0] + " " + grid[0][1] + " " + grid[0][2] + " |");
        System.out.println("| " + grid[1][0] + " " + grid[1][1] + " " + grid[1][2] + " |");
        System.out.println("| " + grid[2][0] + " " + grid[2][1] + " " + grid[2][2] + " |");
        System.out.println("---------");
    }
    static void gridassign(String input_string, char[][] grid, int map, int xcount, int ocount) { // Method to assign grid values with initial string
        for(int i = 0; i < 3; i++) { // Assigning characters to grid, traverse row
            for(int j = 0; j < 3; j++, map++) { // column traverse
                grid[i][j] = input_string.charAt(map);
                if(input_string.charAt(map) == 'X') {
                    xcount += 1;
                } else if(input_string.charAt(map) == 'O') {
                    ocount += 1;
                }
            }
        }
    }
    static boolean checkblank(char[][] grid){ //Method to check if grid has blank spaces\
        boolean check = false;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == ' ' || grid[i][j] == '_'){
                    check = true;
                    break;
                }
            }
        }
        return check;
    }
    public static void main(String[] args) {
        // Grid initialization
        int map = 0, xcount = 0, ocount = 0; // used to map string location to verify game state
        char turn = 'X'; // X starts first.
        Scanner scan = new Scanner(System.in);
        char[][] grid = new char[3][3];
        gridassign("         ", grid, map, xcount, ocount); // Initialize empty board state
        gridprint(grid); //Print board state

        /* Enable code to input board state
        System.out.println("Enter cells: "); // Initial board state input
        String input_string = scan.nextLine();
        gridassign(input_string, grid, map, xcount, ocount); // Assign read grid values
        gridprint(grid); //Print board state
        */

        // input loop
        boolean in = true;
        while(in) {
            //coordinate input, try to check if numbers input
            System.out.print("It is " + turn + "'s turn! Enter the coordinates: ");
            try { // Try to catch exceptions in following code for input

                int x = scan.nextInt() - 1;
                int y = scan.nextInt() - 1;
                if (grid[x][y] == 'X' || grid[x][y] == 'O') { // Cell occupied by X or O
                    System.out.println("This cell is occupied! Choose another one!");
                    scan.nextLine(); // Clear scanner, break loop
                    continue;
                }

                // Determine X/O turn.
                switch(turn){
                    case 'X':
                        grid[x][y] = 'X'; // Give X value
                        xcount += 1;
                        break;
                    case 'O':
                        grid[x][y] = 'O'; // Give O value
                        ocount += 1;
                        break;
                    default:
                        System.out.println("Error. Cannot identify whose turn it is.");
                        break;
                }


                gridprint(grid); //Print game state

                // Check game state
                if(ocount - xcount > 1 || xcount - ocount > 1 || ((grid[0][0] + grid[0][1] + grid[0][2] == 264 || grid[1][0] + grid[1][1] + grid[1][2] == 264 || grid[2][0] + grid[2][1] + grid[2][2] == 264 || grid[0][0] + grid[1][0] + grid[2][0] == 264 || grid[0][1] + grid[1][1] + grid[2][1] == 264 || grid[0][2] + grid[1][2] + grid[2][2] == 264 || grid[0][0] + grid[1][1] + grid[2][2] == 264 || grid[0][2] + grid[1][1] + grid[2][0] == 264) && (grid[0][0] + grid[0][1] + grid[0][2] == 237 || grid[1][0] + grid[1][1] + grid[1][2] == 237 || grid[2][0] + grid[2][1] + grid[2][2] == 237 || grid[0][0] + grid[1][0] + grid[2][0] == 237 || grid[0][1] + grid[1][1] + grid[2][1] == 237 || grid[0][2] + grid[1][2] + grid[2][2] == 237 || grid[0][0] + grid[1][1] + grid[2][2] == 237 || grid[0][2] + grid[1][1] + grid[2][0] == 237))){
                    System.out.println("Impossible game state :("); // Occurs when imbalance in number of X vs O, two winners in board state
                }else if(grid[0][0] + grid[0][1] + grid[0][2] == 264 || grid[1][0] + grid[1][1] + grid[1][2] == 264 || grid[2][0] + grid[2][1] + grid[2][2] == 264 || grid[0][0] + grid[1][0] + grid[2][0] == 264 || grid[0][1] + grid[1][1] + grid[2][1] == 264 || grid[0][2] + grid[1][2] + grid[2][2] == 264 || grid[0][0] + grid[1][1] + grid[2][2] == 264 || grid[0][2] + grid[1][1] + grid[2][0] == 264){ // X win check
                    System.out.println("X wins! :)");
                }else if(grid[0][0] + grid[0][1] + grid[0][2] == 237 || grid[1][0] + grid[1][1] + grid[1][2] == 237 || grid[2][0] + grid[2][1] + grid[2][2] == 237 || grid[0][0] + grid[1][0] + grid[2][0] == 237 || grid[0][1] + grid[1][1] + grid[2][1] == 237 || grid[0][2] + grid[1][2] + grid[2][2] == 237 || grid[0][0] + grid[1][1] + grid[2][2] == 237 || grid[0][2] + grid[1][1] + grid[2][0] == 237){ // O win check
                    System.out.println("O wins! :)");
                }else if(checkblank(grid)) { //Checks for blanks in grid, game not finished
                    // Alternate turns
                    if(turn == 'X'){
                        turn = 'O';
                    }else {
                        turn = 'X';
                    }
                    continue; // Restart input loop again, game state not finished
                }else {
                    System.out.println("Game ended in a Draw!");
                }
                in = false; // Stop input loop and display game state
            }
            // Catching exceptions in java:
            catch (InputMismatchException e) { // Inputs are not integers
                System.out.println("You should enter numbers!");
                scan.nextLine(); // Clear scanner, break loop
            } catch (ArrayIndexOutOfBoundsException e) { // Numbers dont fit in array (ie. out of bounds)
                System.out.println("Coordinates should be from 1 to 3!");
                scan.nextLine(); // Clear scanner, break loop
            }

        }
    }
}
