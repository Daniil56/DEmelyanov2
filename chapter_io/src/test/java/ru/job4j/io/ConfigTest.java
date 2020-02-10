package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/resources/pair_without_comments.properties";
        Config config = new Config(path);
        System.out.println(config);
        System.out.println(config.value("server.port="));
//        assertThat(config);


    }

}