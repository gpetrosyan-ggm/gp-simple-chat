package gp.assessments.chat.command;

import gp.assessments.chat.common.error.InvalidLoginCommandParameterException;
import gp.assessments.chat.utils.StringUtils;
import lombok.Getter;

@Getter
public class LoginCommand implements Command {
    private String userName;
    private String password;

    @Override
    public void init(String[] params) {
        if (params.length != 2 || StringUtils.isBlank(params[0]) || StringUtils.isBlank(params[1])) {
            throw new InvalidLoginCommandParameterException("User name or password cannot be blank.");
        }
        this.userName = params[0];
        this.password = params[1];
    }

}
