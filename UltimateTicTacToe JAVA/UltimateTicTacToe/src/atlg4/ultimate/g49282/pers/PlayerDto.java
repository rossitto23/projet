package atlg4.ultimate.g49282.pers;

import atlg4.ultimate.g49282.exception.DTOException;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class PlayerDto extends EntityDto<Integer> {

    private String pseudo;
    private int nbWin;
    private int nbExAequo;
    private int nbDefeat;

    public PlayerDto(String pseudo, int nbWin, int nbExAequo, int nbDefeat) throws DTOException {

        this.pseudo = pseudo;
        this.nbWin = nbWin;
        this.nbExAequo = nbExAequo;
        this.nbDefeat = nbDefeat;
    }

    public PlayerDto(String pseudo) throws DTOException {
        if (pseudo == null) {
            throw new DTOException("the name is required!");

        }
        this.pseudo = pseudo;
        this.nbWin = 0;
        this.nbExAequo = 0;
        this.nbDefeat = 0;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getNbWin() {
        return nbWin;
    }

    public int getNbExAequo() {
        return nbExAequo;
    }

    public int getNbDefeat() {
        return nbDefeat;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNbWin(int nbWin) {
        this.nbWin = nbWin;
    }

    public void setNbExAequo(int nbExAequo) {
        this.nbExAequo = nbExAequo;
    }

    public void setNbDefeat(int nbDefeat) {
        this.nbDefeat = nbDefeat;
    }

}
