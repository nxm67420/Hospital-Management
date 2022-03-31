//Packages
package patient;

//Imports
import java.util.Date;
import java.util.ArrayList;

public class Patient {

    private String firstName, lastName;
    private int counter;
    private int idNum;
    private Date timeStamp = new Date();

    // Constructors
    public Patient() {
    }

    public Patient(String name1, String name2, int id, Date timeStamp) {
        this.firstName = name1;
        this.lastName = name2;
        this.idNum = id;
        this.timeStamp = new Date();
        counter += 1;
        System.out.println("Patient Added: " + timeStamp);
    }

    public Patient(String last, int id) {
        this.lastName = last;
        this.idNum = id;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return idNum;
    }

    public Date getTimeCreated() {
        return timeStamp;
    }

    // Count Amount of Patients
    public int getCount() {
        return counter;
    }

    public void removePatient(Patient patient, ArrayList<Patient> array){
        array.remove(patient);
        counter--;
    }

    // Displays User Information
    @Override
    public String toString() {
        return "\nFirst Name : " + firstName +
                "\nLast Name : " + lastName +
                "\nID : " + idNum;
    }
}
