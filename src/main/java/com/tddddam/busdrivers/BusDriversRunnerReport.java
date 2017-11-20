package com.tddddam.busdrivers;

public abstract class BusDriversRunnerReport<S, G> {
    protected BusDriversRunner<S, G> runner;

    private BusDriversRunnerReport() {}

    public BusDriversRunnerReport(BusDriversRunner<S, G> runner){
        this.runner = runner;
    }

    abstract public<T> T getReport();
}
