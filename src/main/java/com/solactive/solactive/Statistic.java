package com.solactive.solactive;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        return 0;
    }

    private double CalculateVolatility() {
        return 0;
    }

    private double CalculateMaxDrawdown() {
        return 0;
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
