import java.util.List;
import java.util.stream.Collectors;

public class Example {

    /**
     * Multiplies all elements of a list of integers by some other integer and returns the result.
     * 
     * @param numbers the list of numbers
     * @param multiplier the scalar to multiply by
     * @return a new list
     */
    public static List<Integer> multiplyAll(List<Integer> numbers, int multiplier) {
        if (numbers.isEmpty()) {
            return numbers;
        }
        return numbers.stream().map(num -> num * multiplier).collect(Collectors.toList());
    }

    /**
     * Divides all elements of a list of integers by some other integer and returns the result.
     * 
     * @param numbers the list of numbers
     * @param divider the scalar to divide by
     * @return a new list
     */
    public static List<Integer> divideAll(List<Integer> numbers, int divider) {
        if (numbers.isEmpty()) {
            return numbers;
        }
        try {
            return numbers.stream().map(num -> num / divider).collect(Collectors.toList());
        } catch (ArithmeticException e) {
            return numbers.stream().filter(num -> false).collect(Collectors.toList());
        }
    }

}
