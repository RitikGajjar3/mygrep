# mygrep
Implementation of Unix grep command 

<br>

## 🚀 Execution
```python
1. Create Jar file of the project
2. Exectue Command: java -jar <JAR_PATH> <GREP_ARGUMENTS>
3. E.g. java -jar /Users/ritik/Desktop/mygrep.jar hello print.txt  

```

<br>

## ⚙️ Commands 

  
|   <div style="width:400px">Command</div> | Example & Usecase |
| ----  |   ---   |  
| [search string] [read file name] |  &#8226; command Example - <br> Hello welcomeRead.txt <br> &#8226; used to search given search string from file |
| [search string] | &#8226; command Example - <br> Hello World! <br> How are you? <br> ^D <br> &#10; &#8226; used to search given search string from lines which are passed in command lines. ^D is used as end of input line| 
| -i [search string] [read file name] | &#8226; command Example - <br> hello -i testFile.txt <br> &#8226; used to search given search string as a case insensitive from file | 
| [search string] [read file name] -o [write file name] | &#8226; command Example - <br> hello testReadFile.txt -o testWriteFile.txt <br> &#8226; used to search given search string from read file and write the output to write file | 
| -i [search string] [read file name] -o [write file name] | &#8226; command Example - <br> hello testReadFile.txt -o testWriteFile.txt <br> &#8226; used to search given search string as a case insensitive from read file and write the output to write file | 

<br>

## ✅  Internal Implementation 

- **File Read** 
   - Here is a detail of what are all ways to read a file in java

| Approach | Usecase |
| ---   | --- | 
| BufferedReader | &#8226; BufferedReader is uesd to read large text files efficiently in Java. <br>&#10; &#8226; BufferedReader reads a file line by line, which can help you to avoid loading the entire file into memory. |
| FileInputStream | &#8226; FileInputStream is used to read a binary file (such as an image or audio file). <br>&#10; &#8226; FileInputStream reads a file byte by byte or in a specified block size.|
| RandomAccessFile | &#8226; RandomAccessFile is used to read and write files at random positions. <br>&#10; &#8226; RandomAccessFile provides a seek() method that allows you to move the file pointer to a specific position in the file.|
| Memory-mapped files | &#8226; Memory-mapped files is used when large binary file that doesn't fit into memory, we can use memory-mapped files to read and write data. <br>&#10; &#8226; Memory-mapped files allow you to map a portion of a file directly into memory, which can improve performance.|

   - Assuming we have to read large text files and thus choosing BufferedReader implementation for reading files. Apart from the mentioned approaches, there are various other third-party libraries that we can use according to our requirements.      


<br>


- **Case Insensitive Search**
  - Here is a detail of what are all ways to search string as case-insensitive
  
| Approach | Usecase |
| ---   | --- | 
| using toLowerCase() or toUpperCase() |  &#8226; Pros: This is a simple and lightweight approach that requires no additional libraries. It's easy to understand and implement. <br>&#10; &#8226; Cons: This approach creates a new string, which could be expensive if the original string is very large. Additionally, if you need to perform multiple case-insensitive searches, you would need to convert the string to lowercase or uppercase each time, which could be repetitive.| 
| Using Pattern and Matcher classes |  &#8226; Pros: This approach offers a lot of flexibility and power, and can be used for more complex regex searches. It also offers good performance for large strings and multiple searches. <br>&#10; &#8226; Cons: This approach is more complex than the first method and requires knowledge of regular expressions. |
| Using StringUtils class from Apache Commons Lang library | &#8226; Pros: This approach offers a simple and easy-to-use method that requires no additional coding or libraries. It's also highly readable and self-explanatory. <br>&#10; &#8226; Cons: This approach requires the addition of the Apache Commons Lang library to your project, which could add extra overhead and dependencies to your code. |

   - Chossing Pattern and Matcher classes as assuming the usecase is for a large strings. 
   - Not choosing 
      - toLowerCase() and toUpperCase() : For large strings, it is inefficient and takes up additional space.
      - StringUtils : Building a light-weight grep without StringUtils as that requires additional dependencies.


<br>


- **File Write** 
   - Here is a detail of what are all ways to write a data into file using java

| Approach | Usecase |
| ---   | --- | 
| FileWriter | &#8226; Good for writing textual data to a file <br>&#10; &#8226; Simple and easy to use <br>&#10; &#8226; Does not support writing binary data |
| BufferedWriter | &#8226; More efficient than FileWriter for writing large amounts of textual data to a file. <br>&#10; &#8226; Can improve performance by reducing the number of disk writes|
| PrintWriter | &#8226; RandomAccessFile is used to read and write files at random positions. <br>&#10; &#8226; RandomAccessFile provides a seek() method that allows you to move the file pointer to a specific position in the file.|
| FileOutputStream | &#8226; Good for writing binary data to a file <br>&#10; &#8226; Simple and easy to use|
| ObjectOutputStream | &#8226; Good for writing Java objects to a file <br>&#10; &#8226; Can serialize complex object graphs to a file <br>&#10; &#8226; Provides options for customizing the serialization process|

   - Assuming we have to write large amount of text data into files and thus choosing BufferedWriter implementation for writing into files. 
   - Main Reason for choosing BufferedWriter 
      - BufferedWriter can improve performance by buffering the data in memory before writing it to the file. This can reduce the number of disk writes and improve performance, especially when writing large amounts of data to the file.
   - Not choosing
      -  FileWriter, PrintWriter: BufferedWriter gives better peformance compare to these approaches because of the above reason
      -  FileOutputStream, ObjectOutputStream: Not primarly used for text files 


<br>

## 🤝 Contact

Email Id: ritik.gajjar.3@gmail.com

Linkedin: https://www.linkedin.com/in/ritik-gajjar/

[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://github.com/RitikGajjar3)
