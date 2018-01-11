package dm550.tictactoe;

/** main class creating a board and the GUI
 * defines the game play
 */
public class CFGame implements Game {
    //this is not a test
    /** currently active player */
    private int currentPlayer;

    /** total number of players */
    private int numPlayers;
    
    /** the board we play on */
    private CFBoard board;
    
    /** the gui for board games */
    private UserInterface ui;
    
    /** constructor that gets the number of players */
    public CFGame() {
        this.currentPlayer = 1;
        this.numPlayers = 2;
        this.board = new CFBoard(2);
    }

    @Override
    public String getTitle() { return "Connect 4";
    }

    @Override
    public void addMove(int x) {
        this.board.addMove(x, this.currentPlayer);
        if (this.currentPlayer == this.numPlayers) {
            this.currentPlayer = 1;
        } else {
            this.currentPlayer++;
        }
    }

    @Override
    public String getContent(Coordinate pos) {
        String result = "";
        int player = this.board.getPlayer(pos);
        if (player > 0) {
            result += player;
        }
        return result;
    }

    @Override
    public int getHorizontalSize() {
        return this.board.getxSize();
    }

    @Override
    public int getVerticalSize() {
        return this.board.getySize();
    }

    @Override
    public void checkResult() {
        int winner = this.board.checkWinning();
        if (this.board.checkFull()) {
            this.ui.showResult("This is a DRAW!");
        }
        if (winner > 0) {
            this.ui.showResult("Player "+winner+" wins!");
        }
    }

    @Override
    public boolean isFree(Coordinate pos) {
        return this.board.isFree(pos);
    }

    @Override
    public boolean isFreeCol(int x) {
        return this.board.isFreeCol(x);
    }

    @Override
    public void setUserInterface(UserInterface ui) {
        this.ui = ui;
        
    }
    
    public String toString() {
        return "Board before Player "+this.currentPlayer+" of "+this.numPlayers+"'s turn:\n"+this.board.toString();
    }

}
