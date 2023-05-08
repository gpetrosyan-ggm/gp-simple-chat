package gp.assessments.chat.command;

public interface Command {

    default void init(String[] params) {
        // When command doesn't have parameters
    }

}
