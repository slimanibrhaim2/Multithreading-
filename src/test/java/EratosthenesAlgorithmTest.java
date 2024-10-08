import junit.framework.TestCase;
import org.example.Algorithms.EratosthenesAlgorithm;
import org.example.Range;
import java.util.List;
import java.util.Arrays;


public class EratosthenesAlgorithmTest extends TestCase {

    //Testing the primes that the algorithm return if they are correct in small range
    public void testPrimesInRange() {
        EratosthenesAlgorithm eratosthenesAlgorithm = new EratosthenesAlgorithm();
        Range range = new Range(0, 25);
        List<Integer> result = eratosthenesAlgorithm.findPrimes(range);
        List<Integer> expected = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23);
        assertEquals(expected, result);
    }

    //Testing the algorithm in a range that contain no primes numbers
    public void testNoPrimesInRange() {
        EratosthenesAlgorithm eratosthenesAlgorithm = new EratosthenesAlgorithm();
        Range range = new Range(0, 1);
        List<Integer> result = eratosthenesAlgorithm.findPrimes(range);
        assertTrue(result.isEmpty());
    }

    //Testing the primes that the algorithm return if they are correct in larger range
    public void testPrimesInLargerRange() {
        EratosthenesAlgorithm eratosthenesAlgorithm = new EratosthenesAlgorithm();
        Range range = new Range(90, 100);
        List<Integer> result = eratosthenesAlgorithm.findPrimes(range);
        List<Integer> expected = Arrays.asList(97);
        assertEquals(expected, result);
    }

    //Testing the number of primes in range [0 - 10^4]
    public void testNumberOfPrimesInRange() {
        EratosthenesAlgorithm eratosthenesAlgorithm = new EratosthenesAlgorithm();
        Range range = new Range(0, 10000);
        List<Integer> result = eratosthenesAlgorithm.findPrimes(range);
        //i get this value from the Google [ the number of primes in the [0 - 10000] is 1229 ]
        int correctAnswer = 1229;
        assertEquals(correctAnswer, result.size());
    }

    //Testing the number of primes in range [0 - 10^6]
    public void testNumberOfPrimesInLargerRange() {
        EratosthenesAlgorithm eratosthenesAlgorithm = new EratosthenesAlgorithm();
        Range range = new Range(0, 1000000);
        List<Integer> result = eratosthenesAlgorithm.findPrimes(range);
        //i get this value from the Google [ the number of primes in the [0 - 10^6] is 78498  ]
        int correctAnswer = 78498 ;
        assertEquals(correctAnswer, result.size());
    }
}


