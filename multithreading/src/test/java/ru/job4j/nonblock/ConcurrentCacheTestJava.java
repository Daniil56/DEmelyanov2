package ru.job4j.nonblock;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConcurrentCacheTestJava {
    private ConcurrentCache storage = new ConcurrentCache();

    class ThreadUpdate extends Thread {
        ConcurrentCache stor;
        int id;
        int value;
       private ThreadUpdate(ConcurrentCache stor, int id, int value) {
            this.stor = stor;
            this.id = id;
            this.value = value;
        }
        @Override
        public void run() {
            stor.upDateValue(id, value);
        }
    }
    @Test()
    public void whenUpdateValueThenNotInctrmentVersion() throws InterruptedException {
        storage.add(new Base(1, 10));
        Thread first = new ThreadUpdate(storage, 1, 15);
        Thread second = new ThreadUpdate(storage, 1, 20);
        first.start();
       // second.start();
        first.join();
        second.join();
        assertThat(storage.getBase(1).getVersion(), is(1));
        assertThat(storage.getBase(1).getValue(), is(15));

    }
}
