//Packages
package healthcare;
import database_package.connection;
import patient.Patient;

//Imports
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
public class MedicalApp {
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

        //Thread
        /* Navigate t1 = new Navigate();
        Thread thread = new Thread(t1);
        thread.start(); */
        
        //Testing Purposes
        //System.out.println(thread.isAlive());

        // Variables
        Random randomItem = new Random();
        Scanner scan = new Scanner(System.in);
        int wait = 0;
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
            System.out.print("Wait Room Current Occupation (");
            for (Patient p : waitRoom) {
                p.getID();
            }
            //Displays Current Wait Room Occupation +AmountOfPatients
            System.out.println(wait + ") / (20)"); // Wait Room MAX Occupation = 20
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
                String firstName = scan.next();

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
                String lastName = scan.next();

                //Give Patient ID
                int id = randomItem.nextInt(1001);

                //Iterate Through List, If 'id' == 'id', Randomize New 'id'
                /*
                

        
                */
                
                Patient p1 = new Patient(firstName, lastName, id, new Date());



                /*Add User To Database

                
                // Statements
                String sql = "INSERT INTO waitroom(firstName, lastName, id, session_date, time)" + "VALUES(?,?,?,?,?)";

                // Create a Statement
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                preparedStatement.setString(1, topic);
                preparedStatement.setString(2, building);
                preparedStatement.setString(3, room);
                preparedStatement.setString(4, date);
                preparedStatement.setString(5, timeOf);

                //Execution Returns Amount of Results
                int resultSet = preparedStatement.executeUpdate();

                //Server Connection
                //connectToClient(topic, building, room, date, timeOf);
                //connectToServer();


                    if(resultSet > 0) {
                        System.out.println("Session Topic: " + topic);
                        System.out.println("Building Name: " + building);
                        System.out.println("Room Number: " + room);
                        System.out.println("Date: " + date);
                        System.out.println("Time: " + timeOf);
                    }
                    else{
                        System.out.println("No Results Found");
                        return;
                    }
            }
            catch (SQLException e) {
                System.out.println("SQL Error Found : " + e);
            }
            catch (Exception e) {
                System.out.println("OTHER ERRORS : " + e);
            }
            */
                
                



                //Added To WaitList
                waitRoom.add(p1);
                wait += 1;

                //Loop Variable
                boolean incorrect = false;
                //Loop Until "incorrect" boolean == true
                while(!incorrect){
                System.out.print("\nWhat brings you in today " + "'" + p1.getFirstName().toUpperCase() + " " + p1.getLastName().toUpperCase() + "'" + "?");
                System.out.print("\n1.Coronavirus Testing\n2.Medical Emergency\n3.Health Checkup\n4.Cancel Sign Up\n");
                //Patient Response to Above Question
                int answer = scan.nextInt();
                // Adds Patient To Que Based Off of Visit Type
                switch (answer) {
                    case 1:
                        System.out.println("Added To Virus Testing Que");
                        testing.add(p1);
                        incorrect = true;
                        break;
                    case 2:
                        System.out.println("Added To Medical Emergency Que");
                        emergency.add(p1);
                        incorrect = true;
                        break;
                    case 3:
                        System.out.println("Added To Health Checkup Que");
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
                
                //Iterate Through List To Find Patient
                Iterator<Patient> scroll = waitRoom.listIterator();
                while (scroll.hasNext()) {
                    Patient patient2 = scroll.next();
                    if (last.equals(patient2.getLastName()) && myID == patient2.getID()) {
                        System.out.println("\n-UPDATE-\n" + patient2.getFirstName().toUpperCase() + " "
                                + patient2.getLastName().toUpperCase() + " \"FOUND\" ");
                        System.out.println("You are already in the System");
                        break;
                    }
                    else {
                        System.out.println("\n-UPDATE-\nUser Not Found. Try Again!!");
                    }
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
                System.out.println("Would you like a copy of todays log?");
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
                                                patient.firstName.substring(0, 5) + "\t\t" + 
                                                    patient.lastName.substring(0, 5) + "\t\t" +   
                                                        patient.idNum + "\t\t\t" + 
                                                            patient.timeStamp);
                                patientCounter++;
                        }
                        writer.close();
                        System.out.println("Completed");
                    } catch (Exception e) {
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
        scan.close();//Close Scanner 'Memory Leak'
    } //End of Main()
    
    /* Methods */
    //Checks If User Is In "WAIT" System
    public static void inSystem(Patient patient, ArrayList<Patient> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.size() <= 0) {
                return;
            }
            else if(patient.getLastName().equals(array.get(i).getLastName())
                    && patient.getID() == array.get(i).getID()) {
                System.out.println("You Are In The System");
                return;
            }
            else {
                continue;
            }
        }
    }
    /* // Remove Duplicates
    private static int[] removeDouble(int[] array) {
        int detective;
        int[] fakeArray = array;

        for (int a = 0; a < array.length - 1; a++) {
            for (int b = 0; b < array.length - 1; b++) {
                if (fakeArray[a] == array[b]) {
                    fakeArray[a] = 0;
                }
            }
        }
        return fakeArray;
    }

    // Prints All Values Inside Array
    public static void printArray(int[] array) {
        for (Integer integer : array) {
            System.out.print(integer + " ");
        }
    }

    // Checks If Array is Empty
    public static boolean isEmpty(int[] array) {
        if (array.length <= 0) {
            return true;
        } else {
            return false;
        }
    }

    // Checks Array Size
    public static int arraySize(int[] array) {
        int count = 0;
        for (Integer number : array) {
            count++;
        }
        return count;
    }

    // Removes Last inserted Item in Array
    public void StackRemove(int[] array) {
        if (isEmpty(array) == true) {
            System.out.println("Nothing To Remove");
            return;
        } else {
            int number = arraySize(array);
        }
    }

    // Sort Array
    public static void sortArray(int[] array) {
        if (isEmpty(array) == true) {
            return;
        } else {
            int max, min, temp;
            min = array[0];
            for (int i = 0; i < array.length - 1; i++) {
                if (min > array[i + 1]) {
                    temp = min;
                    array[i] = min;
                    max = array[i + 1];
                }
            }
        }
    } */
}
