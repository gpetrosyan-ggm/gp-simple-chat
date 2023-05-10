package gp.assessments.chat.common.error;

import gp.assessments.chat.common.enums.ErrorType;
import lombok.Getter;

@Getter
public class SimpleChatBaseException extends RuntimeException {

    private final ErrorType errorType;

    public SimpleChatBaseException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

}
