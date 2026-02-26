import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Board {
    
    /*
    Create 2 instance variables:

        board(2d ArrayList): Stores Piece objects
        activePlayer(String): Stores a string "white" or "black" which dictates whose turn it currently is
                                by default, activePlayer should be equal to "white"

    */

    /*
    Create a Board constructor with default values

        board = an empty ArrayList
        activePlayer = "white" (white always moves first in a chess game)
     */

    //gets the current activePlayer
    public String getActivePlayer(){
        return activePlayer;
    }

    //gets the board 2d ArrayList
    public ArrayList<ArrayList<Piece>> getBoard(){
        return board;
    }

    //sets the current activePlayer
    public void setActivePlayer(String player){
        activePlayer = player;
    }

    /*
    Write the generateBoard method

        generate board does not return anything
        generate board uses an algorithm to populate the board with the proper pieces for the start of a game
            Piece type objects are constructed (a total of 32 pieces) with their proper values 
            (reference Piece class to see how pieces are constructed)
    
        if there is no piece on a given square, the value should be null

        reference the chart on slideshow to see how pieces should be added

        notice that the vertical axis is flipped from what we expect:
        white's pieces begin in the rows at indices 6 and 7
        black's pieces begin in the rows at indices 0 and 1
    */

    /*
    print board prints out the board in a formatted way, including coordinate labels
    white's pieces are printed in uppercase
    black's pieces are prited in lowercase
    */
    public void printBoard(){
        System.out.println("    a   b   c   d   e   f   g   h");
        for (int i = 0; i < board.size(); i++){
            System.out.println("  +---+---+---+---+---+---+---+---+");

            System.out.print((8-(i))+" | ");
            for (Piece piece : board.get(i)){
                if (piece == null){
                    System.out.print("  | ");
                }else{
                    System.out.print(piece + " | ");
                }
            }
            System.out.print(" "+(8-(i)));
            System.out.println();
        }
        System.out.println("  +---+---+---+---+---+---+---+---+");
        System.out.println("    a   b   c   d   e   f   g   h");
    }

    /*
    stringToCoord converts a string of 2 characters into an integer Array
    the items of this array represent the x and y indices of the coordinate in the board

    NOTE:
        the vertical axis is flipped so the 1st rank is index 7
        and the 8th rank is index 0
    ex. 
        a5 ~ [0,7]
        e6 ~ [4,2]
    */
    public static int[] stringToCoord(String coord){
        String[] xCoords = {"a","b","c","d","e","f","g","h"};
        String[] yCoords = {"1","2","3","4","5","6","7","8"};

        String xCoord = coord.substring(0,1);
        String yCoord = coord.substring(1,2);

        int x = Arrays.asList(xCoords).indexOf(xCoord);
        int y = 7-Arrays.asList(yCoords).indexOf(yCoord);

        int[] result = {x,y};
        return result;
    }

    /*
    Write the move method

        move does not return anything
        move takes 2 parameters:
            currentCoord(String): the coordinate of the piece you would like to move
            targetCoord(String): the coordinate of the square you would like to move to

            coordinates are given as xy
                ex. 
                    a5
                    h2

        set the value of hasMoved for the piece at currentCoord to be true

        using these 2 coordinates, edit the 2d ArrayList so that the piece moves to the targetCoord
        and the currentCoord becomes null (the piece is no longer there)

        finally, edit the value of activePlayer using the setActivePlayer method so that is the opposite value

        YOU MUST USE THE stringToCoord METHOD FOR THIS!!!
     */

    //this method runs a game of chess in your terminal
    public static void runGame(){
        Board board1 = new Board();
        board1.generateBoard();
        
        Scanner scanner = new Scanner(System.in);
        String coords = "";
        String currentCoord = "";
        String targetCoord = "";

        System.out.println("Type 'quit' at any time to end the game");
        System.out.println();

        while (coords != "quit"){
            
            
            board1.printBoard();
            System.out.println("Current Player: "+board1.getActivePlayer() );
            
            System.out.println();
            System.out.print("Enter the coordinates of the piece you want to move and the square you want to move it to(format: xyxy): ");
            coords = scanner.nextLine();

            if (MoveValidator.isValidCoord(coords)){
                currentCoord = coords.substring(0,2);
                targetCoord = coords.substring(2,4);

                if (MoveValidator.isValidMove(currentCoord, targetCoord, board1)){
                    board1.move(currentCoord, targetCoord);
                } else {
                    System.out.println("Not A Valid Move!");
                }
            }else{
                System.out.println("Not A Valid Move!");
            }

        }
        scanner.close();
    }
}
