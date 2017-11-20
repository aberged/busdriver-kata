package com.tddddam.busdrivers.reporters;

import com.tddddam.busdrivers.BusDriver;
import com.tddddam.busdrivers.BusDriversRunner;
import com.tddddam.busdrivers.BusDriversRunnerReport;

import java.util.Set;

public class SimpleBusDriversRunnerReport<S, G> extends BusDriversRunnerReport {

    public SimpleBusDriversRunnerReport(BusDriversRunner<S, G> busDriversRunner) {
        super(busDriversRunner);
    }

    private int totalDrivers = 0;

    public String getReport() {
        StringBuilder result = new StringBuilder();
        Set<BusDriver<S, G>> drivers = runner.getDriversSet();
        this.runner.getDriversAtStopsMap().forEach((s, g) -> {
            int size = ((Set<BusDriver<S, G>>) g).size();
            totalDrivers += size;
            result.append(s).append(" -> ").append(size).append("\n");
        });
        result.append("Total drivers: ");
        result.append(totalDrivers);
        return result.toString();
    }
}
