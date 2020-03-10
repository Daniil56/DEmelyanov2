package ru.job4j.io;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void whenUnavailableIntegrateRun() throws FileNotFoundException {
        String in = "src/main/resources/source.csv";
        String out = "src/main/resources/target.csv";
        Analysis analysis = new Analysis(in, out);
        Map<String, String> data = getDataMap();
        analysis.init(data, new PrintWriter(out));
        List<String> expect = getExpectedList();
        assertThat(analysis.unavailable(), is(expect));
    }

    @NotNull
    public List<String> getExpectedList() {
        return List.of(
                "10:57:01",
                "10:59:01",
                "11:01:02",
                "11:02:02");
    }

    @NotNull
    public Map<String, String> getDataMap() {
        Map<String, String>  data =  new LinkedHashMap<>();
        data.put("10:56:01", "200");
        data.put("10:57:01", "500");
        data.put("10:58:01", "400");
        data.put("10:59:01", "200");
        data.put("11:01:02", "500");
        data.put("11:02:02", "200");
        return data;
    }

    @Test
    public void whenAddEdDataListToAnalizyInitThenFileSourceWellBeFill() throws IOException {
        Analysis analysis = new Analysis(null, null);
        File source = folder.newFile("source.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            analysis.init(getDataMap(), out);
        }
        List<String> initList = new ArrayList<>();
        List<String> acList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(initList::add);
        }
        getDataMap().forEach((k, v) -> acList.add(String.format("%s %s", k, v)));
        assertThat(initList, is(acList));
    }

    @Test
    public void whenUnavaibleMockRunThenCreateInTmp() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analysis analysis = new Analysis(null, null);
        try (PrintWriter out = new PrintWriter(source)) {
            analysis.init(getDataMap(), out);
        }
        try (PrintWriter in = new PrintWriter(target);
             BufferedReader reader = new BufferedReader(new FileReader(source))
             ) {
            analysis.addUnavailableFromList(reader, in);
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            assertThat(analysis.sourceWrite(analysis.getUnavailableList(), out), is(getExpectedList()));
        }
    }
}