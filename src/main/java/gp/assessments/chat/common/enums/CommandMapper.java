package gp.assessments.chat.common.enums;

import gp.assessments.chat.command.*;
import gp.assessments.chat.handler.*;

import java.util.Arrays;
import java.util.Optional;

public enum CommandMapper {

    LOGIN_COMMAND("/login", new LoginCommand(), new LoginCommandHandler()),
    JOIN_COMMAND("/join", new JoinCommand(), new JoinCommandHandler()),
    LEAVE_COMMAND("/leave", new LoginCommand(), new LeaveCommandHandler()),
    DISCONNECT_COMMAND("/disconnect", new DisconnectCommand(), new DisconnectCommandHandler()),
    CHANNELS_COMMAND("/list", new ChannelsCommand(), new ChannelsCommandHandler()),
    USERS_COMMAND("/users", new ChannelsCommand(), new UsersCommandHandler()),
    MESSAGE_COMMAND("", new MessageCommand(), new MessageCommandHandler());

    private final String commandName;
    private final Command command;
    private final CommandHandler commandHandler;

    CommandMapper(final String commandName, final Command command, final CommandHandler commandHandler) {
        this.commandName = commandName;
        this.command = command;
        this.commandHandler = commandHandler;
    }

    public String getCommandName() {
        return commandName;
    }

    public Command getCommand() {
        return command;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static Optional<CommandMapper> getByCommandName(final String commandName) {
        if (commandName.isBlank()) {
            return Optional.empty();
        }
        return Arrays.stream(CommandMapper.values())
                     .filter(p -> p.getCommandName().equals(commandName))
                     .findAny();
    }

}
