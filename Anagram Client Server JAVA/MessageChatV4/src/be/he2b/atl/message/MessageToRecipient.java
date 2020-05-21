package be.he2b.atl.message;

import be.he2b.atl.chat.users.User;

/**
 * The <code> Message </code> represents a general message send to a user.
 */
public class MessageToRecipient implements Message {

    private final Type type;
    private final User author;
    private final User recipient;
    private final String text;

    /**
     * Constructs a general text message between users.
     *
     * @param type the type of the message.
     * @param author the author of the message
     * @param recipient the recipient of the message.
     * @param text the text of the message.
     */
    public MessageToRecipient(Type type, User author, User recipient, String text) {
        this.type = type;
        this.author = author;
        this.recipient = recipient;
        this.text = text;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public User getRecipient() {
        return recipient;
    }

    /**
     * Return the text within this message.
     *
     * @return the text within this message.
     */
    @Override
    public Object getContent() {
        return text;
    }

}
