import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;

/*
 * <h1>Main</h1>
 * This program generates a randomly sorted ArrayList and performs a quicksort
 * on it using various pivot selection strategies. 
 * <p> 
 * 
 * @author Cameron Meyer
 * @since 11/17/2019
 */
public class Main
{
	/*
	 * This method generates a randomly sorted ArrayList and duplicates it
	 * repeatedly so quicksort can be performed on each using a different
	 * pivot selection strategy each time. 
	 * @param arraySize This is the size of the generated ArrayList
	 * @param reportFileName This is the name of the file that will be created for report output
	 * @param unsortedFileName This is the name of the file created for output of the unsorted list
	 * @param sortedFileName This is the name of the file created for output of the sorted list
	 */
	public static void main(String [] args)
	{
		//check that the arguments are correct
        if (args.length != 4)
        {
            System.out.print("Error Incorrect Arguments: " + Arrays.toString(args));
            System.exit(0); 
        }
        
        try
        {
        	int arrSize = Integer.parseInt(args[0]);
        	
        	File reports_file = new File(args[1]);
        		PrintWriter report = new PrintWriter(reports_file);
        		
        	File uns_arr = new File(args[2]);
        		PrintWriter uns = new PrintWriter(uns_arr);
        		
        	File sor_arr = new File(args[3]);
        		PrintWriter sor = new PrintWriter(sor_arr);
        	
        	ArrayList<Integer> theList1 = QuickSorter.generateRandomList(arrSize);
        	
        	/*
        	 * THE FOLLOWING LINES WERE USED DURING TESTING BUT ARE NOW INACTIVE
        	 * 
        	 * 
        	 * ArrayList<Integer> theList1 = QuickSorter.generateSortedList(arrSize);
        	 * ArrayList<Integer> theList1 = QuickSorter.generateNearlySortedList(arrSize);
        	 */

        	uns.print(theList1.toString());
        	
        	report.print("Array Size = " + arrSize);
        	
        	ArrayList<Integer> theList2 = new ArrayList<Integer>();
        	ArrayList<Integer> theList3 = new ArrayList<Integer>();
        	ArrayList<Integer> theList4 = new ArrayList<Integer>();
        	
        	theList2.addAll(theList1);
        	theList3.addAll(theList1);
        	theList4.addAll(theList1);

        	report.print("\nFIRST_ELEMENT : " + QuickSorter.timedQuickSort(theList1, QuickSorter.PivotStrategy.FIRST_ELEMENT)); 
        	report.print("\nRANDOM_ELEMENT : " + QuickSorter.timedQuickSort(theList2, QuickSorter.PivotStrategy.RANDOM_ELEMENT)); 
        	report.print("\nMEDIAN_OF_THREE_RANDOM_ELEMENTS : " + QuickSorter.timedQuickSort(theList3, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS)); 
        	report.print("\nMEDIAN_OF_THREE_ELEMENTS : " + QuickSorter.timedQuickSort(theList4, QuickSorter.PivotStrategy.MEDIAN_OF_THREE_ELEMENTS)); 
        	
        	sor.print(theList1.toString());
        	
        	report.close();
        	uns.close();
        	sor.close();
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
	}
}
