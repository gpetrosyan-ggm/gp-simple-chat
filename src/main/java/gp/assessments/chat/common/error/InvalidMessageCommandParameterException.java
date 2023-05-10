package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidMessageCommandParameterException extends SimpleChatBaseException {

    public InvalidMessageCommandParameterException(String message) {
        super(message, ErrorType.INVALID_MESSAGE_COMMAND_PARAMETER);
    }

}
