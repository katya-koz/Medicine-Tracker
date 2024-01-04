//import org.firmata4j.Pin;
//import org.firmata4j.firmata.FirmataDevice;
//import java.io.IOException; // Exceptions
//import java.util.Timer;
//
///** This class controls the hardware of the Arduino
// *
// */
//public class Board {
//
//    public static FirmataDevice groveBoard;
//    public static OLED myOLED;
//    public static Pin buzzerObject;
//    public static Pin LEDObject;
//    public static Pin potObject;
//
//    public Board()
//            throws IOException, InterruptedException {
//        String myUSBPort = "COM3";
//
//        final int BUZZER_PIN = 5;
//
//        final int BUTTON_PIN = 6;
//
//        final int POT_PIN = 14;
//
//        final int LED_PIN = 4;
//
//        // Create a FirmataDevice, start it and ensure Init is done.
//        Board.groveBoard = new FirmataDevice(myUSBPort);
//        try {
//            // start groveBoard
//            Board.groveBoard.start();
//            System.out.println("Board started.");
//            Board.groveBoard.ensureInitializationIsDone();
//
//        } catch (Exception ex) {
//            System.out.println("Couldn't connect to board");
//
//        } finally {
//            Board.myOLED = new OLED();
//
//            var buttonObject = Board.groveBoard.getPin(BUTTON_PIN);
//            buttonObject.setMode(Pin.Mode.INPUT);
//
//            LEDObject = Board.groveBoard.getPin(LED_PIN);
//            LEDObject.setMode(Pin.Mode.OUTPUT);
//
//            buzzerObject = Board.groveBoard.getPin(BUZZER_PIN);
//            buzzerObject.setMode(Pin.Mode.PWM);
//
//            potObject = Board.groveBoard.getPin(POT_PIN);
//            potObject.setMode(Pin.Mode.ANALOG);
//
//            var potTask = new PotTask();
//            new Timer().schedule(potTask, 0, 200);
//
//            Board.groveBoard.addEventListener(new ButtonListener(buttonObject));
//
////
//
//        }
//    }
//}