package Exam.SecondMidtermExam.Task44;

import java.time.LocalDateTime;

public class DateHelpers {
    public static String parse(LocalDateTime dateTime) {
        String str = String.format("%d-%d", dateTime.getDayOfMonth(), dateTime.getMonthValue());
        return str;
    }

    public static String toExceptionDate(LocalDateTime dateTime) {
        String month = getMonthOrDay(dateTime.getMonth().toString());
        String day = getMonthOrDay(dateTime.getDayOfWeek().toString());

        return String.format("%s %s %d %02d:%02d:%02d UTC %d",
                day, month, dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond(),
                dateTime.getYear());
    }

    public static String toNormalDate(LocalDateTime dateTime) {
        String month = getMonthOrDay(dateTime.getMonth().toString());
        return String.format("%d %s, %d %02d:%02d",
                dateTime.getDayOfMonth(), month, dateTime.getYear(), dateTime.getHour(), dateTime.getMinute());
    }

    private static String getMonthOrDay(String dateTime) {
        String monthStr = dateTime.toLowerCase();
        return Character.toUpperCase(monthStr.charAt(0)) + monthStr.substring(1, 3);
    }
}