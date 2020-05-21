
package bmr_rossitto.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class MenuBar extends javafx.scene.control.MenuBar {
    
    public MenuBar(){
        super();
        
        
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("exit");
        
        file.getItems().add(exit);
        
    }
    
}
