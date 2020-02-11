package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/resources/pair_without_comments.properties";
        Config config = new Config(path);
        config.load();
        String actual = config.value("hibernate.connection.driver_class");
        String expect = "org.postgresql.Driver";
        assertThat(actual, is(expect));
    }
}