package gp.assessments.chat.chat;

import gp.assessments.chat.ChatApplication;
import gp.assessments.chat.common.enums.CommandType;
import gp.assessments.chat.common.factory.CommandFactory;
import gp.assessments.chat.common.model.CommandMapperModel;
import gp.assessments.chat.storage.impl.TokenStorageImpl;
import gp.assessments.chat.utils.PropertiesUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ChatCommandDecoder extends MessageToMessageDecoder<String> {
    private static final Logger logger = LoggerFactory.getLogger(ChatCommandDecoder.class);

    private static final boolean ENABLE_TOKEN_VALIDATION = PropertiesUtils.getAsBoolean("enable.token.validation");

    @Override
    protected void decode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        CommandType commandType;
        String[] commandParams;

        if (msg.startsWith("/")) {
            String[] parts = msg.trim().split("\\s");
            String commandName = parts[0];

            Optional<CommandType> commandTypeOpt = CommandType.getByCommandName(commandName);
            if (commandTypeOpt.isPresent()) {
                commandType = commandTypeOpt.get();
            } else {
                throw new InvalidObjectException("Invalid command: " + commandName);
            }

            if (ENABLE_TOKEN_VALIDATION && CommandType.LOGIN_COMMAND != commandType) {
                logger.info("Validating token...");

                if (parts.length < 2) {
                    throw new RuntimeException("Token not exists");
                }

                if (!TokenStorageImpl.getInstance().existByToken(parts[1])) {
                    throw new RuntimeException("Invalid token");
                }

                commandParams = parts.length > 2 ? Arrays.copyOfRange(parts, 2, parts.length) : new String[0];
            } else {
                commandParams = parts.length > 1 ? Arrays.copyOfRange(parts, 1, parts.length) : new String[0];
            }
        } else {
            commandType = CommandType.MESSAGE_COMMAND;
            commandParams = new String[]{msg};
        }

        logger.info("'{}' command received", commandType);
        CommandMapperModel model = new CommandFactory().getCommandMapperModel(commandType, commandParams);

        out.add(model);
    }

}
