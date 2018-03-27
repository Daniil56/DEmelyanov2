package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1","testDescription",123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1","testDescription",123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2","testDescription2",1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
    @Test
    public  void whenFindidThenReturnId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testdesc", 123L);
        tracker.add(item);
        assertThat((tracker.findById(item.getId()).getName()), is("test1"));
    }
    @Test
    public void whenDeleteItemThenReturnNewArrayWithoutItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "desc1", 123L);
        tracker.add(item);
        Item next = new Item("test2","testDescription2",1234L);
        tracker.add(next);
        Item next2 = new Item("test22","testDescription22",12342L);
        tracker.add(next);
        tracker.delete(item.getId());
        Item[] expectArray = {next,  next2};
        assertThat(tracker.getAll(), is(expectArray));

    }
}
