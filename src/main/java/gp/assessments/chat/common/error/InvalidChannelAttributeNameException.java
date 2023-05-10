package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidChannelAttributeNameException extends SimpleChatBaseException {

    public InvalidChannelAttributeNameException(String message) {
        super(message, ErrorType.INVALID_CHANNEL_ATTRIBUTE_NAME);
    }

}
