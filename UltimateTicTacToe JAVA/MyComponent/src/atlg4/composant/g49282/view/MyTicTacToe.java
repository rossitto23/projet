/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.composant.g49282.view;

import atlg4.composant.g49282.MainApp;
import atlg4.composant.g49282.model.Nameplayer;
import atlg4.composant.g49282.model.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author g49282
 */
public class MyTicTacToe extends StackPane implements Initializable {

    private AnchorPane grid;

    @FXML
    private Button button1 = new Button(null);
    @FXML
    private Button button2 = new Button(null);
    @FXML
    private Button button3 = new Button(null);
    @FXML
    private Button button4 = new Button(null);
    @FXML
    private Button button5 = new Button(null);
    @FXML
    private Button button6 = new Button(null);
    @FXML
    private Button button7 = new Button(null);
    @FXML
    private Button button8 = new Button(null);
    @FXML
    private Button button9 = new Button(null);

    @FXML
    private Label win = new Label("");

    private Player player;

    private ArrayList<Button> list = new ArrayList<Button>(9);

    public MyTicTacToe() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TicTacToe.fxml"));
        try {
            loader.setController(this);
            grid = (AnchorPane) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(MyTicTacToe.class.getName()).log(Level.SEVERE, null, ex);
        }
        initList();
        this.getChildren().addAll(grid,win);
        player = new Player(Nameplayer.JOUEUR_X);
    }

    public void edButton(int index) {
        editButton(list.get(index), player);
        System.out.println("Je suis appelé pour le bouton " + index);

    }

    public void initList() {
        list.add(button1);
        list.add(button2);
        list.add(button3);
        list.add(button4);
        list.add(button5);
        list.add(button6);
        list.add(button7);
        list.add(button8);
        list.add(button9);
    }

    public void addEvent(int index, EventHandler<MouseEvent> event) {

        list.get(index).setOnMouseClicked(event);
    }

    public void editButton(Button btn, Player player) {
        btn.setText(player.getSymbol().getValue());
    }

    /**
     * TODO
     *
     * @param l
     */
    public void disableButton(ArrayList<Button> l) {
        for (Button btn : l) {
            btn.setDisable(true);
        }
    }

    /**
     * return true if the player win
     *
     * @param player
     * @return true or false
     */
//    public boolean winning(Player player) {
//        boolean play = false;
//
//        if ((win(button1, button2, button3, player))
//                || (win(button4, button5, button6, player))
//                || (win(button7, button8, button9, player))
//                || (win(button1, button4, button7, player))
//                || (win(button2, button5, button8, player))
//                || (win(button3, button6, button9, player))
//                || (win(button1, button5, button9, player))
//                || (win(button3, button5, button7, player))) {
//            play = true;
//        }
//        return play;
//    }
//
//    private boolean win(Button btn1, Button btn2, Button btn3, Player player) {
//        boolean ok = false;
//
//        if (btn1.getText().equals(player.getSymbol().getValue())
//                && btn2.getText().equals(player.getSymbol().getValue())
//                && btn3.getText().equals(player.getSymbol().getValue())) {
//            ok = true;
//        }
//        return ok;
//    }
//
//    /**
//     * if there is a winner, display his name, otherwise display nothing
//     */
//    public void winnerIs(Player player1, Player player2) {
//
//        Label playerX = new Label("x");
//        playerX.setTextFill(Color.BLUE);
//        playerX.setId("subtitle");
//
//        Label playerO = new Label("o");
//        playerO.setTextFill(Color.RED);
//        playerO.setId("subtitle1");
//
//        if (winning(player1)) {
//            //   this.winner.setText(player1.getSymbol().getValue());
//            disableButton(list);
//            grid.getChildren().add(playerX);
//
//        } else if (winning(player2)) {
//            // this.winner.setText(player2.getSymbol().getValue());
//            disableButton(list);
//            grid.getChildren().add(playerO);
//        } else {
//            //  this.winner.setText("");
//        }
//
//    }
    public ArrayList<Button> getList() {
        return list;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWinner(String s) {
        this.win.setMaxSize(160, 160);
        this.win.setText(s);
        if (s.equals("O")) {
            win.setTextFill(Color.web("#FF1951"));
        } else {
            win.setTextFill(Color.web("#1C53CC"));
        }
    }
}
