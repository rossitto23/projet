package atlg4.ultimate.g49282.pers;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class PlayerSel {

    private int id;
    private String pseudo;
    private int nbWin;
    private int nbExAequo;
    private int nbDefeat;

    public PlayerSel(int id, String pseudo, int nbWin, int nbExAequo, int nbDefeat) {
        this.id = id;
        this.pseudo = pseudo;
        this.nbWin = nbWin;
        this.nbExAequo = nbExAequo;
        this.nbDefeat = nbDefeat;
    }

    public PlayerSel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbWin() {
        return nbWin;
    }

    public void setNbWin(int nbWin) {
        this.nbWin = nbWin;
    }

    public int getNbExAequo() {
        return nbExAequo;
    }

    public void setNbExAequo(int nbExAequo) {
        this.nbExAequo = nbExAequo;
    }

    public int getNbDefeat() {
        return nbDefeat;
    }

    public void setNbDefeat(int nbDefeat) {
        this.nbDefeat = nbDefeat;
    }

}
