package org.example.Algorithms;

import org.example.PrimeAlgorithms;
import org.example.Range;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EratosthenesAlgorithm implements PrimeAlgorithms {

    // Function to find primes in the given range using the segmented sieve algorithm
    public List<Integer> findPrimes(Range range) {
        int limit = (int) Math.sqrt(range.getHigh()) + 1;

        // Find all primes up to sqrt(high) using Sieve of Eratosthenes
        boolean[] prime = new boolean[limit + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= limit; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= limit; i += p) {
                    prime[i] = false;
                }
            }
        }

        // Collect all primes up to sqrt(high)
        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= limit; p++) {
            if (prime[p]) {
                primes.add(p);
            }
        }

        // Use the primes to mark non-prime numbers in the range [low, high]
        boolean[] isPrimeInRange = new boolean[range.getHigh() - range.getLow() + 1];
        Arrays.fill(isPrimeInRange, true);

        for (int p : primes) {
            // Find the minimum number in the [low, high] range that is a multiple of p
            int minMultiple = Math.max(p * p, (range.getLow() + p - 1) / p * p);

            for (int i = minMultiple; i <= range.getHigh(); i += p) {
                isPrimeInRange[i - range.getLow()] = false;
            }
        }

        // Handle special cases for 0 and 1
        if (range.getLow() == 1) {
            isPrimeInRange[0] = false;
        }
        if (range.getLow() == 0) {
            isPrimeInRange[0] = false;
            if (isPrimeInRange.length > 1) {
                isPrimeInRange[1] = false;
            }
        }

        // Collect prime numbers in the specified range
        List<Integer> primeList = new ArrayList<>();
        for (int i = range.getLow(); i <= range.getHigh(); i++) {
            if (isPrimeInRange[i - range.getLow()]) {
                primeList.add(i);
            }
        }

        return primeList;
    }

    @Override
    public void run(Range range) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Integer> primes = findPrimes(range);
        for (int prime : primes) {
            System.out.print(prime + " ");
        }
    }
}
