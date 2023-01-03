package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> arr1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", arr1.isEmpty());
        arr1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arr1.size());
        assertFalse("lld1 should now contain 1 item", arr1.isEmpty());

        arr1.addLast("middle");
        assertEquals(2, arr1.size());

        arr1.addLast("back");
        assertEquals(3, arr1.size());

        System.out.println("Printing out deque: ");
        arr1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arr1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", arr1.isEmpty());

        arr1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", arr1.isEmpty());

        arr1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", arr1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> arr1 = new ArrayDeque<Integer>();
        arr1.addFirst(3);

        arr1.removeLast();
        arr1.removeFirst();
        arr1.removeLast();
        arr1.removeFirst();

        int size = arr1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String> arr1 = new LinkedListDeque<String>();
        LinkedListDeque<Double> arr2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> arr3 = new LinkedListDeque<Boolean>();

        arr1.addFirst("string");
        arr2.addFirst(3.14159);
        arr3.addFirst(true);

        String s = arr1.removeFirst();
        double d = arr2.removeFirst();
        boolean b = arr3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> add1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, add1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, add1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }


    }
}
