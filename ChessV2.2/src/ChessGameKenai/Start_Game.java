/*
 * Description: Class Start_Game.java
 * Author: Dimtri Pankov
 * Date: 23-Dec-2010
 * Version: 1.0
 */
package ChessGameKenai;

/**
 * Start_Game class is used to run the application
 * Instead of having all this unnecessary code in our ChessBoardView class
 * We hide this code in this class and by calling its instance.
 * By hiding the code we are achieving one of the fundamental rules in programming which is "encapsulation"
 * Hiding the data from the user
 * @author Dimitri Pankov
 * @see ChessBoardView class
 * @version 1.0
 */
public class Start_Game {

    /**
     * Empty Constructor of the class
     * When the object of this type is constructed it follows the strict rules
     * and instruction statements which are specified in the constructor
     */
    public Start_Game() {

        //CONSTRUCT THE MODEL OBJECT
        Chess_Data data = new Chess_Data();

        //CONSTRUCT THE MAIN VIEW WHICH IS ALSO A CONTROLLER
        ChessBoardView cbv = new ChessBoardView(data);

        //USE A SQUARE OBJECT TO CONSTRUCT AN OBJECT OUT OF ITS NESTED CLASS
        Square square = new Square();
        Square.SendData sd = square.new SendData(data);

        //CALL THE MODEL METHOD IN ORDER TO BE NOTIFIED OF THE STATE OF THE CHESS BOARD
        data.notifyView();

    }
}
