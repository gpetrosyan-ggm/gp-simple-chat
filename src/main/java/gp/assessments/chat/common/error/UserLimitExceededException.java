package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class UserLimitExceededException extends SimpleChatBaseException {

    public UserLimitExceededException(String message) {
        super(message, ErrorType.USER_LIMIT_EXCEEDED);
    }

}
