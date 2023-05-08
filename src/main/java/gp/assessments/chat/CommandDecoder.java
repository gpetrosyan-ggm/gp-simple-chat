package gp.assessments.chat;

import gp.assessments.chat.common.enums.CommandType;
import gp.assessments.chat.common.factory.CommandFactory;
import gp.assessments.chat.common.model.CommandMapperModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        CommandType commandType;
        String[] commandParams;

        if (msg.startsWith("/")) {
            String[] parts = msg.trim().split("\\s");
            String commandName = parts[0];
            commandParams = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];

            Optional<CommandType> commandTypeOpt = CommandType.getByCommandName(commandName);
            if (commandTypeOpt.isPresent()) {
                commandType = commandTypeOpt.get();
            } else {
                throw new InvalidObjectException("Invalid command: " + commandName);
            }
        } else {
            commandType = CommandType.MESSAGE_COMMAND;
            commandParams = new String[]{msg};
        }

        CommandMapperModel model = new CommandFactory().getCommandMapperModel(commandType, commandParams);

        out.add(model);
    }

}
