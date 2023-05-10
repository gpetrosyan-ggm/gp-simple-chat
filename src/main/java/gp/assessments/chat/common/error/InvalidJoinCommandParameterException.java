package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidJoinCommandParameterException extends SimpleChatBaseException {

    public InvalidJoinCommandParameterException(String message) {
        super(message, ErrorType.INVALID_JOIN_COMMAND_PARAMETER);
    }

}
