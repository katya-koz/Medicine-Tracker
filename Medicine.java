/**
 Outline for user to enter their medicine into the system
**/

import java.util.Date;
public class Medicine {
    private String name;
    private Date time;
    private String[] days;

    private boolean isDone;
    public Medicine(String name, Date time, String[] days){

        this.name = name;
        this.time = time;
        this.days = days;
        this.isDone = false; // this is the property which determines if there is a check beside the medication or not in the list

    }//getters
    public String[] getDay(){
        return this.days;
    }
    public Date getTime(){return this.time;}
    public String getName(){return this.name;}
    public Boolean getIsDone(){return this.isDone;}

    //setter

    public void setIsDone(Boolean val){this.isDone = val;}
}
