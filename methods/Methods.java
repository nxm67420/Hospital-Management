//Packages
package methods;
import patient.Patient;

//util.Imports
import java.util.ArrayList;

//Contains a Body
public interface Methods {

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
    
    // Remove Duplicates
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
    public static void StackRemove(int[] array) {
        if (isEmpty(array) == true) {
            System.out.println("Nothing To Remove");
            return;
        } 
        else {
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
    }
    
    
    // Iterate Through List, If 'id' == 'id', Randomize New 'id' for Patient
    /*
    
    
    
    */
}
