/**
 * This class checks if a subset of numbers from an array can sum up to a target number (b),
 * using threads to explore different possibilities.
 * 
 * @version V1.0.0 
 * @author Noam Darlinger
 * @author Gal Mitrani
 */
public class ThreadCheckArray implements Runnable 
{
	private boolean flag;
	private boolean [] winArray;
	SharedData sd;
	int[] array;
	int b;
	
	/**
	 * Constructor that initializes the shared data and copies the array and target value.
	 * 
	 * @param sd the shared data object
	 */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.length];
	}
	
	/**
	 * Recursive method to check if a subset of the array can sum to a given value.
	 * 
	 * @param n index in the array
	 * @param b current target value
	 */
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array[n-1])
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array[n-1])
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array[n-1]);
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}

	/**
	 * Starts the thread logic and launches the recursive subset check.
	 * It also updates shared result if a valid subset is found.
	 */
	public void run() {
		if (array.length != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.length-1, b - array[array.length - 1]);
			else 
				rec(array.length-1, b);
		if (array.length == 1)
			if (b == array[0] && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.length - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
