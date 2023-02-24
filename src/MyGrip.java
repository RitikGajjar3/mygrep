import exception.InvalidArgumentException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyGrip {

    // Flags
    private static final Character HYPHEN_CHARACTER = '-';
    private static final String CASE_INSENSITIVE_FLAG = "-i";
    private static final String WRITE_OUTPUT_TO_FILE_FLAG = "-o";

    private static final String END_OF_LINE = "^D";

    // Main Method of the Project
    public static void main(String[] args) {
        try {
            myGrepCommand(args);
        }catch (FileNotFoundException fileNotFoundException){
            System.err.println(fileNotFoundException.getMessage());
        }catch (FileAlreadyExistsException fileAlreadyExistsException){
            System.err.println(fileAlreadyExistsException.getMessage() + ": File Already Exist");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    /*
        myGrepCommand
            - Method which executes grep command

        Parameters:
            args - command line arguments which are provided with mygrep command
    */
    private static void myGrepCommand(String[] args) throws Exception {

        // 1. Initialise the variables
        String searchString = null;
        String inputFileName = null;
        String outputFileName = null;
        Boolean isCaseInsensitiveSearchEnabled = false;
        Boolean isWriteOutputToFileEnabled = false;
        List<String> outputLines = new ArrayList<>();
        List<String> inputLines = new ArrayList<>();

        // 2. Iterate over the arguments and populate the variables
        for(int i=0 ; i<args.length ; i++){
            if(isFlag(args[i])) {
                switch (args[i]){
                    case CASE_INSENSITIVE_FLAG:
                        isCaseInsensitiveSearchEnabled = true;
                        break;
                    case WRITE_OUTPUT_TO_FILE_FLAG:
                        isWriteOutputToFileEnabled = true;
                        break;
                    default:
                        throw new InvalidArgumentException("Invalid Flag provided");
                }
            } else{
                if(Objects.isNull(searchString))
                    searchString = args[i];
                else if(Objects.isNull(inputFileName))
                    inputFileName = args[i];
                else if(Objects.isNull(outputFileName))
                    outputFileName = args[i];
                else
                    throw new InvalidArgumentException("Invalid Argument");
            }
        }

        // 3. validate the arguments
        if(Objects.isNull(searchString))
            throw new InvalidArgumentException("Search argument is not provided");
        else if(Objects.isNull(inputFileName))
            inputLines = takeInputLinesFromConsole();
        else if(isWriteOutputToFileEnabled && Objects.isNull(outputFileName))
            throw new InvalidArgumentException("Output filename is not provided");
        else if(!isWriteOutputToFileEnabled && Objects.nonNull(outputFileName))
            throw new InvalidArgumentException("Write to file flag not provided");


        // 4. Get the output lines which contains searchString
        outputLines  = inputLines.isEmpty() ? readFileAndSearchWord(searchString, inputFileName, isCaseInsensitiveSearchEnabled) :
                searchWordInInputLines(inputLines, searchString, isCaseInsensitiveSearchEnabled);

        // 5. Write the output data into new file or print the data on the console according to flag
        if(isWriteOutputToFileEnabled)
            writeDataIntoNewFile(outputLines, outputFileName);
        else
            printList(outputLines);
    }

    private static boolean isFlag(String arg) {
        // Hyphen symbol means character should be treated as flag.
        return arg.length() == 2 && Objects.equals(arg.charAt(0), HYPHEN_CHARACTER);
    }

    public static List<String> searchWordInInputLines(List<String> inputLines, String searchString, Boolean isCaseInsensitiveSearchEnabled){
        List<String> matchedLineList = new ArrayList<>();
        for(String input : inputLines){
            if(isSearchStringPresentInInput(input, searchString, isCaseInsensitiveSearchEnabled))
                matchedLineList.add(input);
        }
        return matchedLineList;
    }

    public static List<String> readFileAndSearchWord(String searchString, String fileName, Boolean isCaseInsensitiveSearchEnabled) throws Exception{
        // Initialise the variables
        List<String> matchedLineList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Iterate over the file line by line and search given searchString into every line
        String line = br.readLine();
        while (line != null) {
            if(isSearchStringPresentInInput(line, searchString, isCaseInsensitiveSearchEnabled))
                matchedLineList.add(line);
            line = br.readLine();
        }
        br.close();

        return matchedLineList;
    }

    public static boolean isSearchStringPresentInInput(String input, String searchString, Boolean isCaseInsensitiveSearchEnabled){
        if(isCaseInsensitiveSearchEnabled){
            Pattern pattern = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        }else
            return input.contains(searchString);
    }

    public static void writeDataIntoNewFile(List<String> lines, String newFileName) throws Exception {

        // Check whether file is already exist, If exist then print error message on console.
        File outputFile = new File(newFileName);
        if(outputFile.exists())
            throw new FileAlreadyExistsException(newFileName);

        // If file not exist then create a new file with value that is passed in parameter "newFileName" and
        // write the lines into it.
        BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    public static List<String> takeInputLinesFromConsole(){
        // Initialise the variables
        List<String> inputLines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Taking input lines from the console iteratively until we encounter the END_OF_LINE string
        String line = scanner.nextLine();
        while(!Objects.equals(line, END_OF_LINE)){
            inputLines.add(line);
            line = scanner.nextLine();
        }
        return inputLines;
    }

    private static void printList(List<String> matchedLineList) {
        for(String line : matchedLineList)
            System.out.println(line);
    }
}
