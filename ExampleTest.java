import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExampleTest {

    // Set up some lists... what would be good for equivalence partitioning?
    private static List<Integer> list1;
    private static List<Integer> list2;

    @BeforeClass
    public static void init() {
        // Initialize those lists
        list1 = new ArrayList<>();
        list2 = Arrays.asList(new Integer[] {-1, -2, 0, 1, 2});
    }

    @Test
    public void test() {
        assertTrue(true);
    }

    // Make example tests...
    @Test
    public void multiplyNeg() {
        assertEquals(Example.multiplyAll(list1, -2), list1);
        assertEquals(Example.multiplyAll(list2, -2),
                Arrays.asList(new Integer[] {2, 4, 0, -2, -4}));
    }

    @Test
    public void multiplyPos() {
        assertEquals(Example.multiplyAll(list1, 2), list1);
        assertEquals(Example.multiplyAll(list2, 2), Arrays.asList(new Integer[] {-2, -4, 0, 2, 4}));
    }

    @Test
    public void multiplyZero() {
        assertEquals(Example.multiplyAll(list1, 0), list1);
        assertEquals(Example.multiplyAll(list2, 0), Arrays.asList(new Integer[] {0, 0, 0, 0, 0}));
    }

    @Test
    public void divideNeg() {
        assertEquals(Example.divideAll(list1, -2), list1);
        assertEquals(Example.divideAll(list2, -2), Arrays.asList(new Integer[] {0, 1, 0, 0, -1}));
    }

    @Test
    public void dividePos() {
        assertEquals(Example.divideAll(list1, 2), list1);
        assertEquals(Example.divideAll(list2, 2), Arrays.asList(new Integer[] {0, -1, 0, 0, 1}));
    }

    @Test
    public void divideZero() {
        assertEquals(Example.divideAll(list1, 0), list1);
        assertTrue(Example.divideAll(list2, 0).isEmpty());
    }

}
