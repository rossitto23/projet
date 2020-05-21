package atlg4.server.g49282.server;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Cette classe sert à écouter les demandes de connexion de la part des clients.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public abstract class AbstractServer extends Observable implements Runnable {

    /**
     * socket de la connexion au serveur
     */
    private ServerSocket serverSocket = null;

    /**
     * Thread écoutant les demandes de connexion des clients
     */
    private Thread connectionListener = null;

    /**
     * le numéro du port
     */
    private int port;

    /**
     * temps entre deux verifications de demande d'arrêt du serveur
     */
    private int timeOut = 500;

    /**
     * nb max de clients en attente de connexion
     */
    private int backLog = 10;

    /**
     * liste des Threads dédiés à chaque client
     */
    private final List<Thread> threads;

    /**
     * signale si une demande d'arrêt du serveur a été faite
     */
    private boolean readyToStop = true;

    /**
     * constructor of AbstractServer initialize port and threads
     *
     * @param port
     */
    public AbstractServer(int port) {
        this.port = port;
        threads = new ArrayList<>();
    }

    /**
     * cette méthode permet d'arrêter le serveur en n'écoutant plus les demandes
     * de connexions et en fermant tout les flux ouvert
     *
     * @throws IOException
     */
    final public void close() throws IOException {
        if (serverSocket == null) {
            return;
        }

        stopListening();

        try {
            serverSocket.close();
        } finally {
            for (Thread clientThreadList1 : getClientConnections()) {
                try {
                    ((ConnectionToClient) clientThreadList1).close();
                } catch (Exception ex) {

                }
            }

            serverSocket = null;
        }

        try {
            connectionListener.join();
        } catch (InterruptedException | NullPointerException ex) {

        }
    }

    final public void stopListening() {
        readyToStop = true;
    }

    synchronized final public List<Thread> getClientConnections() {
        return Collections.unmodifiableList(threads);
    }

    /**
     * cette méthode permet l'écoute des demandes de connexoin des clients pour
     * ca elle lance un Thread et le range dans l'attribut
     * <code>connexionListener</code> c'est possible grâce a l'implémentation de
     * l'interface Runnable
     *
     * @throws IOException
     */
    final public void listen() throws IOException {

        if (!isListening()) {
            if (serverSocket == null) {
                serverSocket = new ServerSocket(getPort(), backLog);
            }

            serverSocket.setSoTimeout(timeOut);
            connectionListener = new Thread(this);
            connectionListener.start();
        }
    }

    /**
     * verifie si il y a une connexion et si elle est encore en vie elle
     * retournera vrai si le serveur est prêt a accepter de nouveau client
     *
     * @return
     */
    final public boolean isListening() {
        return connectionListener != null && connectionListener.isAlive();
    }

    /**
     * retourne le numéro du port
     *
     * @return the port number
     */
    final public int getPort() {
        return port;
    }

    protected void clientConnected(ConnectionToClient client) {

    }

    synchronized protected void clientDisconnected(ConnectionToClient client) {

    }

    protected abstract void handleMessageFromClient(
            Object msg, ConnectionToClient client);

    public void sendToAllClients(Object msg) {
        for (Thread clientThreadList1 : getClientConnections()) {
            try {
                ((ConnectionToClient) clientThreadList1).sendToClient(msg);
            } catch (Exception ex) {
            }
        }
    }

    final synchronized void receiveMessageFromClient(
            Object msg, ConnectionToClient client) {
        this.handleMessageFromClient(msg, client);
    }

    /**
     * elle attend les demandes de connexion de la part du client via
     * l'instruction <code>Socket clientSocket = serverSocket.accept()</code>
     * une fois la demande de connexion reçu, un Thread est créé pour
     * communiquer avec le client via l'instruction
     * <code>new ConnectionToClient(clientSocket,this)</code>
     */
    @Override
    final public void run() {
        readyToStop = false;
        serverStarted();

        try {
            while (!readyToStop) {
                try {
                    Socket clientSocket = serverSocket.accept();

                    synchronized (this) {
                        if (!readyToStop) {
                            ConnectionToClient client = new ConnectionToClient(
                                    clientSocket, this);
                            this.threads.add(client);
                        }
                    }
                } catch (InterruptedIOException exception) {

                }
            }
        } catch (IOException exception) {
            if (!readyToStop) {
                listeningException(exception);
            }
        } finally {
            readyToStop = true;
            connectionListener = null;
            serverStopped();
        }

    }

    synchronized protected void clientException(
            ConnectionToClient client, Throwable exception) {
    }

    protected void listeningException(Throwable exception) {

    }

    protected void serverStopped() {

    }

    protected void serverStarted() {

    }

    protected void serverClosed() {

    }

    final public int getNumberOfClients() {
        return threads.size();
    }

}
