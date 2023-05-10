package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;

public class TokenNotExistsInRequestException extends SimpleChatBaseException {

    public TokenNotExistsInRequestException(String message) {
        super(message, ErrorType.TOKEN_NOT_EXISTS_IN_REQUEST);
    }

}
