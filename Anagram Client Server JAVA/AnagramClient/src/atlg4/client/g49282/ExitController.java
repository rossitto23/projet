package atlg4.client.g49282;

import atlg4.client.g49282.model.ChatClient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class ExitController {

    @FXML
    private Label thanks = new Label("");

    ChatClient client;

    public ExitController(ChatClient client) {
        this.client = client;
    }

    @FXML
    public void initialize() {
        thanks.setText("Thanks to you");
    }
}
