package atlg4.client.g49282.model;

import atlg4.client.g49282.PropositionController;
import atlg4.client.g49282.client.AbstractClient;
import be.he2b.atl.chat.users.Members;
import be.he2b.atl.chat.users.User;
import be.he2b.atl.message.Message;
import be.he2b.atl.message.MessageCheckAnswer;
import be.he2b.atl.message.MessageFailWord;
import be.he2b.atl.message.MessagePass;
import be.he2b.atl.message.MessageProfile;
import be.he2b.atl.message.MessageProposition;
import be.he2b.atl.message.MessageToRecipient;
import be.he2b.atl.message.Type;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class ChatClient extends AbstractClient {

    private final Members members;
    private User mySelf;
    private int numberProposition;
    private String proposition;
    private PropositionController p;
    private String name;

    /**
     * Constructs the client. Opens the connection with the server. Sends the
     * user name inside a <code> MessageProfile </code> to the server. Builds an
     * empty list of users.
     *
     * @param host the server's host name.
     * @param port the port number.
     * @param name the name of the user.
     * @param password the password needed to connect.
     * @throws IOException if an I/O error occurs when opening.
     */
    public ChatClient(String host, int port, String name, String password) throws IOException {
        super(host, port);

        openConnection();
        this.name = name;
        updateName(name);

        members = new Members();
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        Message message = (Message) msg;
        Type type = message.getType();
        switch (type) {
            case PROFILE:
                setMySelf(message.getAuthor());

                break;
            case MAIL_TO:

                showMessage(message);
                break;
            case MEMBERS:
                Members members = (Members) message.getContent();
                updateMembers(members);
                break;
            case PROPOSITION:

                p.setFindWord(message.getContent().toString());
                break;
            case NB_FAIL:
                Platform.runLater(() -> p.setWrongWord((int) message.getContent()));
                break;
            case NB_FOUND:
                Platform.runLater(() -> p.setWordSucess((int) message.getContent()));
            case NB_TRY:
                Platform.runLater(() -> p.setNbPropo((int) message.getContent()));
                break;

            case NB_LEFT:

                Platform.runLater(() -> p.setNbWordLeft((int) message.getContent()));
                break;
            case IS_OVER: {
                try {
                    p.runExit();
                } catch (IOException ex) {
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            default:
                throw new IllegalArgumentException("Message type unknown " + type);
        }
    }

    /**
     * Quits the client and closes all aspects of the connection to the server.
     *
     * @throws IOException if an I/O error occurs when closing.
     */
    public void quit() throws IOException {
        closeConnection();
    }

    /**
     * Return all the connected users.
     *
     * @return all the connected users.
     */
    public Members getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    /**
     * Return the user with the given id.
     *
     * @param id of the user.
     * @return the user with the given id.
     */
    public User getUsers(int id) {
        return members.getUser(id);
    }

    /**
     * Send the text message to the given user.
     *
     * @param recipient recipient of the message.
     * @param text message send.
     * @throws IOException if an I/O error occurs when closing.
     */
    public void sendMessage(User recipient, String text) throws IOException {
        if (recipient == null) {
            throw new IllegalArgumentException("Pas de destinataire selectionne");
        }
        MessageToRecipient message = new MessageToRecipient(Type.MAIL_TO, getMySelf(), recipient, text);
        sendToServer(message);
    }

    /**
     * Return the user.
     *
     * @return the user.
     */
    public User getMySelf() {
        return mySelf;
    }

    void setMySelf(User user) {
        this.mySelf = user;
    }

    void updateMembers(Members members) {
        this.members.clear();
        for (User member : members) {
            this.members.add(member);
        }
        notifyChange();
    }

    void showMessage(Message message) {
        notifyChange(message);
    }

    private void updateName(String name) throws IOException {
        sendToServer(new MessageProfile(0, name));
    }

    private void notifyChange() {
        setChanged();
        notifyObservers();
    }

    private void notifyChange(Message message) {
        setChanged();
        notifyObservers(message);
    }

    /**
     * Return the numbers of connected users.
     *
     * @return the numbers of connected users.
     */
    public int getNbConnected() {
        return members.size();
    }

    private void updateProp() throws IOException {
        sendToServer(new MessageProposition(""));
        sendToServer(new MessageFailWord(0));
        /*sendToServer(new MessageFoundWord(0));
        sendToServer(new MessageNbTry(0));*/
    }

    public void setProp(PropositionController aThis) throws IOException {
        this.p = aThis;
        updateProp();
    }

    public void checkAnswer(String text) throws IOException {
        sendToServer(new MessageCheckAnswer(text));
    }

    public void pass() throws IOException {
        sendToServer(new MessagePass());
        sendToServer(new MessageFailWord(0));
    }

}
