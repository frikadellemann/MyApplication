package dm550.tictactoe;

/** represents a tic tac toe board of a given size */
public class CFBoard {
    
    /** 2-dimensional array representing the board
     * coordinates are counted from top-left (0,0) to bottom-right (size-1, size-1)
     * board[x][y] == 0   signifies free at position (x,y)
     * board[x][y] == i   for i > 0 signifies that Player i made a move on (x,y)
     */
    private int[][] board;
    
    /** size of the (rectangular) board */
    private int xSize;
    private int ySize;
    
    /** constructor for creating a copy of the board
     * not needed in Part 1 - can be viewed as an example 
     */
    public CFBoard(CFBoard original) {
        //this.size = original.size;
        for (int y = 0; y < this.ySize; y++) {
            for (int x = 0; x < this.ySize; x++) {
                this.board[x][y] = original.board[x][y];
            }
        }
    }
    
    /** constructor for creating an empty board for a given number of players */
    public CFBoard(int numPlayers) {
        this.xSize = 7 ;
        this.ySize = 6 ;
        this.board = new int[this.getxSize() + 1][this.getySize()];
    }
    
    /** checks whether the board is free at the given position */
    public boolean isFree(Coordinate c) {
        if (board[c.getX()][c.getY()]==0)
            return true;
        else
            return false;
    }
    public boolean isFreeCol(int x) {
        for(int y = 0; y < ySize; y++) {
            if (board[x][y] == 0)
                return true;
        }
            return false;
    }
    
    /** returns the players that made a move on (x,y) or 0 if the positon is free */
    public int getPlayer(Coordinate c) {
        if (!isFree(c))
            return board[c.getX()][c.getY()];
        else
            return 0;
    }
    
    /** record that a given player made a move at the given position
     * checks that the given positions is on the board
     * checks that the player number is valid 
     */
    public void addMove(int x, int player) {
        try {
            //if there is room in this column
            if (board[x][0]==0) {
                if (player < 3) {
                    for(int y = ySize - 1; y >= 0; y--)
                        if(board[x][y]==0) {
                            board[x][y] = player;
                            break;
                        }
                }
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Illegal argument exception: " + e.toString());
        }
    }

    /** returns true if, and only if, there are no more free positions on the board */
    public boolean checkFull() {
        for(int x = 0; x < xSize; x++)
            for (int y = 0; y < ySize; y++)
            {
                if(board[x][y]==0)
                    return false;
            }
        return true;
    }

    /** returns 0 if no player has won (yet)
     * otherwise returns the number of the player that has three in a row
     */
    public int checkWinning() {
        int result = 0;
        for(int x = 0; x < xSize;x++) {
            for (int y = 0; y < ySize; y++) {
                Coordinate start = new XYCoordinate(x, y);
                //We are interested in checking cells that are not free
                if(!isFree(start)) {
                    result = checkSequence(start, 0, 1);
                    if (result > 0)
                        return result;
                    result = checkSequence(start, 1, 0);
                    if (result > 0)
                        return result;
                    result = checkSequence(start, 1, 1);
                    if (result > 0)
                        return result;
                    result = checkSequence(start, -1, 1);
                    if (result > 0)
                        return result;
                }
            }
        }
        return result;
    }

    /** internal helper function checking one row, column, or diagonal */
    private int checkSequence(Coordinate start, int dx, int dy) {
        //The
        int count = 1;
        int currentPlayer = getPlayer(start);
        for (int i = 1; i < 4; i++) {
            start = start.shift(dx, dy);
            if (start.checkBoundaries(xSize, ySize)) {
                if (getPlayer(start) == currentPlayer)
                    count++;
            }
            //If 2nd neighbouring cell is not marked by current player - there is no point to continue the checking process
            else
                break;
        }
        if (count == 4)
            return currentPlayer;
        else
            return 0;

    }


    /** getter for size of y */
    public int getySize() {
        return this.ySize;
    }

    /** getter for size of x */
    public int getxSize() {
        return this.xSize;
    }

    
    /** pretty printing of the board
     * usefule for debugging purposes
     */
    public String toString() {
        String result = "";
        for (int y = 0; y < this.ySize; y++) {
            for (int x = 0; x < this.xSize; x++) {
                result += this.board[x][y]+" ";
            }
            result += "\n";
        }
        return result;
    }

}
