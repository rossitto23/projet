package atlg4.ultimate.g49282.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 */
public class UltimateTicTacToe implements TicTacToe {

    private List<MiniTicTacToe> boardParts;
    private MiniTicTacToe lastMoveBoardPart;
    private Symbol bigWinner;

    /**
     * The constructor of UltimateTicTacToe.
     */
    public UltimateTicTacToe() {
        this.boardParts = new ArrayList<>(8);
        initList();
        this.lastMoveBoardPart = (MiniTicTacToe) boardParts.get(0);
        this.bigWinner = null;

    }

    public void initList() {
        for (int i = 0; i <= 8; i++) {
            this.boardParts.add(new MiniTicTacToe());
        }
    }

    public List<MiniTicTacToe> getBoardParts() {
        return boardParts;
    }

    public MiniTicTacToe getLastMoveBoardPart() {
        return lastMoveBoardPart;
    }

    public Symbol getBigWinner() {
        return bigWinner;
    }

    public void setLastMoveBoardPart(MiniTicTacToe b) {
        this.lastMoveBoardPart = b;
    }

    @Override
    public boolean isFinished() {
        return checkColumn() || checkDiag() || checkRow() || isFull();
    }

    @Override
    public void playpart(int partIndex, Symbol sym) {
        this.lastMoveBoardPart.playpart(partIndex, sym);
    }

    @Override
    public Symbol getWinner() {
        return bigWinner;
    }

    @Override
    public boolean canPlayPart(int partIndex) {
        if (boardParts.get(partIndex).isFinished()) {
            return false;
        }
        return boardParts.get(partIndex).isEmpty() && boardParts.get(partIndex).equals(lastMoveBoardPart);

    }

    private boolean checkDiagonalesfirst() {
        for (int i = 0; i <= 8; i += 4) {
            if (boardParts.get(i).getSymbol() == null) {
                return false;
            }

        }
        return true;
    }

    private boolean checkDiagonalesSec() {
        for (int i = 2; i <= 6; i += 2) {
            if (boardParts.get(i).getSymbol() == null) {
                return false;
            }

        }
        return true;
    }

    @Override
    public boolean checkDiag() {
        boolean valid = false;

        if (checkDiagonalesfirst() || checkDiagonalesSec()) {
            if (boardParts.get(0).getSymbol() == boardParts.get(4).getSymbol()
                    && boardParts.get(4).getSymbol() == boardParts.get(8).getSymbol()) {
                this.bigWinner = boardParts.get(0).getSymbol();
                valid = true;
            } else if (boardParts.get(2).getSymbol() == boardParts.get(4).getSymbol() && boardParts.get(4).getSymbol() == boardParts.get(6).getSymbol()) {
                this.bigWinner = boardParts.get(2).getSymbol();
                valid = true;
            }
        }

        return valid;
    }

    @Override
    public boolean checkRow() {
        boolean valid = false;

        for (int i = 0; i <= 6; i += 3) {
            if (boardParts.get(i).getSymbol() != null) {
                if (boardParts.get(i).sym == boardParts.get(i + 1).sym && boardParts.get(i + 1).sym == boardParts.get(i + 2).sym) {
                    this.bigWinner = boardParts.get(i).getSymbol();
                    valid = true;
                    break;
                }
            }

        }
        return valid;
    }

    @Override
    public boolean checkColumn() {
        boolean valid = false;

        for (int i = 0; i <= 2; i++) {
            if (boardParts.get(i).getSymbol() != null) {
                if (boardParts.get(i).sym == boardParts.get(i + 3).sym && boardParts.get(i + 3).sym == boardParts.get(i + 6).sym) {
                    this.bigWinner = boardParts.get(i).getSymbol();
                    valid = true;
                    break;
                }
            }

        }
        return valid;
    }

    @Override
    public boolean isFull() {
        boolean valid = true;

        for (int i = 0; i < boardParts.size(); i++) {
            if (boardParts.get(i).getSymbol() == null) {
                valid = false;
            }
        }

        return valid;
    }

}
