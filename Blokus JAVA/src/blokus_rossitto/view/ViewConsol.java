package blokus_rossitto.view;

import blokus_rossitto.controller.ControllerBlokus;
import blokus_rossitto.model.Colored;
import blokus_rossitto.model.Game;
import blokus_rossitto.model.Model;
import blokus_rossitto.model.Piece;
import blokus_rossitto.viewFx.BlokusMain;
import java.util.Scanner;

public class ViewConsol {

    private final Scanner in;
    BlokusMain blok = new BlokusMain();

    public ViewConsol() {
        in = new Scanner(System.in);

    }

    /**
     * ask the name of the player
     *
     * @return
     */
    public String AskName() {
        String name = null;
        try {
            System.out.println("Enter the name of the players: ");
            name = in.nextLine();

        } catch (Exception e) {
            System.out.println("Enter one NAME please!!");
        }
        return name;
    }

    /**
     * ask number player
     *
     * @return
     */
    public int NbJoueur() {
        System.out.println("A combien jouez vous?");
        return in.nextInt();
    }

    /**
     * displays an error message
     */
    public static void error() {
        System.out.println("ERROR");
        // A FAIRE!!!!!
    }

    /**
     * display an help message
     */
    public void help() {
        System.out.println("------------ HELP -----------\n"
                + "example of request: \n"
                + "show (affiche le plateau de jeu)\n"
                + "stock (affiche le numéro du joueur"
                + "courant et son stock de pièce)\n"
                + "play n i j (permet de placer la pièce n de"
                + "son stock dans la case(i, j))");
    }

    /**
     *
     * show the current player
     *
     * @param game
     */
    public static void turnTo(Model game) {
        System.out.println("Turn to: " + game.getCurrentPlayer().getName());
    }

    /**
     * show an welcome message with different order
     */
    public static void welcome() {
        System.out.println("-------------------------------------------");
        System.out.println("Welcome in Blokus! Choose one option please: \n"
                + "Show : (affiche le plateu de jeu \n"
                + "stock (affiche le numéro du joueur courant "
                + "et son stock de pièce \n"
                + "play n i j (permet de placer le pièce n de son stock "
                + "dans la case (i,j))");
    }

    /**
     * if show: show the board , if stock: show the stock of current player , if
     * play: place the piece n has the position (i, j)
     *
     * @param tab
     * @param game
     * @param b
     */
    public static boolean showStockPlay(String[] tab, Model game, boolean b) throws Exception {

        switch (tab[0].toLowerCase()) {
            case "show":
                System.out.println(boardView(game));
                b = true;
                break;
            case "stock":
                System.out.println(game.getCurrentPlayer().getStock().toString());
                b = true;
                break;
            case "play":
                ControllerBlokus.play(tab, game);
                b = true;
                break;
            case "exit":
                b = false;
                break;
            default:
                error();
        }
        return b;
    }

    /**
     * show the board in consol
     *
     * @param game
     * @return
     */
    public static String boardView(Model game) {
        String s = "";

        int size = game.getBlokus().getBoardSize();
        for (int i = 0; i <= size - 1; i++) {
            if (i < 10) {
                s += "|" + i;

            } else {
                s += "|" + i + "";
            }
        }
        s += "|\n";
        for (int i = 0; i < 20; i++) {
            //  s += " |";
            for (int j = 0; j < 20; j++) {

                Piece piece = game.getBlokus().getPiece(j, i);
                if (piece == null) {
                    s += printWhiteCase() + " ";
                } else {

                    s += printColor(piece.getColor()) + " ";
                }

            }
            s += i + size + "\n";
            size = size - 2;

        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        String request;
        String[] s = null;
        boolean continu = true;
        Model game = new Game();
        game.start();
        Scanner in = new Scanner(System.in);
        welcome();

        while (continu) {
            try {
                turnTo(game);

                game.getCurrentPlayer();
                request = in.nextLine();
                s = request.split(" ");
                continu = showStockPlay(s, game, continu);

            } catch (NumberFormatException e) {
                System.out.println("you don't respect the types of characters");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("You don't respect the instruction! "
                        + "please, enter the complete instruction");
            }
        }

    }

    /**
     * print color in board
     *
     * @param color
     * @return
     */
    public static String printColor(Colored color) {
        String c = "";
        switch (color) {
            case RED:
                c = "\u001B[31m" + "\u25a1" + "\u001B[0m";
                break;
            case BLUE:
                c = "\u001B[34m" + "\u25a1" + "\u001B[0m";
                break;
            case YELLOW:
                c = "\u001B[33m" + "\u25a1" + "\u001B[0m";
                break;
            case GREEN:
                c = "\u001B[32m" + "\u25a1" + "\u001B[0m";
        }

        return c;
    }

    public static String printWhiteCase() {
        return "\u001B[37m" + "\u25A0" + "\u001B[0m";
    }
}
