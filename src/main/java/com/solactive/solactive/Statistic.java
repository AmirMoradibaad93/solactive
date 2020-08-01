package com.solactive.solactive;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;


public class Statistic {
    private final List<Tick> ticks;

    public Statistic(List<Tick> ticks) {
        this.ticks = ticks;
        count = CalculateCount();
        avg = CalculateAvg();
        max = CalculateMax();
        min = CalculateMin();
        maxDrawdown = CalculateMaxDrawdown();
        volatility = CalculateVolatility();
        quantile = CalculateQuantite();
        twap = CalculateTwap();
        twap2 = CalculateTwap2();
    }

    public double avg;
    public double max;
    public double min;
    public double maxDrawdown;
    public double volatility;
    public double quantile;
    public double twap;
    public double twap2;
    public long count;

    private long CalculateCount() {
        return ticks.size();
    }

    private double CalculateTwap2() {
        return 0;
    }

    private double CalculateTwap() {
        return 0;
    }

    private double CalculateQuantite() {
    	double quantile = (CalculateMax()-CalculateMin())/20 + CalculateMin();   	
        return quantile;
    }

    private double CalculateVolatility() {
    	Set<String> tickInstruments = ticks.stream().map(t -> t.GetInstrument()).collect(Collectors.toSet());
    	
        return 0;
    }

    private double CalculateMaxDrawdown() {
        Set<String> tickInstruments = ticks.stream().map(t -> t.GetInstrument()).collect(Collectors.toSet());
        double maxDrawDown = 0;
        
        for(String s:tickInstruments) {
        	List<Double> prices= ticks.stream().filter(t -> t.GetInstrument() == s).map(t -> t.GetPrice())
        			.collect(Collectors.toList()); 
        	double minPrice = Collections.min(prices);
        	double maxPrice = Collections.max(prices);
        	if(maxPrice-minPrice > maxDrawDown) maxDrawDown = maxPrice-minPrice;
        }
        
    	return maxDrawDown;
    }

    private double CalculateMin() {
        return Collections.min(ticks, getTickComparingByPrice()).GetPrice();
    }

    private double CalculateMax() {
        return Collections.max(ticks, getTickComparingByPrice()).GetPrice();
    }

    private Comparator<Tick> getTickComparingByPrice() {
        return Comparator.comparing(Tick::GetPrice);
    }

    private double CalculateAvg() {
        return CalculateSum() / CalculateCount();
    }

    private double CalculateSum() {
        return ticks.stream().mapToDouble(Tick::GetPrice).sum();
    }
}
