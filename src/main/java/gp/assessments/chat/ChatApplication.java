package gp.assessments.chat;

import gp.assessments.chat.chat.ChatServer;
import gp.assessments.chat.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatApplication {
    private static final Logger logger = LoggerFactory.getLogger(ChatApplication.class);

    public static void main(String[] args) throws Exception {
        int port = PropertiesUtils.getAsInt("server.port");
        logger.info("Chat server starting on the '{}' port...", port);
        new ChatServer(port).start();
    }

}
