package gp.assessments.chat.common.enums;

public enum ErrorType {

    NOT_SUPPORTED_COMMAND(1000, "not.support.command.message"),
    TOKEN_NOT_EXISTS_IN_REQUEST(1001, "token.not.exists.in.request.message"),
    INVALID_TOKEN(1002, "invalid.token.message"),
    INVALID_JOIN_COMMAND_PARAMETER(1003, "invalid.join.command.parameter.message"),
    INVALID_MESSAGE_COMMAND_PARAMETER(1004, "invalid.message.command.parameter.message"),
    INVALID_LOGIN_COMMAND_PARAMETER(1005, "invalid.login.command.parameter.message"),
    INVALID_CHANNEL_ATTRIBUTE_NAME(1006, "invalid.channel.attribute.name.message"),
    INVALID_CHANNEL_NAME(1007, "invalid.channel.name.message"),
    USER_ALREADY_JOINED_CHANNEL(1008, "user.already.joined.channel.message"),
    USER_INCORRECT_PASSWORD(1009, "user.incorrect.password.message");

    ErrorType(int errorCode, String errorCodeProp) {
        this.errorCode = errorCode;
        this.errorCodeProp = errorCodeProp;
    }

    private final int errorCode;

    private final String errorCodeProp;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorCodeProp() {
        return errorCodeProp;
    }

}
