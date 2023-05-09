package gp.assessments.chat.service.impl;

import gp.assessments.chat.service.AuthService;
import gp.assessments.chat.service.ChatService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final AuthService authService;

}
