package atlg4.ultimate.g49282.model;

import java.util.Objects;

/**
 * This class represent a square in the game.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class Square implements BoardPart {

    Symbol symbol;

    /**
     * The constructor of the square.
     */
    public Square() {
        this.symbol = null;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;

    }

    @Override
    public void setSymbol(Symbol sym) {

        if (sym.equals(null)) {
            throw new NullPointerException("There's no symbol to put");
        }
        this.symbol = sym;
    }

    @Override
    public boolean isEmpty() {
        return this.symbol == null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Square other = (Square) obj;
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
    }

}
