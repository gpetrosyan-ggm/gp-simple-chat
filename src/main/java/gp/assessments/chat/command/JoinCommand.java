package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidJoinCommandParameterException;
import lombok.Getter;

@Getter
public class JoinCommand implements Command {
    private String channelName;

    @Override
    public void init(String[] params) {
        if (params.length != 1 || params[0].isBlank()) {
            throw new InvalidJoinCommandParameterException("Channel name cannot be blank.");
        }
        this.channelName = params[0];
    }

}
