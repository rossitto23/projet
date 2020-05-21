package be.he2b.atl.message;

import be.he2b.atl.chat.users.User;

/**
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public class MessageOver implements Message {

    Type type;

    public MessageOver() {
        this.type = Type.IS_OVER;
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
        return null;
    }

}
