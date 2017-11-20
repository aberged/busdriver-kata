package com.tddddam.busdrivers.reporters;

import com.tddddam.busdrivers.BusDriver;
import com.tddddam.busdrivers.BusDriversRunner;
import com.tddddam.busdrivers.BusDriversRunnerReport;
import java.util.Iterator;

public class SimpleBusDriversRunnerReport<S, G> extends BusDriversRunnerReport<S, G> {

    public SimpleBusDriversRunnerReport(BusDriversRunner<S, G> busDriversRunner) {
        super(busDriversRunner);
    }

    public Boolean getReport() {
        return isGossipsExchangeComplete();
    }

    private boolean isGossipsExchangeComplete(){
        if (runner.getDriversSet().isEmpty()) return false;
        if (runner.getDriversSet().size() == 1) return true;
        Iterator<BusDriver<S, G>> iterator = runner.getDriversSet().iterator();
        BusDriver<S, G> aDriver = iterator.next();
        while (iterator.hasNext()){
            if (!aDriver.getGossips().equals(iterator.next().getGossips()))
                return false;
        }
        return true;
    }

}
