package be.he2b.atl.message;

import be.he2b.atl.chat.users.User;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class MessageFailWord implements Message {

    private Type type;
    private int nb;

    public MessageFailWord(int nb) {
        type = Type.NB_FAIL;
        this.nb = nb;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public User getAuthor() {
        return User.ADMIN;
    }

    @Override
    public User getRecipient() {
        return User.EVERYBODY;
    }

    @Override
    public Object getContent() {
        return nb;
    }

}
