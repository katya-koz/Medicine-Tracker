/**
 * Class which handles actions associated with the three buttons at the bottom of the list screen
 */
public class Control {
    public static String selected = "up"; // init

    public static int index = 0; // index of med. that is selected

    public static void up(){ // user presses up
        if(Control.index > 0){
            Control.index --;
        }
    }
    public static void down(){ // down
        if(Control.index < Main.reminder.dailyList.size() - 1){
            Control.index ++;
        }
    }
    public static void tick(){ // user ticks a box
        if(Main.reminder.dailyList.get(Control.index).getIsDone()){
            Main.reminder.dailyList.get(Control.index).setIsDone(Boolean.FALSE);
        }else{
            Main.reminder.dailyList.get(Control.index).setIsDone(Boolean.TRUE);
        }
        OLED.displayList(); // refresh list with new tick
    }
}
