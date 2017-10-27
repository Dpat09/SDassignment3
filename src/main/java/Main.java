import java.util.Scanner;
import org.apache.commons.*;
import static java.lang.System.exit;
import static java.lang.System.out;

public class Main {
    public static void main (String []args){

        //If no arguments are passed, the program prints '-1' to indicate error and exits
        if (args.length == 0){
            out.println(-1);
            exit(0);
        }

        //key is temporarily initialized to 0
        double key = 0;

        //Try-catch block in case of erroneous input such as an alphabetical character
        try {
            // Create scanner for key input
            Scanner keyboard = new Scanner(System.in);
            key = keyboard.nextDouble();
        }catch(Exception e){
            //Error printout
            out.println(-1);
            exit(0);
        }

        // Create int array to store inputted arguments, and another that will run parallel to keep track of unsorted locations
        double[] aList = new double [args.length];
        int[] location = new int[args.length];

        try { //Loop to parse string type list 'args' to int type and store in 'aList
            for (int i = 0; i < args.length; i++) {
                location[i] = i;
                aList[i] = Double.parseDouble(args[i]);
            }
            //System.out.println(Arrays.toString(location));
        }catch(Exception e){
            //Error printout
            out.println(e);
            exit(0);
        }

        //run quicksort to sort inputted array
        nlognSort(aList,location,0,aList.length-1);

        //store index of key given it was found
        int index = binSearch(aList,location,key);

        // Prints index if number exists in aList, else prints 0
        out.println(index>(-1)?index:"The key was not found!");

    }

    private static int binSearch(double[] aList, int[] location,double key){

        int left = 0, right = aList.length-1;

        while  (left <= right){

            int mid = left + (right-left)/2;

            if (Double.compare(aList[mid],key)==0) {//aList[mid]==key)
                return checkFirst(aList,location,mid);
            }

            if (Double.compare(aList[mid],key)>0)//aList[mid]>key)
                right = mid - 1;

            else
                left = mid + 1;
        }
        return -1;
    }
    private static int checkFirst(double[] aList, int[] location, int mid){
        boolean found = false;
        boolean first = true;
        int i = mid, least = location[mid];

        for (i = mid; i < aList.length - 1 && i + 1 <= aList.length - 1; i++){
            if (Double.compare(aList[i], aList[i + 1]) == 0) {
                least = least > location[i] ? location [i] : least > location[i+1] ? location[i+1] : least;
                found = true;
            }
            else
                break;

        }
        for (i = mid; i > 0 && i - 1 >= 0; i--){
            if (Double.compare(aList[i], aList[i - 1]) == 0) {
                least = least > location[i] ? location[i] : least > location[i - 1] ? location[i - 1] : least;
                found = true;
            }
            else
                break;
        }

        return found ? least : location[mid];
    }

    private static void nlognSort(double[] aList, int[] location,int left, int right){

        int i = left, j = right;
        double pivot = aList[left+(right-left)/2];

        while (i <= j){
            while(Double.compare(aList[i],pivot)<0)
                i++;
            while(Double.compare(aList[j],pivot)>0)
                j--;
            if (i <= j){
                double temp = aList[i];
                aList[i] = aList[j];
                aList[j] = temp;
                int locTemp = location[i];
                location[i] = location[j];
                location[j] = locTemp;
                i++;
                j--;
            }
        }
        if (left < j)
            nlognSort(aList, location, left, j);
        if (i < right)
            nlognSort(aList, location, i, right);
    }
}
