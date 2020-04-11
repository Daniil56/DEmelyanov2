package ru.job4j.io.encod.output.log;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class LoggerChat implements Logger {
    private final String path;
    private final List<String> list = new ArrayList<>();

    public LoggerChat(String path) {
        this.path = path;
    }


    @Override
    public void write(String input) {
        try (RandomAccessFile writer = new RandomAccessFile((path), "rw");
             FileChannel channel = writer.getChannel()) {
            list.add(input);
            list.forEach((in) -> {
                var strBytes = in.getBytes();
                var buffer = ByteBuffer.allocate(strBytes.length);
                buffer.put(strBytes);
                buffer.flip();
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException writeExc) {
            writeExc.printStackTrace();
        }
    }
}
