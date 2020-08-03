package com.solactive.solactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class StatisticTest {
    @Test
    public void CalculateStatistic() {
        Statistic statistic = Statistic.CalculateStatistic(MockTick());
        Statistic expectedStatistic = ExpectedStatistic();
        assertThat(statistic).isEqualToComparingOnlyGivenFields(expectedStatistic,
                "avg", "max", "min", "maxDrawdown", "volatility", "quantile", "twap", "count");
    }

    @Test
    public void CalculateStatisticIfInstrumentIsNullOrEmpty() {
        var ticks = MockTick();
        ticks.add(new Tick(null, 2.02, System.currentTimeMillis()));
        ticks.add(new Tick("", 53.302, System.currentTimeMillis()));
        Statistic.CalculateStatistic(ticks);
    }

    @Test
    public void CalculateStatisticIfPriceIsNullOrZero() {
        // price can't be null
        var ticks = MockTick();
        ticks.add(new Tick("test3", 0, System.currentTimeMillis()));
        Statistic.CalculateStatistic(ticks);
    }

    public static List<Tick> MockTick() {
        List<Tick> ticks = new ArrayList<>();
        ticks.add(new Tick("test1", 2.01, System.currentTimeMillis()));
        ticks.add(new Tick("test1", 0.014, System.currentTimeMillis()));
        ticks.add(new Tick("test2", 32.8, System.currentTimeMillis()));
        return ticks;
    }

    public static Statistic ExpectedStatistic() {
        Statistic expectedStatistic = new Statistic();
        expectedStatistic.count = 3;
        expectedStatistic.avg = 11.607999999999999;
        expectedStatistic.max = 32.8;
        expectedStatistic.min = 0.014;
        expectedStatistic.quantile = 0.014;
        expectedStatistic.twap = 2.147483647E9;
        expectedStatistic.twap2 = 265894.0;
        expectedStatistic.volatility = 2.0201479999999994;
        expectedStatistic.maxDrawdown = 1.9959999999999998;
        return expectedStatistic;
    }

    public static Statistic ExpectedStatisticByInstrument() {
        Statistic expectedStatistic = new Statistic();
        expectedStatistic.count = 2;
        expectedStatistic.avg = 1.0119999999999998;
        expectedStatistic.max = 2.01;
        expectedStatistic.min = 0.014;
        expectedStatistic.quantile = 3;
        expectedStatistic.twap = 2.147483647E9;
        expectedStatistic.twap2 = 2.147483647E9;
        expectedStatistic.volatility = 0.0;
        expectedStatistic.maxDrawdown = 0.0;
        return expectedStatistic;
    }
}