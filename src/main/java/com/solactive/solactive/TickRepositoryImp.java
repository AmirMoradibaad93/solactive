package com.solactive.solactive;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class TickRepositoryImp implements TickRepository {
    private final List<Tick> Ticks = new ArrayList<>();

    @Override
    public void Add(Tick tick) {
        Ticks.add(tick);
    }

    @Async
    @Override
    public CompletableFuture<Statistic> GetLastSixtySecondStatistics() {
        List<Tick> ticksList = GetLastSixtySecondTicks();
        return CompletableFuture.completedFuture(Statistic.CalculateStatistic(ticksList));
    }

    @Async
    @Override
    public CompletableFuture<Statistic> GetLastSixtySecondStatisticsByInstrument(String instrument) {
        List<Tick> ticksList = GetLastSixtySecondTicksByInstrument(instrument);
        return CompletableFuture.completedFuture(Statistic.CalculateStatistic(ticksList));
    }

    private List<Tick> GetLastSixtySecondTicksByInstrument(String instrument) {
        long timestamp = System.currentTimeMillis();
        return Ticks.stream().filter(tick -> timestamp - 600000 <= tick.GetTimestamp() && tick.GetTimestamp() < timestamp && tick.GetInstrument().equals(instrument)).collect(Collectors.toList());
    }

    private List<Tick> GetLastSixtySecondTicks() {
        long timestamp = System.currentTimeMillis();
        return Ticks.stream().filter(tick -> timestamp - 600000 <= tick.GetTimestamp() && tick.GetTimestamp() < timestamp).collect(Collectors.toList());
    }
}
