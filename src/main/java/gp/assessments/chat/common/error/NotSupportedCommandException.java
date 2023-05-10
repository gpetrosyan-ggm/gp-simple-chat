package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class NotSupportedCommandException extends SimpleChatBaseException {

    public NotSupportedCommandException(String message) {
        super(message, ErrorType.NOT_SUPPORTED_COMMAND);
    }

}
