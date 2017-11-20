package com.tddddam.busdrivers;

import java.util.*;

public class BusDriver<S, G> {

    final private List<S> stops;
    final private Set<G> gossips;
    private int positionIndex;

    public BusDriver() {
        stops = new LinkedList<>();
        positionIndex = 0;
        gossips = new HashSet<>();
    }

    public BusDriver(List<S> stops) {
        this();
        if (stops!=null)
            this.stops.addAll(stops);
    }

    public BusDriver(List<S> stops, Set<G> gossips) {
        this(stops);
        if (gossips!=null)
            this.gossips.addAll(gossips);
    }

    public Set<G> getGossips() {
        return gossips;
    }

    public void addGossips(Set<G> gossips) {
        this.gossips.addAll(gossips);
    }

    public void exchangeGossips(BusDriver<S, G> busDriver) {
        S currentStop = this.getCurrentStop();
        if (currentStop != null){
            if (currentStop.equals(busDriver.getCurrentStop())) {
                this.gossips.addAll(busDriver.getGossips());
                busDriver.addGossips(this.gossips);
            }
        }
    }

    public boolean drive() {
        if (positionIndex < stops.size()-1)
            positionIndex++;
        else
            positionIndex = 0;
        return !stops.isEmpty();
    }

    public int getStopPosition() { return positionIndex; }

    public S getCurrentStop() {
        if (stops.size() == 0) return null;
        return stops.get(positionIndex);
    }
}
