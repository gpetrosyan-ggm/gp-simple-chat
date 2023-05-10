package gp.assessments.chat.command;

import lombok.Getter;

@Getter
public class LoginCommand implements Command {
    private String userName;
    private String password;

    @Override
    public void init(String[] params) {
        if (params.length != 2) {
            throw new RuntimeException("Invalid login params");
        }
        this.userName = params[0];
        this.password = params[1];
    }

}
