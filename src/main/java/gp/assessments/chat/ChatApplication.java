package gp.assessments.chat;

import gp.assessments.chat.chat.ChatServer;

public class ChatApplication {

    public static void main(String[] args) throws Exception {
        new ChatServer(8888).start();
    }

}
