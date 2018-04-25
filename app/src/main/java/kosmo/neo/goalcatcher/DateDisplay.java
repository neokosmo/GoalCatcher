package kosmo.neo.goalcatcher;

import java.util.Calendar;

public class DateDisplay {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;

    private String monthName [] = { "Jan","Feb","Mar",
                                    "Apr","May","Jun",
                                    "Jul","Aug","Sep",
                                    "Oct","Nov","Dec" };

    private String dayOfWeekName [] = { "Sun","Mon","Tue","Wed",
                                        "Thu","Fri","Sat" };

    DateDisplay(int year, int month, int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = 0;
        this.minute = 0;
    }
    DateDisplay(int year, int month, int dayOfMonth, int hour, int minute){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
    }

    private String getMonthName(){
        return monthName[this.month];
    }

    private String getDayOfWeekName(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month, this.dayOfMonth);
        return dayOfWeekName[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public String dateToday(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.dayOfMonth = calendar.get(Calendar.DATE);
        return toString();
    }

    public boolean compareBefore(DateDisplay dateToday){
        return  this.year < dateToday.year || this.year <= dateToday.year &&
                (this.month < dateToday.month || this.month <= dateToday.month &&
                        (this.dayOfMonth < dateToday.dayOfMonth || this.dayOfMonth <= dateToday.dayOfMonth &&
                                (this.hour < dateToday.hour || this.hour <= dateToday.hour&&this.minute <= dateToday.minute)));
    }

    @Override
    public String toString(){
        return getDayOfWeekName()+ ", "+ getMonthName() + " " +this.dayOfMonth + ", " + this.year;
    }
}
