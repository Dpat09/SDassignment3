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
        options.addOption("t","type",true,"Integer or String (i / s)");
        options.addOption("k","key",true,"Key to search for");
        //options.addOption("l","list",true,"List to search for Key");
        Option list = Option.builder("list")
                .longOpt("list")
                .hasArgs()
                .desc("List to search in")
                //.valueSeparator(' ')
                .build();
        options.addOption(list);

        // Created all necessary var fo parsing and storing
        CommandLine commandLine;
        Comparable[] aList = {0};
        Comparable key = 0;
        String[] aList1 = {};

        try {
            commandLine = parser.parse(options,args);
            aList = commandLine.getOptionValues("list");
            key = commandLine.getOptionValue("key");
            aList1 = commandLine.getOptionValues("list");
        } catch (ParseException e) {
            //e.printStackTrace();
            System.out.println("Problem parsing inputs");
            exit(0);
        }
        //int success = compBinSearch(aList,key);
        out.println(binSearch(aList,key) > 0 ? 1 : 0);
    }

    private static int binSearch(Comparable[] aList, Comparable key){
        int left = 0, right = aList.length-1;

        while(left<=right){

            int mid = left + (right-left)/2;
            int compareResult = key.compareTo(aList[mid]);

            if (compareResult==0)
                return 1;

            if (compareResult>0)
                right = mid - 1;

            else
                left = mid + 1;
        }
        return -1;
    }
}
