import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;


/** This class handles the button press tasks, and is able to differentiate between whether the button is stopping a reminder,
 * or if a button is trying to control the medication list
 */
public class ButtonListener implements
        IODeviceEventListener {
    private final Pin buttonPin;

    ButtonListener(Pin buttonPin) {
        this.buttonPin = buttonPin;

    }
    @Override
    public void onPinChange(IOEvent event) {
        // Return right away if the event isn't from the Button.
        if (event.getPin().getIndex() !=
                buttonPin.getIndex()) {
            return;
        }else{
            try {
                if(this.buttonPin.getValue() == 1 && Main.reminder.isRunning){ //cancel reminder

                    Main.buzzerObject.setValue(0);
                    Main.LEDObject.setValue(0);
                    Main.reminder.isRunning = false;

                    OLED.displayList();


                }else if(this.buttonPin.getValue() == 1 && !Main.reminder.isRunning){ // control the buttons at the bottom of the list
                    switch(Control.selected){
                        case "up":
                            Control.up();
                            break;

                        case "select":
                            Control.tick();

                            break;

                        case "down":
                            Control.down();
                            break;

                    }
                    OLED.drawSelectorLines();
//
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onStart(IOEvent event) {}
    @Override
    public void onStop(IOEvent event) {}
    @Override
    public void onMessageReceive(IOEvent event, String
            message) {}
}