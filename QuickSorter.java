
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Collections;
//import java.util.Comparator;

public class QuickSorter
{
	private static final int CUTOFF = 20;
	
    /*
     * This private constructor is optional, but it does help to prevent accidental client instantiation of QuickSorter
     * via the default constructor.  (defining any constructor prevents the compiler from creating a default constructor)
     * This particular anti-instantiation technique is exactly what {@link java.util.Collections} does.
     */
    private QuickSorter() {}

    public static <E extends Comparable<E>> Duration timedQuickSort(ArrayList<E> list, PivotStrategy strategy)
    {
    	long startTime = System.nanoTime();

    	switch(strategy)
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

        return tsqHelper(startTime);
    }
    
    public static <E extends Comparable<E>> Duration tsqHelper(long startTime)
    {
    	long finishTime = System.nanoTime();
        Duration elapsedTime = Duration.ofNanos(finishTime - startTime);
    	return elapsedTime;
    }

    public static ArrayList<Integer> generateRandomList(int size)
    {
    	/* According to the JavaDoc, the no-args constructor of Random "sets the seed of the random number generator to
         * a value very likely to be distinct from any other invocation of this constructor."
         */
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < size; ++i)
        {
            int num = random.nextInt();
            list.add(num);
        }
        
        return list;
    }

    /**
	* Generates a pseudo-random integer in the range [min, max]
	* @param min : the starting value of the range (inclusive)
	* @param max : the ending value of the range (inclusive)
    * @return 
	*/
    public static int rand(int min, int max)
	{
		if (min > max || (max - min + 1 > Integer.MAX_VALUE))
		{
			throw new IllegalArgumentException("Invalid range");
		}

		return new Random().nextInt(max - min + 1) + min;
	}

    public static enum PivotStrategy
    {
        FIRST_ELEMENT,
        RANDOM_ELEMENT,
        MEDIAN_OF_THREE_RANDOM_ELEMENTS,
        MEDIAN_OF_THREE_ELEMENTS
    }
    
    private static <E extends Comparable<E>> void qsFirstElem(ArrayList<E> list)
    {
    	qsFirstElem(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsFirstElem(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //example used "cutoff" I hardcoded to 3 since we need at least 3 indices(?)
        {
            E pivot = list.get(left);
            swap(list, left, right);

            quickSortHelper(list, left, right, pivot, PivotStrategy.FIRST_ELEMENT);
        }
        else  // Do an insertion sort on the subarray
            insertionSort(list, left, right);
    }
    
    private static <E extends Comparable<E>> void qsRandElem(ArrayList<E> list)
    {
    	qsRandElem(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsRandElem(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //example used "cutoff" I hardcoded to 3 since we need at least 3 indices(?)
        {
    		Random random = new Random();
        	int index = random.nextInt(right + 1 - left) + left;
            E pivot = list.get(index);
            swap(list, index, right);

            quickSortHelper(list, left, right, pivot, PivotStrategy.RANDOM_ELEMENT);
        }
        else  // Do an insertion sort on the subarray
            insertionSort(list, left, right);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThreeRand(ArrayList<E> list)
    {
    	qsMedOfThreeRand(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThreeRand(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //example used "cutoff" I hardcoded to 3 since we need at least 3 indices(?)
        {
            E pivot = medianOfThreeRand(list, left, right);  //call pivot selection helper

            quickSortHelper(list, left, right, pivot, PivotStrategy.MEDIAN_OF_THREE_RANDOM_ELEMENTS);
        }
        else  // Do an insertion sort on the subarray
            insertionSort(list, left, right);
    }
    
    private static <E extends Comparable<E>> E medianOfThreeRand(ArrayList<E> list, int left, int right )
    {
    	//generate 3 random indices
    	Random random = new Random();
    	int ind1 = random.nextInt(right + 1 - left) + left;
    	int ind2 = random.nextInt(right + 1 - left) + left;
    	int ind3 = random.nextInt(right + 1 - left) + left;
    	
    	//make sure all indices are unique
    	while(ind2 == ind1)
    		ind2 = random.nextInt(right + 1 - left) + left;
    	
    	while(ind3 == ind1 || ind3 == ind2)
    		ind3 = random.nextInt(right + 1 - left) + left;

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

    	//ind1 equiv to left, ind2 equiv to center, ind3 equiv to right
        if(list.get(ind2).compareTo(list.get(ind1)) < 0)
            swap(list, ind1, ind2);
        if(list.get(ind3).compareTo(list.get(ind1)) < 0)
            swap(list, ind1, ind3);
        if(list.get(ind3).compareTo(list.get(ind2)) < 0)
            swap(list, ind2, ind3);

        // Place pivot at position right
        swap(list, ind2, right);
        return list.get(right);  //find out whether right or right-1 is correct, why default to right-1??
    }
    
    private static <E extends Comparable<E>> void qsMedOfThree(ArrayList<E> list)
    {
    	qsMedOfThree(list, 0, list.size() - 1);
    }
    
    private static <E extends Comparable<E>> void qsMedOfThree(ArrayList<E> list, int left, int right)
    {
    	if(left + CUTOFF <= right) //example used "cutoff" I hardcoded to 3 since we need at least 3 indices(?)
        {
            E pivot = medianOfThree(list, left, right); //call pivot selection helper

            quickSortHelper(list, left, right, pivot, PivotStrategy.MEDIAN_OF_THREE_ELEMENTS);
        }
        else  // Do an insertion sort on the subarray
            insertionSort(list, left, right);
    }
    
    private static <E extends Comparable<E>> E medianOfThree(ArrayList<E> list, int left, int right )
    {
    	int center = (left + right) / 2;
    	
        if(list.get(center).compareTo(list.get(left)) < 0)
            swap(list, left, center);
        if(list.get(right).compareTo(list.get(left)) < 0)
            swap(list, left, right);
        if(list.get(right).compareTo(list.get(center)) < 0)
            swap(list, center, right);

        // Place pivot at position right
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
                list.set(j, list.get(j - 1));
            list.set(j, tmp);
        }
    }
    
    private static <E extends Comparable<E>> void quickSortHelper(ArrayList<E> list, int left, int right, E pivot, PivotStrategy strategy)
    {
    	 // Begin partitioning
        int i = left - 1, j = right;
        while(true)
        {
            while(list.get(++i).compareTo(pivot) < 0 && i < right) {}
            while(list.get(--j).compareTo(pivot) > 0 && j > 0) {}
            if(i < j)
                swap(list, i, j);
            else
                break;
        }

        swap(list, i, right);   // Restore pivot
        
        switch(strategy)
    	{
    	case FIRST_ELEMENT:
    		qsFirstElem(list, left, i - 1);    // Sort small elements
            qsFirstElem(list, i + 1, right);   // Sort large elements
    		break;
    	case RANDOM_ELEMENT:
    		qsRandElem(list, left, i - 1);    // Sort small elements
            qsRandElem(list, i + 1, right);   // Sort large elements
    		break;
    	case MEDIAN_OF_THREE_RANDOM_ELEMENTS:
    		qsMedOfThreeRand(list, left, i - 1);    // Sort small elements
            qsMedOfThreeRand(list, i + 1, right);   // Sort large elements
    		break;
    	case MEDIAN_OF_THREE_ELEMENTS:
    		qsMedOfThree(list, left, i - 1);    // Sort small elements
            qsMedOfThree(list, i + 1, right);   // Sort large elements
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
