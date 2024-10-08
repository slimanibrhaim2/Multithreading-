
package org.example.Algorithms;
import org.example.PrimeAlgorithms;
import org.example.Range;
import java.util.List;
import java.util.ArrayList;

public class SimpleAlgorithm implements PrimeAlgorithms {

    public List<Integer> findPrimes(Range range) {
        List<Integer> primes = new ArrayList<>();
        for (int i = range.getLow(); i <= range.getHigh(); i++) {
            int sqrt = (int) Math.sqrt(i);
            int counter = 0;
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0) counter++;
            }
            if (i > 1 && counter == 0) {
                primes.add(i);
            }
        }
        return primes;
    }

    @Override
    public void run(Range range) {
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        List<Integer> primes = findPrimes(range);
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
    }
}
