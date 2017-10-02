package sg.edu.rp.webservices.firebasetodoapp2;

/**
 * Created by 15039840 on 25/7/2017.
 */
import java.io.Serializable;

/**
 * Created by 15039840 on 25/7/2017.
 */
public class toDoItem implements Serializable {
    private String title;
    private String date;
    private int days;
    private boolean completed;



    public toDoItem() {

    }

    public toDoItem(String title, String date, int days, boolean completed) {
        this.title = title;
        this.date = date;
        this.days = days;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "toDoItem{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", days=" + days +
                ", completed=" + completed +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}