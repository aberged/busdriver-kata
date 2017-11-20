package com.tddddam.busdrivers;

import com.tddddam.busdrivers.reporters.SimpleBusDriversRunnerReport;
import com.tddddam.busdrivers.stops.DummyStop;
import sun.net.www.http.HttpCaptureInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import static java.lang.System.out;

public class App 
{
    public static void main( String[] args )
    {

        BusDriversRunner<DummyStop, String> pool = new BusDriversRunner<>();

        int numOfStops = 2000;
        int numOfBuses = 1000;

        Date start = new Date();
        for (int i=0;i<numOfBuses;i++){
            HashSet<String> gossips = new HashSet<>();
            gossips.add("trach"+Integer.toString(i));
            int randomNumOfStops = (int) Math.ceil(Math.random()*(numOfStops));
            LinkedList<DummyStop> stops = new LinkedList<>(/*randomNumOfStops > 0? randomNumOfStops : 1*/);
            for (int j = 0; j < randomNumOfStops; j++){
                stops.add(new DummyStop(
                            Integer.toString(
                                (int) Math.ceil(Math.random()*numOfStops)
                            )
                        )
                );
            }
            pool.addBusDriver(new BusDriver<>(stops, gossips));
        }
        long setupDuration = new Date().getTime() - start.getTime();

        pool.drive(1);

        long driveDuration = new Date().getTime() - start.getTime();
        start = new Date();

        out.println(new SimpleBusDriversRunnerReport<>(pool).getReport());

        long reportDuration = new Date().getTime() - start.getTime();
        out.println("Setup took: "+Long.toString(setupDuration)+"ms");
        out.println("Driving took: "+Long.toString(driveDuration)+"ms");
        out.println("Reporting took: "+Long.toString(reportDuration)+"ms");
    }
}
