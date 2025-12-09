# Submission 3 - Software Engineering - CS2043

Author: Abdullah Tauqir, Sanjida Shakhayet, Senil Macwan, Ayman Malik

Group: 10


## Project Overview
This software creates Test case, loads them, saves them and does similar things for test suite and then uses these test suites made up of test cases to compile and run mutiple students code and provide result based on comparing output from students code and that given in respective test case. It also saves and reloads result and gives option to run the same test suite on a different set of code. 

## Contents
- CodeTesterUI.java — User interface implementation
- COORD.java — Coordinates management
- TestCase.java — Defines individual test cases
- TestSuite.java — Defines test suites
- ListTS.java — Lists all test suites
- ListTC.java — Manages test cases
- ExecuteTestSuite.java — Executes test suites
- studentResult.java - stores the result generated on particular Test case
- SuccessRateCalculator - calulates and compares success rate for students

- scenario.md — Contains project scenarios and user guide

## Usage
1.Start the Application
User Action : Launch the Code Tester JavaFX application.
System Response : The main UI appears with buttons such as Create Test Case, Load Test Case, Create Test Suite, Save Test Case, Add Test Case to Test Suite, etc.

2.Create a Test Suite
User Action:Click “Create Test Suite” to initialize a new TestSuite object.
System Response:The system creates an empty TestSuite (as required by the professor—only one TestSuite is used in Version 1).
A message or UI indicator confirms the suite has been created.

3. Add Test Cases to the Test Suite
Before running anything, the user must define test cases.

Option A – Create Test Case Manually
User Actions:Click “Create Test Case”.
Enter the input value.
Enter the expected output value.

Click “Add Test Case to Test Suite”.

System Response:A TestCase object is created and added to the TestSuite’s list.

Option B – Load Test Case From File
User Actions:Click “Load Test Case”.
Select a file that contains input and expected output paths.
After loading, click “Add Test Case to Test Suite”.

System Response:
The loaded test case is inserted into the TestSuite.
The TestSuite must contain at least one test case before executing the suite.

Set the Root Folder
The execution process requires a root folder that contains student program directories.

User Actions:
Enter the root folder path into the UI textbox.
Click “Confirm Root Folder” (or the button your team uses).

System Response:
The system stores the root folder.
All subdirectories will be treated as individual programs (e.g., ProgramA, ProgramB).
Each folder is assumed to contain exactly one .java file representing the main class.

Execute the Test Suite

User Action:
Click “Execute Test Suite” (the button triggered by your team).
System Response (following the professor’s sequence diagram):
The system scans the root folder for program subdirectories.

For each program:
Load its .java file
Compile it
Run it once for each TestCase

For each test case:
Input file → program
Capture program output
Compare output with expected output
Compiler or runtime errors are collected using the Error, Result, and FinalResult classes.
Store Results

User Actions:
Click Store Results.
Enter a filename (e.g., results_v1.ser) and confirm.

System Response:
Results of the test suite execution are serialized and saved.

UI confirms: "Results saved successfully".
Execute Test Suite on Resubmission Folder

User Actions:
Enter path to second folder containing resubmissions (e.g., ./StudentPrograms_v2).
Click Confirm Root Folder for the new folder.
Click Execute Test Suite.
Store results as results_v2.ser.

System Response:
System executes all test cases on resubmitted programs.
Results saved and displayed in the same UI format.
Reload Stored Results

User Actions:
Click Reload Results.
Select previously saved result file (e.g., results_v1.ser or results_v2.ser).

System Response:
UI displays all results from the selected file in a read-only text area.
Compare Success Rates Between Two Submissions

User Actions:
Click Compare Success Rates.
Select two result files (e.g., results_v1.ser and results_v2.ser).

System Response:
UI displays a side-by-side comparison for each student:
Number of test cases passed in each version / total number of test cases.

Special messages if:
Student did not resubmit → "No Submission"
Program failed to compile → "Compile Error"

save result : 

User Action :
make sure there is a set of result generated 
click save result

system response:
it will create a serializable object and store it in the same folder as of your software files

Load Result :

User Action:
User clicks the “Load Results” button.
The system opens a FileChooser window.
User selects a results file (e.g., results_v1.ser).
User confirms the selection.

System Response :
The UI calls COORD.loadStudentResults(filePath, debug).
COORD opens and deserializes the .ser file.
COORD returns a List<StudentResults> back to the UI.
UI formats the results (student name, passed/total, success %).
UI displays the formatted results in the output area.

Compare Success Rate:

User Action : 
User clicks “Compare Success Rate” button.
User is prompted to enter:
    Test Suite name
    Student 1 folder path
    Student 2 folder path
User provides both folder paths and confirms.

System Response:
UI calls
COORD.compareStudents(suiteName, folder1, folder2)
COORD loads the TestSuite and runs all its test cases on Student 1’s program.
COORD records number of tests passed by Student 1.
COORD repeats the process for Student 2.
COORD calculates pass rate for both.
COORD constructs a text comparison summarizing:
    Passed tests
    Total tests
    Success percentages
    Who performed better
UI displays the comparison in the output area.

Student Success Rate :

User Action:
User clicks “Check Single Student Success Rate” (your new button).
User is prompted for:
Test Suite name
Student folder path
User selects the folder and confirms the input.

System Response:
UI calls
    COORD.calculateStudentSuccessRate(suiteName, studentFolder)
    COORD finds the .java file with a main method in that folder.
    COORD compiles the program (via ExecuteTestSuite).
    COORD runs each Test Case from the chosen Test Suite.
For each Test Case:
    Program is executed
    Output is captured
    PASS/FAIL is determined
COORD counts passed tests and total tests.
COORD calculates:
    successRate = (passed / total) * 100
COORD returns the formatted success rate string.

UI displays:
Number of tests
Passed tests
Percentage success

