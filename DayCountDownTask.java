import java.util.TimerTask; // Timer tasks.

// This is the timer task. every time it's called, it measures the soil moisture and calls the soilMoistureSensor to see if there was a state change
public class DayCountDownTask extends TimerTask {
    DayCountDownTask() {

    }
    @Override
    public void run() {
        Main.reminder.beginNewDay(); // start a new day
        if(!Main.reminder.isRunning){
            OLED.displayList();
            OLED.drawSelectorLines();
        }
    };

}