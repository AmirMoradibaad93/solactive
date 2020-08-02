package com.solactive.solactive;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Statistic {
    public static Statistic CalculateStatistic(List<Tick> ticks) {
        Statistic statistic = new Statistic();
        statistic.count = CalculateCount(ticks);
        statistic.avg = CalculateAvg(ticks);
        statistic.max = CalculateMax(ticks);
        statistic.min = CalculateMin(ticks);
        statistic.maxDrawdown = CalculateMaxDrawdown(ticks);
        statistic.volatility = CalculateVolatility(ticks);
        statistic.quantile = CalculateQuantite(ticks);
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
        return 0;
    }

    private static double CalculateTwap(List<Tick> ticks) {
        return 0;
    }

    private static double CalculateQuantite(List<Tick> ticks) {
        return 0;
    }

    private static double CalculateVolatility(List<Tick> ticks) {
        return 0;
    }

    private static double CalculateMaxDrawdown(List<Tick> ticks) {
        return 0;
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
