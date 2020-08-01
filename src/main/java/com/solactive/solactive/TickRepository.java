package com.solactive.solactive;

public interface TickRepository {
    void Add(Tick tick);

    Statistic GetLastSixtySecondStatistics();

    Statistic GetLastSixtySecondStatisticsByInstrument(String instrument);
}
