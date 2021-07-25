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

        WeekFields weekFields = WeekFields.of(Locale.KOREA);
        int weekNumber = now.get(weekFields.weekOfWeekBasedYear());

        System.out.println("오늘 몇 주차? : " + weekNumber);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println("오늘 무슨 요일? : " + dayOfWeek);

        int value = now.getDayOfWeek().getValue();
        System.out.println("오늘 요일 숫자로 : " + value);

//        LocalDateTime localDateTime = now.minusDays(5);
//        System.out.println(localDateTime.getDayOfWeek());

        LocalDateTime tomorrow = now.plusDays(1);
        int tomorrowNumber = tomorrow.get(weekFields.weekOfWeekBasedYear());
        System.out.println("내일 몇 주차? : " + tomorrowNumber);

        DayOfWeek tomorrowDayOfWeek = tomorrow.getDayOfWeek();
        System.out.println("내일 무슨 요일? = " + tomorrowDayOfWeek);

        int tomorrowValue = tomorrow.getDayOfWeek().getValue();
        System.out.println("내일 요일 숫자로 : " + tomorrowValue);


    }

}
