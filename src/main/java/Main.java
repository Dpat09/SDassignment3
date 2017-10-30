import org.apache.commons.cli.*;
import static java.lang.System.exit;
import static java.lang.System.out;

public class Main {
    public static void main (String []args){

        //If no arguments are passed, the program prints '-1' to indicate error and exits
        if (args.length == 0){
            out.println(-1);
            exit(0);
        }

        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        Option type = Option.builder("t").longOpt("type").hasArg(true).desc("Integer or String (i / s)").build();
        Option key = Option.builder("k").longOpt("key").hasArg(true).desc("Key to search for").build();
        Option list = Option.builder("l").longOpt("list").hasArgs().desc("List to search in").build();
        options.addOption(type);
        options.addOption(key);
        options.addOption(list);

        String inputType = "";
        String[] aList = {};
        String sKey = "";
        try {
            CommandLine commandLine = parser.parse(options, args);
            aList = commandLine.getOptionValues("list");
            sKey = commandLine.getOptionValue("key");
            inputType = commandLine.getOptionValue("type");
            //inputType = commandLine.getOptionValue("type").compareTo("i") == 0? 0 : 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Built in redundancy for cases of erroneous input of type (fixes if list is strings and type = i)
        try{
            Integer check = Integer.parseInt(aList[0]);
        }catch(Exception e){
            inputType = "s";
        }

        int success = 0;

        // If dealing with integers
        if (inputType.contentEquals("i")){
            int intKey = Integer.parseInt(sKey);
            Integer[] intList = new Integer[aList.length];
            for(int i = 0; i < aList.length; i++)
                intList[i] = Integer.parseInt(aList[i]);

            //System.out.println(Arrays.toString(intList));
            //System.out.println("Key: " + intKey);

            success = binSearch(intList,intKey);
        }

        // If dealing with strings
        else if (inputType.contentEquals("s")){
            Comparable newKey = sKey;
            Comparable[] newList = new Comparable[aList.length];
            for (int i = 0; i < aList.length; i++)
                newList[i] = aList[i];

            //System.out.println(Arrays.toString(newList));
            //System.out.println("Key: " + newKey);

            success = binSearch(newList, newKey);
        }
        else if (!(inputType.contentEquals("i")) || !(inputType.contentEquals("s"))){
            out.println("You did not enter a valid type.\nPlease try again.");
            exit(0);
        }

        out.println(success>0?1:0);
    }

    private static int binSearch(Comparable[] aList, Comparable key){
        int left = 0, right = aList.length-1, mid;

        while(left <= right){

            mid = left + (right-left)/2;

            if (aList[mid].compareTo(key) == 0)
                return 1;

            else if (aList[mid].compareTo(key) > 0)
                right = mid - 1;

            else
                left = mid + 1;
        }
        return -1;
    }
}
