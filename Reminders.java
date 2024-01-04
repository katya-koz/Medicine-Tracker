import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 This class controls the timer tasks and reminders for the medication in calendar object.

 The timer tasks start new from each new day, where there is a countdown to a reminder for each medication the user must take.
 **/
public class Reminders {
    WeeklyCalendar weeklyCalendar;
    DayOfWeek dateNow;
    ArrayList<Medicine> dailyList;
    TimerTask dayTimerTask;
    Boolean isRunning = false; // is there currently a reminder active?
    private Timer reminderTimer;
    public Reminders(WeeklyCalendar weeklyCalendar){

        this.weeklyCalendar = weeklyCalendar;
        Timer dayTimer = new Timer();

        this.reminderTimer = new Timer();

        dayTimerTask = new DayCountDownTask();

        dayTimer.schedule(dayTimerTask, getTimeOfNextDay(), (24*60*60*1000)); // period is ms in a day, delay is from now until next day (00:00)

        beginNewDay();

    }
    public Date getTimeOfNextDay(){ // use in the initialisation step, to find out the initial delay of the dayTimer
        //https://beginnersbook.com/2017/10/java-convert-localdate-to-date/
        Date date;
        LocalDate nextDay = LocalDate.from(LocalDate.now().plusDays(1));
        date = Date.from(nextDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
    public Date getTimeOfReminder(Date time){
        // the user only enters the time, this method figures out the ms until that time
        Date today = Calendar.getInstance().getTime();

        Date date = new Date(today.getYear(), today.getMonth(), today.getDate(), time.getHours(), time.getMinutes());
        System.out.println(date);

        return date;
    }
    public void beginNewDay(){
        // call this when there is a new day to switch daily medicine list
        this.dateNow = LocalDate.now().getDayOfWeek();

        // switch case to identify current daily list
        System.out.println(this.dateNow.toString());
        switch(this.dateNow.toString()) {
            case "SUNDAY":
                this.dailyList = this.weeklyCalendar.sundayList;
                break;

            case "MONDAY":
                this.dailyList = this.weeklyCalendar.mondayList;
                break;

            case "TUESDAY":
                this.dailyList = this.weeklyCalendar.tuesdayList;
                break;

            case "WEDNESDAY":
                this.dailyList = this.weeklyCalendar.wednesdayList;
                break;

            case "THURSDAY":
                this.dailyList = this.weeklyCalendar.thursdayList;
                break;

            case "FRIDAY":
                this.dailyList = this.weeklyCalendar.fridayList;
                break;

            case "SATURDAY":
                this.dailyList = this.weeklyCalendar.saturdayList;
                break;

            default:
                System.out.println("Error!");
                break;
        }
        setReminders();
    }
    public void setReminders(){ // iterate throught the list of meds for the day, and create reminders for each of them

        for(int i = 0; i < this.dailyList.size(); i++){
            this.reminderTimer.schedule(new RemindTask(this.dailyList.get(i)), getTimeOfReminder(this.dailyList.get(i).getTime()));
        }



    };

}
