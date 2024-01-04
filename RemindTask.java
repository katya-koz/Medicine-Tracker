
import java.io.IOException;
import java.util.TimerTask; // Timer tasks.

/** This is the reminder task, which is activated once a user needs to be reminded to take a medication
 *
 */
public class RemindTask extends TimerTask {
    private String name;
    RemindTask(Medicine med) {
       this.name = med.getName();

    }
    @Override
    public void run() {
        OLED.displayReminder(this.name);
        try {
            Main.reminder.isRunning = true;
            Main.buzzerObject.setValue(1);
            Main.LEDObject.setValue(1);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    };

}