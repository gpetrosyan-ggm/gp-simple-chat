package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidLoginCommandParameterException;
import lombok.Getter;

@Getter
public class LoginCommand implements Command {
    private String userName;
    private String password;

    @Override
    public void init(String[] params) {
        if (params.length != 2 || params[0].isBlank() || params[1].isBlank()) {
            throw new InvalidLoginCommandParameterException("User name or password cannot be blank.");
        }
        this.userName = params[0];
        this.password = params[1];
    }

}
