package com.solactive.solactive;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CompletableFuture;

@RestController
public class TickController {
    private final TickRepository tickRepository;

    public TickController(TickRepository tickRepository) {
        this.tickRepository = tickRepository;
    }

    @RequestMapping(value = "/ticks", method = RequestMethod.POST)
    public void Post(@RequestBody TickRequest request, HttpServletResponse response) {
        long now = System.currentTimeMillis();
        if (now - request.timestamp > 600000 
        		|| request.price < 0 
        		|| request.instrument == null) {
        	
            tickRepository.Add(new Tick(request.instrument, request.price, request.timestamp));
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            
        } else {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
    }

    @Async
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public CompletableFuture<Statistic> GetLastSixtySecondStatistics() {
        return tickRepository.GetLastSixtySecondStatistics();
    }

    @Async
    @RequestMapping(value = "/statistics/{instrument_identifier}", method = RequestMethod.GET)
    public CompletableFuture<Statistic> GetLastSixtySecondStatisticsByTimestamp(@PathVariable("instrument_identifier") String instrument) {
        return tickRepository.GetLastSixtySecondStatisticsByInstrument(instrument);
    }
}
