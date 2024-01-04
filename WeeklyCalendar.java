import java.util.ArrayList;

/**
This class outlines a storage for all the medication a user would enter into the system
 **/
public class WeeklyCalendar {
    ArrayList<Medicine> sundayList;
    ArrayList<Medicine> mondayList;
    ArrayList<Medicine> tuesdayList;
    ArrayList<Medicine> wednesdayList;
    ArrayList<Medicine> thursdayList;
    ArrayList<Medicine> fridayList;
    ArrayList<Medicine> saturdayList;

    public WeeklyCalendar(){
        // initialise all the lists for days of the week
        this.sundayList = new ArrayList<>();
        this.mondayList = new ArrayList<>();
        this.tuesdayList = new ArrayList<>();
        this.wednesdayList = new ArrayList<>();
        this.thursdayList = new ArrayList<>();
        this.fridayList = new ArrayList<>();
        this.saturdayList = new ArrayList<>();
    }
    public void addMedicine(Medicine med){
        // add medicine into corresponding day of the week
        for(int i = 0; i < med.getDay().length; i++){
            switch(med.getDay()[i]) {
                case "Sunday":
                    this.sundayList.add(med);
                    break;
                case "Monday":
                    this.mondayList.add(med);
                    break;
                case "Tuesday":
                    this.tuesdayList.add(med);
                    break;
                case "Wednesday":
                    this.wednesdayList.add(med);
                    break;
                case "Thursday":
                    this.thursdayList.add(med);
                    break;
                case "Friday":
                    this.fridayList.add(med);
                    break;
                case "Saturday":
                    this.saturdayList.add(med);
                    break;

                default:
                    System.out.println("Error!");
            }
        }
    }
}
