package be.he2b.atl.message;

import be.he2b.atl.chat.users.User;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class MessageNbWordLeft implements Message {

    private Type type;
    private int nb;

    public MessageNbWordLeft(int nb) {
        type = Type.NB_LEFT;
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
