package edu.xihua.project.pms;

import edu.xihua.project.pms.chat.ChatServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PropertyManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyManageSystemApplication.class, args);
        ChatServer.run();
    }

}
