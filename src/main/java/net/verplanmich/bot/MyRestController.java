package net.verplanmich.bot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Autowired
    Bot bot;

    public void msgDiscord() {

        //bot.sendMessage(message.getGuild(), message.getChannel(), message.getMessage());
    }
}
