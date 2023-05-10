package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class UserAlreadyJoinedChannelException extends SimpleChatBaseException {

    public UserAlreadyJoinedChannelException(String message) {
        super(message, ErrorType.USER_ALREADY_JOINED_CHANNEL);
    }

}
