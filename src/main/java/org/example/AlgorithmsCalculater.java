package org.example;

public class AlgorithmsCalculater extends Thread {
    public AlgorithmsCalculater(Range range, PrimeAlgorithms algorithm){
        this.range = range;
        this.algorithm=algorithm;
    }
    @Override
    public void run() {
        algorithm.run(range);
    }

    private Range range;
    private PrimeAlgorithms algorithm;
}
