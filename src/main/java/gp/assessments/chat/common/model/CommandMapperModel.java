package gp.assessments.chat.common.model;

import gp.assessments.chat.command.*;
import gp.assessments.chat.handler.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandMapperModel {

    private final Command command;

    private final CommandHandler commandHandler;

}
