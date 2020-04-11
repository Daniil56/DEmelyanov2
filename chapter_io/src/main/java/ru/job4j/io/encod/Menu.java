package ru.job4j.io.encod;

import ru.job4j.io.encod.output.Output;
import ru.job4j.io.encod.output.log.Logger;
import ru.job4j.io.encod.output.log.LoggerChat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Menu implements Output {
    private final Output output;
    private final Logger logger =  new LoggerChat(System.getProperty("java.io.tmpdir") + "log.txt");
    private boolean stop;

    private Map<String, ChatAction> actions = new HashMap<>();

    public Menu(Output output) {
        this.output = output;
    }

    public final void fillActions() {
        this.actions = Map.of(
                "stop", new Stop(),
                "continue", new Continue(),
                "end", new End(),
                "any", new Any());
    }


    @Override
    public void open() throws IOException {
        this.output.open();
    }

    public final void select(final String key) throws IOException {
        ChatAction action = this.actions.getOrDefault(key, this.actions.get("any"));
        action.execute(this.output, key);
    }

    private class Continue implements ChatAction {
        @Override
        public void execute(Output out, String key) throws IOException {
            logger.write(";" + key);
            stop = false;
            final String answer = Bot.getAny();
            logger.write(answer);
            System.out.println(answer);
        }
    }

    private class Any implements ChatAction {

        @Override
        public void execute(Output out, String key) throws IOException {
            if (stop) {
                logger.write(";" + key);
            } else {
                logger.write(";" + key);
                final String answer = Bot.getAny();
                System.out.println(answer);
                logger.write(";" + answer);
            }
        }
    }

    private class Stop implements ChatAction {

        @Override
        public void execute(Output out, String key) throws IOException {
            stop = true;
            logger.write(key);
        }
    }

    private class End implements ChatAction {

        @Override
        public void execute(Output out, String key) throws IOException {
            logger.write(";" + key);

        }
    }



}
