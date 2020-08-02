package com.solactive.solactive;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Statistic {
    public static Statistic CalculateStatistic(List<Tick> ticks) {
        Statistic statistic = new Statistic();
        statistic.count = CalculateCount(ticks);
        statistic.avg = CalculateAvg(ticks);
        statistic.max = CalculateMax(ticks);
        statistic.min = CalculateMin(ticks);
        statistic.maxDrawdown = CalculateMaxDrawdown(ticks);
        statistic.volatility = CalculateVolatility(ticks);
        statistic.quantile = CalculateQuantile(ticks);
        statistic.twap = CalculateTwap(ticks);
        statistic.twap2 = CalculateTwap2(ticks);
        return statistic;
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

    private static long CalculateCount(List<Tick> ticks) {
        return ticks.size();
    }

    private static double CalculateTwap2(List<Tick> ticks) {
    	int sum = 0;
    	long now = System.currentTimeMillis();
    	for(int i = 1; i < ticks.size() ; i++) {
    		Tick tick = ticks.get(i);
    		if(tick.GetTimestamp()<now)
    			sum+= Math.exp(now - tick.GetTimestamp()) * tick.GetPrice();
    	}
    	
        return sum;
    }

    private static double CalculateTwap(List<Tick> ticks) {
    	int sum = 0;
    	if(CalculateCount(ticks) != 0) {
	    	Tick firstTick = ticks.get(0);
	    	Tick secondTick = ticks.get(0);
	    	for(int i = 1; i < ticks.size() && firstTick != null; i++) {
	    		secondTick = ticks.get(i);
	    		sum+= (secondTick.GetTimestamp() - firstTick.GetTimestamp()) * firstTick.GetPrice();
	    		firstTick = secondTick;
	    	}
	    	if(ticks.size()>0 && secondTick != null)
	    		sum += (ticks.get(0).GetTimestamp() + 600000 - secondTick.GetPrice())* secondTick.GetPrice();
    	}
        return sum;
    }

    private static double CalculateQuantile(List<Tick> ticks) {
    	var count = CalculateCount(ticks);
    	double price = 0;
    	int quantile = 0;
    	
    	if(count != 0) {
    		quantile = (int) Math.round(CalculateCount(ticks)*0.05) + 1;
    		price = ticks.get(quantile).GetPrice();
    	}    	
        return price;
    }

    private static double CalculateVolatility(List<Tick> ticks) {
    	Set<String> tickInstruments = ticks.stream().map(t -> t.GetInstrument()).collect(Collectors.toSet());
        double volatility = 0;
        
        for(String s:tickInstruments) {
        	double avg= ticks.stream().filter(t -> t.GetInstrument() == s)
        			.mapToDouble(Tick::GetPrice).sum(); 
        	double deviationSquared = ticks.stream().filter(t -> t.GetInstrument() == s)
        			.mapToDouble(Tick::GetPrice).map(t -> (t-avg)*(t-avg)).sum();
        	
        	volatility += deviationSquared/CalculateCount(ticks.stream().filter(t -> t.GetInstrument() == s).collect(Collectors.toList()));
        }
        
    	return volatility;
    }

    private static double CalculateMaxDrawdown(List<Tick> ticks) {
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

    private static double CalculateMin(List<Tick> ticks) {
        return CalculateCount(ticks) == 0 ? 0 : Collections.min(ticks, getTickComparingByPrice()).GetPrice();
    }

    private static double CalculateMax(List<Tick> ticks) {
        return CalculateCount(ticks) == 0 ? 0 : Collections.max(ticks, getTickComparingByPrice()).GetPrice();
    }

    private static Comparator<Tick> getTickComparingByPrice() {
        return Comparator.comparing(Tick::GetPrice);
    }

    private static double CalculateAvg(List<Tick> ticks) {
        var count = CalculateCount(ticks);
        return count == 0 ? 0 : CalculateSum(ticks) / count;
    }

    private static double CalculateSum(List<Tick> ticks) {
        return ticks.stream().mapToDouble(Tick::GetPrice).sum();
    }
}
