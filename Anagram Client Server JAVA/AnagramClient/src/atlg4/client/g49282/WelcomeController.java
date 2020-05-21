package atlg4.client.g49282;

import atlg4.client.g49282.model.ChatClient;
import atlg4.client.g49282.view.console.ChatClientConsole;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class WelcomeController {

    @FXML
    private TextField log_in;

    @FXML
    private TextField address;

    @FXML
    private TextField port;

    @FXML
    private Button ok;

    private ChatClient cli;

    @FXML
    public void initialize() {
        ok.setOnAction((event) -> {
            try {
                ok();
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void ok() throws IOException {
        ChatClient client = null;
        try {
            String host = address.getText();
            int por = Integer.parseInt(port.getText());
            String name = log_in.getText();

            client = new ChatClient(host, por, name, "");
            ChatClientConsole console = new ChatClientConsole(client);
            console.printUsage();
            console.askCommand();
            PropositionController start = new PropositionController(client);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Proposition.fxml"));

            loader.setController(start);
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage s = new Stage();

            s.setMinHeight(450);
            s.setMinWidth(650);
            s.setScene(scene);
            s.show();

            Stage st = (Stage) ok.getScene().getWindow();
            st.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatClientConsole.class.getName()).log(Level.SEVERE, "Main error", ex);
            try {
                client.quit();
            } catch (NullPointerException | IOException clientEx) {
                Logger.getLogger(ChatClientConsole.class.getName()).log(Level.SEVERE, "Quit client error", clientEx);
            }
            System.exit(0);
        }
    }
}
