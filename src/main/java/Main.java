import java.util.Arrays;
import java.util.Scanner;
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

        String[] aList = {};
        String sKey = "";
        try {
            CommandLine commandLine = parser.parse(options, args);
            aList = commandLine.getOptionValues("list");
            sKey = commandLine.getOptionValue("key");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Comparable[] newList = new Comparable[aList.length];
        for (int i = 0; i < aList.length; i++)
            newList[i] = aList[i];
        Comparable newKey = sKey;

        System.out.println(Arrays.toString(newList));
        System.out.println("Key: "+ newKey);

        int success = compBinSearch(newList, newKey);
        //out.println(key.compareTo(aList[5]));

        //out.println(success>0?1:0);
        out.println(success);
    }

    private static int compBinSearch(Comparable[] aList, Comparable key){
        int left = 0, right = aList.length-1, mid;

        while(left <= right){

             //mid = (left+right)/2;
            mid = left + (right-left)/2;
            //int place = key.compareTo(aList[mid]);

            if (aList[mid].compareTo(key) == 0)
                return 1;

            else if (aList[mid].compareTo(key) > 0)
                right = mid - 1;

            else if (aList[mid].compareTo(key) < 0)
                left = mid + 1;
        }
        return -1;
    }
}
