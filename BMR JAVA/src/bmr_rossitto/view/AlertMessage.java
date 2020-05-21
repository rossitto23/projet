package bmr_rossitto.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMessage extends Alert {

    public AlertMessage(AlertType alertType, String contentText, ButtonType... buttons) {

        super(alertType, contentText, buttons);

    }

    @Override
    public String toString() {
        return "Message aux utilisateurs";
    }

}
