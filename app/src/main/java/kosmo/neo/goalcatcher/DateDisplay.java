package kosmo.neo.goalcatcher;

import java.util.Calendar;

public class DateDisplay {
    private int year;
    private int month;
    private int dayOfMonth;

    private String monthName [] = { "Jan","Feb","Mar",
                                    "Apr","May","Jun",
                                    "Jul","Aug","Sep",
                                    "Oct","Nov","Dec" };

    private String dayOfWeekName [] = { "Sun","Mon","Tue","Wed",
                                        "Thu","Fri","Sat" };

    DateDisplay(){
    }

    DateDisplay(int year, int month, int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
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

    @Override
    public String toString(){
        return getDayOfWeekName()+ ", "+ getMonthName() + " " +this.dayOfMonth + ", " + this.year;
    }
}
