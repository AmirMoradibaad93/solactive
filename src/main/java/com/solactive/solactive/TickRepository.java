package com.solactive.solactive;

import java.util.concurrent.CompletableFuture;

public interface TickRepository {
    void Add(Tick tick);

    CompletableFuture<Statistic> GetLastSixtySecondStatistics();

    CompletableFuture<Statistic> GetLastSixtySecondStatisticsByInstrument(String instrument);
}
