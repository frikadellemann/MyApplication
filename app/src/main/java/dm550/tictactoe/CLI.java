package dm550.tictactoe;

import java.util.Scanner;

 public class CLI implements UserInterface {
    public static void main(String[] args) {
        while (true){
            UserInterface ui = new CLI();
            //int numPlayers = getParameter("number of players",2,6);
            Game game = new CFGame();
            ui.startGame(game);
        }
    }

    private static int getParameter(String message, int min, int max) {
        int result = min - 1;
        while (result < min || result > max) {
            //System.out.print("Please enter "+message+" between "+min+" and "+max+": ");
            try {
                result = ((int) (Math.random() * 7 + 1));
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }

    @Override
    public void showResult(String message) {
        System.out.println(message + "\n\nThanks for playing.");
        //System.exit(0);
    }

    @Override
    public void startGame(Game game) {
        game.setUserInterface(this);
        while (true) {
            System.out.println(game);
            game.checkResult();
            int x;
            while (true) {
                x = this.getParameter("column", 1, game.getHorizontalSize()) - 1;

                if (game.isFreeCol(x)) {
                    break;
                }
                System.out.println("The column is not free!");
            }
            game.addMove(x);
        }
    }

}

