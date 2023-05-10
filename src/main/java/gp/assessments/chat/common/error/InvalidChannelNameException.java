package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidChannelNameException extends SimpleChatBaseException {

    public InvalidChannelNameException(String message) {
        super(message, ErrorType.INVALID_CHANNEL_NAME);
    }

}
