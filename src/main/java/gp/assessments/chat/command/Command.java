package gp.assessments.chat.command;


/**
 * Inbound base command model with possibility init some parameters
 */
public interface Command {

    default void init(String[] params) {
        // When command doesn't have parameters
    }

}
