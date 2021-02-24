import org.junit.*;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class dHeapTester {

    dHeap max2;
    dHeap min2;
    dHeap max3;
    dHeap max4;
    dHeap min4;

    @Before
    public void setUp() {
        max2 = new dHeap();
        int[] max2Ints = {95, 89, 80, 65, 31, 32, 78, 28, 26, 5, 15, 14};
        for (int val : max2Ints) { max2.add(val); }

        min2 = new dHeap(2, 4, false);
        int[] min2Ints = {60, 96, 61, 15};
        for (int val : min2Ints) { min2.add(val); }

        max3 = new dHeap(3, 4, true);
        max4 = new dHeap(4, 3, true);
        int[] maxDaryInts = {91, 89, 74, 81, 82, 61, 54, 72, 48, 29, 31};
        for (int val : maxDaryInts) {
            max3.add(val);
            max4.add(val);
        }

        min4 = new dHeap(4, 12, false);
    }

    @Test
    public void testSize() {
        assertEquals(12, max2.size());
        assertEquals(4, min2.size());
        assertEquals(0, min4.size());
        assertEquals(11, max3.size());
        assertEquals(11, max4.size());
    }

    @Test (expected = NullPointerException.class)
    public void testAddThrowsNPE() { min4.add(null); }

    @Test
    public void testAdd() {
        int[] min2Add = {22, 63, 99, 84};
        for (int val : min2Add) { min2.add(val); }
        assertEquals(8, min2.size());
        int[] min2Add2 = {86, 22, 48, 95};
        for (int val : min2Add2) { min2.add(val); }
        assertEquals(12, min2.size());

        int[] max3Add = {0, -1, -12};
        for (int val : max3Add) { max3.add(val); }
        assertEquals(14, max3.size());

        String[] min4Add = {"String", "str", "st", "s", "S", "test"};
        for (String val : min4Add) { min4.add(val); }
        assertEquals(6, min4.size());
    }

    @Test (expected = NoSuchElementException.class)
    public void testRemoveThrowsNSEE() { min4.remove(); }

    @Test
    public void testRemove() {
        int[] max2Remove = {95, 89, 80, 78, 65, 32};
        for (int removed : max2Remove) { assertEquals(removed, max2.remove()); }
        assertEquals(6, max2.size());
        assertEquals(31, max2.element());

        int[] min2Add = {22, 63, 99, 84, 86, 22};
        for (int val : min2Add) { min2.add(val); }
        int[] min2Remove = {15, 22, 22, 60, 61, 63, 84, 86, 96, 99};
        for (int removed : min2Remove) { assertEquals(removed, min2.remove()); }
        assertEquals(0, min2.size());

        int[] min2Add2 = {22, 63, 99, 84, 86, 22};
        for (int val : min2Add2) { min2.add(val); }
        assertEquals(6, min2.size());
        min2.add(-2);
        assertEquals(-2, min2.remove());

        String[] min4Add = {"String", "str", "st", "s", "S", "test"};
        for (String val : min4Add) { min4.add(val); }
        String[] min4Remove = {"S", "String", "s", "st", "str", "test"};
        for (String removed : min4Remove) { assertEquals(removed, min4.remove()); }

        int[] max4Remove = {91, 89, 82, 81, 74, 72};
        for (int removed : max4Remove) { assertEquals(removed, max4.remove()); }
        assertEquals(5, max4.size());
    }

    @Test
    public void testClear() {
        min2.clear();
        assertEquals(0, min2.size());
        min2.add(0);
        assertEquals(0, min2.element());

        max3.clear();
        assertEquals(0, max3.size());

        min4.add("STRING");
        min4.clear();
        assertEquals(0, min4.size());
        assertEquals(0, min4.size());
    }

    @Test (expected = NoSuchElementException.class)
    public void testElementThrowsNSEE() { min4.element(); }

    @Test
    public void testElement() {
        assertEquals(95, max2.element());
        assertEquals(95, max2.element());
        assertEquals(15, min2.element());

        int[] max3Element = {89, 82, 81, 74, 72, 61, 54, 48, 31, 29};
        for (int element : max3Element) {
            max3.remove();
            assertEquals(element, max3.element());
        }
        assertEquals(29, max3.remove());
        assertEquals(0, max3.size());
    }
}