package gp.assessments.chat.common.model;

import gp.assessments.chat.command.Command;
import gp.assessments.chat.handler.CommandHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandMapperModel {

    private final Command command;

    private final CommandHandler commandHandler;

}
