package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidJoinCommandParameterException;
import gp.assessments.chat.utils.StringUtils;
import lombok.Getter;

/**
 * Inbound join command model with possibility init 'channelName' parameter
 */
@Getter
public class JoinCommand implements Command {
    private String channelName;

    @Override
    public void init(String[] params) {
        if (params.length != 1 || StringUtils.isBlank(params[0])) {
            throw new InvalidJoinCommandParameterException("Channel name cannot be blank.");
        }
        this.channelName = params[0];
    }

}
