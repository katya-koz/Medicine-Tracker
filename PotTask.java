import java.util.TimerTask;

/** Class which handles converting the potentiometer value into a 'button' - either up, down, or select
 *
 */
public class PotTask extends TimerTask {

    public PotTask() {

    }
    @Override
    public void run() {
        if(Main.potObject.getValue() <= 341){
            Control.selected = "down";

        }else if(Main.potObject.getValue() > 341 && Main.potObject.getValue() <  682){
            Control.selected = "select";

        } else if(Main.potObject.getValue() >= 682){
            Control.selected = "up";
        }
        OLED.select();

    }
}
