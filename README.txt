Name: Cameron Meyer
NetID: cdm180003
Class: CS 3345
Section: 001
Semester: Fall 2019

Project 5 - Implements a quicksort algorithm for ArrayLists using various pivot selection strategies. 

This project contains the following java files:
	QuickSorter.java
	Main.java

The code was written and developed using Eclipse (2019-06 release). The code can be compiled and run in Eclipse. 

Steps for compiling and running in Eclipse (if needed):
	1. Set up a java project with the 2 java files in the src folder (default package).
	2. Insert the input file into the project folder (the parent of src). 
	3. Go to Run -> Run Configurations -> Arguments. 
	4. In the "Program arguments" field, add <array size> <report filename> <unsorted array filename> <sorted array filename>
	5. Apply changes. 
	6. Run Main.java using the run button.

Steps for compilation and running in the Command Line(if needed):
	1. cd to the directory containing all java files and input text files. 
	2. Enter the command: javac Main.java
	3. Run the program using the command: java Main <array size> <report filename> <unsorted array filename> <sorted array filename>

{--- Analysis of Pivot Selection Strategies ---}
From my testing (shown below), the "median of three elements" pivot selection strategy proved to be the fastest. It was consistently 
faster than the other options except for one randomized list case, where "median of three random elements" was faster. I think these
results occurred because, for repeated recursive calls on the sort, "median of three elements" more consistently chooses an element
that splits the list nearly in half. "First element" progressively chooses worse pivots with each recursive call, "random element" 
gives no guarantee of choosing a good pivot, and "median of three random elements" MAY choose a good dividing pivot, but it's less
common than "median of three elements". In an already sorted or nearly sorted list, "median of three elements" is even better at
choosing pivots, since the median element is more consistently in the middle of the range of elements being sorted than if the 
elements were randomly placed.  
	
{--- Test Run Output From Reports (varied ArrayList sizes and generation methods) ---}
|-- 100 elements --|
[Random List]
Array Size = 100
FIRST_ELEMENT : PT0.0005759S
RANDOM_ELEMENT : PT0.0001646S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0001725S
MEDIAN_OF_THREE_ELEMENTS : PT0.0001084S

[Sorted List]
Array Size = 100
FIRST_ELEMENT : PT0.0007269S
RANDOM_ELEMENT : PT0.0000845S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0000578S
MEDIAN_OF_THREE_ELEMENTS : PT0.0000442S

[Nearly Sorted List]
Array Size = 100
FIRST_ELEMENT : PT0.000481S
RANDOM_ELEMENT : PT0.0000848S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.000082S
MEDIAN_OF_THREE_ELEMENTS : PT0.000055S



|-- 1000 elements --|
[Random List]
Array Size = 1000
FIRST_ELEMENT : PT0.0037734S
RANDOM_ELEMENT : PT0.0051003S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0010159S
MEDIAN_OF_THREE_ELEMENTS : PT0.0005832S

[Sorted List]
Array Size = 1000
FIRST_ELEMENT : PT0.0140584S
RANDOM_ELEMENT : PT0.0005076S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0003368S
MEDIAN_OF_THREE_ELEMENTS : PT0.0002266S

[Nearly Sorted List]
Array Size = 1000
FIRST_ELEMENT : PT0.0129469S
RANDOM_ELEMENT : PT0.000582S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0003106S
MEDIAN_OF_THREE_ELEMENTS : PT0.0002006S



|-- 5000 elements --|
[Random List]
Array Size = 5000
FIRST_ELEMENT : PT0.0075788S
RANDOM_ELEMENT : PT0.0063912S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0026116S
MEDIAN_OF_THREE_ELEMENTS : PT0.0028135S

[Sorted List]
Array Size = 5000
FIRST_ELEMENT : PT0.0861164S
RANDOM_ELEMENT : PT0.0053426S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0016228S
MEDIAN_OF_THREE_ELEMENTS : PT0.0013554S

[Nearly Sorted List]
Array Size = 5000
FIRST_ELEMENT : PT0.0123275S
RANDOM_ELEMENT : PT0.007784S
MEDIAN_OF_THREE_RANDOM_ELEMENTS : PT0.0060705S
MEDIAN_OF_THREE_ELEMENTS : PT0.0019542S