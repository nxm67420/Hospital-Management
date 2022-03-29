package patient;

import java.util.Date;

// Class 2
public class Patient {

    // Variables
    // Disease type;
    public String firstName, lastName, preffix;
    public int age, counter;
    public int idNum;
    public Date birthday, timeStamp = new Date();

    // Constructors
    public Patient() {
    }

    public Patient(String last, int id) {
        this.lastName = last;
        this.idNum = id;
    }

    public Patient(String name1, String name2, int id) {
        this.firstName = name1;
        this.lastName = name2;
        this.idNum = id;
        counter += 1;
        System.out.println("Patient Added: " + timeStamp);
    }

    // Get First Name
    public String getFirstName() {
        return firstName;
    }

    // Get Last Name
    public String getLastName() {
        return lastName;
    }

    // Get User ID
    public int getID() {
        return idNum;
    }

    // Count Amount of Patients
    public int getCount() {
        return counter;
    }

    // Displays User Information
    @Override
    public String toString() {
        return "\nFirst Name : " + firstName + "\nLast Name : " + lastName + "\nID : " + idNum;
    }
}
