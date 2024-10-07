package org.example;

public class Range {

    public Range (int low, int high){
        this.low= low;
        this.high= high;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public void setLow(int low) {
        this.low = low;
    }

    private  int low, high;

}
