package ru.job4j.tracker;


import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenCreatetingAnItemAnItemIsCreated() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "des1");
        Input input = new ConsoleInput();
        StartUI startUI = new StartUI(input, tracker);
    }

}
