package be.he2b.atl.message;

/**
 * The <code> Type </code> represents the type of a message send between a user
 * and the server.
 *
 * @author Nicolas Rossitto, <49282@etu.he2b.be>
 * @author Meihdi El Amouri, <49262@etu.he2b.be>
 */
public enum Type {

    /**
     * Message with the profile of a specific user.
     */
    PROFILE,
    /**
     * General message send between two connected users.
     */
    MAIL_TO,
    /**
     * Message with the list of all connected users.
     */
    MEMBERS,
    /**
     *
     */
    PROPOSITION,
    /**
     *
     */
    CHECK_ANSWER,
    /**
     *
     */
    PASS,
    /**
     *
     */
    NB_TRY,
    /**
     *
     */
    NB_FOUND,
    /**
     *
     */
    NB_FAIL,
    /**
     *
     */
    NB_LEFT,
    /**
     *
     */
    IS_OVER;
}
