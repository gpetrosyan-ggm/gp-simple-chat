package gp.assessments.chat.command;

import lombok.Getter;

@Getter
public class JoinCommand implements Command {
    private String channelName;

    @Override
    public void init(String[] params) {
        if (params.length != 1) {
            throw new RuntimeException("Invalid channel name");
        }
        this.channelName = params[0];
    }

}
