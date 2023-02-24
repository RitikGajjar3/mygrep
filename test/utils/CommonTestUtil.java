package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonTestUtil {

    protected static final String TEST_READ_FILE_PATH = "/Users/ritik/Desktop/mygrep/test/testfiles/mygreptestfile.txt";
    protected static final String TEST_EMPTY_FILE_PATH = "/Users/ritik/Desktop/mygrep/test/testfiles/mygreptestemptyfile.txt";
    // TEST_INVALID_FILE_PATH - File which does not exist in the system
    protected static final String TEST_INVALID_FILE_PATH = "\"/Users/ritik/Desktop/mygrep/test/testfiles/mygreptestinvalidfile.txt\"";
    protected static final String NEW_FILE_NAME = "writeDataIntoNewFileTestFile.txt";

    protected static final String INPUT_SEARCH_STRING_1 = "Welcome";
    protected static final String INPUT_SEARCH_STRING_2 = "Hello";
    protected static final String INPUT_SEARCH_STRING_3 = "file";
    protected static final String INPUT_SEARCH_STRING_4 = "This";
    protected static final String INPUT_SEARCH_STRING_5 = "hello";
    protected static final String INPUT_SEARCH_STRING_DOUBLE_SPACE = "  ";
    protected static final String INPUT_SEARCH_STRING_SINGLE_SPACE = " ";
    protected static final String INPUT_SEARCH_STRING_EMPTY_STRING = "";
    protected static final String CONSOLE_INPUT = "Hello\nJava\nWorld\n^D";

    protected static final String CASE_INSENSITIVE_FLAG = "-i";
    protected static final String WRITE_OUTPUT_TO_FILE_FLAG = "-o";

    protected static final String OUTPUT_LINE_1 = "This is Test file for mygrep functionality.";
    protected static final String OUTPUT_LINE_2 = "This test file contains test sentences.";
    protected static final String OUTPUT_LINE_3 = "This is Hello World !";
    protected static final List<String> OUTPUT_EMPTY_LIST = new ArrayList<>();
    protected static final List<String> OUTPUT_SINGLE_LINE_IN_LIST = Arrays.asList(OUTPUT_LINE_3);
    protected static final List<String> OUTPUT_TWO_LINES_IN_LIST = Arrays.asList(OUTPUT_LINE_1, OUTPUT_LINE_2);
    protected static final List<String> OUTPUT_ALL_LINES_IN_LIST = Arrays.asList(OUTPUT_LINE_1, OUTPUT_LINE_2, OUTPUT_LINE_3);

    protected static final String INVALID_ARGUMENT_ERROR_SEARCH_ARGUMENT_NOT_PROVIDED = "Search argument is not provided\n";
    protected static final String INVALID_ARGUMENT_ERROR_WRITE_TO_FILE_FLAG_NOT_PROVIDED = "Write to file flag not provided\n";
    protected static final String INVALID_ARGUMENT_ERROR_OUTPUT_FILE_NOT_PROVIDED = "Output filename is not provided\n";
    protected static final String FILE_ALREADY_EXIST_ERROR = ": File Already Exist\n";

    protected static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected static final PrintStream originalPrintSteamOut = System.out;
    protected static final PrintStream originalPrintSteamErr = System.err;
    protected static final InputStream originalInputSteam = System.in;


    protected List<String> readFileAndGetDataInList(String fileName) throws Exception{
        // Initialise the variables
        List<String> dataList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        // Iterate over the file, line by line and add each line into dataList
        java.lang.String line = br.readLine();
        while (line != null) {
            dataList.add(line);
            line = br.readLine();
        }
        return dataList;
    }
}
