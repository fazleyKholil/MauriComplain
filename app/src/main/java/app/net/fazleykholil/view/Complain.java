package app.net.fazleykholil.view;

/**
 * Created by Fazley on 08/06/2015.
 */
public class Complain {
    private int id;
    private String dateStamp;
    private String date;
    private String details;
    private String otherDetails;
    private String place;
    private String additionalPlace;
    private String department;
    private String author; //phone No
    private String status;

    public int getId() {
        return id;
    }

    public Complain setId(int id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Complain setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Complain setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public Complain setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Complain setDate(String date) {
        this.date = date;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Complain setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public Complain setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public Complain setPlace(String place) {
        this.place = place;
        return this;
    }

    public String getAdditionalPlace() {
        return additionalPlace;
    }

    public Complain setAdditionalPlace(String additionalPlace) {
        this.additionalPlace = additionalPlace;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Complain setStatus(String status) {
        this.status = status;
        return this;
    }

}
