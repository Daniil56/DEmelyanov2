package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Analizy {
   private final String pathIn;
   private final String pathOut;
   private final String temp;
   private final List<String> unavailableList;
   final AtomicBoolean server = new AtomicBoolean(true);


    public Analizy(String in, String out) {
        this.temp = "src/main/resources/temp.csv";
        this.pathIn = in;
        this.pathOut = out;
        unavailableList = new ArrayList<>();
    }

    public List<String> getUnavailableList() {
        return unavailableList;
    }

    public void init(Map<String, String> data) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(pathIn))) {
            data.forEach((key, value) ->
                    out.printf("%s %s%n", key, value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void unavailable() {
        try (BufferedReader readerIn = new BufferedReader(new FileReader(this.pathIn));
        PrintWriter out = new PrintWriter(new FileOutputStream(this.temp))) {
            readerIn.lines()
                    .filter(Objects::nonNull)
                    .filter(val -> !val.isEmpty())
                    .map(str -> str.split(" "))
                    .forEach(logg ->  {
                int status = Integer.parseInt(logg[1]);
                String time = logg[0];
                if (server.get() && status == 500 || status == 400) {
                    out.printf("%s;", time);
                    server.set(false);
                }
                if (!server.get() && status == 200 || status == 300) {
                    out.printf("%s;\n", time);
                    server.set(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader readerOut = new BufferedReader(new FileReader(this.temp));
        PrintWriter out = new PrintWriter(new FileOutputStream(this.pathOut))) {
            readerOut.lines()
                    .map(str -> str.split(";"))
                    .forEach(array -> {
                        this.unavailableList.add(array[0]);
                        this.unavailableList.add(array[array.length - 1]);
                        out.printf("%s;%s;%n", array[0], array[array.length - 1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
