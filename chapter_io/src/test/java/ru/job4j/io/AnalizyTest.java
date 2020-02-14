package ru.job4j.io;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenUnavailableRun()  {
        String in = "src/main/resources/source.csv";
        String out = "src/main/resources/target.csv";
        Analizy analizy = new Analizy(in, out);
        Map<String, String> data = new LinkedHashMap<>();
        data.put("10:56:01", "200");
        data.put("10:57:01", "500");
        data.put("10:58:01", "400");
        data.put("10:59:01", "200");
        data.put("11:01:02", "500");
        data.put("11:02:02", "200");
        analizy.init(data);

        List<String> expect = new ArrayList<>();
        expect.add("10:57:01");
        expect.add("10:59:01");
        expect.add("11:01:02");
        expect.add("11:02:02");
        assertThat(analizy.unavailable(), is(expect));
    }
}