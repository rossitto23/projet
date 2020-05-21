/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atlg4.server.g49282;

import anagram.Anagram;
import anagram.controller.ConsoleController;
import anagram.model.Facade;
import anagram.model.Model;
import anagram.view.ConsoleView;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class main {

    public static void main(String[] args) {
        Facade model = new Model();
        ConsoleView view = new ConsoleView();
        ConsoleController controller = new ConsoleController(model, view);
        controller.initialize();
        controller.start();

    }

}
