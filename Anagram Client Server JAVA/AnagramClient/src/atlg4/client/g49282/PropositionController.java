package atlg4.client.g49282;

import atlg4.client.g49282.model.ChatClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class PropositionController {

    ChatClient chat;

    @FXML
    private TextField nbPropo;

    @FXML
    private TextField nbWordLeft = new TextField("");

    @FXML
    private TextField enterPropo = new TextField("");

    @FXML
    private Button send;

    @FXML
    private TextField findWord;

    @FXML
    private Label wordSucess = new Label("");

    @FXML
    private Label wrongWord = new Label("");

    @FXML
    private Button pass;

    public PropositionController(ChatClient client) throws IOException {
        this.findWord = new TextField("");
        this.chat = client;
        chat.setProp(this);
    }

    @FXML
    public void initialize() {
        send.setOnAction((event) -> {
            try {
                send();
            } catch (IOException ex) {
                Logger.getLogger(PropositionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        pass.setOnAction((event) -> {
            try {
                pass();
            } catch (IOException ex) {
                Logger.getLogger(PropositionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setFindWord(String mess) {
        System.out.println(mess);
        this.findWord.setText(mess);
    }

    public void setNbPropo(int mess) {
        this.nbPropo.setText("" + mess);

    }

    private void send() throws IOException {
        System.out.println(enterPropo.getText().toString());
        String sende = enterPropo.getText();
        this.enterPropo.setText("");
        chat.checkAnswer(sende);
    }

    public TextField getEnterPropo() {
        return enterPropo;
    }

    public void setWrongWord(int number) {
        this.wrongWord.setText("et échoué sur " + number + " mot(s)");
    }

    public void setWordSucess(int number) {
        this.wordSucess.setText("Vous avez trouvé " + number + " mot(s)");
    }

    private void pass() throws IOException {
        this.chat.pass();
    }

    public void setNbWordLeft(int number) {
        this.nbWordLeft.setText("" + number);
    }

    public void runExit() throws IOException {

        PropositionController start = new PropositionController(chat);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Proposition.fxml"));

        loader.setController(start);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage s = new Stage();

        s.setMinHeight(450);
        s.setMinWidth(650);
        s.setScene(scene);
        s.show();

        Stage st = (Stage) pass.getScene().getWindow();
        st.close();
    }

}
