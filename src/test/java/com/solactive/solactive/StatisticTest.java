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
        assertThat(statistic).isEqualTo(expectedStatistic);
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
        expectedStatistic.avg = 3;
        expectedStatistic.max = 3;
        expectedStatistic.min = 3;
        expectedStatistic.quantile = 3;
        expectedStatistic.twap = 3;
        expectedStatistic.twap2 = 3;
        expectedStatistic.volatility = 3;
        return expectedStatistic;
    }

    public static Statistic ExpectedStatisticByInstrument() {
        Statistic expectedStatistic = new Statistic();
        expectedStatistic.count = 3;
        expectedStatistic.avg = 3;
        expectedStatistic.max = 3;
        expectedStatistic.min = 3;
        expectedStatistic.quantile = 3;
        expectedStatistic.twap = 3;
        expectedStatistic.twap2 = 3;
        expectedStatistic.volatility = 3;
        return expectedStatistic;
    }
}
