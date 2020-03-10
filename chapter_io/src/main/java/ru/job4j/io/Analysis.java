package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Analysis {
    private final String pathIn;
    private final String pathOut;
    private final List<String> unavailableList = new ArrayList<>();


    public Analysis(String in, String out) {
        this.pathIn = in;
        this.pathOut = out;
    }


    private static String[][] apply(String[] splitTarget) {
        String[] split = splitTarget[0].split(";");
        String[] split1 = splitTarget[splitTarget.length - 1].split(";");
        return new String[][]{split, split1};
    }


    public void init(Map<String, String> data, PrintWriter out) {
            data.forEach((key, value) ->
                    out.printf("%s %s%n", key, value));
    }

    public List<String> unavailable() {
        List<String> result = new ArrayList<>();
        try (BufferedReader readerIn = new BufferedReader(new FileReader(this.pathIn));
             PrintWriter out = new PrintWriter(new FileOutputStream(this.pathOut))) {
            addUnavailableFromList(readerIn, out);
            result = sourceWrite(unavailableList, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return result;
    }

    public void addUnavailableFromList(BufferedReader readerIn, PrintWriter out) {
        final String[] join = {""};
        try {
            var line = "";
            boolean server = true;
           while ((line = readerIn.readLine()) != null) {
               var log = line.split(" ");
               int status = Integer.parseInt(log[1]);
               var time = log[0];
               if (server && status == 500 || status == 400) {
                   out.printf("%s;", time);
                   join[0] = join[0].concat(time + ";");
                   server = false;
               }
               if (!server && status == 200 || status == 300) {
                   out.printf("%s;\n", time);
                   join[0] = join[0].concat(time + ";\n");
                   server = true;
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        unavailableList.add(join[0]);
    }

    public List<String> sourceWrite(List<String> unavailableList, PrintWriter inWrite) {
        List<String> result = new ArrayList<>();
        unavailableList.stream().map(str -> str.split(";\n")).map(Analysis::apply).forEach(period -> {
            result.add(period[0][0]);
            result.add(period[0][period[0].length - 1]);
            inWrite.printf("%s;%s;%n", period[0][0], period[0][period[0].length - 1]);
            result.add(period[period.length - 1][0]);
            result.add(period[period.length - 1][period[period.length - 1].length - 1]);
            inWrite.printf("%s;%s;%n", period[period.length - 1][0], period[1][period[period.length - 1].length - 1]);
        });
        unavailableList.clear();
        return result;
    }

    public List<String> getUnavailableList() {
        return unavailableList;
    }
}
