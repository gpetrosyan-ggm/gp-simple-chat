package gp.assessments.chat.common.factory;

import gp.assessments.chat.command.*;
import gp.assessments.chat.common.enums.CommandType;
import gp.assessments.chat.common.model.CommandMapperModel;
import gp.assessments.chat.handler.*;

public class CommandFactory {

    public CommandMapperModel getCommandMapperModel(final CommandType commandType, final String[] params) {
        return switch (commandType) {
            case LOGIN_COMMAND -> {
                Command command = new LoginCommand();
                command.init(params);
                yield new CommandMapperModel(command, new LoginCommandHandler());
            }
            case JOIN_COMMAND -> {
                Command command = new JoinCommand();
                command.init(params);
                yield new CommandMapperModel(command, new JoinCommandHandler());
            }
            case LEAVE_COMMAND -> new CommandMapperModel(new LeaveCommand(), new LeaveCommandHandler());
            case DISCONNECT_COMMAND -> new CommandMapperModel(new DisconnectCommand(), new DisconnectCommandHandler());
            case CHANNELS_COMMAND -> new CommandMapperModel(new ChannelsCommand(), new ChannelsCommandHandler());
            case USERS_COMMAND -> new CommandMapperModel(new UsersCommand(), new UsersCommandHandler());
            case MESSAGE_COMMAND -> {
                Command command = new MessageCommand();
                command.init(params);
                yield new CommandMapperModel(command, new MessageCommandHandler());
            }
        };
    }

}
