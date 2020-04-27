package ru.job4j.io.encod;

import ru.job4j.io.encod.output.log.Logger;
import ru.job4j.io.encod.output.log.LoggerChat;

import java.io.IOException;
import java.util.Map;

public class Menu  {
    private final Bot output;
    private final Logger logger =  new LoggerChat(System.getProperty("java.io.tmpdir") + "log.txt");
    private boolean stop;
    private Map<String, ChatAction> actions;

    public Menu(Bot output) {
        this.output = output;
    }

    public final void fillActions() {
        this.actions = Map.of(
                "stop", (bot, key) -> {
                    stop = true;
                    logger.write(key);
                },
                "continue", (bot, key) -> {
                    logger.write(";" + key);
                    stop = false;
                    final String answer = Bot.getAny();
                    logger.write(answer);
                    System.out.println(answer);
                },
                "end", (bot, key) -> logger.write(";" + key),
                "any", (bot, key) -> {
                    if (stop) {
                        logger.write(";" + key);
                    } else {
                        logger.write(";" + key);
                        final String answer = Bot.getAny();
                        System.out.println(answer);
                        logger.write(";" + answer);
                    }
                });
    }
    public final void select(final String key) throws IOException {
        ChatAction action = this.actions.getOrDefault(key, this.actions.get("any"));
        action.execute(this.output, key);
    }

}
