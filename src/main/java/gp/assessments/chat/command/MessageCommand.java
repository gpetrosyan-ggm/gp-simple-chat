package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidMessageCommandParameterException;
import lombok.Getter;

@Getter
public class MessageCommand implements Command {
    private String message;

    @Override
    public void init(String[] params) {
        if (params.length != 1 || params[0].isBlank()) {
            throw new InvalidMessageCommandParameterException("Message cannot be blank");
        }
        this.message = params[0];
    }

}
