package blokus_rossitto.model;

public class Bot extends Player {

    private boolean bot;

    /**
     * the constructor of bot
     *
     * @param name
     * @param color
     * @param idPlayer
     * @param pos
     * @param bot
     */
    public Bot(String name, Colored color, int idPlayer, Position pos, boolean bot) {
        super(name, color, idPlayer, pos);
        this.bot = bot;
    }

    /**
     * the getter of attribute bot
     *
     * @return bot
     */
    public boolean isBot() {
        return bot;
    }

}
