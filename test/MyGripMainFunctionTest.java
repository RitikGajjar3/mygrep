import org.junit.jupiter.api.*;
import utils.CommonTestUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

public class MyGripMainFunctionTest extends CommonTestUtil {

    @BeforeAll
    public static void setUpStreams() {
        // Overriding the setOut printStream with outContent and setErr printStream with errContent
        // By doing this, we can get the output strings which will be printed on the console.
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        // Reverting the PrintStream of System out, System Err and InputStream of System in to default one.
        System.setOut(originalPrintSteamOut);
        System.setIn(originalInputSteam);
        System.setErr(originalPrintSteamErr);
    }

    @BeforeEach
    public void initialise(){
        outContent.reset();
        errContent.reset();;
    }


    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 1 Testcase - Read File and Search Word
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void mainTest_Story1_ReadFileAndSearchWord() throws Exception{
        String[] arguments = {INPUT_SEARCH_STRING_2, TEST_READ_FILE_PATH};
        MyGrip.main(arguments);
        Assertions.assertEquals(OUTPUT_LINE_3 + "\n",outContent.toString());
    }

    @Test
    void mainTest_Story1_ReadFileAndSearchWord_InvalidArgument(){
        String[] arguments = {};
        MyGrip.main(arguments);
        Assertions.assertEquals(INVALID_ARGUMENT_ERROR_SEARCH_ARGUMENT_NOT_PROVIDED,errContent.toString());
    }


    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 2 Testcase - Taking Input From Console
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void mainTest_Story2_TakeInputLinesFromConsole() throws Exception{
        String[] arguments = {"Java"};
        ByteArrayInputStream testIn = new ByteArrayInputStream(CONSOLE_INPUT.getBytes());
        System.setIn(testIn);

        MyGrip.main(arguments);

        Assertions.assertEquals("Java\n",outContent.toString());
    }



    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 3 Testcase - Case InSensitive Search
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void mainTest_Story3_CaseSensitiveSearch() throws Exception{
        String[] arguments = {CASE_INSENSITIVE_FLAG, INPUT_SEARCH_STRING_5, TEST_READ_FILE_PATH};
        MyGrip.main(arguments);

        Assertions.assertEquals(OUTPUT_LINE_3 + "\n",outContent.toString());
    }

    @Test
    void mainTest_Story3_CaseSensitiveSearch_ConsoleInput() throws Exception{
        String[] arguments = {CASE_INSENSITIVE_FLAG, INPUT_SEARCH_STRING_5};
        ByteArrayInputStream testIn = new ByteArrayInputStream(CONSOLE_INPUT.getBytes());
        System.setIn(testIn);

        MyGrip.main(arguments);

        Assertions.assertEquals("Hello\n",outContent.toString());
    }


    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 4 Testcase - Write Data Into New File
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void mainTest_Story4_writeDataIntoNewFile() throws Exception {
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_2, TEST_READ_FILE_PATH, WRITE_OUTPUT_TO_FILE_FLAG, NEW_FILE_NAME};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. Now get the lines which are stored into the new files
        List<String> actualLines = readFileAndGetDataInList(NEW_FILE_NAME);

        // 4. Delete the newly created file
        File file = new File(NEW_FILE_NAME);
        file.delete();

        // 5. compare the actualLines with the inputLines
        System.out.println(actualLines);
        Assertions.assertNotNull(actualLines);
        Assertions.assertEquals(1, actualLines.size());
        Assertions.assertEquals(OUTPUT_LINE_3, actualLines.get(0));
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_ArgumentOrderChanged() throws Exception {
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_2, WRITE_OUTPUT_TO_FILE_FLAG, TEST_READ_FILE_PATH, NEW_FILE_NAME};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. Now get the lines which are stored into the new files
        List<String> actualLines = readFileAndGetDataInList(NEW_FILE_NAME);

        // 4. Delete the newly created file
        File file = new File(NEW_FILE_NAME);
        file.delete();

        // 5. compare the actualLines with the inputLines
        Assertions.assertNotNull(actualLines);
        Assertions.assertEquals(1, actualLines.size());
        Assertions.assertEquals(OUTPUT_LINE_3, actualLines.get(0));
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_CaseInsensitive() throws Exception {
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_5, CASE_INSENSITIVE_FLAG, TEST_READ_FILE_PATH, WRITE_OUTPUT_TO_FILE_FLAG, NEW_FILE_NAME};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. Now get the lines which are stored into the new files
        List<String> actualLines = readFileAndGetDataInList(NEW_FILE_NAME);

        // 4. Delete the newly created file
        File file = new File(NEW_FILE_NAME);
        file.delete();

        // 5. compare the actualLines with the inputLines
        System.out.println(actualLines);
        Assertions.assertNotNull(actualLines);
        Assertions.assertEquals(1, actualLines.size());
        Assertions.assertEquals(OUTPUT_LINE_3, actualLines.get(0));
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_CaseInsensitive_ArgumentOrderChanged() throws Exception {
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_5, TEST_READ_FILE_PATH, CASE_INSENSITIVE_FLAG, WRITE_OUTPUT_TO_FILE_FLAG, NEW_FILE_NAME};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. Now get the lines which are stored into the new files
        List<String> actualLines = readFileAndGetDataInList(NEW_FILE_NAME);

        // 4. Delete the newly created file
        File file = new File(NEW_FILE_NAME);
        file.delete();

        // 5. compare the actualLines with the inputLines
        System.out.println(actualLines);
        Assertions.assertNotNull(actualLines);
        Assertions.assertEquals(1, actualLines.size());
        Assertions.assertEquals(OUTPUT_LINE_3, actualLines.get(0));
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_FileAlreadyExist(){
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_5, TEST_READ_FILE_PATH, CASE_INSENSITIVE_FLAG, WRITE_OUTPUT_TO_FILE_FLAG, TEST_EMPTY_FILE_PATH};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. compare the actual and expected error
        Assertions.assertEquals(TEST_EMPTY_FILE_PATH + FILE_ALREADY_EXIST_ERROR,errContent.toString());
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_InvalidArgument(){
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_5, TEST_READ_FILE_PATH, CASE_INSENSITIVE_FLAG, WRITE_OUTPUT_TO_FILE_FLAG};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. compare the actual and expected error
        Assertions.assertEquals(INVALID_ARGUMENT_ERROR_OUTPUT_FILE_NOT_PROVIDED, errContent.toString());
    }

    @Test
    void mainTest_Story4_writeDataIntoNewFile_InvalidArgument2(){
        // 1. Initialise the variables
        String[] arguments = {INPUT_SEARCH_STRING_5, TEST_READ_FILE_PATH, CASE_INSENSITIVE_FLAG, TEST_EMPTY_FILE_PATH};

        // 2. execute the main method
        MyGrip.main(arguments);

        // 3. compare the actual and expected error
        Assertions.assertEquals(INVALID_ARGUMENT_ERROR_WRITE_TO_FILE_FLAG_NOT_PROVIDED, errContent.toString());
    }
}
