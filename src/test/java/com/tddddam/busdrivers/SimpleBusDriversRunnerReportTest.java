package com.tddddam.busdrivers;

import com.tddddam.busdrivers.reporters.SimpleBusDriversRunnerReport;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleBusDriversRunnerReportTest {

    private BusDriversRunner<Integer, Integer> runner;
    private BusDriversRunnerReport<Integer, Integer> reporter;

    @Before
    public void setUp(){
        runner = new BusDriversRunner<>();
        reporter = new SimpleBusDriversRunnerReport<>(runner);
        
        Integer[][] s = new Integer[3][];
        s[0] = new Integer[]{3,1,2,3};
        s[1] = new Integer[]{3,2,3,1};
        s[2] = new Integer[]{4,2,3,4,5};

        for (int i=0;i<s.length;i++) {
            List<Integer> stops = new ArrayList<>(Arrays.asList(s[i]));
            Collections.addAll(stops, 3, 1, 2, 3);
            Set<Integer> gossips = new HashSet<>();
            Collections.addAll(gossips, i);
            runner.addBusDriver(new BusDriver<>(stops, gossips));
        }
    }

    @Test
    public void cant_be_instantiated_without_BusDriversRunner_injected(){
        new SimpleBusDriversRunnerReport(new BusDriversRunner());
    }

    @Test
    public void should_return_false_for_empty_pool(){
        assertFalse(new SimpleBusDriversRunnerReport(new BusDriversRunner()).getReport());
    }

    @Test
    public void should_return_true_for_one_driver_in_the_pool(){
        BusDriver<Integer, Integer> driver = new BusDriver<>();
        BusDriversRunner<Integer, Integer> runner = new BusDriversRunner<>();
        runner.addBusDriver(driver);
        SimpleBusDriversRunnerReport<Integer, Integer> reporter = new SimpleBusDriversRunnerReport<>(runner);
        assertTrue(reporter.getReport());
    }

    @Test
    public void should_return_false_if_drivers_dont_have_common_stops(){
        ArrayList<Integer> stops1 = new ArrayList<>();
        ArrayList<Integer> stops2 = new ArrayList<>();

        Collections.addAll(stops1, 1);
        Collections.addAll(stops2, 2);

        HashSet<Integer> gossips1 = new HashSet<>();
        gossips1.add(1);
        HashSet<Integer> gossips2 = new HashSet<>();
        gossips2.add(2);

        BusDriver<Integer, Integer> driver1 = new BusDriver<>(stops1, gossips1);
        BusDriver<Integer, Integer> driver2 = new BusDriver<>(stops2, gossips2);

        BusDriversRunner<Integer, Integer> runner = new BusDriversRunner<>();
        runner.addBusDriver(driver1);
        runner.addBusDriver(driver2);

        SimpleBusDriversRunnerReport<Integer, Integer> reporter = new SimpleBusDriversRunnerReport<>(runner);

        assertFalse(reporter.getReport());
        runner.drive(10);
        assertFalse(reporter.getReport());
    }

    @Test
    public void should_return_true_if_drivers_meet_on_the_same_stop(){
        ArrayList<Integer> stops1 = new ArrayList<>();
        ArrayList<Integer> stops2 = new ArrayList<>();

        Collections.addAll(stops1, 1,3);
        Collections.addAll(stops2, 2,3);

        HashSet<Integer> gossips1 = new HashSet<>();
        gossips1.add(1);
        HashSet<Integer> gossips2 = new HashSet<>();
        gossips2.add(2);

        BusDriver<Integer, Integer> driver1 = new BusDriver<>(stops1, gossips1);
        BusDriver<Integer, Integer> driver2 = new BusDriver<>(stops2, gossips2);

        BusDriversRunner<Integer, Integer> runner = new BusDriversRunner<>();
        runner.addBusDriver(driver1);
        runner.addBusDriver(driver2);

        SimpleBusDriversRunnerReport<Integer, Integer> reporter = new SimpleBusDriversRunnerReport<>(runner);

        assertFalse(reporter.getReport());
        runner.drive(1);
        assertTrue(reporter.getReport());
    }

    @Test
    public void should_return_true_for_the_setUp_scenario_after_4_rides(){
        runner.drive(4);
        assertTrue(reporter.getReport());
    }

    @Test
    public void should_return_false_for_the_setUp_scenario_after_3_or_less_rides(){
        runner.drive(3);
        assertFalse(reporter.getReport());
    }

    @Test
    public void should_return_false_after_480_rider_for_the_scenario(){
        ArrayList<Integer> stops1 = new ArrayList<>();
        ArrayList<Integer> stops2 = new ArrayList<>();

        Collections.addAll(stops1, 2,1,2);
        Collections.addAll(stops2, 5,2,8);

        HashSet<Integer> gossips1 = new HashSet<>();
        gossips1.add(1);
        HashSet<Integer> gossips2 = new HashSet<>();
        gossips2.add(2);

        BusDriver<Integer, Integer> driver1 = new BusDriver<>(stops1, gossips1);
        BusDriver<Integer, Integer> driver2 = new BusDriver<>(stops2, gossips2);

        BusDriversRunner<Integer, Integer> runner = new BusDriversRunner<>();
        runner.addBusDriver(driver1);
        runner.addBusDriver(driver2);

        SimpleBusDriversRunnerReport<Integer, Integer> reporter = new SimpleBusDriversRunnerReport<>(runner);

        runner.drive(479);
        assertFalse(reporter.getReport());

    }

    @Test
    public void simple_report_output_content_is_configurable(){
        //todo
    }

}
