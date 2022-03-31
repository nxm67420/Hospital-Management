//Packages
package healthcare;
import database_package.connection;
import methods.Methods;
import patient.Patient;

//Imports
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;
import java.util.Scanner;
import com.mysql.jdbc.Connection;
import java.util.ArrayList;

//Thread Class
class Navigate implements Runnable {
    Thread t1;

    public synchronized void run() {
        System.out.println("Thread running");
    }
}

// Application Main
public class MedicalApp implements Methods{
    public static void main(String[] args) throws IOException {

        //Start Database
        Thread thread = new Thread(new Runnable(){
            public void run(){
                connection.Connect();
                System.out.println("Database Connected From Main");
            }
        });
        //start the thread
        thread.start(); 

        // Variables
        Random randomItem = new Random();
        Scanner scan = new Scanner(System.in);
        int wait = 0;
        int maxCapacity = 20;
        boolean loop = false;

        // Arrays To Hold Patients
        ArrayList<Patient> waitRoom = new ArrayList<>();
        ArrayList<Patient> testing = new ArrayList<>();
        ArrayList<Patient> emergency = new ArrayList<>();
        ArrayList<Patient> checkUp = new ArrayList<>();
        
        //Main Menu
        do{
            System.out.println("\n-- Main  Menu --" +
                                "\n1.(Add Patient)" +
                                "\n2.(Remove Patient)" +
                                "\n3.(Check Patient Status)" +
                                "\n4.(Check Wait List)" +
                                "\n5.(Write to File)" + 
                                "\n6.(Exit)");

            // Count Amount of Patients in Waitroom
            System.out.print("Wait Room Current Occupation: ");
            for (Patient p : waitRoom) {
                p.getID();
            }
            
            //Displays Current Wait Room Occupation +AmountOfPatients
            System.out.println("(" + wait + ") / (" + maxCapacity + ")"); // Wait Room MAX Occupation = 20
            if (wait == 20) {
                System.out.println("WAITING ROOM IS AT MAX CAPACITY ");
            }
            //Create Patient
            int choice = scan.nextInt();
            switch(choice){
                case 1:

                // Insert User First Name
                System.out.print("\nEnter First Name (Enter \"done\" to quit): ");
                while (!scan.hasNext("[A-Za-z]+")) {
                    System.out.println("Sorry, character values only [A-Z]!\n");
                    System.out.print("\nEnter First Name (Enter \"done\" to quit): ");
                    scan.next();
                }
                String firstName = scan.next().toUpperCase();

                if (firstName.toLowerCase().equals("done")) {
                    break;
                }
                
                //Insert User Last Name
                System.out.print("Enter Last Name : ");
                while (!scan.hasNext("[A-Za-z]+")) {
                    System.out.println("Sorry, character values only [A-Z]!\n");
                    System.out.print("\nEnter Last Name : ");
                    scan.next();
                }
                //Last Name Confirmed
                String lastName = scan.next().toUpperCase();

                //Give Patient ID
                int id = randomItem.nextInt(1001);

                //Iterate Through List, If 'id' == 'id', Randomize New 'id' for Patient 
                /*
                

        
                */
                
                Patient p1 = new Patient(firstName, lastName, id, new Date());


                //Use Hibernate Framework
                /*Add User To Database

                */
                
                //Added To WaitList
                waitRoom.add(p1);
                wait += 1;

                //Loop Variable
                boolean incorrect = false;

                //Loop Until "incorrect" boolean == true
                while(!incorrect){
                System.out.print("\nWhat brings you in today " + "'" + p1.getFirstName().toUpperCase() + ", " + p1.getLastName().toUpperCase() + "'" + "?");
                System.out.print("\n1.Coronavirus Testing" + 
                                 "\n2.Medical Emergency" +
                                 "\n3.Health Checkup" +
                                 "\n4.Cancel Sign Up\n");
                                 
                //Patient Response to Above Question
                int answer = scan.nextInt();
                // Adds Patient To Que Based Off of Visit Type
                switch (answer) {
                    case 1:
                        System.out.println("Added To Virus-Testing Que");
                        testing.add(p1);
                        incorrect = true;
                        break;
                    case 2:
                        System.out.println("Added To Medical-Emergency Que");
                        emergency.add(p1);
                        incorrect = true;
                        break;
                    case 3:
                        System.out.println("Added To Health-Checkup Que");
                        checkUp.add(p1);
                        incorrect = true;
                        break;
                    case 4:
                        System.out.println("Sign Up Canceled");
                        waitRoom.remove(p1);
                        wait = wait - 1;
                        incorrect = true;
                        break;
                    default:
                        System.out.print("\nInvalid Option. Try Again!!\n");
                        break;
                }//End of Inner Switch Case
            }//End of Switch Case 1
            break;
        
            /*Remove Patient */
            case 2: 
                // Last Name of Patient
                System.out.print("Enter Last Name of Patient: ");
                String secondName = scan.next();
                
                // ID of Patient
                System.out.print("Enter ID of Patient: ");
                int checkID = scan.nextInt();

                //Iterate thrugh List and Remove Object
                Iterator<Patient> it = waitRoom.listIterator();
                while(it.hasNext()){
                    //for (Iterator<Patient> it2 = checkUp.iterator(); it2.hasNext();){
                    Patient patient = it.next();
                    if (secondName.equals(patient.getLastName()) && checkID == patient.getID()) {
                        System.out.println("\n-UPDATE-\n" + patient.getFirstName().toUpperCase() + " "
                                + patient.getLastName().toUpperCase() + " \"Removed\" ");
                        System.out.println(new Date());
                        waitRoom.remove(patient);
                        testing.remove(patient);
                        emergency.remove(patient);
                        checkUp.remove(patient);
                        wait--;
                        break;
                    }
                }
                //}
                break;

            //Check Patient Status
            case 3:
                
                //Retrieve Last Name
                System.out.print("\nCheck Patient Status\n1.Enter Last Name : ");
                String last = scan.next();
                
                //Retrieve User ID
                System.out.print("2.Enter User ID : ");
                int myID = scan.nextInt();
                
                //boolean Result
                boolean userFound = false;

                //Iterate Through List To Find Patient
                Iterator<Patient> scroll = waitRoom.listIterator();
                while (scroll.hasNext()) {

                    Patient patient2 = scroll.next();
                    if (last.equals(patient2.getLastName()) && myID == patient2.getID()) {
                        System.out.println("\n-UPDATE-\n" +
                                            patient2.getFirstName().toUpperCase() + " " +
                                            patient2.getLastName().toUpperCase() + 
                                            " \"FOUND\" ");
                        System.out.println("You are already in the System");
                        userFound = true;
                        break;
                    }
                    else {
                        userFound = false;
                    }
                }
                if(userFound == false){
                    System.out.println("\n-UPDATE-\nUser Not Found. Try Again!!");
                }

                break;
            case 4:/*Check Wait List For Each List*/

                // Displays ALL Patients for Coronavirus Testing
                System.out.println("\n---------------");
                System.out.println("|Virus Testing|");
                System.out.print("---------------");
                for (Patient p : testing) {
                    System.out.println(p.toString());
                }

                // Displays ALL Patients for Medical Emergency
                System.out.println("\n-------------------");
                System.out.println("|Medical Emergency|");
                System.out.print("-------------------");
                for (Patient p : emergency) {
                    System.out.println(p.toString());
                }

                // Displays ALL Patients for Helath Checking
                System.out.println("\n-----------------");
                System.out.println("|Health Screening|");
                System.out.print("-----------------");
                for (Patient p : checkUp) {
                    System.out.println(p.toString());
                }

                break;

            //Track User Sign in, & Date & Time
            case 5:
                int patientCounter = 1;
                System.out.print("Would you like a copy of today's log? : ");
                String response = scan.next();

                if(response.equals("yes") || response.equals("y") || response.equals("Yes")){
                    try {
                        File file = new File("DailyLog.txt");

                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        PrintWriter writer = new PrintWriter(file);
                        writer.println("\t" + "First_Name\t" + "Last_Name\t" + "ID\t\t\t" + "Date\'Time");
                        for(Patient patient : waitRoom){
                                writer.println(patientCounter  + ".\t" + 
                                                patient.getFirstName().substring(0, 5) + "\t\t" + 
                                                    patient.getLastName().substring(0, 5) + "\t\t" +   
                                                        patient.getID() + "\t\t\t" + 
                                                            patient.getTimeCreated());
                        patientCounter++;
                        }
                        writer.close();
                        System.out.println("Daily Log Completed");
                    } 
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                break;
            case 6: /*Quit System*/
                loop = true;
                break;
            default:
                System.out.println("Invalid Option");
                break;
           }
        } while(!loop);
        scan.close();//Close Scanner to prevent 'Memory Leak'
    } //End of Main()
}
