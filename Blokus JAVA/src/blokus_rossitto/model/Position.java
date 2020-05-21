package blokus_rossitto.model;

public class Position {

    private int x;
    private int y;

    /**
     * Constructor of Position
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * return the value of x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * return the value of y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * new position in (x,y)
     *
     * @param p
     */
    public Position(Position p) {
        this(p.x, p.y);
    }

    /**
     *
     * method to edit x and y. x = y and y = 0-x
     */
    public void swap() {
        int nb = x;
        x = y;
        y = 0 - nb;
    }

    /**
     *
     * method to edit x and y. x= -X and y=-y
     */
    public void mirror() {
        x = (-x);
        y = (-y);
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

}
