package gp.assessments.chat.command;

import lombok.Getter;

@Getter
public class MessageCommand implements Command {
    private String message;

    @Override
    public void init(String[] params) {
        if (params.length != 1) {
            throw new RuntimeException("Invalid message");
        }
        this.message = params[0];
    }

}
