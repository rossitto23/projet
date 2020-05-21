package atlg4.client.g49282;

import javafx.scene.control.Alert;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>.
 * @author Meihdi El Amouri, <49262@etu.he2b.be>.
 */
public class AlertMessage extends Alert {

    public AlertMessage(AlertType alertType, String title, String header, String msg) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(msg);
        this.showAndWait();
    }

}
