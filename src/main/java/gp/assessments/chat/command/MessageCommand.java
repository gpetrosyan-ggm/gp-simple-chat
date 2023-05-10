package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidMessageCommandParameterException;
import gp.assessments.chat.utils.StringUtils;
import lombok.Getter;

@Getter
public class MessageCommand implements Command {
    private String message;

    @Override
    public void init(String[] params) {
        if (params.length != 1 || StringUtils.isBlank(params[0])) {
            throw new InvalidMessageCommandParameterException("Message cannot be blank");
        }
        this.message = params[0];
    }

}
