package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidLoginCommandParameterException extends SimpleChatBaseException {

    public InvalidLoginCommandParameterException(String message) {
        super(message, ErrorType.INVALID_LOGIN_COMMAND_PARAMETER);
    }

}
