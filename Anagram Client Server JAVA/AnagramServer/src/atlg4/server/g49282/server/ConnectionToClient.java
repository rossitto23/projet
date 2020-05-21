package atlg4.server.g49282.server;

import anagram.controller.ConsoleController;
import anagram.model.Facade;
import anagram.model.Model;
import anagram.view.ConsoleView;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * sert à communiquer avec un client particulier.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class ConnectionToClient extends Thread {

    /**
     * référence au serveur
     */
    private AbstractServer server;

    /**
     * socket de la connexion au serveur
     */
    private Socket clientSocket;

    /**
     * flux d'entrée des données venant du client
     */
    private ObjectInputStream input;

    /**
     * flux de sortie des données à envoyer vers le client
     */
    private ObjectOutputStream output;

    /**
     * signale si une demande d'arrêt du serveur a été demandée
     */
    private boolean readyToStop;

    /**
     * map des différentes informations que l'on souhaite conserver sur le
     * serveur concernant un client
     */
    private HashMap<String, Object> savedInfo = new HashMap<>(10);

    /**
     * initialise les attributs <code>Socket clientSocket</code>
     * <code>AbstractServer server</code>
     * <p>
     * Il ouvre également les flux de communications avec le client.
     *
     * @param clientSocket
     * @param server
     * @throws IOException
     */
    protected ConnectionToClient(Socket clientSocket,
            AbstractServer server) throws IOException {

        this.clientSocket = clientSocket;
        this.server = server;

        clientSocket.setSoTimeout(0);

        try {
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException ex) {
            try {
                closeAll();
            } catch (Exception exc) {
            }
            throw ex;
        }

        Facade model = new Model();
        readyToStop = false;
        start(); // start le thread en attente des data du socket

    }

    /**
     * permet d'arrêter la communication avec le client et fermer les flux
     * ouverts.
     *
     * @throws IOException
     */
    final public void close() throws IOException {
        readyToStop = true;
        closeAll();
    }

    /**
     * permet de fermer les flux utilisés pour communiquer avec le client.
     *
     * @throws IOException
     */
    private void closeAll() throws IOException {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (output != null) {
                output.close();
            }
            if (input != null) {
                input.close();
            }
        } finally {
            output = null;
            input = null;
            clientSocket = null;
        }
    }

    final public InetAddress getInetAdress() {
        return clientSocket == null ? null : clientSocket.getInetAddress();
    }

    /**
     * ajoute un élément a la map
     *
     * @param infoType
     * @param info
     */
    public void setInfo(String infoType, Object info) {
        savedInfo.put(infoType, info);
    }

    /**
     * retourne l'élément de la map a la clés infoType
     *
     * @param infoType
     * @return
     */
    public Object getInfo(String infoType) {
        return savedInfo.get(infoType);
    }

    /**
     *
     * @param message
     * @return
     */
    protected boolean handleMessageFromClient(Object message) {
        return true;
    }

    /**
     * cette méthode envoi un Object vers le client.<p>
     * Pour ce faire, le flux de sortie de données doit être nettoyé avant
     * chaque envoi.
     *
     *
     *
     * @param msg
     * @throws IOException
     */
    public void sendToClient(Object msg) throws IOException {
        if (clientSocket == null || output == null) {
            throw new SocketException("socket does not exist");

        }
        output.reset(); //nettoyage
        output.writeObject(msg);
    }

    /**
     * cette méthode attend que des données arrivent via le flux d'entrée des
     * données par l'instruction <code>msg = input.readObject();</code>.
     * Losqu'un message est reçu, il est envoyé à la classe AbstractServer afin
     * d'être interprété par la méthode
     * <code>handleMessageFromeClient(...)</code>
     */
    @Override
    final public void run() {
        server.clientConnected(this);

        try {
            Object msg;

            while (!readyToStop) {
                try {
                    msg = input.readObject();

                    if (!readyToStop && handleMessageFromClient(msg)) {
                        server.receiveMessageFromClient(msg, this);
                    }
                } catch (ClassNotFoundException | RuntimeException ex) {
                    server.clientException(this, ex);
                }
            }
        } catch (Exception exception) {
            if (!readyToStop) {
                try {
                    closeAll();
                } catch (Exception ex) {
                }
                server.clientException(this, exception);
            }
        } finally {
            server.clientDisconnected(this);
        }
    }

    @Override
    public String toString() {
        return clientSocket == null ? null
                : clientSocket.getInetAddress().getHostName()
                + " (" + clientSocket.getInetAddress().getHostAddress() + ")";
    }

    @Override
    protected void finalize() {
        try {
            closeAll();
        } catch (IOException e) {
        }
    }

    final public InetAddress getInetAddress() {
        return clientSocket == null ? null : clientSocket.getInetAddress();
    }

    final public boolean isConnected() {
        return clientSocket != null && output != null;
    }

    private void startGame() {
        Facade model = new Model();
        ConsoleView view = new ConsoleView();
        ConsoleController controller = new ConsoleController(model, view);
        controller.initialize();
        controller.start();
    }

}
