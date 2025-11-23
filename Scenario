/********************************************************************
*  UI Scenario
*
*@author: Sanjida Shakhayet
* CS 2043 / Group 10
********************************************************************/

//We are following this 6 steps to build up UI scenario

1. Start the Application

User Action:

Launch the Code Tester JavaFX application.

System Response:

The main UI appears with buttons such as
Create Test Case, Load Test Case, Create Test Suite,
Save Test Case, Add Test Case to Test Suite, and soon an execution option.

2. Create a Test Suite

User Action:

Click “Create Test Suite” to initialize a new TestSuite object.

System Response:

The system creates an empty TestSuite (as required by the professor—only one TestSuite is used in Version 1).

A message or UI indicator confirms the suite has been created.

3. Add Test Cases to the Test Suite

Before running anything, the user must define test cases.

Option A – Create Test Case Manually

User Actions:

Click “Create Test Case”.

Enter the input file path.

Enter the expected output file path.

Click “Save Test Case” to finalize the TestCase object.

Click “Add Test Case to Test Suite”.

System Response:

A TestCase object is created and added to the TestSuite’s list.

Option B – Load Test Case From File

User Actions:

Click “Load Test Case”.

Select a file that contains input and expected output paths.

After loading, click “Add Test Case to Test Suite”.

System Response:

The loaded test case is inserted into the TestSuite.

🔸 Important:
The TestSuite must contain at least one test case before executing the suite.

 4. Set the Root Folder

The execution process requires a root folder that contains student program directories.

User Actions:

Enter the root folder path into the UI textbox.

Click “Confirm Root Folder” (or the button your team uses).

System Response:

The system stores the root folder.

All subdirectories will be treated as individual programs (e.g., ProgramA, ProgramB).

Each folder is assumed to contain exactly one .java file representing the main class.

 5. Execute the Test Suite

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

 6. View Results and Feedback

User Action:

Review the displayed output in the UI.

System Response:
The UI shows:

Each test case

Expected output

Actual output

Pass / Fail

Any compile or runtime errors

A summary via FinalResult and Feedback
