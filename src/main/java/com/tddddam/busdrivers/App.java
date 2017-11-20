package com.tddddam.busdrivers;

import com.tddddam.busdrivers.reporters.SimpleBusDriversRunnerReport;

import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        BusDriversRunner<Integer, Integer> runner = new BusDriversRunner<>();
        SimpleBusDriversRunnerReport<Integer, Integer> reporter = new SimpleBusDriversRunnerReport<>(runner);

        Integer[][] s = new Integer[3][];
        s[0] = new Integer[]{3,1,2,3};//{2,1,2};
        s[1] = new Integer[]{3,2,3,1};//{5,2,8};
        s[2] = new Integer[]{4,2,3,4,5};

        for (int i=0;i<s.length;i++) {
            List<Integer> stops = new ArrayList<>(Arrays.asList(s[i]));
            Set<Integer> gossips = new HashSet<>();
            Collections.addAll(gossips, i);
            runner.addBusDriver(new BusDriver<>(stops, gossips));
        }

        int count = 1;
        while (!reporter.getReport() && count < 481){
            runner.drive();
            count++;
        }

        System.out.println(count == 481? "Never" : count);

    }
}
