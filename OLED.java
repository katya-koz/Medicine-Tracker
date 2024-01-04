import org.firmata4j.I2CDevice;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;

/** OLED class handles all display events
 *
 */
public class OLED {
    public static final int  DRAWDELAYSHORT = 300; // ms
    public static final int DRAWDELAYLONG = 400; // ms
    public static SSD1306 display;
    public static final byte I2C0 = 0x3C; // oled Display

    public static Integer[][] medSelectLines = {{0,9, 30, 9}, {0,18, 30, 18}, {0,27, 30, 27},
            {0,36, 30, 36}, {0,45, 30, 45}}; // sorry for hardcoding i am tired
    public OLED()
    throws IOException {
        I2CDevice i2cObject = Main.groveBoard.getI2CDevice(OLED.I2C0); // Use 0x3C for the Grove oled
        OLED.display = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64); // 128x64 oled SSD1515

        // Initialize the OLED (SSD1306) object
        OLED.display.init();
    }

    public static void displayList(){ // display the list of medication
        try {
            Thread.sleep(DRAWDELAYLONG);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        OLED.display.clear();
        for(int i = 0; i < Main.reminder.dailyList.size(); i++)
        {
            OLED.display.getCanvas().drawString(0, i*9, Main.reminder.dailyList.get(i).getName());
            if(Main.reminder.dailyList.get(i).getIsDone()){
                OLED.display.getCanvas().fillRoundRect(110, i*9, 8, 8, 2, MonochromeCanvas.Color.BRIGHT);

            }else{
                OLED.display.getCanvas().drawRoundRect(110, i*9, 8, 8, 2, MonochromeCanvas.Color.BRIGHT);
            }


        }

        OLED.display.getCanvas().drawString(10, 50, "^");
        OLED.display.getCanvas().drawString(42, 50, "Select");
        OLED.display.getCanvas().drawString(118, 50, "v");

        OLED.display.display();
    }

    public static void select(){ // draw the underline underneath the three buttons
        try {
            Thread.sleep(DRAWDELAYSHORT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        switch(Control.selected){
            case "up":
                OLED.display.getCanvas().drawLine(0,60, 30,60, MonochromeCanvas.Color.BRIGHT);

                OLED.display.getCanvas().drawLine(40,60, 80,60, MonochromeCanvas.Color.DARK);
                OLED.display.getCanvas().drawLine(90,60, 120,60, MonochromeCanvas.Color.DARK);
                    break;
            case "select":
                OLED.display.getCanvas().drawLine(40,60, 80,60, MonochromeCanvas.Color.BRIGHT);

                OLED.display.getCanvas().drawLine(0,60, 30,60, MonochromeCanvas.Color.DARK);
                OLED.display.getCanvas().drawLine(90,60, 120,60, MonochromeCanvas.Color.DARK);
                    break;
            case "down":
                OLED.display.getCanvas().drawLine(90,60, 120,60, MonochromeCanvas.Color.BRIGHT);

                OLED.display.getCanvas().drawLine(0,60, 30,60, MonochromeCanvas.Color.DARK);
                OLED.display.getCanvas().drawLine(40,60, 80,60, MonochromeCanvas.Color.DARK);
                    break;
            default:
                break;

        }
        OLED.display.display();
    }
    public static void drawSelectorLines(){ // selector lines refer to the underline underneath the medication in the list
        try {
            Thread.sleep(DRAWDELAYSHORT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i < medSelectLines.length; i++){
            if(i == Control.index){
                OLED.display.getCanvas().drawLine(medSelectLines[i][0],medSelectLines[i][1], medSelectLines[i][2], medSelectLines[i][3], MonochromeCanvas.Color.BRIGHT);
            }else{
                OLED.display.getCanvas().drawLine(medSelectLines[i][0],medSelectLines[i][1], medSelectLines[i][2], medSelectLines[i][3], MonochromeCanvas.Color.DARK);
            }

        }
        OLED.display.display();

    }
    public static void displayReminder(String name){ // show the reminder
        try{
            OLED.display.clear();
            Thread.sleep(OLED.DRAWDELAYSHORT);

            OLED.display.getCanvas().drawString(0,0, "It's time to take:\n" +name);

            OLED.display.display();
            Thread.sleep(OLED.DRAWDELAYLONG);

        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }


    }



}
