package atlg4.server.g49282.model;

import anagram.exception.ModelException;
import atlg4.server.g49282.server.AbstractServer;
import atlg4.server.g49282.server.ConnectionToClient;
import be.he2b.atl.chat.users.Members;
import be.he2b.atl.chat.users.User;
import be.he2b.atl.message.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class AnagramServer extends AbstractServer {

    private static final int PORT = 12345;
    static final String ID_MAPINFO = "ID";
    List<AnagramGame> games;
    private int clientId;

    private final Members members;

    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
            while (b.hasMoreElements()) {
                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
                    if (f.getAddress().isSiteLocalAddress()) {
                        return f.getAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, "NetworkInterface error", e);
        }
        return null;
    }

    /**
     * le constructeur appelle le constructeur de son parent grâce a super, et
     * écoute les demandes de connexion via l'intruction
     * <code>this.listen()</code>. En cas d'erreur, l'exception n'est pas gérée
     * dans le constructeur.
     *
     * @throws IOException
     */
    public AnagramServer() throws IOException {
        super(PORT);
        members = new Members();
        clientId = 0;
        games = new ArrayList<>();
        this.listen();
    }

    public Members getMembers() {
        return members;
    }

    public String getIP() {
        if (getLocalAddress() == null) {
            return "Unknown";
        }
        return getLocalAddress().getHostAddress();
    }

    public int getNbConnected() {
        return getNumberOfClients();
    }

    /**
     * Cette méthode demande à son parent d'arrêter d'écouter les demandes de
     * connexions et de fermer toutes les connexions.
     *
     * @throws IOException
     */
    public void quit() throws IOException {
        this.stopListening();
        this.close();
    }

    final synchronized int getNextId() {
        clientId++;
        return clientId;
    }

    /**
     * réécrite pour être interprété les messages reçus de chaque client.
     *
     * @param msg
     * @param client
     */
    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                int memberId = (int) client.getInfo(ID_MAPINFO);
                User author = message.getAuthor();
                members.changeName(author.getName(), memberId);
                Message messageName = new MessageProfile(memberId, author.getName());

                sendToClient(messageName, memberId);
                sendToAllClients(new MessageMembers(members));
                break;
            case MAIL_TO:
                sendToClient(message, message.getRecipient());
                break;

            case PROPOSITION: {
                try {

                    games.add(new AnagramGame(client, ""));

                    Message mess = new MessageProposition(getGameFromPlayer(client).getFindAnswer());
                    client.sendToClient(mess);

                } catch (IOException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ModelException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;
            case CHECK_ANSWER: {
                try {
                    if (getGameFromPlayer(client).isOver()) {
                        Message mess = new MessageOver();
                        client.sendToClient(mess);
                    }
                    if (getGameFromPlayer(client).isValidAnswer((String) message.getContent())) {

                        getGameFromPlayer(client).nextWord();
                        Message mess1 = new MessageFoundWord(getGameFromPlayer(client).getNbSucess());
                        Message mess2 = new MessageNbWordLeft(getGameFromPlayer(client).getNbWordLeft());
                        client.sendToClient(mess1);
                        client.sendToClient(mess2);
                        Message messProp = new MessageProposition(getGameFromPlayer(client).getFindAnswer());

                        client.sendToClient(messProp);
                    } else {

                        Message mess1 = new MessageNbTry(games.get(0).getNbTry());
                        client.sendToClient(mess1);
                    };
                } catch (ModelException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case PASS: {
                try {

                    if (getGameFromPlayer(client).isOver()) {
                        Message mess = new MessageOver();
                        client.sendToClient(mess);
                    }
                    getGameFromPlayer(client).pass();
                    getGameFromPlayer(client).nextWord();
                    Message messProp = new MessageProposition(getGameFromPlayer(client).getFindAnswer());
                    Message mess2 = new MessageNbWordLeft(getGameFromPlayer(client).getNbWordLeft());
                    client.sendToClient(messProp);
                    client.sendToClient(mess2);

                } catch (ModelException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case MEMBERS:
                break;

            case NB_FAIL: {
                try {
                    Message mess = new MessageFailWord(getGameFromPlayer(client).getNbFail());
                    client.sendToClient(mess);
                } catch (ModelException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case NB_FOUND:
                Message mess;
                try {
                    mess = new MessageFoundWord(getGameFromPlayer(client).getNbSucess());
                    client.sendToClient(mess);
                } catch (ModelException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                 {

                }
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
        setChanged();
        notifyObservers(message);
    }

    /**
     * Réecrire la méthode afin d'effectuer un traitement lorsqu'un client vient
     * de se connecter. Ce sera l'occasion pour notre serveur de mettre à jour
     * la liste à tous ses clients. Attention, l'identifiant d'un utilisateur,
     * une fois calculé, est conservé au sein de la
     * <code>HashMap savedInfo</code> du client <code>clientConnected</code>
     * donné en paramètre.
     *
     * @param client
     */
    @Override
    protected void clientConnected(ConnectionToClient client) {
        super.clientConnected(client);
        int memberId = members.add(getNextId(), client.getName(), client.getInetAddress());
        client.setInfo(ID_MAPINFO, memberId);
        sendToAllClients(new MessageMembers(members));
        setChanged();
        notifyObservers();
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {

    }

    @Override
    protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
        super.clientException(client, exception);
        try {
            if (client.isConnected()) {
                client.sendToClient(new IllegalArgumentException("Message illisible " + exception.getMessage()));
            }
        } catch (IOException ex) {
            Logger.getLogger(AnagramServer.class.getName()).log(Level.SEVERE, "Impossible d envoyer erreur au client", ex);
        }
    }

    void sendToClient(Message message, User recipient) {
        sendToClient(message, recipient.getId());
    }

    void sendToClient(Message message, int clientId) {

    }

    private AnagramGame getGameFromPlayer(final ConnectionToClient client) {
        for (AnagramGame game : games) {
            if (game.getClient() != null && game.getClient().equals(client)) {
                return game;
            }

        }
        return null;
    }

}
