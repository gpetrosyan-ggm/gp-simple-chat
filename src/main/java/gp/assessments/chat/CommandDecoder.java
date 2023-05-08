package gp.assessments.chat;

import gp.assessments.chat.common.enums.CommandMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandDecoder extends MessageToMessageDecoder<String> {

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        CommandMapper commandMapper;
        String[] commandParams;

        if (msg.startsWith("/")) {
            String[] parts = msg.trim().split("\\s");
            String commandName = parts[0];
            commandParams = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];

            Optional<CommandMapper> commandMapperOpt = CommandMapper.getByCommandName(commandName);
            if (commandMapperOpt.isPresent()) {
                commandMapper = commandMapperOpt.get();
            } else {
                throw new InvalidObjectException("Invalid command: " + commandName);
            }
        } else {
            commandMapper = CommandMapper.MESSAGE_COMMAND;
            commandParams = new String[]{msg};
        }

        commandMapper.getCommand().init(commandParams);

        out.add(commandMapper);
    }

}
