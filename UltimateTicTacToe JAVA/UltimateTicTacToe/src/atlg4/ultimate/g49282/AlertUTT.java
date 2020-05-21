package atlg4.ultimate.g49282;

import javafx.scene.control.Alert;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class AlertUTT extends Alert {

    public AlertUTT(AlertType alertType, String title, String header, String msg) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(msg);
        this.showAndWait();

    }

}
