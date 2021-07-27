
/*************************************************************************
 *  Compilation:  javac Insertion.java
 *  Execution:    java Insertion < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/21sort/tiny.txt
 *                http://algs4.cs.princeton.edu/21sort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using insertion sort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Insertion < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Insertion < words3.txt
 *  all bad bed bug dad ... yes yet zoo   [ one string per line ]
 *
 *************************************************************************/

import java.util.Comparator;

/**
 * The <tt>Insertion</tt> class provides static methods for sorting an
 * array using insertion sort.
 * <p>
 * For additional documentation, see
 * <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Insertion {

    /**
    *This method should not be initiated
    */
    private Insertion() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param a
     *            the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    /**
     * Rearranges the array in ascending order, using a comparator.
     *
     * @param a
     *            the array
     * @param c
     *            the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator c) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(c, a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, c, 0, i);
        }
        assert isSorted(a, c);
    }

       // return a permutation that gives the elements in a[] in ascending
       // order do not change the original array a[]
    /**
     * Returns a permutation that gives the elements in the array in
     * ascending order.
     *
     * @param a
     *            the array
     * @return a permutation <tt>p[]</tt> such that <tt>a[p[0]]</tt>,
     *         <tt>a[p[1]]</tt>, ..., <tt>a[p[N-1]]</tt> are in ascending
     *         order
     */
    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
    for (int i = 0; i < n; i++) {
      index[i] = i;
}
    for (int i = 0; i < n; i++) {
      for (int j = i; j > 0 && less(a[index[j]], a[index[j - 1]]); j--) {
        exch(index, j, j - 1);
        }
    }
        return index;
    }

    /********************************************************************
     * Helper sorting functions
     ********************************************************************/

  /**
   * This method checks if Comparable v is smaller than Comparable w.
   * @param v First Comparable parameter
   * @param w Second Comparable parameter
   * @return boolean This method returns a boolean stating if v is
   * smaller than w.
   */
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }
  /**
   * This method checks if Comparable v is smaller than Comparable w.
   * @param c Comparator for Object v and Object w
   * @param v First Object
   * @param w Second Object
   * @return boolean This method returns a boolean stating if v is
   * smaller than w.
   */
    // is v < w ?
    private static boolean less(Comparator c, Object v, Object w) {
        return (c.compare(v, w) < 0);
    }

  /**
   * This method swaps two objects in an array.
   * @param a Object array
   * @param i Index of first object to be swapped
   * @param j Index of second object to be swapped
   */
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

  /**
   * This method swaps two ints in an array.
   * @param a int array
   * @param i First index of int to be swapped
   * @param j Second index of int to be swapped
   */
    // exchange a[i] and a[j] (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /********************************************************************
     * Check if array is sorted - useful for debugging
     ********************************************************************/
  /**
   * This method checks if the given array is sorted.
   * @param a Comparable array
   * @return boolean This method returns a boolean that states if the
   * array is sorted.
   */
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

  /**
   * This method returns if the given Comparable array is sorted.
   * @param a Comparable array
   * @param lo lowest index
   * @param hi highest index
   * @return boolean This method returns a boolean stating if the array
   * is sorted.
   */
    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      if (less(a[i], a[i - 1])) {
        return false;
      }
      }
        return true;
    }

  /**
   * This method determines if an Object array is sorted.
   * @param a Object array
   * @param c Comparator to be used when comparing
   * @return boolean This method returns a boolean stating if the array
   * is sorted.
   */
    private static boolean isSorted(Object[] a, Comparator c) {
        return isSorted(a, c, 0, a.length - 1);
    }

  /**
   * This method determines if the given array is sorted from a[lo] to
   * a[hi].
   * @param a Object array
   * @param c Comparator for comparisons
   * @param lo lowest index
   * @param hi highest index
   * @return boolean This method returns a boolean to state if the
   * array is sorted.
   */
    // is the array sorted from a[lo] to a[hi]
    private static boolean isSorted(Object[] a, Comparator c,
                            int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++) {
      if (less(c, a[i], a[i - 1])) {
        return false;
      }
    }
        return true;
    }

  /**
   * This method prints out the array to standard output.
   * @param a Comparable array
   */
  // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input;
     * insertion sorts them;
     * and prints them to standard output in ascending order.
     * @param args String arguments to be Insertion sorted
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        show(a);
    }
}
