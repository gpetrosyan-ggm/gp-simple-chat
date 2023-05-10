package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class InvalidTokenException extends SimpleChatBaseException {

    public InvalidTokenException(String message) {
        super(message, ErrorType.INVALID_TOKEN);
    }

}
