package gp.assessments.chat.command;

import lombok.Getter;

@Getter
public class LoginCommand implements Command {
    private String name;
    private String password;

    @Override
    public void init(String[] params) {
        if (params.length != 2) {
            throw new RuntimeException("Invalid login params");
        }
        this.name = params[0];
        this.password = params[1];
    }

}
