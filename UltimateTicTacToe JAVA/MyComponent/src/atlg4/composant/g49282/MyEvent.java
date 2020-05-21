/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.composant.g49282;

import atlg4.composant.g49282.model.Player;
import atlg4.composant.g49282.view.MyTicTacToe;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author g49282
 */
public class MyEvent implements EventHandler{
   
    private int index;
    private MyTicTacToe ttt;
            
    public MyEvent(int index, MyTicTacToe ttt){
           this.index = index;
           this.ttt = ttt;
    }

    @Override
    public void handle(Event event) {
        ttt.edButton(index);
    }
    
}
