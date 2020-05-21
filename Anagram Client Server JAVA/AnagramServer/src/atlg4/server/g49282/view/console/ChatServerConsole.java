package atlg4.server.g49282.view.console;

import be.he2b.atl.message.*;
import atlg4.server.g49282.model.AnagramServer;
import be.he2b.atl.chat.users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Démarre le serveur en créant une instance de la classe ChatServer. Les
 * messages reçus par le serveur seront affichés automatiquement car la classe
 * ChatServerConsole observe la classe ChatServer.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class ChatServerConsole implements Observer {

    public static void main(String[] args) {
        try {
            AnagramServer model = new AnagramServer();
            ChatServerConsole console = new ChatServerConsole(model);

            model.addObserver(console);
            System.out.println("Server started");
            System.out.println("");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            if (line.equals("quit")) {
                model.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(ChatServerConsole.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    private final AnagramServer model;

    public ChatServerConsole(AnagramServer model) {
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateUser();
        if (arg != null) {
            Message message = (Message) arg;
            updateMessage(message);
        }

    }

    private void updateUser() {
        System.out.println("");
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Liste Users ---- ----\n");
        builder.append("Nombre d'utilisateurs connectes : ")
                .append(model.getNbConnected()).append("\n");
        builder.append("ID").append("\t");
        builder.append("IP").append("\t\t");
        builder.append("NAME").append("\n");
        for (User member : model.getMembers()) {
            builder.append(member.getId()).append("\t");
            builder.append(member.getAddress()).append("\t");
            builder.append(member.getName()).append("\n");
        }
        System.out.println(builder);
        System.out.println("");
    }

    private void updateMessage(Message message) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n---- ---- Message recu ---- ----\n");
        builder.append(LocalDateTime.now()).append(" \n");
        builder.append("Type : ").append(message.getType()).append("\n");
        builder.append("De : ").append(message.getAuthor()).append("\t");
        builder.append("Pour : ").append(message.getRecipient()).append("\n");
        builder.append("Contenu\t").append(message.getContent());
        builder.append("\n");
        System.out.println(builder);
    }

}
