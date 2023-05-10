package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class UserIncorrectPasswordException extends SimpleChatBaseException {

    public UserIncorrectPasswordException(String message) {
        super(message, ErrorType.USER_INCORRECT_PASSWORD);
    }

}
