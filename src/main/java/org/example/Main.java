package org.example;
import org.example.Algorithms.SimpleAlgorithm;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(0, 20);
        PrimeAlgorithms algorithm = new SimpleAlgorithm();
        int threadNumber =2;
        List<AlgorithmsCalculater> threads = new ArrayList<>();
        int length = range.getHigh() - range.getLow();
        int partSize= length / threadNumber;

        for ( int i=0 ; i<threadNumber ; i++){
            int start = range.getLow() + i * partSize;
            int end = (i == threadNumber-1 ) ? range.getHigh() : start+partSize;
            Range subRange = new Range(start, end);
            threads.add(new AlgorithmsCalculater(subRange, algorithm));
            threads.getLast().start();
        }
    }
}