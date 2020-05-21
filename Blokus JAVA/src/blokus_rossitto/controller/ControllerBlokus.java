package blokus_rossitto.controller;

import blokus_rossitto.model.Model;
import blokus_rossitto.model.Player;
import blokus_rossitto.model.Position;

public class ControllerBlokus {

    /**
     * check if the player's stock is empty or not
     *
     * @param player
     * @return one boolean value
     */
    public boolean stockVide(Player player) {
        return player.getStock().isEmpty();
    }

    /**
     * place a piece in the tray at position x y
     *
     * @param tab
     * @param game
     */
    public static void play(String[] tab, Model game) throws Exception {
        try {
            int piece = Integer.parseInt(tab[1]);
            int i = Integer.parseInt(tab[2]);
            int j = Integer.parseInt(tab[3]);
            if (game.getCurrentPlayer().getStock().getPiece(piece) != null) {
                game.selectPiece(game.getCurrentPlayer().getStock().getPiece(piece), game.getCurrentPlayer());
                game.putPiece(game.getSelectedPiece(), new Position(i, j), game.getCurrentPlayer());
                game.playLap(new Position(i, j));
            }
        } catch (NumberFormatException e) {
            System.out.println("Data is not ok!!");
        }
    }

}
