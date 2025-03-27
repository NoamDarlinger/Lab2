/**
 * This class represents a shared data object that holds:
 * - an array of integers
 * - a boolean array (for win flags or similar purposes)
 * - a final integer value
 * - a flag (boolean)
 * 
 * Used to share data between different components or threads.
 * 
 * @author Noam darlinger 
 * @author Gal mitrani
 *  */
 

public class SharedData 
{
    /** An array of integers */
    private int[] array;

    /** A boolean array indicating win status or similar flags */
    private boolean[] winArray;

    /** A flag used for signaling */
    private boolean flag;

    /** A constant integer value */
    private final int b;
    
    

    /**
     * Constructor for SharedData
     * 
     * @param array an array of integers
     * @param b a constant integer value
     */
    public SharedData(int[] array, int b) {
        this.array = array;
        this.b = b;
    }
    

    /**
     * Gets the win array
     * 
     * @return a boolean array
     */
    public boolean[] getWinArray() {
        return winArray;
    }

    /**
     * Sets the win array
     * 
     * @param winArray a new boolean array
     */
    public void setWinArray(boolean[] winArray) {
        this.winArray = winArray;
    }

    /**
     * Gets the array of integers
     * 
     * @return an int array
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Gets the constant value b
     * 
     * @return an integer
     */
    public int getB() {
        return b;
    }

    /**
     * Gets the flag value
     * 
     * @return true or false
     */
    public boolean getFlag() {
        return flag;
    }

    /**
     * Sets the flag value
     * 
     * @param flag the new  boolean flag value
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}