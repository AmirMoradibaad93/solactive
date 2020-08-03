package com.solactive.solactive;

public class Tick {
    public Tick(String instrument, double price, long timestamp) {
        if(instrument == null) {
        	instrument = " ";
        }
        this.instrument = instrument;
        this.price = price;
        this.timestamp = timestamp;
    }

    private final String instrument;
    private final Double price;
    private final long timestamp;

    public long GetTimestamp() {
        return timestamp;
    }

    public Double GetPrice() {
        return price;
    }

    public String GetInstrument() {
        return instrument;
    }
}
