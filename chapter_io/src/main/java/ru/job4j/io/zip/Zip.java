package ru.job4j.io.zip;

import ru.job4j.io.Search;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final Args args;
    private final List<File> excludeFiles;
    private final Search search;

    public Zip(Args args) {
        this.excludeFiles = new ArrayList<>();
        this.args = args;
        search = new Search();

    }

    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                if (!this.excludeFiles.contains(file)) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void seekBy(String root, String ext) {
        StringBuilder builder = new StringBuilder(ext);
        builder.deleteCharAt(0);
        Predicate<String> conditional = s -> s.endsWith(builder.toString());
         excludeFiles.addAll(this.search.files(root, conditional));
    }

    public static void main(String[] args) {
        Args arg = new Args(args);
        Zip zip = new Zip(arg);
        zip.seekBy(zip.args.directory().getPath(), zip.args.exclude());
        zip.pack(zip.search.files(arg.directory().getPath(), s -> true), zip.args.output());
    }
}
