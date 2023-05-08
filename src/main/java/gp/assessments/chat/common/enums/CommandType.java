package gp.assessments.chat.common.enums;


import java.util.Arrays;
import java.util.Optional;

public enum CommandType {

    LOGIN_COMMAND("/login"),
    JOIN_COMMAND("/join"),
    LEAVE_COMMAND("/leave"),
    DISCONNECT_COMMAND("/disconnect"),
    CHANNELS_COMMAND("/list"),
    USERS_COMMAND("/users"),
    MESSAGE_COMMAND("");

    private final String commandName;

    CommandType(final String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public static Optional<CommandType> getByCommandName(final String commandName) {
        if (commandName.isBlank()) {
            return Optional.empty();
        }
        return Arrays.stream(CommandType.values())
                     .filter(p -> p.getCommandName().equals(commandName))
                     .findAny();
    }

}
