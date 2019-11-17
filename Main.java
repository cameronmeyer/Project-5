import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
//import java.util.Scanner;

public class Main
{
	public static void main(String [] args)
	{
		// Check that the arguments are correct
        if (args.length != 4)
        {
            System.out.print("Error Incorrect Arguments: " + Arrays.toString(args));
            System.exit(0); 
        }
        
        /* The first - array size
         * The second - filename for the reports file
         * The third - filename to store the unsorted array
         * The fourth - filename to store sorted array. 
         * The command line execution will look like the following:
         * 		./Main_Class 100000 report.txt unsorted.txt sorted.txt
         */
        
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
        	uns.print(theList1.toString());
        	//System.out.println(theList1.toString() + "  << unsorted array");
        	
        	
        	/*
        	 * 
        	 * THE FOLLOWING LINES WERE USED DURING TESTING BUT ARE NOW INACTIVE
        	 * 
        	 */
        	
        	
        	//ArrayList<Integer> theList1 = QuickSorter.generateSortedList(arrSize);
        	//ArrayList<Integer> theList1 = QuickSorter.generateNearlySortedList(arrSize);
        		
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
        	
        	sor.print(theList4.toString()); //to test med of 3
        	//System.out.println(theList1.toString() + "  << sorted array via first elem");
        	//System.out.println(theList2.toString() + "  << sorted array via rand elem");
        	//System.out.println(theList3.toString() + "  << sorted array via med of 3 rand elems");
        	//System.out.println(theList4.toString() + "  << sorted array via med of 3 elems");
        	
        	report.close();
        	uns.close();
        	sor.close();
        }
        catch(Exception e) {System.out.println(e.getMessage());}
	}
}
