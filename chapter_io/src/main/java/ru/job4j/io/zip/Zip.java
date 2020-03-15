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

    public Zip(Args args) {
        this.excludeFiles = new ArrayList<>();
        this.args = args;
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

        private List<File> directoryList() {
        List<File>  result = new ArrayList<>();
        Queue<File> queue = new ArrayDeque<>();
        List<File>  checked = new LinkedList<>();
        if (args.directory().isDirectory()) {
            File[] files = args.directory().listFiles();
            queue.addAll(Arrays.asList(Objects.requireNonNull(files)));
            while (!queue.isEmpty()) {
                File current = queue.remove();
                if (current.isDirectory()) {
                    File[] childList = current.listFiles();
                    queue.addAll(Arrays.asList(Objects.requireNonNull(childList)));
                } else if (!checked.contains(current)) {
                    result.add(current);
                    checked.add(current);
                }
            }
        }
        return result;
    }


    public void seekBy(String root, String ext) {
        StringBuilder builder = new StringBuilder(ext);
        builder.deleteCharAt(0);
        Predicate<String> conditional = s -> s.endsWith(builder.toString());
         excludeFiles.addAll(new Search().files(root, conditional));
    }

    public static void main(String[] args) {
        Zip zip = new Zip(new Args(args));
        zip.seekBy(zip.args.directory().getPath(), zip.args.exclude());
        zip.pack(zip.directoryList(), zip.args.output());
    }
}
