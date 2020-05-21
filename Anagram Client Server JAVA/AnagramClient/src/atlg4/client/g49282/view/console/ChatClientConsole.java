package atlg4.client.g49282.view.console;

import atlg4.client.g49282.model.ChatClient;
import be.he2b.atl.message.Message;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

/**
 * cette classe contient la vue console de l'application. Une partie se trouve
 * dans <code>WelcomeController</code>
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class ChatClientConsole implements Observer {

    private String host = null;

    private int port = 0;

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            Message message = (Message) arg;

        }
    }
    private final ChatClient model;
    private final DateTimeFormatter formatter;

    public ChatClientConsole(ChatClient client) {
        this.model = client;
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.model.addObserver(this);
    }

    public void printUsage() {

    }

    public void askCommand() {

    }

}
