package atlg4.server.g49282.model;

import anagram.exception.ModelException;
import anagram.model.Facade;
import anagram.model.Model;
import atlg4.server.g49282.server.ConnectionToClient;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class AnagramGame {

    public String answer;
    public String findAnswer;
    private ConnectionToClient client;
    private int nbFail;
    private int nbSucess;
    private int nbTry;

    private Facade model;

    public AnagramGame(final ConnectionToClient aClient, String aAnswer) throws ModelException {
        if (aClient == null) {
            throw new IllegalArgumentException("The client can be null");
        }
        this.model = new Model();
        model.initialize();
        model.start();
        this.findAnswer = model.nextWord();
        this.client = aClient;

        this.answer = aAnswer;
        this.nbTry = 0;
    }

    public boolean isValidAnswer(String propo) throws ModelException {

        return model.propose(propo);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setFindAnswer(String findAnswer) {
        this.findAnswer = findAnswer;
    }

    public ConnectionToClient getClient() {
        return client;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFindAnswer() {
        return findAnswer;
    }

    public void nextWord() throws ModelException {
        this.findAnswer = model.nextWord();
    }

    public void pass() throws ModelException {
        model.pass();
    }

    public int getNbFail() throws ModelException {
        return model.getNbUnsolvedWords();
    }

    public int getNbSucess() throws ModelException {
        return model.getNbSolvedWords();
    }

    public int getNbTry() throws ModelException {
        return model.getNbProposal();
    }

    public int getNbWordLeft() throws ModelException {
        return model.getNbRemainingWords();
    }

    boolean isOver() throws ModelException {
        return model.isOver();
    }

}
