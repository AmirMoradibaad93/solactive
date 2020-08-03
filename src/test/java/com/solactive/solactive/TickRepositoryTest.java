package com.solactive.solactive;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TickRepositoryTest {
    @Mock
    TickRepositoryImp tickRepositoryImp;

    @Test
    public void GetLastSixtySecondStatistics() throws ExecutionException, InterruptedException {
        for (Tick tick : StatisticTest.MockTick()) {
            tickRepositoryImp.Add(tick);
        }
        when(tickRepositoryImp.GetLastSixtySecondStatistics())
                .thenReturn(CompletableFuture.completedFuture(StatisticTest.ExpectedStatistic()));
        var getLastSixtySecondStatistics = tickRepositoryImp.GetLastSixtySecondStatistics();
        assertThat(getLastSixtySecondStatistics.get()).isEqualToComparingFieldByField(StatisticTest.ExpectedStatistic());
    }

    @Test
    public void GetLastSixtySecondStatisticsByInstrument() throws ExecutionException, InterruptedException {
        for (Tick tick : StatisticTest.MockTick()) {
            tickRepositoryImp.Add(tick);
        }
        when(tickRepositoryImp.GetLastSixtySecondStatisticsByInstrument("test1"))
                .thenReturn(CompletableFuture.completedFuture(StatisticTest.ExpectedStatisticByInstrument()));
        var getLastSixtySecondStatisticsByInstrument = tickRepositoryImp.GetLastSixtySecondStatisticsByInstrument("test1");
        assertThat(getLastSixtySecondStatisticsByInstrument.get()).isEqualToComparingFieldByField(StatisticTest.ExpectedStatisticByInstrument());
    }
}