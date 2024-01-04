import java.sql.Time;

import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import java.io.IOException; // Exceptions
import java.util.Timer;

/** Medication Reminder - Develop an Arduino-powered medication reminder that helps patients to manage their medication
schedules. Use Java and the OLED to create a user interface that displays medication schedules and alerts patients when it's time to
take their medications. This project relates to the challenge of engineering better medicines by improving medication
adherence and patient outcomes.
 **/

/** I had to combine my main class with the board class since there is a 10 file limit on eclass
 * 
 */
public class Main {

    public static FirmataDevice groveBoard;
    public static OLED myOLED;
    public static Pin buzzerObject;
    public static Pin LEDObject;
    public static Pin potObject;
    static Reminders reminder;
    public static void main(String[] args) throws IOException, InterruptedException {
        String myUSBPort = "COM3";

        final int BUZZER_PIN = 5;

        final int BUTTON_PIN = 6;

        final int POT_PIN = 14;

        final int LED_PIN = 4;

        // Create a FirmataDevice, start it and ensure Init is done.
        Main.groveBoard = new FirmataDevice(myUSBPort);
        try {
            // start groveBoard
            Main.groveBoard.start();
            System.out.println("Board started.");
            Main.groveBoard.ensureInitializationIsDone();

        } catch (Exception ex) {
            System.out.println("Couldn't connect to board");

        } finally {
            Main.myOLED = new OLED();

            var buttonObject = Main.groveBoard.getPin(BUTTON_PIN);
            buttonObject.setMode(Pin.Mode.INPUT);

            LEDObject = Main.groveBoard.getPin(LED_PIN);
            LEDObject.setMode(Pin.Mode.OUTPUT);

            buzzerObject = Main.groveBoard.getPin(BUZZER_PIN);
            buzzerObject.setMode(Pin.Mode.PWM);

            potObject = Main.groveBoard.getPin(POT_PIN);
            potObject.setMode(Pin.Mode.ANALOG);

            var potTask = new PotTask();
            new Timer().schedule(potTask, 0, 200);

            Main.groveBoard.addEventListener(new ButtonListener(buttonObject));

            WeeklyCalendar myWeeklyCalendar = new WeeklyCalendar();

            //add medication here
            Medicine medTest1 = new Medicine("Lamictal", new Time(23, 59, 0), new String[]{"Monday"});
            Medicine medTest2 = new Medicine("Seroquel", new Time(00, 01, 0), new String[]{"Tuesday"});
            Medicine medTest3 = new Medicine("Zyprexa", new Time(20, 0, 0), new String[]{"Monday"});
            Medicine medTest4 = new Medicine("Symbyax", new Time(11, 45, 0), new String[]{"Monday"});

            myWeeklyCalendar.addMedicine(medTest1);
            myWeeklyCalendar.addMedicine(medTest2);
            myWeeklyCalendar.addMedicine(medTest3);
            myWeeklyCalendar.addMedicine(medTest4);

            reminder = new Reminders(myWeeklyCalendar);

            if(!Main.reminder.isRunning){
                OLED.drawSelectorLines();
                OLED.displayList();
            }
        }




    }
}