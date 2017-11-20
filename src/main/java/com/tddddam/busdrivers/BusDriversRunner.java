package com.tddddam.busdrivers;

import java.util.*;

public class BusDriversRunner<S, G> {

    final private Set<BusDriver<S, G>> driversSet;
    final private Map<S, Set<BusDriver<S, G>>> driversAtStopsMap;
    private int times = 1;

    public BusDriversRunner(){
        driversAtStopsMap = new HashMap<>();
        driversSet = new HashSet<>();
    }

    public boolean addBusDriver(BusDriver<S, G> driver){
        syncDriverWithPool(driver);
        return driversSet.add(driver);
    }

    public boolean removeBusDriver(BusDriver<S, G> driver) {
        removeDriverFromPool(driver);
        return driversSet.remove(driver);
    }

    public Set<BusDriver<S, G>> getDriversAtStop(S stop){
        if (driversAtStopsMap.containsKey(stop)){
            return driversAtStopsMap.get(stop);
        }
        return null;
    }

    public void drive() {
        for (int i=0;i<times;i++){
            driversAtStopsMap.clear();
            Iterator<BusDriver<S, G>> iterator = driversSet.iterator();
            while (iterator.hasNext()){
                BusDriver<S, G> busDriver = iterator.next();
                if (busDriver.drive()) {
                    syncDriverWithPool(busDriver);
                }
            }
        }
    }

    public void drive(int n){
        times = n;
        drive();
    }

    public Map<S, Set<BusDriver<S, G>>> getDriversAtStopsMap() { return driversAtStopsMap; }
    public Set<BusDriver<S, G>> getDriversSet() { return driversSet; }

    private void syncDriverWithPool(BusDriver<S, G> driver){
        Set<BusDriver<S, G>> driversAtStopSet = getDriversAtStop(driver.getCurrentStop());
        if (driversAtStopSet == null) {
            driversAtStopSet = new HashSet<>();
            driversAtStopsMap.put(driver.getCurrentStop(), driversAtStopSet);
        }
        for (BusDriver<S, G> aDriversAtStopSet : driversAtStopSet) {
            driver.exchangeGossips(aDriversAtStopSet);
        }
        driversAtStopSet.add(driver);
    }

    private void removeDriverFromPool(BusDriver<S, G> driver){
        Set<BusDriver<S, G>> stopList = getDriversAtStop(driver.getCurrentStop());
        if (stopList != null) {
            stopList.remove(driver);
        }
    }
}
