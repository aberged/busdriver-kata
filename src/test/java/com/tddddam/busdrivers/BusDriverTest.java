package com.tddddam.busdrivers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class BusDriverTest {

    @Test
    public void empty_bus_driver_can_be_constructed(){
        new BusDriver();
    }

    @Test
    public void bus_driver_should_have_list_constructor_for_setting_stops(){
        ArrayList<Integer> arr = new ArrayList<>();
        Collections.addAll(arr,  1,2,3);
        new BusDriver(arr);
    }

    @Test
    public void bus_driver_should_have_container_for_gossips(){
        BusDriver busDriver = new BusDriver();
        busDriver.getGossips();
    }

    @Test
    public void driver_should_have_constructor_with_stops_and_gossips_params(){
        ArrayList<Integer> stops = new ArrayList<>();
        Collections.addAll(stops, 1,2,3);
        HashSet<Integer> gossips = new HashSet<Integer>();
        new BusDriver<>(stops, gossips);
    }

    @Test
    public void constructors_should_ignore_nulls_and_leave_default_empty_data(){
        BusDriver driver = new BusDriver(null, null);
        assertEquals(0, driver.getGossips().size());
        assertEquals(null, driver.getCurrentStop());
    }

    @Test
    public void constructors_should_not_take_params_by_reference(){
        ArrayList<Integer> steps = new ArrayList<>();
        Collections.addAll(steps, 1,2,3);
        HashSet<Integer> gossips = new HashSet<Integer>();
        Collections.addAll(gossips, 1,2,3);
        BusDriver driver = new BusDriver(steps, null);
        assertFalse(gossips == driver.getGossips());
        assertEquals( 0, driver.getGossips().size());
    }

    @Test
    public void empty_bus_driver_should_return_empty_set_of_gosspis(){
        assertEquals(0, new BusDriver().getGossips().size());
    }

    @Test
    public void two_bus_drivers_should_be_able_to_exchange_gossips_only_if_on_the_same_stop_and_have_stops() {
        BusDriver b1 = new BusDriver();
        HashSet<Integer> gossips1 = new HashSet<>();
        gossips1.add(1);
        b1.addGossips(gossips1);
        BusDriver b2 = new BusDriver();
        b1.exchangeGossips(b2);
        assertNotEquals(b1.getGossips(), b2.getGossips());
    }

    @Test
    public void after_exchanging_gossips_drivers_should_have_same_gossip_sets() {
        HashSet<Integer> gossips1 = new HashSet<Integer>();
        Collections.addAll(gossips1, 1,2,3,4);
        ArrayList<Integer> steps1 = new ArrayList<>();
        Collections.addAll(steps1, 1,2,3);
        BusDriver driver1 = new BusDriver(steps1);
        driver1.addGossips(gossips1);
        BusDriver driver2 = new BusDriver(steps1);
        HashSet<Integer> gossips2 = new HashSet<Integer>();
        Collections.addAll(gossips2, 1,2);
        driver2.addGossips(gossips2);
        driver1.exchangeGossips(driver2);
        assertEquals(driver1.getGossips(), driver2.getGossips());
    }

    @Test
    public void bus_driver_should_be_able_to_drive(){
        new BusDriver().drive();
    }

    @Test
    public void bus_driver_should_provide_stop_position(){
        new BusDriver().getStopPosition();
    }

    @Test
    public void for_empty_driver_after_a_drive_stop_position_should_not_change(){
        BusDriver driver = new BusDriver();
        int start = driver.getStopPosition();
        driver.drive();
        int end = driver.getStopPosition();
        assertEquals(start, end);
    }

    @Test
    public void for_non_empty_driver_after_the_drive_stop_position_should_change(){
        ArrayList<Integer> stops = new ArrayList<>();
        Collections.addAll(stops,1,2,3);
        BusDriver driver = new BusDriver(stops);
        int start = driver.getStopPosition();
        driver.drive();
        int end = driver.getStopPosition();
        assertNotEquals(start, end);
    }

    @Test
    public void after_reaching_the_end_of_stops_should_return_to_start(){
        ArrayList<Integer> stops = new ArrayList<>();
        Collections.addAll(stops, 1,2,3);
        BusDriver driver = new BusDriver(stops);
        int start = driver.getStopPosition();
        for (int i=0;i<stops.size();i++)
            driver.drive();
        int end = driver.getStopPosition();
        assertEquals(start, end);
    }

    @Test
    public void driver_should_be_able_to_return_current_stop() {
        new BusDriver().getCurrentStop();
    }

    @Test
    public void two_drivers_should_not_be_able_to_exchange_gossips_if_one_has_no_stops() {
        ArrayList<Integer> stops1 = new ArrayList<>();
        Collections.addAll(stops1, 1,2,3,4);
        HashSet<Integer> gossips1 = new HashSet<Integer>();
        Collections.addAll(gossips1, 1,2,3);
        BusDriver driver1 = new BusDriver(stops1, gossips1);
        BusDriver driver2 = new BusDriver();
        driver1.exchangeGossips(driver2);
        assertNotEquals(driver1.getGossips(), driver2.getGossips());
    }

    @Test
    public void driver_should_be_able_to_exchange_gossips_only_when_on_the_same_step(){
        ArrayList<Integer> stops1 = new ArrayList<>();
        ArrayList<Integer> stops2 = new ArrayList<>();
        Collections.addAll(stops1, 1,2,3,4);
        Collections.addAll(stops2, 2,1,3,4);
        HashSet<Integer> gossips1 = new HashSet<>();
        Collections.addAll(gossips1, 1,2,3);
        BusDriver driver1 = new BusDriver(stops1, gossips1);
        BusDriver driver2 = new BusDriver(stops2);
        driver2.drive();
        driver1.exchangeGossips(driver2);
        assertEquals(driver1.getGossips(), driver2.getGossips());
    }
}
