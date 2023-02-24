import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.CommonTestUtil;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MyGrepSubFunctionsTest extends CommonTestUtil {

    @AfterAll
    public static void restoreStreams() {
        // Reverting the InputStream to default one.
        System.setIn(originalInputSteam);
    }


    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 1 Testcase - Read File and Search Word
    // --------------------------------------------------------------------------------------------------------------------

    @ParameterizedTest
    @MethodSource("MyGrepSubFunctionsTest#getReadFileAndSearchWordInputAndOutputData")
    void readFileAndSearchWordTest_HappyFlow(List<String> expectedMatchedLineList, String searchString, String fileName) throws Exception {
        Assertions.assertEquals(expectedMatchedLineList, MyGrep.readFileAndSearchWord(searchString, fileName, false));
    }


    private static Stream<Arguments> getReadFileAndSearchWordInputAndOutputData() {
        return Stream.of(
                // Zero Line Match
                // case 1 - Zero Line Match
                arguments(OUTPUT_EMPTY_LIST, INPUT_SEARCH_STRING_1, TEST_READ_FILE_PATH),
                // case 2 - Zero Line Match with double space search string as input
                arguments(OUTPUT_EMPTY_LIST, INPUT_SEARCH_STRING_DOUBLE_SPACE, TEST_READ_FILE_PATH),

                // One Line Match
                // case 3 - One Line Match
                arguments(OUTPUT_SINGLE_LINE_IN_LIST, INPUT_SEARCH_STRING_2, TEST_READ_FILE_PATH),

                // Two Line Match
                // case 4 - Two Lines Match
                arguments(OUTPUT_TWO_LINES_IN_LIST, INPUT_SEARCH_STRING_3, TEST_READ_FILE_PATH),

                // All Lines Match
                // case 5 - All Lines Match
                arguments(OUTPUT_ALL_LINES_IN_LIST, INPUT_SEARCH_STRING_4, TEST_READ_FILE_PATH),
                // case 6 - All Lines Match with empty search string as input
                arguments(OUTPUT_ALL_LINES_IN_LIST, INPUT_SEARCH_STRING_EMPTY_STRING, TEST_READ_FILE_PATH),
                // case 7 - All Lines Match with single space search string as input
                arguments(OUTPUT_ALL_LINES_IN_LIST, INPUT_SEARCH_STRING_SINGLE_SPACE, TEST_READ_FILE_PATH),


                // case 8 - Zero Line match in Empty file with empty search string as input
                arguments(OUTPUT_EMPTY_LIST, INPUT_SEARCH_STRING_EMPTY_STRING, TEST_EMPTY_FILE_PATH),
                // case 9 - Zero Line match in Empty file with single search string as input
                arguments(OUTPUT_EMPTY_LIST, INPUT_SEARCH_STRING_SINGLE_SPACE, TEST_EMPTY_FILE_PATH)
        );
    }

    @Test
    void readFileAndSearchWordTest_FileNotFound(){
        Assertions.assertThrows(FileNotFoundException.class, () -> MyGrep.readFileAndSearchWord(INPUT_SEARCH_STRING_1, TEST_INVALID_FILE_PATH, false));
    }



    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 2 Testcase - Taking Input From Console
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void takeInputLinesFromConsoleTest_HappyFlow(){
        String consoleInput = "Hello\nJava\nWorld\n^D";
        ByteArrayInputStream testIn = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(testIn);

        List<String> actualInputList = MyGrep.takeInputLinesFromConsole();
        List<String> expectedInputList = Arrays.asList("Hello", "Java", "World");
        Assertions.assertNotNull(actualInputList);
        Assertions.assertEquals(3, actualInputList.size());
        Assertions.assertEquals(expectedInputList, actualInputList);
    }

    @Test
    void takeInputLinesFromConsoleTest_NoInput(){
        String consoleInput = "";
        ByteArrayInputStream testIn = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(testIn);
        Assertions.assertThrows(NoSuchElementException.class, MyGrep::takeInputLinesFromConsole);
    }

    @Test
    void takeInputLinesFromConsoleTest_InvalidInput(){
        String consoleInput = "Hello\nJava\nWorld";                         // Not Provided character '^D' which is end of input
        ByteArrayInputStream testIn = new ByteArrayInputStream(consoleInput.getBytes());
        System.setIn(testIn);
        Assertions.assertThrows(NoSuchElementException.class, MyGrep::takeInputLinesFromConsole);
    }




    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 3 Testcase - Case InSensitive Search
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void isStringPresentInInputTest_CaseInsensitive_HappyFlow(){
        Assertions.assertEquals(true,
                MyGrep.isSearchStringPresentInInput(INPUT_SEARCH_STRING_1, INPUT_SEARCH_STRING_1.toLowerCase(Locale.ROOT), true));
    }

    @Test
    void isStringPresentInInputTest_CaseInsensitive2_HappyFlow(){
        Assertions.assertEquals(true,
                MyGrep.isSearchStringPresentInInput(INPUT_SEARCH_STRING_1, INPUT_SEARCH_STRING_1,true));
    }

    @Test
    void isStringPresentInInputTest_CaseSensitive_HappyFlow(){
        Assertions.assertEquals(true,
                MyGrep.isSearchStringPresentInInput(INPUT_SEARCH_STRING_1, INPUT_SEARCH_STRING_1, false));
    }

    @Test
    void isStringPresentInInputTest_CaseSensitive2_HappyFlow(){
        Assertions.assertEquals(false,
                MyGrep.isSearchStringPresentInInput(INPUT_SEARCH_STRING_1, INPUT_SEARCH_STRING_1.toLowerCase(Locale.ROOT), false));
    }




    // --------------------------------------------------------------------------------------------------------------------
    //                                  Story 4 Testcase - Write Data Into New File
    // --------------------------------------------------------------------------------------------------------------------

    @Test
    void writeDataIntoNewFileTest_HappyFlow() throws Exception {
        // 1. execute writeDataIntoNewFile function
        List<String> inputLines = Arrays.asList("Hello", "World");
        String newFileName = "writeDataIntoNewFileTestFile.txt";
        MyGrep.writeDataIntoNewFile(inputLines, newFileName);

        // 2. Now get lines which are stored into the new files
        List<String> actualLines = readFileAndGetDataInList(newFileName);

        // 3. Delete the newly created file
        File file = new File(newFileName);
        file.delete();

        // 4. compare the actualLines with the inputLines
        Assertions.assertEquals(inputLines, actualLines);
    }

    @Test
    void writeDataIntoNewFileTest_FileAlreadyExist(){
        Assertions.assertThrows(FileAlreadyExistsException.class, () -> MyGrep.writeDataIntoNewFile(new ArrayList<>(), TEST_READ_FILE_PATH));
    }
}