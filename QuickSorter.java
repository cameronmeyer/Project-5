import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Collections;
//import java.util.Comparator;

/*
 * <h1>QuickSorter</h1>
 * This program is equipped with functions to generate and sort ArrayLists with quicksort
 * and various pivot selection strategies.
 * <p> 
 * 
 * @author Cameron Meyer
 * @since 11/17/2019
 */
public class QuickSorter
{
	private static final int CUTOFF = 20;
	
    private QuickSorter() {}
    
    /*
     * This is an enum to contain each pivot strategy
     */
    public static enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }

    /*
     * This method accepts a list and pivot strategy, then performs the proper
     * quicksort to the list and times how long it takes to complete. 
     * @param list This is the ArrayList to be sorted
     * @param strategy This is the pivot strategy to use during the quicksort
     * @return Duration This is how long the program took to complete the quicksort
     */
    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy)
    {
    	if(list == null || strategy == null)
    	{
    		throw new NullPointerException();
    	}
    	
    	long startTime = System.nanoTime(); //start duration timer

    	if(list.size() > 1) //only non-empty lists with more than 1 element need to be sorted
    	{
	    	switch(strategy) //perform a quicksort using the intended pivot selection strategy
	    	{
	    	case FIRST_ELEMENT:
	    		qsFirstElem(list);
	    		break;
	    	case RANDOM_ELEMENT:
	    		qsRandElem(list);
	    		break;
	    	case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
	    		qsMedOfThreeRand(list);
	    		break;
	    	case MEDIAN_OF_THREE_ELEMENTS:
	    		qsMedOfThree(list);
	    		break;
	    	default:
	    		System.out.println("Not a valid pivot strategy.");
	    	}
    	}

        return tsqHelper(startTime);
    }
    
    private static <E extends Comparable<E>> Duration tsqHelper(long startTime)
    {
    	long finishTime = System.nanoTime(); //end duration timer
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime); //calculate elapsed time
    	return elapsedTime;
    }

    /*
     * This method generates a random ArrayList of a given size
     * @param size This is the intended size for the ArrayList
     * @return ArrayList This is the generated random ArrayList
     */
    public static ArrayList<Integer> generateRandomList(int size)
    {
    	if(size < 0) //size of the list must be nonnegative
    	{
    		throw new IllegalArgumentException();
    	}
    	
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();

        //add random values to list
        for (int i = 0; i < size; ++i)
        {
            int num = random.nextInt();
            list.add(num);
        }
        
        return list;
    }
    
    private static <E extends Comparable<E>> void qsFirstElem(ArrayList<E> list)
    {
    	qsFirstElem(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsFirstElem(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //only perform quicksort if we have a sufficient number of elements
        {
    		//first element will be the pivot
            E pivot = list.get(left);
            
            //place pivot at position right
            swap(list, left, right);

            quickSortHelper(list, left, right, pivot, PivotStrategy.FIRST_ELEMENT);
        }
        else
        {
        	//do an insertion sort on the subarray
            insertionSort(list, left, right);
        }
    }
    
    private static <E extends Comparable<E>> void qsRandElem(ArrayList<E> list)
    {
    	qsRandElem(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsRandElem(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //only perform quicksort if we have a sufficient number of elements
        {
    		//random element will be the pivot
    		Random random = new Random();
        	int index = random.nextInt(right + 1 - left) + left;
            E pivot = list.get(index);
            
            //place pivot at position right
            swap(list, index, right);

            quickSortHelper(list, left, right, pivot, PivotStrategy.RANDOM_ELEMENT);
        }
        else
        {
        	//do an insertion sort on the subarray
            insertionSort(list, left, right);
        }
    }
    
    private static <E extends Comparable<E>> void qsMedOfThreeRand(ArrayList<E> list)
    {
    	qsMedOfThreeRand(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThreeRand(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //only perform quicksort if we have a sufficient number of elements
        {
            E pivot = medianOfThreeRand(list, left, right);  //call pivot selection helper

            quickSortHelper(list, left, right, pivot, PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        }
        else
        {
        	//do an insertion sort on the subarray
            insertionSort(list, left, right);
        }
    }
    
    private static <E extends Comparable<E>> E medianOfThreeRand(ArrayList<E> list, int left, int right )
    {
    	//generate 3 random indices within range of the list
    	Random random = new Random();
    	int ind1 = random.nextInt(right + 1 - left) + left;
    	int ind2 = random.nextInt(right + 1 - left) + left;
    	int ind3 = random.nextInt(right + 1 - left) + left;
    	
    	//make sure all indices are unique
    	while(ind2 == ind1)
    	{
    		ind2 = random.nextInt(right + 1 - left) + left;
    	}
    	
    	while(ind3 == ind1 || ind3 == ind2)
    	{
    		ind3 = random.nextInt(right + 1 - left) + left;
    	}
    	
    	//ind1 should hold the leftmost index, ind2 holds the middle, and ind3 will hold the rightmost index
    	if(ind1 > ind2)
    	{
    		int temp = ind1;
    		ind1 = ind2;
    		ind2 = temp; 
    	}
    	if(ind1 > ind3)
    	{
    		int temp = ind1;
    		ind1 = ind3;
    		ind3 = temp;
    	}
    	if(ind2 > ind3)
    	{
    		int temp = ind2;
    		ind2 = ind3;
    		ind3 = temp;
    	}

    	//swap indices so values are ordered least to greatest (similar to code from median of 3)
        if(list.get(ind2).compareTo(list.get(ind1)) < 0)
        {
            swap(list, ind1, ind2);
        }
        if(list.get(ind3).compareTo(list.get(ind1)) < 0)
        {
            swap(list, ind1, ind3);
        }
        if(list.get(ind3).compareTo(list.get(ind2)) < 0)
        {
            swap(list, ind2, ind3);
        }

        //place pivot at position right
        swap(list, ind2, right);
        
        return list.get(right);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThree(ArrayList<E> list)
    {
    	qsMedOfThree(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThree(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //only perform quicksort if we have a sufficient number of elements
        {
            E pivot = medianOfThree(list, left, right); //call pivot selection helper

            quickSortHelper(list, left, right, pivot, PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
        }
        else
        {
        	//do an insertion sort on the subarray
            insertionSort(list, left, right);
        }
    }
    
    private static <E extends Comparable<E>> E medianOfThree(ArrayList<E> list, int left, int right )
    {
    	//find the index at the center of the array range
    	int center = (left + right) / 2;
    	
    	//swap indices so values are ordered least to greatest
        if(list.get(center).compareTo(list.get(left)) < 0)
            swap(list, left, center);
        if(list.get(right).compareTo(list.get(left)) < 0)
            swap(list, left, right);
        if(list.get(right).compareTo(list.get(center)) < 0)
            swap(list, center, right);

        //place pivot at position right
        swap(list, center, right);

        return list.get(right);
    }
    
    private static <E extends Comparable<E>> void swap(ArrayList<E> list, int ind1, int ind2)
    {
    	E temp = list.get(ind1);
    	list.set(ind1, list.get(ind2));
    	list.set(ind2, temp);
    }
    
    private static <E extends Comparable<E>> void insertionSort(ArrayList<E> list, int left, int right )
    {
        for(int p = left + 1; p <= right; p++)
        {
            E tmp = list.get(p);
            int j;

            for(j = p; j > left && tmp.compareTo(list.get(j - 1)) < 0; j--)
            {
                list.set(j, list.get(j - 1));
            }
            
            list.set(j, tmp);
        }
    }
    
    private static <E extends Comparable<E>> void quickSortHelper(ArrayList<E> list, int left, int right, E pivot, PivotStrategy strategy)
    {
    	//begin partitioning
        int i = left - 1, j = right;
        
        while(true)
        {
            while(list.get(++i).compareTo(pivot) < 0 && i < right) {}
            while(list.get(--j).compareTo(pivot) > 0 && j > 0) {}
            
            if(i < j)
            {
                swap(list, i, j);
            }
            else
            {
                break;
            }
        }

        swap(list, i, right); //restore pivot to intended location
        
        switch(strategy)
    	{
    	case FIRST_ELEMENT:
    		qsFirstElem(list, left, i - 1);    //sort small elements
            qsFirstElem(list, i + 1, right);   //sort large elements
    		break;
    	case RANDOM_ELEMENT:
    		qsRandElem(list, left, i - 1);     //sort small elements
            qsRandElem(list, i + 1, right);    //sort large elements
    		break;
    	case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
    		qsMedOfThreeRand(list, left, i - 1);    //sort small elements
            qsMedOfThreeRand(list, i + 1, right);   //sort large elements
    		break;
    	case MEDIAN_OF_THREE_ELEMENTS:
    		qsMedOfThree(list, left, i - 1);    //sort small elements
            qsMedOfThree(list, i + 1, right);   //sort large elements
    		break;
    	default:
    		System.out.println("Not a valid pivot strategy.");
    	}
    }
    
    /*
    
    THE FOLLOWING TWO FUNCTIONS WERE USED DURING TESTING BUT ARE NOW INACTIVE 
     
    public static ArrayList<Integer> generateSortedList(int size)
    {
        ArrayList<Integer> list = generateRandomList(size);

        Collections.sort(list);
        
        return list;
    }
    
    public static ArrayList<Integer> generateNearlySortedList(int size)
    {
        ArrayList<Integer> list = generateSortedList(size);
        
        Random random = new Random();
        int numElemsToSwap = size / 10;
        
        for(int i = 0; i < numElemsToSwap / 2; i++) //numElemsToSwap / 2 since each swap takes 2 elements so we only need half this number
        {
        	int ind1 = random.nextInt(list.size() - 1); //choose index between 0 and size - 1
        	int ind2 = random.nextInt(list.size() - 1); //choose index between 0 and size - 1
        	
        	swap(list, ind1, ind2);
        }
        
        return list;
    }
    */
}
