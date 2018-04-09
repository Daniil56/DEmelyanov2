package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.pseudo.Paint;
import ru.job4j.pseudo.Square;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    @Test
    public void whenDrowShape() {
        // получаем ссылку на стандартный вывод в консоль
        PrintStream stdout = System.out;
        // Создаем буфер для хранения ввода
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // Заменяем стандартный вывод на вывод в память для тестировани
        System.setOut(new PrintStream(out));
        // Выполняем действия пишущиее в консоль.
        new Paint().draw(new Square());
        // проверяем вычисления
        assertThat(new String(out.toByteArray()),
                is( new StringBuilder()
                        .append("++++")
                        .append("+  +")
                        .append("+  +")
                        .append("++++")
                        .append(System.lineSeparator())
                        .toString()));
        // Возврощаем обратно стандартный вывод в консоль.
        System.setOut(stdout);
    }
}
