package hcy.gym;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class WeekTest {

    @Test
    void weekOfWeekBasedYearTest() {
        LocalDateTime now = LocalDateTime.now();

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = now.get(weekFields.weekOfWeekBasedYear());

        System.out.println(weekNumber);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek);

        int value = now.getDayOfWeek().getValue();
        System.out.println(value);

        LocalDateTime localDateTime = now.minusDays(5);
        System.out.println(localDateTime.getDayOfWeek());


    }

}
