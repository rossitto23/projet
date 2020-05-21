package atlg4.ultimate.g49282.model;

/**
 * This class represent the user.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class User {

    private String pseudonyme;

    private String passwordHash;

    Score score;

    public User(String pseudo, String pswd) {
        this.pseudonyme = pseudo;
        this.passwordHash = pswd;
        this.score = new Score(0, 0, 0);
    }

    /**
     *
     * @return the pseudonyme.
     */
    public String getPseudonyme() {
        return pseudonyme;
    }

    /**
     *
     * @return the passwordHash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

}
